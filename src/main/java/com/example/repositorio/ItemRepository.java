package com.example.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dominio.Item;

public interface ItemRepository extends JpaRepository<Item,String>
{ 

}
