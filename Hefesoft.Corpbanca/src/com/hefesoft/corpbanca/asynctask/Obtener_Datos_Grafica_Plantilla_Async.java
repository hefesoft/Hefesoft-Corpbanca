package com.hefesoft.corpbanca.asynctask;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.content.Context;
import android.os.AsyncTask;

public class Obtener_Datos_Grafica_Plantilla_Async extends AsyncTask<Object, Object, Object>
{
	//private ProgressDialog dialog;
	protected Context applicationContext;
	
	public int mes;
	public int anio;
	public int idCentroCosto;
	
	protected void onPreExecute() {
		//this.dialog = ProgressDialog.show(applicationContext, "Llamando", "Cargando centros de costo", true);
	}
	
	@Override
	protected Object doInBackground(Object... params) {
		List<com.hefesoft.corpbanca.entities.Graficas_Plantilla> lst = new ArrayList<com.hefesoft.corpbanca.entities.Graficas_Plantilla>();
		
		try {
			lst.addAll(com.hefesoft.corpbanca.fachada.Plantilla_Grafica.obtenerCentrosCosto(idCentroCosto,mes,anio));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lst;	
	}
 
    private AsyncTaskCompleteListener<List<com.hefesoft.corpbanca.entities.Graficas_Plantilla>> listener;
 
    public Obtener_Datos_Grafica_Plantilla_Async(Context ctx, AsyncTaskCompleteListener<List<com.hefesoft.corpbanca.entities.Graficas_Plantilla>> listener)
    {
        this.applicationContext = ctx;        
        this.listener = listener;
    }
  
    @Override
    protected void onPostExecute(Object result)
    {
    	//this.dialog.cancel();
		super.onPostExecute((List<com.hefesoft.corpbanca.entities.Graficas_Plantilla>)result);
		listener.onTaskComplete((List<com.hefesoft.corpbanca.entities.Graficas_Plantilla>)result);
    }
}