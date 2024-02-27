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
public class Item
{
	@Id
	@NonNull
	private String item;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "item")
	private List<CategoriaItem> categoriasItem=new ArrayList<>();

	@Override
	public String toString()
	{
		return "Item [item=" + item + "]";
	}
	
	public void addCategoriaItem(CategoriaItem categoriaItem)
	{
		categoriasItem.add(categoriaItem);
		categoriaItem.setItem(this);
	}
	
	public void removeCategoriaItem(CategoriaItem categoriaItem)
	{
		if(categoriasItem.contains(categoriaItem))
		{
			categoriaItem.setItem(null);
			categoriasItem.remove(categoriaItem);
		}
	}
}
