package com.example.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dominio.Alumno;


@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer>
{
}
