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


public class Cordenadas {

	public Cordenadas() {
		// TODO Auto-generated constructor stub
	}
	
	 public static List<com.hefesoft.corpbanca.entities.Cordenadas> obtenerCordenadas() throws JSONException {
	    	
		 List<com.hefesoft.corpbanca.entities.Cordenadas> lst = new ArrayList<com.hefesoft.corpbanca.entities.Cordenadas>();
		 
			try {
				JSONArray json =
				      	JSONfunctions.cargarArray(GlobalVars.urlServices + "cordenadas");				
				
				  for(int i=0;i < json.length();i++){
	            	  
		              	JSONObject e = json.getJSONObject(i);
		              	
		              	com.hefesoft.corpbanca.entities.Cordenadas cordenada= new com.hefesoft.corpbanca.entities.Cordenadas();
		              	
		              	if(!e.isNull("id"))
		              	{
		              		cordenada.setId(e.getString("id"));
		              	}
		              	
		              	if(!e.isNull("CentroCosto"))
		              	{
		              		cordenada.setCentro_Conto(e.getString("CentroCosto"));
		              	}
		              	
		              	if(!e.isNull("Cordenada_x"))
		              	{
		              		cordenada.setCordenada_X(e.getString("Cordenada_x"));
		              	}
		              	
		              	if(!e.isNull("cordenada_y"))
		              	{
		              		cordenada.setCordenada_Y(e.getString("cordenada_y"));
		              	}
		              	
		              	lst.add(cordenada);
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
