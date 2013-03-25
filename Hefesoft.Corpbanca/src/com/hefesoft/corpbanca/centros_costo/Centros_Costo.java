package com.hefesoft.corpbanca.centros_costo;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.Toast;

import com.hefesoft.corpbanca.GlobalVars;
import com.hefesoft.corpbanca.R;
import com.hefesoft.corpbanca.adapters.AdaptadorSindicatos_Ingresos;
import com.hefesoft.corpbanca.adapters.Adaptador_Plantilla_Centros_Costo;
import com.hefesoft.corpbanca.asynctask.AsyncTaskCompleteListener;
import com.hefesoft.corpbanca.asynctask.Obtener_Centros_Costo_Planilla_Async;
import com.hefesoft.corpbanca.asynctask.Obtener_Ingresos_Sindicato_Async;
import com.hefesoft.corpbanca.entities.Planilla_Centro_Costo;
import com.hefesoft.corpbanca.sindicalizados.Ingresos;
import com.hefesoft.corpbanca.slider.MenuActivity;
import com.hefesoft.corpbanca.slider.MenuCentrosCostoActivity;
import com.hefesoft.corpbanca.util.hasConnection;
import com.korovyansk.android.slideout.SlideoutActivity;

public class Centros_Costo extends Activity {

	ActionBar actionBar;
	Calendar dateAndTime=Calendar.getInstance();
	Adaptador_Plantilla_Centros_Costo adaptador_Plantilla;
	GridView Planilla_Centro_Costo;
	View mLoginStatusView;
	View mLoginFormView;
	View layout_Centros_Costo_Splash;
	View layout_Centros_Costo_Splash_Inicial;
	int idCentroCostoGlobal;
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)


	@Override
	protected void onRestart() {
		
			try {
				if(GlobalVars.objetoEntreVistas != null)
				{
					int id = Integer.parseInt(((com.hefesoft.corpbanca.entities.Centros_Costo)GlobalVars.objetoEntreVistas).getId());
					cargarCentroCosto(id);
					super.onRestart();
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			super.onResume();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_centros__costo);		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		mLoginStatusView = findViewById(R.id.layout_Cargando_Centros_Costo);
		mLoginFormView = findViewById(R.id.Contenedor_Centros_Costo);
		layout_Centros_Costo_Splash = findViewById(R.id.Contenedor_Centros_Costo);
		layout_Centros_Costo_Splash_Inicial = findViewById(R.id.layout_Centros_Costo_Splash_Inicial);
		
		actionBar = getActionBar();	
		actionBar.setHomeButtonEnabled(true);
		
		Planilla_Centro_Costo = (GridView)findViewById(R.id.Grid_Centros_Costo_Plantilla);
		
		layout_Centros_Costo_Splash_Inicial.setVisibility(View.VISIBLE);
		
		
	}
	
	AsyncTaskCompleteListener<List<Planilla_Centro_Costo>> cargaPlanilla = new AsyncTaskCompleteListener<List<Planilla_Centro_Costo>>() {
		
		@Override
		public void onTaskComplete(List<Planilla_Centro_Costo> result) {
			
			adaptador_Plantilla = new Adaptador_Plantilla_Centros_Costo(Centros_Costo.this, result);
			Planilla_Centro_Costo.setAdapter(adaptador_Plantilla);
			adaptador_Plantilla.notifyDataSetChanged();
			showProgress(false);
			
			layout_Centros_Costo_Splash_Inicial.setVisibility(View.GONE);
			
			if(result.size() == 0)
			{
				layout_Centros_Costo_Splash.setVisibility(View.VISIBLE);
			}
			else
			{
				layout_Centros_Costo_Splash.setVisibility(View.GONE);
			}
		}
	};

	public void cargarCentroCosto(int idCentroCosto)
	{
		
		if(hasConnection.validateConnection())
		
		{
			
		layout_Centros_Costo_Splash_Inicial.setVisibility(View.GONE);
			
		Obtener_Centros_Costo_Planilla_Async obtenerIngresos = new Obtener_Centros_Costo_Planilla_Async(Centros_Costo.this, cargaPlanilla);
		idCentroCostoGlobal = idCentroCosto;
		
		obtenerIngresos.anio = dateAndTime.get(Calendar.YEAR);
		obtenerIngresos.mes = dateAndTime.get(Calendar.MONTH) + 1;
		obtenerIngresos.idCentroCosto = idCentroCosto;
		
		obtenerIngresos.execute(this);
		
	    showProgress(true);
		}
		else
		{
			Toast.makeText(this,"Sin conexion a internet", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
		getMenuInflater().inflate(R.menu.activity_centros__costo, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if(item.getItemId() == android.R.id.home){
			listadoMenu();
		}
		
		else if(item.getItemId() == R.id.menu_centro_Costo_cambiar_Centro){
			listadoCentrosCosto();
		}
		
		else if(item.getItemId() == R.id.centros_costo_seleccionar_mes)
		{
			DatePickerDialog  dialog = createDialogWithoutDateField();
			dialog.setTitle("Visualizar para el mes");
			dialog.show();
			return true;
		}
		
		return true;
	}

	private void listadoCentrosCosto() {
		int Orientation = getResources().getConfiguration().orientation;
		int width = 0;
		
		if(android.content.res.Configuration.ORIENTATION_LANDSCAPE == Orientation)
		{
			width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, getResources().getDisplayMetrics());
		}
		
		else if(android.content.res.Configuration.ORIENTATION_PORTRAIT == Orientation)
		{
			width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
		}
		
		else 
		{
			width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
		}
		
		SlideoutActivity.prepare(Centros_Costo.this, R.id.Contenedor_Centros_Costo, width);
		startActivity(new Intent(Centros_Costo.this, MenuCentrosCostoActivity.class));
		overridePendingTransition(0, 0);
	}

	private void listadoMenu() {
		int Orientation = getResources().getConfiguration().orientation;
		int width = 0;
		
		if(android.content.res.Configuration.ORIENTATION_LANDSCAPE == Orientation)
		{
			width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, getResources().getDisplayMetrics());
		}
		
		else if(android.content.res.Configuration.ORIENTATION_PORTRAIT == Orientation)
		{
			width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
		}
		
		else 
		{
			width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
		}
		
		SlideoutActivity.prepare(Centros_Costo.this, R.id.Contenedor_Centros_Costo, width);
		startActivity(new Intent(Centros_Costo.this, MenuActivity.class));
		overridePendingTransition(0, 0);
	}

	
	private DatePickerDialog createDialogWithoutDateField(){		
	    DatePickerDialog dpd = new DatePickerDialog(Centros_Costo.this, ExpiryDateSetListener,dateAndTime.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH), dateAndTime.get(Calendar.DAY_OF_MONTH));
	    try{
	    Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
	    for (Field datePickerDialogField : datePickerDialogFields) { 
	        if (datePickerDialogField.getName().equals("mDatePicker")) {
	            datePickerDialogField.setAccessible(true);
	            DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);
	            Field datePickerFields[] = datePickerDialogField.getType().getDeclaredFields();
	            for (Field datePickerField : datePickerFields) {
	               if ("mDaySpinner".equals(datePickerField.getName())
	            		   ||
	            	  "mCalendarView".equals(datePickerField.getName())
	            		   ) {
	                  datePickerField.setAccessible(true);
	                  Object dayPicker = new Object();
	                  dayPicker = datePickerField.get(datePicker);
	                  ((View) dayPicker).setVisibility(View.GONE);
	               }
	            }
	         }
	      }
	    }catch(Exception ex){
	    }
	  return dpd;
	}
	
	 public OnDateSetListener ExpiryDateSetListener = new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				
					
				Obtener_Centros_Costo_Planilla_Async obtenerIngresos = new Obtener_Centros_Costo_Planilla_Async(Centros_Costo.this, cargaPlanilla);
				obtenerIngresos.anio = year;
				obtenerIngresos.mes = monthOfYear+1;
				obtenerIngresos.idCentroCosto = idCentroCostoGlobal;
				
				obtenerIngresos.execute(this);
			
				showProgress(true);
				
			}
		};
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}


}
