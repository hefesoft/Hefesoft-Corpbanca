package com.hefesoft.copbanca.login;

import com.hefesoft.corpbanca.GlobalVars;
import com.hefesoft.corpbanca.helpers.JSONfunctions;

public class Login {

	public Login() {
		// TODO Auto-generated constructor stub
	}
	
	 public static Object IngresarUsuario(String Usuario, String Password) {
	    	
			String json =
			      	JSONfunctions.getJSONfromURL(GlobalVars.urlServices + "values/0",Usuario,Password);

			if(json.isEmpty())
			{
				return false;
			}
			else
			{
				GlobalVars.Token = json;
				return true;
			}

	 } 	 


}
