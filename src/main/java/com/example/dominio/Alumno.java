package com.example.dominio;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


/**
 *
 * @author Rodrigo
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Alumno
{
    @Id
    @NonNull
    Integer alumno;
    
    @NonNull
    String nombre,materno,paterno;
    
    @ManyToMany(cascade = CascadeType.ALL)
    List<Curso> cursos=new ArrayList<>();
    
    /**
     * Agrega un Curso, manteniendo la consistencia de la bidireccionalidad
     * @param curso El curso a añadir
     * 
     */
    public void addCurso(Curso curso)
    {
        cursos.add(curso);
        curso.getAlumnos().add(this);
    }
    
    /**
     * Elimina un Curso, manteniendo la consistencia de la bidireccionalidad
     * @param curso El curso a eliminar
     * 
     */
    public void removeCurso(Curso curso)
    {
    	if(cursos.contains(curso))
    	{
    		curso.getAlumnos().remove(this);
        	cursos.remove(curso);
    	}
    }

	@Override
	public String toString()
	{
		return "Alumno [alumno=" + alumno + ", nombre=" + nombre + ", materno=" + materno + ", paterno=" + paterno
				+ "]";
	}
}
