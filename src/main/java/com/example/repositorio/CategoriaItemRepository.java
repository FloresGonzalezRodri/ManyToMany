package com.example.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dominio.CategoriaItem;
import com.example.dominio.CategoriaItem.CategoriaItemIdentificador;;

public interface CategoriaItemRepository extends JpaRepository<CategoriaItem,CategoriaItemIdentificador>
{

}
