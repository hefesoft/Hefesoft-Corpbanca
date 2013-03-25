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


public class Centros_Costo {

	public Centros_Costo() {
		// TODO Auto-generated constructor stub
	}
	
	 public static List<com.hefesoft.corpbanca.entities.Centros_Costo> obtenerCentrosCosto(int mes,int anio) throws JSONException {
	    	
		 List<com.hefesoft.corpbanca.entities.Centros_Costo> lst = new ArrayList<com.hefesoft.corpbanca.entities.Centros_Costo>();
		 
			try {
				JSONArray json =
				      	JSONfunctions.cargarArray(GlobalVars.urlServices + "centroscosto?anio=" + anio +"&&mes=" + mes);				
				
				  for(int i=0;i < json.length();i++){
	            	  
		              	JSONObject e = json.getJSONObject(i);
		              	
		              	com.hefesoft.corpbanca.entities.Centros_Costo Centros_Costo = new com.hefesoft.corpbanca.entities.Centros_Costo();
		              	
		              	if(!e.isNull("Id"))
		              	{
		              		Centros_Costo.setId(e.getString("Id"));
		              	}
		              	
		              	if(!e.isNull("CentroCosto"))
		              	{
		              		Centros_Costo.setCentroCosto(e.getString("CentroCosto"));
		              	}
		              	
		              	if(!e.isNull("IdEmpresa"))
		              	{
		              		Centros_Costo.setIdEmpresa(e.getString("IdEmpresa"));
		              	}
		              	
		              	if(!e.isNull("Muestra"))
		              	{
		              		Centros_Costo.setMuestra(e.getString("Muestra"));
		              	}
		              	
		              	if(!e.isNull("Orden"))
		              	{
		              		Centros_Costo.setOrden(e.getString("Orden"));
		              	}
		              	
		              	lst.add(Centros_Costo);
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
