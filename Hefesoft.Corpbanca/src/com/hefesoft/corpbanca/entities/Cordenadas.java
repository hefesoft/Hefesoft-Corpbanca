package com.hefesoft.corpbanca.entities;

public class Cordenadas {

	public Cordenadas() {
		// TODO Auto-generated constructor stub
	}

	
	public Cordenadas(String id, String centro_Conto, String cordenada_X,
			String cordenada_Y) {
		super();
		this.id = id;
		Centro_Conto = centro_Conto;
		Cordenada_X = cordenada_X;
		Cordenada_Y = cordenada_Y;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCentro_Conto() {
		return Centro_Conto;
	}
	public void setCentro_Conto(String centro_Conto) {
		Centro_Conto = centro_Conto;
	}
	public String getCordenada_X() {
		return Cordenada_X;
	}
	public void setCordenada_X(String cordenada_X) {
		Cordenada_X = cordenada_X;
	}
	public String getCordenada_Y() {
		return Cordenada_Y;
	}
	public void setCordenada_Y(String cordenada_Y) {
		Cordenada_Y = cordenada_Y;
	}


	private String id;
	private String Centro_Conto;
	private String Cordenada_X;
	private String Cordenada_Y;
	
}
