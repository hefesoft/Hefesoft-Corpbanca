package com.hefesoft.corpbanca.asynctask;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class Obtener_Ingresos_Sindicato_Async extends AsyncTask<Object, Object, Object>
{
	private ProgressDialog dialog;
	protected Context applicationContext;
	
	public int mes;
	public int anio;
	
	protected void onPreExecute() {
		//this.dialog = ProgressDialog.show(applicationContext, "Llamando", "Cargando empleados sindicalizados", true);
	}
	
	@Override
	protected Object doInBackground(Object... params) {
		List<com.hefesoft.corpbanca.entities.Retiros_Ingresos> lst = new ArrayList<com.hefesoft.corpbanca.entities.Retiros_Ingresos>();
		
		try {
			lst.addAll(com.hefesoft.corpbanca.fachada.Retiros_Ingresos_Sindicato.obtenerIngresos(mes,anio));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lst;	
	}
 
    private AsyncTaskCompleteListener<List<com.hefesoft.corpbanca.entities.Retiros_Ingresos>> listener;
 
    public Obtener_Ingresos_Sindicato_Async(Context ctx, AsyncTaskCompleteListener<List<com.hefesoft.corpbanca.entities.Retiros_Ingresos>> listener)
    {
        this.applicationContext = ctx;        
        this.listener = listener;
    }
  
    @Override
    protected void onPostExecute(Object result)
    {
    	//this.dialog.cancel();
		super.onPostExecute((List<com.hefesoft.corpbanca.entities.Retiros_Ingresos>)result);
		listener.onTaskComplete((List<com.hefesoft.corpbanca.entities.Retiros_Ingresos>)result);
    }
}