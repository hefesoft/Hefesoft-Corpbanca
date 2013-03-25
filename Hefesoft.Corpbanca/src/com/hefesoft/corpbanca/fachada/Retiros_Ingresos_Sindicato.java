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


public class Retiros_Ingresos_Sindicato {

	public Retiros_Ingresos_Sindicato() {
		// TODO Auto-generated constructor stub
	}
	
	 public static List<com.hefesoft.corpbanca.entities.Retiros_Ingresos> obtenerIngresos(int mes,int anio) throws JSONException {
	    	
		 List<com.hefesoft.corpbanca.entities.Retiros_Ingresos> lst = new ArrayList<com.hefesoft.corpbanca.entities.Retiros_Ingresos>();
		 
			try {
				JSONArray json =
				      	JSONfunctions.cargarArray(GlobalVars.urlServices + "ingresossindicatos?anio=" + anio +"&&mes=" + mes);				
				
				  for(int i=0;i < json.length();i++){
	            	  
		              	JSONObject e = json.getJSONObject(i);
		              	
		              	com.hefesoft.corpbanca.entities.Retiros_Ingresos Ingreso_Retiro = new com.hefesoft.corpbanca.entities.Retiros_Ingresos();
		              	
		              	if(!e.isNull("IdEmpleado"))
		              	{
		              		Ingreso_Retiro.setIdEmpleado(e.getString("IdEmpleado"));
		              	}
		              	
		              	if(!e.isNull("Nombre"))
		              	{
		              		Ingreso_Retiro.setNombre(e.getString("Nombre"));
		              	}
		              	
		              	if(!e.isNull("Cedula"))
		              	{
		              		Ingreso_Retiro.setCedula(e.getString("Cedula"));
		              	}
		              	if(!e.isNull("IDcENTROcOSTO"))
		              	{
		              		Ingreso_Retiro.setIDcENTROcOSTO(e.getString("IDcENTROcOSTO"));
		              	}
		              	if(!e.isNull("CentroCosto"))
		              	{
		              		Ingreso_Retiro.setCentroCosto(e.getString("CentroCosto"));
		              	}
		              	if(!e.isNull("NombreCargo"))
		              	{
		              		Ingreso_Retiro.setNombreCargo(e.getString("NombreCargo"));
		              	}
		              	if(!e.isNull("NombreSindicato"))
		              	{
		              		Ingreso_Retiro.setNombreSindicato(e.getString("NombreSindicato"));
		              	}
		              	if(!e.isNull("IdSindicato"))
		              	{
		              		Ingreso_Retiro.setIdSindicato(e.getString("IdSindicato"));
		              	}		              	
		              	if(!e.isNull("VicePresidencia"))
		              	{
		              		Ingreso_Retiro.setVicePresidencia(e.getString("VicePresidencia"));
		              	}
		              	
		              	lst.add(Ingreso_Retiro);
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
