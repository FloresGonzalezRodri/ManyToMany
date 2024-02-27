package com.example.dominio;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Categoria
{
	@Id
	@NonNull
	private String categoria;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
	private List<CategoriaItem> categoriaItems=new ArrayList<>();

	@Override
	public String toString()
	{
		return "Categoria [categoria=" + categoria + "]";
	}
	
	public void addCategoriaItem(CategoriaItem categoriaItem)
	{
		categoriaItems.add(categoriaItem);
		categoriaItem.setCategoria(this);
	}
	
	public void removeCategoriaItem(CategoriaItem categoriaItem)
	{
		if(categoriaItems.contains(categoriaItem))
		{
			categoriaItem.setCategoria(null);
			categoriaItems.remove(categoriaItem);
		}
	}
}
