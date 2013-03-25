package com.hefesoft.corpbanca.sindicalizados;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.hefesoft.corpbanca.R;
import com.hefesoft.corpbanca.adapters.AdaptadorSindicatos_Ingresos;
import com.hefesoft.corpbanca.asynctask.AsyncTaskCompleteListener;
import com.hefesoft.corpbanca.asynctask.Obtener_Ingresos_Sindicato_Async;
import com.hefesoft.corpbanca.entities.Retiros_Ingresos;
import com.hefesoft.corpbanca.graficas.Graficas;
import com.hefesoft.corpbanca.slider.MenuActivity;
import com.korovyansk.android.slideout.SlideoutActivity;



public class Ingresos extends Activity {
	
	GridView Sindicalizados;
	AdaptadorSindicatos_Ingresos adaptador_Ingresos;
	Calendar dateAndTime=Calendar.getInstance();
	Retiros_Ingresos Empleado_Seleccionado;
	View mLoginStatusView;
	View mLoginFormView;
	View layout_Sindicalizados_Costo_Splash;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ingresos);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Sindicalizados = (GridView)findViewById(R.id.Grid_Sindicatos_Ingresos_Nuevos_Ingresos);
		Sindicalizados.setOnItemLongClickListener(menuEmpleado);
		
		mLoginStatusView = findViewById(R.id.layout_Cargando_Sindicalizados);
		mLoginFormView = findViewById(R.id.Sindicalizados_Ingresos_Contenedor);
		layout_Sindicalizados_Costo_Splash = findViewById(R.id.layout_Sindicalizados_Costo_Splash);
	
		Obtener_Ingresos_Sindicato_Async obtenerIngresos = new Obtener_Ingresos_Sindicato_Async(Ingresos.this, cargaIngresosCompletada);
		obtenerIngresos.anio = dateAndTime.get(Calendar.YEAR);
		obtenerIngresos.mes = dateAndTime.get(Calendar.MONTH) + 1;
		obtenerIngresos.execute(this);
		
		showProgress(true);
	}
	
	
	OnItemLongClickListener menuEmpleado = new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> l, View view,
				int position, long id) {
			
			
			Empleado_Seleccionado = (Retiros_Ingresos)Sindicalizados.getItemAtPosition(position);
			
			Intent i = new Intent(Intent.ACTION_SEND);
			i.setType("message/rfc822");
			//i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
			i.putExtra(Intent.EXTRA_SUBJECT, "Atencion la siguiente persona "  + Empleado_Seleccionado.getNombre() +  " se ha sindicalizado");
			i.putExtra(Intent.EXTRA_TEXT   , Empleado_Seleccionado.getNombre() +   " con cargo "
					+ Empleado_Seleccionado.getNombreCargo() + " perteneciente a la vicepresidencia "
					+ Empleado_Seleccionado.getVicePresidencia() + " a ingresado al sindicato "
					+ Empleado_Seleccionado.getNombreSindicato() + "."
					);
			try {
			    startActivity(Intent.createChooser(i, "Enviado correo..."));
			    return true;
			} catch (android.content.ActivityNotFoundException ex) {
			    Toast.makeText(Ingresos.this, "No hay clientes instalados de envio de correo.", Toast.LENGTH_SHORT).show();
			    return false;
			}
		}
	};
	
	AsyncTaskCompleteListener<List<Retiros_Ingresos>> cargaIngresosCompletada = new AsyncTaskCompleteListener<List<Retiros_Ingresos>>() {
		
		@Override
		public void onTaskComplete(List<Retiros_Ingresos> result) {
			
			showProgress(false);
			
			if(result.size() == 0)
			{
				layout_Sindicalizados_Costo_Splash.setVisibility(View.VISIBLE);
			}
			else
			{
				layout_Sindicalizados_Costo_Splash.setVisibility(View.GONE);
			}
			
			adaptador_Ingresos = new AdaptadorSindicatos_Ingresos(Ingresos.this, result);
			Sindicalizados.setAdapter(adaptador_Ingresos);
			adaptador_Ingresos.notifyDataSetChanged();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
		getMenuInflater().inflate(R.menu.activity_ingresos, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:			
			
			int Orientation = getResources().getConfiguration().orientation;
			int width = 0;
			
			if(android.content.res.Configuration.ORIENTATION_LANDSCAPE == Orientation)
			{
				width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, getResources().getDisplayMetrics());
			}
			
			else if(android.content.res.Configuration.ORIENTATION_PORTRAIT == Orientation)
			{
				width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
			}
			
			else 
			{
				width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
			}
			
			
			SlideoutActivity.prepare(Ingresos.this, R.id.Sindicalizados_Ingresos_Contenedor, width);
			startActivity(new Intent(Ingresos.this, MenuActivity.class));
			overridePendingTransition(0, 0);
			return true;
			
		case R.id.sindicatos_ingresos_seleccionar_mes:			
			DatePickerDialog  dialog = createDialogWithoutDateField();
			dialog.setTitle("Visualizar para el mes");
			dialog.show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private DatePickerDialog createDialogWithoutDateField(){		
	    DatePickerDialog dpd = new DatePickerDialog(Ingresos.this, ExpiryDateSetListener,dateAndTime.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH), dateAndTime.get(Calendar.DAY_OF_MONTH));
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
			
			Obtener_Ingresos_Sindicato_Async obtenerIngresos = new Obtener_Ingresos_Sindicato_Async(Ingresos.this, cargaIngresosCompletada);
			obtenerIngresos.anio = year;
			obtenerIngresos.mes = monthOfYear+1;
			obtenerIngresos.execute(this);
			
			layout_Sindicalizados_Costo_Splash.setVisibility(View.GONE);
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
