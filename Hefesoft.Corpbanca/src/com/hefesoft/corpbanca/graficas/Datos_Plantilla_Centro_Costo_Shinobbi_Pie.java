package com.hefesoft.corpbanca.graficas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
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
import com.shinobicontrols.chart.data.CategoryRadialValueDataSeries;
import com.shinobicontrols.chart.data.item.implementation.CategoryValueDataSeriesItemImpl;
import com.shinobicontrols.chart.series.Pie;

public class Datos_Plantilla_Centro_Costo_Shinobbi_Pie extends Fragment
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
		
		view = inflater.inflate(R.layout.template_shinnobbi_pie_layout, container, false);
		
		
		
		mLoginStatusView = ((Graficas)getActivity()).findViewById(R.id.layout_Cargando_graficas);
		mLoginFormView = view.findViewById(R.id.template_shinnobi_pie_Container);
		layout_chart_Splash = ((Graficas)getActivity()).findViewById(R.id.layout_chart_graficas_Otro_Anio);

		  // *** Added code ***

        // Our "data source"
        final MobileOsData data = new MobileOsData();

        // Get references to the Charts defined in the XML layout.
        Chart mobileOsChart = (Chart) view.findViewById(R.id.mobile_os_chart);
        //Chart androidVersionsChart = (Chart) view.findViewById(R.id.ios_versions_chart);

        // Get a reference to the Pie series defined in the XML layout using
        // their IDs.
        Pie mobileOsPie = mobileOsChart.getSeries(R.id.mobile_os_pie);
        //Pie androidVersionsPie = androidVersionsChart.getSeries(R.id.ios_versions_pie);

        // Get the DataSeries for each Pie series; this allows you to add
        // data points to them.
        CategoryRadialValueDataSeries mobileOsDataSeries = mobileOsPie.getDataSeries();
        //CategoryRadialValueDataSeries androidVersionsDataSeries = androidVersionsPie
                //.getDataSeries();

        // Retrieve the data from your "data source" and add it to the Pie's
        // Data Series
        addDataToDataSeries(data.getMobileOsUseWorldwideData(), mobileOsDataSeries);
        //addDataToDataSeries(data.getAndroidVersionsInUseData(), androidVersionsDataSeries);
		
		showProgress(true);
		
		return view;
	}
	
	
    private void addDataToDataSeries(Map<String, Float> data,
            CategoryRadialValueDataSeries dataSeries) {

        // Sort the keys, which are Strings, into alphabetically order.
        ArrayList<String> keysList = new ArrayList<String>(data.keySet());
        Collections.sort(keysList);

        // Add a data point to the data series for each key-value pair.
        for (String key : keysList) {
            dataSeries.add(
                    new CategoryValueDataSeriesItemImpl(key, data.get(key), true));
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
