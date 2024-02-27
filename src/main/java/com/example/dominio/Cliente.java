package com.example.dominio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

//@Entity
public class Cliente
{
	//@Id
	private String cliente;
	
	//@ManyToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "heladoFavorito")
	private Helado heladoFavorito;
	
	//@ManyToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "heladoMasDisgusta")
	private Helado heladoMasDisgusta;
}
