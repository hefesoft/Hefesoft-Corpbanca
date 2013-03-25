package com.hefesoft.corpbanca.asynctask;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class ObtenerCordenadasAsync extends AsyncTask<Object, Object, Object>
{
	private ProgressDialog dialog;
	protected Context applicationContext;
	
	
	protected void onPreExecute() {
		//this.dialog = ProgressDialog.show(applicationContext, "Llamando", "Validando usuario", true);
	}
	
	@Override
	protected Object doInBackground(Object... params) {
		List<com.hefesoft.corpbanca.entities.Cordenadas> lst = new ArrayList<com.hefesoft.corpbanca.entities.Cordenadas>();
		
		try {
			lst.addAll(com.hefesoft.corpbanca.fachada.Cordenadas.obtenerCordenadas());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lst;	
	}
 
    private AsyncTaskCompleteListener<List<com.hefesoft.corpbanca.entities.Cordenadas>> listener;
 
    public ObtenerCordenadasAsync(Context ctx, AsyncTaskCompleteListener<List<com.hefesoft.corpbanca.entities.Cordenadas>> listener)
    {
        this.applicationContext = ctx;        
        this.listener = listener;
    }
  
    @Override
    protected void onPostExecute(Object result)
    {
		super.onPostExecute((List<com.hefesoft.corpbanca.entities.Cordenadas>)result);
		listener.onTaskComplete((List<com.hefesoft.corpbanca.entities.Cordenadas>)result);
    }
}