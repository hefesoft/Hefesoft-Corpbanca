package com.hefesoft.corpbanca.graficas;

import java.util.Calendar;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYValueSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hefesoft.corpbanca.R;
import com.hefesoft.corpbanca.asynctask.AsyncTaskCompleteListener;
import com.hefesoft.corpbanca.asynctask.Obtener_Datos_Grafica_Plantilla_Async;
import com.hefesoft.corpbanca.entities.Graficas_Plantilla;

public class Datos_Plantilla_Centro_Costo extends Fragment
{

	View view;
	Activity actividad;
	List<Graficas_Plantilla> L;
	View mLoginStatusView;
	View mLoginFormView;
	int anio;
	View layout_chart_Splash;
	Calendar dateAndTime=Calendar.getInstance();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		actividad = getActivity();
		
		anio = 2012;
		
		Obtener_Datos_Grafica_Plantilla_Async obtener_Datos_Grafica = new Obtener_Datos_Grafica_Plantilla_Async(actividad, datosCargados);
		obtener_Datos_Grafica.anio = dateAndTime.get(Calendar.YEAR);
		obtener_Datos_Grafica.mes = dateAndTime.get(Calendar.MONTH) + 1;
		obtener_Datos_Grafica.idCentroCosto = 10000;
		obtener_Datos_Grafica.execute(actividad);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.graficas_plantilla, container, false);
		
		
		
		mLoginStatusView = ((Graficas)getActivity()).findViewById(R.id.layout_Cargando_graficas);
		mLoginFormView = view.findViewById(R.id.graficas_layout_container);
		layout_chart_Splash = ((Graficas)getActivity()).findViewById(R.id.layout_chart_graficas_Otro_Anio);
		
		showProgress(true);
		
		return view;
	}

	AsyncTaskCompleteListener<List<Graficas_Plantilla>> datosCargados = new AsyncTaskCompleteListener<List<Graficas_Plantilla>>() {
		
		@Override
		public void onTaskComplete(List<Graficas_Plantilla> result) {
		
			showProgress(false);
			
			if(result.size() == 0)
			{
				layout_chart_Splash.setVisibility(View.VISIBLE);
			}
			else 
			{
				layout_chart_Splash.setVisibility(View.GONE);
			}
			
			L = (List<Graficas_Plantilla>)result;
			drawChart(L);
			
		}
	};

	private void drawChart(List<Graficas_Plantilla> lst) {
	   
		LineGraph graficaLinea = new LineGraph();

	    GraphicalView chartView = graficaLinea.getIntent(actividad, lst, anio);

	    LinearLayout layout = (LinearLayout) getActivity().findViewById(R.id.graficas_layout_container);	    
	    layout.removeAllViews();
	    layout.addView(chartView, new LayoutParams(
	              LayoutParams.WRAP_CONTENT));
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public void showProgress(final boolean show) {
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

	public void graficarOtroMes(int anioSelected, int mes, int centroCosto)
	{
		Obtener_Datos_Grafica_Plantilla_Async obtener_Datos_Grafica = new Obtener_Datos_Grafica_Plantilla_Async(actividad, datosCargados);
		obtener_Datos_Grafica.anio = anioSelected;
		obtener_Datos_Grafica.mes = mes;
		obtener_Datos_Grafica.idCentroCosto = centroCosto;
		obtener_Datos_Grafica.execute(actividad);
		
		showProgress(true);
	}

		
}
