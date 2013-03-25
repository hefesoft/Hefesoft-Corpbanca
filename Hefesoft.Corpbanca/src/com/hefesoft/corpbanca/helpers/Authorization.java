package com.hefesoft.corpbanca.helpers;

import android.util.Base64;

public class Authorization {

	public Authorization() {
		// TODO Auto-generated constructor stub
	}
	
	public static String getB64Auth (String login, String pass) {
		   String source=login+":"+pass;
		   String ret="Basic "+Base64.encodeToString(source.getBytes(),Base64.URL_SAFE|Base64.NO_WRAP);
		   return ret;
		 }
	
	public static String getJWT (String Token) {
		   String source= Token;
		   String ret="JWT "+Base64.encodeToString(source.getBytes(),Base64.URL_SAFE|Base64.NO_WRAP);
		   return ret;
		 }

}
