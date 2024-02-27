package com.example.dominio;

import java.io.Serializable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CategoriaItem
{
	@EmbeddedId
	private CategoriaItemIdentificador categoriaItemIdentificador;
	
	// En lugar de @MapsId, se pudo haber colocado @Column(updatable=false, insertable=false)
	// para que hibernate escriba el valor de la columna tomando el valor del identificador
	@ManyToOne(cascade = CascadeType.ALL)
	@MapsId("item")
	@JoinColumn(name = "item")
	private Item item;
	
	// En lugar de @MapsId, se pudo haber colocado @Column(updatable=false, insertable=false)
	// para que hibernate escriba el valor de la columna tomando el valor del identificador
	@ManyToOne(cascade = CascadeType.ALL)
	@MapsId("categoria")
	@JoinColumn(name = "categoria")
	private Categoria categoria;
	
	@Embeddable
	@Data
	@AllArgsConstructor
	public static class CategoriaItemIdentificador implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String item;
		
		private String categoria;
		
		public CategoriaItemIdentificador() {}
	}

	public CategoriaItem(Item item,Categoria categoria)
	{
		this.categoriaItemIdentificador=new CategoriaItemIdentificador(item.getItem(),categoria.getCategoria());
		this.item = item;
		this.categoria = categoria;
		item.addCategoriaItem(this);
		categoria.addCategoriaItem(this);
	}

	@Override
	public String toString()
	{
		return "CategoriaItem [categoriaItemIdentificador=" + categoriaItemIdentificador + "]";
	}
	
	public void removeThis()
	{
		categoria.removeCategoriaItem(this);
		item.removeCategoriaItem(this);
		categoria=null;
		item=null;
	}
}
