package com.hefesoft.corpbanca.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.hefesoft.corpbanca.GlobalVars;

public class LoginAsync extends AsyncTask<Object, Object, Object>
{
	private ProgressDialog dialog;
	protected Context applicationContext;
	
	
	protected void onPreExecute() {
		//this.dialog = ProgressDialog.show(applicationContext, "Llamando", "Validando usuario", true);
	}
	
	@Override
	protected Object doInBackground(Object... params) {			
		return com.hefesoft.copbanca.login.Login.IngresarUsuario(GlobalVars.UsuarioEmail, GlobalVars.Password);	
	}
 
    private AsyncTaskCompleteListener<Boolean> listener;
 
    public LoginAsync(Context ctx, AsyncTaskCompleteListener<Boolean> listener)
    {
        this.applicationContext = ctx;        
        this.listener = listener;
    }
  
    @Override
    protected void onPostExecute(Object result)
    {
		super.onPostExecute((Boolean)result);
		listener.onTaskComplete((Boolean)result);
    }
}