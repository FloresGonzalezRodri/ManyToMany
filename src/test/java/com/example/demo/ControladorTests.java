package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.dominio.Alumno;
import com.example.dominio.Categoria;
import com.example.dominio.CategoriaItem;
import com.example.dominio.Curso;
import com.example.dominio.Item;
import com.example.repositorio.AlumnoRepository;
import com.example.repositorio.CategoriaItemRepository;
import com.example.repositorio.CategoriaRepository;
import com.example.repositorio.CursoRepository;
import com.example.repositorio.ItemRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@DataJpaTest
public class ControladorTests
{
	@Autowired
	public AlumnoRepository alumnoRepository;
	
	@Autowired
	public CursoRepository cursoRepository;
	
	@Autowired 
	public EntityManager entityManager;
	
	@Autowired
	public EntityManagerFactory entityManagerFactory;
	
	@Autowired
	public CategoriaItemRepository categoriaItemRepository;
	
	@Autowired
	public ItemRepository itemRepository;
	
	@Autowired
	public CategoriaRepository categoriaRepository;

	/**
	 * Prueba la persistencia en cascada desde Alumno a Curso
	 */
	@Test
	public void onCascadePersistManyToManyRelationship() {
            Alumno rodrigo=new Alumno(2002,"Rodrigo","Flores","Gonzalez");
            //Alumno emilio=new Alumno(2000,"Emilio","Flores","Gonzalez");
            //Curso calculo=new Curso("calculo");
            Curso etica=new Curso("Etica");
            //emilio.addCurso(calculo);
            //rodrigo.addCurso(calculo);
            rodrigo.addCurso(etica);
            alumnoRepository.save(rodrigo);
            //alumnoRepository.save(emilio);
            Curso encontrado=cursoRepository.findById(etica.getNombre()).get();
            System.out.println(encontrado.toString());
	}
	
	/**
	 * Prueba la eliminacion en cascada desde Alumno a Curso
	 */
	@Test
	public void onCascadeDeleteManyToManyRelationship() {
		
            onCascadePersistManyToManyRelationship();
            Alumno rodrigo=alumnoRepository.findById(2002).get();
            alumnoRepository.delete(rodrigo);
            System.out.println(cursoRepository.findById("Etica").isEmpty());

    }
	
	/**
	 * Prueba la operacion merge() sobre un Alumno, despues de cerrar el contexto de persitencia
	 */
	@Test
	public void detachOnCloseThenMerge()
	{
		Alumno rodrigo=new Alumno(2002,"Rodrigo","Flores","Gonzalez");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		//Abrir un EntityManager y comenzar una transaccion para guardar el objeto
		entityManager.getTransaction().begin();		
		entityManager.persist(rodrigo);
		entityManager.getTransaction().commit();
		//Cerrar el EntityManager para separar, detach, el objeto en memoria del programa del administrador de persistencia
		entityManager.close();
		System.out.println("Objeto separado: " + rodrigo.toString());
		//Modificar el objeto en memoria
		rodrigo.setNombre("Emilio");
		//Abrir el EntityManager y comenzar una transaccion para unir el objeto en memoria con la base de datos
		entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Alumno devuelto=entityManager.merge(rodrigo);		
		entityManager.getTransaction().commit();
		devuelto.setMaterno("Velazquez");
		System.out.println("Objeto actualizado con merge: " + devuelto.toString());
		entityManager.close();
		System.out.println(rodrigo==devuelto);
		entityManagerFactory.close();
	}
	
	
	/**
	 * Prueba el la operacion merge() sobre un Alumno, despues de aplicarle detach()
	 */
	@Test
	public void detachThenMerge()
	{
		Alumno rodrigo=new Alumno(2002,"Rodrigo","Flores","Gonzalez");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();	
		// Guardar objeto en el EntityManager
		entityManager.persist(rodrigo);
		entityManager.getTransaction().commit();
		
		// Guardar objeto en la base de datos
		entityManager.getTransaction().begin();	
		//Desincronizar el objeto con la fila
		entityManager.detach(rodrigo);
		//Modificar el objeto
		rodrigo.setNombre("Emilio");
		//Imprimir la fila, no detecta cambios
		System.out.println("Objeto desactualizado: " + entityManager.find(Alumno.class, 2002).toString());
		//Sincronziar cambios
		entityManager.merge(rodrigo);
		System.out.println("Objeto actualizado: " + entityManager.find(Alumno.class, 2002).toString());	
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
	
	/**
	 * Prueba la operacion refresh() sobre un Alumno
	 */
	@Test
	public void reflesh()
	{
		Alumno rodrigo=new Alumno(2002,"Rodrigo","Flores","Gonzalez");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();	
		entityManager.persist(rodrigo);
		entityManager.createQuery("UPDATE Alumno SET nombre='Emilio' WHERE alumno=2002").executeUpdate();
		//Imprimir objeto desincronizado
		System.out.println("Objeto no sincronizado con base de datos: " + rodrigo);
		entityManager.refresh(rodrigo);
		//Imprimir objeto sincronizado
		System.out.println("Objeto no sincronizado con base de datos: " + rodrigo);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
	
	
	/**
	 * Prueba la peristencia en cascada desde Curso a Alumno
	 */
	@Test
	public void persistCursoToAlumno()
	{
		Curso etica=new Curso("Etica");
		Alumno rodrigo=new Alumno(2002,"Rodrigo","Flores","Gonzalez");
		etica.getAlumnos().add(rodrigo);
		cursoRepository.save(etica);
		System.out.println(alumnoRepository.findById(2002).get());
	}
	
	
	/**
	 * Prueba la persistencia en cascada desde CategoriaItem hacia Categoria y Item
	 */
	@Test
	public void onCascadePersist()
	{
		Categoria categoria1=new Categoria("Enlatados");
		Categoria categoria2=new Categoria("Condimentos");
		
		Item item1=new Item("chiles en vinagre");
		Item item2=new Item("Frijoles refritos");
		
		CategoriaItem categoria1Item1=new CategoriaItem(item1,categoria1);
		CategoriaItem categoria2Item1=new CategoriaItem(item1,categoria2);
		CategoriaItem categoria1Item2=new CategoriaItem(item2,categoria1);
		
		categoriaItemRepository.save(categoria1Item1);
		categoriaItemRepository.save(categoria1Item2);
		categoriaItemRepository.save(categoria2Item1);
		
		System.out.println(itemRepository.findAll());
		System.out.println(categoriaRepository.findAll());
	}
}
