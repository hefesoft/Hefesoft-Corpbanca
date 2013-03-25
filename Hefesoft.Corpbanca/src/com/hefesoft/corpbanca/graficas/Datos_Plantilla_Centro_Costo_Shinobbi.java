package com.hefesoft.corpbanca.graficas;

import java.util.Calendar;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hefesoft.corpbanca.R;
import com.hefesoft.corpbanca.asynctask.AsyncTaskCompleteListener;
import com.hefesoft.corpbanca.asynctask.Obtener_Datos_Grafica_Plantilla_Async;
import com.hefesoft.corpbanca.entities.Graficas_Plantilla;
import com.shinobicontrols.chart.Chart;
import com.shinobicontrols.chart.data.XYDataSeries;
import com.shinobicontrols.chart.data.item.implementation.XYDataSeriesItemImpl;
import com.shinobicontrols.chart.series.Line;

public class Datos_Plantilla_Centro_Costo_Shinobbi extends Fragment
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
		
		view = inflater.inflate(R.layout.template_shinnobbi_layout, container, false);
		
		
		
		mLoginStatusView = ((Graficas)getActivity()).findViewById(R.id.layout_Cargando_graficas);
		mLoginFormView = view.findViewById(R.id.template_shinnobi_line_Container);
		layout_chart_Splash = ((Graficas)getActivity()).findViewById(R.id.layout_chart_graficas_Otro_Anio);
		
		

        // Get a reference to the Chart defined in the XML layout using its ID.
        Chart chart = (Chart) view.findViewById(R.id.chart);
        

        // Get a reference to the Line series defined in the XML layout using
        // its ID.
        Line sineWave = chart.getSeries(R.id.sine_wave);
        sineWave.setBackgroundColor(Color.RED);
        
              
        // Get the DataSeries for the Line series; this allows you to add
        // data points to the series.
        XYDataSeries sineWaveDataSeries = sineWave.getDataSeries();

        
        // Add the data points to the data series.
        addDataToDataSeries(sineWaveDataSeries);
		
		showProgress(true);
		
		return view;
	}
	
    private void addDataToDataSeries(XYDataSeries dataSeries) {
        // Set up some variables.
        int numberDataPoints = 1000;
        float x = 0.0f;
        float maxX = (float) (Math.PI * 4);
        float step = (maxX - x) / numberDataPoints;

        // Create and add the data points.
        for (int i = 0; i < numberDataPoints; i++) {
            dataSeries.add(new XYDataSeriesItemImpl(x, (float) Math.sin(x)));
            x += step;
        }
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
	
			
		}
	};

	
	
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
