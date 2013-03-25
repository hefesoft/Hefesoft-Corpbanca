package com.hefesoft.corpbanca.entities;

public class Retiros_Ingresos {

	public String getIdEmpleado() {
		return IdEmpleado;
	}
	public void setIdEmpleado(String idEmpleado) {
		IdEmpleado = idEmpleado;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getCedula() {
		return Cedula;
	}
	public Retiros_Ingresos(String idEmpleado, String nombre, String cedula,
			String iDcENTROcOSTO, String centroCosto, String nombreCargo,
			String nombreSindicato, String idSindicato, String vicePresidencia) {
		super();
		IdEmpleado = idEmpleado;
		Nombre = nombre;
		Cedula = cedula;
		IDcENTROcOSTO = iDcENTROcOSTO;
		CentroCosto = centroCosto;
		NombreCargo = nombreCargo;
		NombreSindicato = nombreSindicato;
		this.idSindicato = idSindicato;
		VicePresidencia = vicePresidencia;
	}
	
	public Retiros_Ingresos() {
		// TODO Auto-generated constructor stub
	}
	public void setCedula(String cedula) {
		Cedula = cedula;
	}
	public String getIDcENTROcOSTO() {
		return IDcENTROcOSTO;
	}
	public void setIDcENTROcOSTO(String iDcENTROcOSTO) {
		IDcENTROcOSTO = iDcENTROcOSTO;
	}
	public String getCentroCosto() {
		return CentroCosto;
	}
	public void setCentroCosto(String centroCosto) {
		CentroCosto = centroCosto;
	}
	public String getNombreCargo() {
		return NombreCargo;
	}
	public void setNombreCargo(String nombreCargo) {
		NombreCargo = nombreCargo;
	}
	public String getNombreSindicato() {
		return NombreSindicato;
	}
	public void setNombreSindicato(String nombreSindicato) {
		NombreSindicato = nombreSindicato;
	}
	public String getIdSindicato() {
		return idSindicato;
	}
	public void setIdSindicato(String idSindicato) {
		this.idSindicato = idSindicato;
	}
	public String getVicePresidencia() {
		return VicePresidencia;
	}
	public void setVicePresidencia(String vicePresidencia) {
		VicePresidencia = vicePresidencia;
	}
	private String IdEmpleado;
	private String Nombre;
	private String Cedula;
	private String IDcENTROcOSTO;
	private String CentroCosto;
	private String NombreCargo;
	private String NombreSindicato;
	private String idSindicato;
	private String VicePresidencia;

}
