package com.hefesoft.corpbanca.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.hefesoft.corpbanca.GlobalVars;

public class JSONfunctions {

		
	public static String getJSONfromURL(String url, String UserName, String Password){
		InputStream is = null;
		String result = "";
		try{
	            HttpClient httpclient = new DefaultHttpClient();
	            HttpGet request = new HttpGet();
	            // jose.douglas y Iguazo26! Membership
	            String login = Authorization.getB64Auth(UserName, Password);
	            request.setHeader("Authorization",login);
	            request.setURI(new URI(url));
	            HttpResponse response = httpclient.execute(request);
	            HttpEntity entity = response.getEntity();
	            is = entity.getContent();	            
	            
	    }catch(Exception e){
	            Log.e("log_tag", "Error in http connection "+e.toString());
	    }
	    
	  //convert response to string
	    try{
	            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                    sb.append(line + "\n");
	            }
	            is.close();
	            result=sb.toString();	
	            
	    }catch(Exception e){
	            Log.e("log_tag", "Error converting result "+e.toString());
	    }	
	    
	    return result;
	    
	}

	public static JSONArray cargarArray(String Url)
			throws URISyntaxException, IOException
			{	
		
		String result1 = "";
		JSONArray jArray = null;
		InputStream is1;
		HttpClient httpclient1 = new DefaultHttpClient();
		HttpGet request1 = new HttpGet();
		String login = Authorization.getJWT(GlobalVars.Token);
		request1.setHeader("Authorization",login);
		request1.setURI(new URI(Url));
		HttpResponse response1 = httpclient1.execute(request1);
		HttpEntity entity1 = response1.getEntity();
		is1 = entity1.getContent();
		
		  //convert response to string
	    try{
	            BufferedReader reader = new BufferedReader(new InputStreamReader(is1,"iso-8859-1"),8);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                    sb.append(line + "\n");
	            }
	            is1.close();
	            result1=sb.toString();
	    }catch(Exception e){
	            Log.e("log_tag", "Error converting result "+e.toString());
	    }
	    
	    try{
	    	
	    	return new JSONArray(result1);
                        
	    }catch(JSONException e){
	            Log.e("log_tag", "Error parsing data "+e.toString());
	    }
    
	    return jArray;
		
	}

	
	public static JSONObject cargarObjeto(String Url)
			throws URISyntaxException, IOException
			{	
		
		String result1 = "";
		JSONObject jArray = null;
		InputStream is1;
		HttpClient httpclient1 = new DefaultHttpClient();
		HttpGet request1 = new HttpGet();
		String login = Authorization.getJWT(GlobalVars.Token);
		request1.setHeader("Authorization",login);
		request1.setURI(new URI(Url));
		HttpResponse response1 = httpclient1.execute(request1);
		HttpEntity entity1 = response1.getEntity();
		is1 = entity1.getContent();
		
		  //convert response to string
	    try{
	            BufferedReader reader = new BufferedReader(new InputStreamReader(is1,"iso-8859-1"),8);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                    sb.append(line + "\n");
	            }
	            is1.close();
	            result1=sb.toString();
	    }catch(Exception e){
	            Log.e("log_tag", "Error converting result "+e.toString());
	    }
	    
	    try{
	    	
	    	return new JSONObject(result1);
                        
	    }catch(JSONException e){
	            Log.e("log_tag", "Error parsing data "+e.toString());
	    }
    
	    return jArray;
		
	}

}
