package com.hefesoft.corpbanca.graficas;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.hefesoft.corpbanca.R;
import com.hefesoft.corpbanca.asynctask.AsyncTaskCompleteListener;
import com.hefesoft.corpbanca.asynctask.Obtener_Datos_Grafica_Plantilla_Async;
import com.hefesoft.corpbanca.entities.Graficas_Plantilla;

public class Datos_Plantilla_Html5 extends Fragment
{		
		WebView webview;
		Activity activity;
		List<Graficas_Plantilla> L;
		View view;
		int anio;
		
		private List<Graficas_Plantilla> anio1 =new ArrayList<Graficas_Plantilla>();
		private List<Graficas_Plantilla> anio2 =new ArrayList<Graficas_Plantilla>();
		private List<Graficas_Plantilla> anio3 =new ArrayList<Graficas_Plantilla>();
		
	    @SuppressLint("SetJavaScriptEnabled")
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	    {   
	        view = inflater.inflate(R.layout.fragment_chart, container, false);
	        
	        activity = getActivity();
	        
	    	// Get a reference to the declared WebView holder
	        webview = (WebView) view.findViewById(R.id.webview);
			WebSettings webSettings = webview.getSettings();
	        
	    	webSettings.setJavaScriptEnabled(true);
			//webSettings.setBuiltInZoomControls(true);
			webview.requestFocusFromTouch();
			webview.setWebViewClient(new WebViewClient());
			webview.setWebChromeClient(new WebChromeClient());
			webview.addJavascriptInterface(this, "webConnector");
			webview.addJavascriptInterface(this, "toaster");
	        
			anio = 2012;
			
			Obtener_Datos_Grafica_Plantilla_Async obtener_Datos_Grafica = new Obtener_Datos_Grafica_Plantilla_Async(activity, datosCargados);
			obtener_Datos_Grafica.anio = anio;
			obtener_Datos_Grafica.mes = 2;
			obtener_Datos_Grafica.idCentroCosto = 10000;
			obtener_Datos_Grafica.execute(activity);  
	        
	        
	    
	        return view;
	    }
	    
	    AsyncTaskCompleteListener<List<Graficas_Plantilla>> datosCargados = new AsyncTaskCompleteListener<List<Graficas_Plantilla>>() {
			
			@Override
			public void onTaskComplete(List<Graficas_Plantilla> result) {
			
				L = (List<Graficas_Plantilla>)result;
				createData();
				webview.loadUrl("file:///android_asset/RGraph/examples/Planilla.html");				
			}
		};
		

		private void createData() {
	        	        
	        for(Graficas_Plantilla pivote : L)
	        {
	        	if(Integer.parseInt(pivote.Anio) == anio)
	        	{
	        		anio3.add(pivote);
	        	}
	        	else if(Integer.parseInt(pivote.Anio) == (anio -1))
	        	{
	        		anio2.add(pivote);
	        	}
	        	else if(Integer.parseInt(pivote.Anio) == (anio -2))
	        	{
	        		anio1.add(pivote);
	        	}	
	        }	
		}
	    
	    
	    public String load() {
	    	
	       JSONArray array = new JSONArray();
	    	
	       array.put(writeJSON(anio1));
	       array.put(writeJSON(anio2));
	       array.put(writeJSON(anio3));
	    	
	 	   webview.loadUrl("javascript:graficar(" + array  +")");	 	  
	 	   return "";
	 }
	 
	 public JSONObject writeJSON(List<Graficas_Plantilla> anio) {
	 	
	 	  JSONObject object = new JSONObject();
	 	  try {
	 	    
	 		  for(Graficas_Plantilla reporte :anio)
	 		  {
	 			 object.accumulate("data", Double.parseDouble(reporte.idEmpleado)/10);
	 		  }
	 		  
//	 		 for(Graficas_Plantilla reporte : anio1)
//			  {
//	 			object.accumulate("labels", reporte.Mes);
//			  }
//	 	    
//	 		for(Graficas_Plantilla reporte : anio1)
//			  {
//	 			object.accumulate("tooltips", reporte.Mes);
//			  }
//	 		
//	 		for(Graficas_Plantilla reporte : anio1)
//			  {
//				object.accumulate("colors", "green");
//			  }	 		
	 	    
	 	  } catch (JSONException e) {
	 	    e.printStackTrace();
	 	  }
	 	  System.out.println(object);
	 	  
	 	  return object;
	 	  
	 	} 

	 public void print(String message){	
	     Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();	     
	 }

	}
