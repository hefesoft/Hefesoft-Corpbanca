package com.hefesoft.corpbanca.slider;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.hefesoft.corpbanca.GlobalVars;
import com.hefesoft.corpbanca.R;
import com.hefesoft.corpbanca.adapters.AdaptadorCentros_Costo;
import com.hefesoft.corpbanca.asynctask.AsyncTaskCompleteListener;
import com.hefesoft.corpbanca.asynctask.Obtener_Centros_Costo_Async;
import com.hefesoft.corpbanca.entities.Centros_Costo;

public class MenuFragment_Centros_Costo extends Fragment {

	String[] lst;
	Calendar dateAndTime=Calendar.getInstance();
	AdaptadorCentros_Costo adaptador_Centro_Costo;
	
	
	View view;
	ListView lstMenu;
	Activity actividad;
	ProgressBar barraProgreso;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		actividad = getActivity();
		view = inflater.inflate(R.layout.template_lista, container, false);		
		lstMenu = (ListView)view.findViewById(R.id.template_lista_listview);		
		lstMenu.setOnItemClickListener(elementoSeleccionado);
		barraProgreso = (ProgressBar)view.findViewById(R.id.template_list_progress_bar);		
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		
		if(GlobalVars.LstEnMemoria != null)
		{
			adaptador_Centro_Costo = new AdaptadorCentros_Costo(actividad, (List<Centros_Costo>)GlobalVars.LstEnMemoria);
			lstMenu.setAdapter(adaptador_Centro_Costo);
			adaptador_Centro_Costo.notifyDataSetChanged();
		}
		
		else
		{
		Obtener_Centros_Costo_Async centrosCosto = new Obtener_Centros_Costo_Async(getActivity(), centrosCostoCargados);
//		centrosCosto.anio = dateAndTime.get(Calendar.YEAR);
//		centrosCosto.mes = dateAndTime.get(Calendar.MONTH) + 1;		
		centrosCosto.anio = 2012;
		centrosCosto.mes = 4;		
		centrosCosto.execute(getActivity());
		
		barraProgreso.setVisibility(ProgressBar.VISIBLE);
		}
	}
	
	AsyncTaskCompleteListener<List<Centros_Costo>> centrosCostoCargados = new AsyncTaskCompleteListener<List<Centros_Costo>>() {
		
		@Override
		public void onTaskComplete(List<Centros_Costo> result) {
			
			GlobalVars.LstEnMemoria = result;			
			adaptador_Centro_Costo = new AdaptadorCentros_Costo(actividad, result);
			lstMenu.setAdapter(adaptador_Centro_Costo);
			adaptador_Centro_Costo.notifyDataSetChanged();
			barraProgreso.setVisibility(ProgressBar.INVISIBLE);
		}
	};
	
	OnItemClickListener elementoSeleccionado = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long id) {
			
			GlobalVars.objetoEntreVistas = (Centros_Costo)lstMenu.getItemAtPosition(position);
			((MenuCentrosCostoActivity)getActivity()).closeMenu();
		}
	};
}
