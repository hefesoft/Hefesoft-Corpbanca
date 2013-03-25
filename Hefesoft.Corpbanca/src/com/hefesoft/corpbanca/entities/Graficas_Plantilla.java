package com.hefesoft.corpbanca.entities;

public class Graficas_Plantilla {

	public int getIdMes() {
		return IdMes;
	}
	public void setIdMes(int idMes) {
		IdMes = idMes;
	}
	public String getIdEmpleado() {
		return idEmpleado;
	}
	public Graficas_Plantilla(int idMes, String idEmpleado, String mes,
			String anio) {
		super();
		IdMes = idMes;
		this.idEmpleado = idEmpleado;
		Mes = mes;
		Anio = anio;
	}
	public Graficas_Plantilla() {
		// TODO Auto-generated constructor stub
	}
	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getMes() {
		return Mes;
	}
	public void setMes(String mes) {
		Mes = mes;
	}
	public String getAnio() {
		return Anio;
	}
	public void setAnio(String anio) {
		Anio = anio;
	}
	public int IdMes;
	public String idEmpleado;
	public String Mes;
	public String Anio;
	
}
