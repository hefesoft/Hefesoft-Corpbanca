package com.hefesoft.corpbanca.fachada;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hefesoft.corpbanca.GlobalVars;
import com.hefesoft.corpbanca.helpers.JSONfunctions;


public class Plantilla_Grafica {

	public Plantilla_Grafica() {
		// TODO Auto-generated constructor stub
	}
	
	 public static List<com.hefesoft.corpbanca.entities.Graficas_Plantilla> obtenerCentrosCosto(int idCentroCosto,int mes,int anio) throws JSONException {
	    	
		 List<com.hefesoft.corpbanca.entities.Graficas_Plantilla> lst = new ArrayList<com.hefesoft.corpbanca.entities.Graficas_Plantilla>();
		 
			try {
				JSONArray json =
				      	JSONfunctions.cargarArray(GlobalVars.urlServices + "graficaplantilla?idCentroCosto=" + idCentroCosto  + "&&anio=" + anio +"&&mes=" + mes);				
				
				  for(int i=0;i < json.length();i++){
	            	  
		              	JSONObject e = json.getJSONObject(i);
		              	
		              	com.hefesoft.corpbanca.entities.Graficas_Plantilla Graficas_Plantilla = new com.hefesoft.corpbanca.entities.Graficas_Plantilla();
		              	

		              	
		              	if(!e.isNull("idEmpleado"))
		              	{
		              		Graficas_Plantilla.setIdEmpleado(e.getString("idEmpleado"));
		              	}
		              	
		              	if(!e.isNull("IdMes"))
		              	{
		              		Graficas_Plantilla.setIdMes(e.getInt("IdMes"));
		              	}
		              	
		              	if(!e.isNull("Anio"))
		              	{
		              		Graficas_Plantilla.setAnio(e.getString("Anio"));
		              	}
		              	
		              	if(!e.isNull("Mes"))
		              	{
		              		Graficas_Plantilla.setMes(e.getString("Mes"));
		              	}
		              	      	
		              	lst.add(Graficas_Plantilla);
				  }
				
				  return lst;
				
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return lst;

	 }
	 
	 public static List<com.hefesoft.corpbanca.entities.Planilla_Centro_Costo> obtenerPlanillaCentrosCosto(int idCentroCosto,int mes,int anio) throws JSONException {
	    	
		 List<com.hefesoft.corpbanca.entities.Planilla_Centro_Costo> lst = new ArrayList<com.hefesoft.corpbanca.entities.Planilla_Centro_Costo>();
		 
			try {
				JSONArray json =
				      	JSONfunctions.cargarArray(GlobalVars.urlServices + "centroscosto?idCentroCosto=" + idCentroCosto +"&&anio=" + anio +"&&mes=" + mes);				
				
				  for(int i=0;i < json.length();i++){
	            	  
		              	JSONObject e = json.getJSONObject(i);
		              	
		              	com.hefesoft.corpbanca.entities.Planilla_Centro_Costo Planilla_Centro_Costo = new com.hefesoft.corpbanca.entities.Planilla_Centro_Costo();
		              	
		              	if(!e.isNull("Cedula"))
		              	{
		              		Planilla_Centro_Costo.setCedula(e.getString("Cedula"));
		              	}
		              	
		              	if(!e.isNull("FechaIngreso"))
		              	{
		              		Planilla_Centro_Costo.setFechaIngreso(e.getString("FechaIngreso"));
		              	}
		              	
		              	if(!e.isNull("IdCargo"))
		              	{
		              		Planilla_Centro_Costo.setIdCargo(e.getString("IdCargo"));
		              	}
		              	
		              	if(!e.isNull("IdProfesion"))
		              	{
		              		Planilla_Centro_Costo.setIdProfesion(e.getString("IdProfesion"));
		              	}
		              	
		              	if(!e.isNull("Nombre"))
		              	{
		              		Planilla_Centro_Costo.setNombre(e.getString("Nombre"));
		              	}
		              	
		              	if(!e.isNull("NombreCargo"))
		              	{
		              		Planilla_Centro_Costo.setNombreCargo(e.getString("NombreCargo"));
		              	}
		              	
		              	if(!e.isNull("NombreProfesion"))
		              	{
		              		Planilla_Centro_Costo.setNombreProfesion(e.getString("NombreProfesion"));
		              	}
		              	
		                    	
		              	lst.add(Planilla_Centro_Costo);
				  }
				
				  return lst;
				
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return lst;

	 }
	 
	 


}
