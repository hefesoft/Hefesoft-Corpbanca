package com.hefesoft.corpbanca.entities;

public class Planilla_Centro_Costo {

	public String getCedula() {
		return Cedula;
	}
	public void setCedula(String cedula) {
		Cedula = cedula;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getFechaIngreso() {
		return FechaIngreso;
	}
	public Planilla_Centro_Costo(String cedula, String nombre,
			String fechaIngreso, String idCargo, String nombreCargo,
			String idProfesion, String nombreProfesion) {
		super();
		Cedula = cedula;
		Nombre = nombre;
		FechaIngreso = fechaIngreso;
		IdCargo = idCargo;
		NombreCargo = nombreCargo;
		this.idProfesion = idProfesion;
		NombreProfesion = nombreProfesion;
	}
	public Planilla_Centro_Costo() {
		// TODO Auto-generated constructor stub
	}
	public void setFechaIngreso(String fechaIngreso) {
		FechaIngreso = fechaIngreso;
	}
	public String getIdCargo() {
		return IdCargo;
	}
	public void setIdCargo(String idCargo) {
		IdCargo = idCargo;
	}
	public String getNombreCargo() {
		return NombreCargo;
	}
	public void setNombreCargo(String nombreCargo) {
		NombreCargo = nombreCargo;
	}
	public String getIdProfesion() {
		return idProfesion;
	}
	public void setIdProfesion(String idProfesion) {
		this.idProfesion = idProfesion;
	}
	public String getNombreProfesion() {
		return NombreProfesion;
	}
	public void setNombreProfesion(String nombreProfesion) {
		NombreProfesion = nombreProfesion;
	}
	public String Cedula;
	public String Nombre;
	public String FechaIngreso;
	public String IdCargo;
	public String NombreCargo;
	public String idProfesion;
	public String NombreProfesion;
}
