package com.hefesoft.corpbanca.asynctask;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class Obtener_Centros_Costo_Async extends AsyncTask<Object, Object, Object>
{
	//private ProgressDialog dialog;
	protected Context applicationContext;
	
	public int mes;
	public int anio;
	
	protected void onPreExecute() {
		//this.dialog = ProgressDialog.show(applicationContext, "Llamando", "Cargando centros de costo", true);
	}
	
	@Override
	protected Object doInBackground(Object... params) {
		List<com.hefesoft.corpbanca.entities.Centros_Costo> lst = new ArrayList<com.hefesoft.corpbanca.entities.Centros_Costo>();
		
		try {
			lst.addAll(com.hefesoft.corpbanca.fachada.Centros_Costo.obtenerCentrosCosto(mes,anio));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lst;	
	}
 
    private AsyncTaskCompleteListener<List<com.hefesoft.corpbanca.entities.Centros_Costo>> listener;
 
    public Obtener_Centros_Costo_Async(Context ctx, AsyncTaskCompleteListener<List<com.hefesoft.corpbanca.entities.Centros_Costo>> listener)
    {
        this.applicationContext = ctx;        
        this.listener = listener;
    }
  
    @Override
    protected void onPostExecute(Object result)
    {
    	//this.dialog.cancel();
		super.onPostExecute((List<com.hefesoft.corpbanca.entities.Centros_Costo>)result);
		listener.onTaskComplete((List<com.hefesoft.corpbanca.entities.Centros_Costo>)result);
    }
}