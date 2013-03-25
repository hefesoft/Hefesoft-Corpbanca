package com.hefesoft.corpbanca.entities;

public class Centros_Costo {

	public String getId() {
		return Id;
	}
	public Centros_Costo(String id, String centroCosto, String muestra,
			String idEmpresa, String orden) {
		super();
		Id = id;
		CentroCosto = centroCosto;
		Muestra = muestra;
		this.idEmpresa = idEmpresa;
		Orden = orden;
	}
	public Centros_Costo() {
		// TODO Auto-generated constructor stub
	}
	public void setId(String id) {
		Id = id;
	}
	public String getCentroCosto() {
		return CentroCosto;
	}
	public void setCentroCosto(String centroCosto) {
		CentroCosto = centroCosto;
	}
	public String getMuestra() {
		return Muestra;
	}
	public void setMuestra(String muestra) {
		Muestra = muestra;
	}
	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getOrden() {
		return Orden;
	}
	public void setOrden(String orden) {
		Orden = orden;
	}
	private String Id;
	private String CentroCosto;
	private String Muestra;
	private String idEmpresa;
	private String Orden;

}
