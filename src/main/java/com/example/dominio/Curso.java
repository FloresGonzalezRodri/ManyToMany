package com.example.dominio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Rodrigo
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Curso
{
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CursoAlumno", joinColumns = @JoinColumn(name = "alumno"), inverseJoinColumns = @JoinColumn(name = "curso"))
    List<Alumno> alumnos=new ArrayList<>();
    
    @Id
    @NonNull
    String nombre;
    /*
    byte grupo;    
    int cupo;*/
    
    /**
     * Agrega un Alumno, manteniendo la consistencia de la bidireccionalidad
     * @param alumno El alumno a añadir
     * 
     */
    public void addAlumno(Alumno alumno)
    {
    	alumnos.add(alumno);
    	alumno.getCursos().add(this);
    }
    
    /**
     * Eliminar un Alumno, manteniendo la consistencia de la bidireccionalidad
     * @param alumno El alumno a eliminar
     * 
     */
    public void removeAlumno(Alumno alumno)
    {
    	if(alumnos.contains(alumno))
    	{
    		alumno.getCursos().remove(this);
        	alumnos.remove(alumno);
    	}
    }
}
