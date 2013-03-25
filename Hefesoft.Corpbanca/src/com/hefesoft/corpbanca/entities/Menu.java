package com.hefesoft.corpbanca.entities;

public class Menu {

	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getImagen() {
		return Imagen;
	}
	public void setImagen(String imagen) {
		Imagen = imagen;
	}
	public String getOrden() {
		return Orden;
	}
	public Menu(String nombre, String imagen, String orden) {
		super();
		Nombre = nombre;
		Imagen = imagen;
		Orden = orden;
	}
	public Menu(String nombre, String imagen, String orden, int id) {
		super();
		Nombre = nombre;
		Imagen = imagen;
		Orden = orden;
		this.id = id;
	}
	public void setOrden(String orden) {
		Orden = orden;
	}
	public String Nombre;
	public String Imagen;
	public String Orden;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int id;
	
}
