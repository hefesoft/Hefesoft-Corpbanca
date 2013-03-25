package com.hefesoft.corpbanca.entities;

public class User {

		
	private String nombre_Completo = "";
	private String nombres = "";
	private String apellidos = "";
	private String email = "";
	private String guid = "";
	private String username = "";
	private String pais = "";
	private String upe = "";
	
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getUpe() {
		return upe;
	}

	public void setUpe(String upe) {
		this.upe = upe;
	}

	
	
	public String getNombre_Completo() {
		return nombre_Completo;
	}

	public void setNombre_Completo(String nombre_Completo) {
		this.nombre_Completo = nombre_Completo;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
