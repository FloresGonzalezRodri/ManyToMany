package com.example.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dominio.Curso;

public interface CursoRepository extends JpaRepository<Curso,String>
{

}
