package com.hefesoft.corpbanca.slider;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.hefesoft.corpbanca.GlobalVars;
import com.hefesoft.corpbanca.R;
import com.hefesoft.corpbanca.adapters.Adaptador_Menu;
import com.hefesoft.corpbanca.entities.Centros_Costo;

public class Menu_Fragment extends Fragment
{

	View view;
	List<com.hefesoft.corpbanca.entities.Menu> lstMenu = new ArrayList<com.hefesoft.corpbanca.entities.Menu>();
	Activity actividad;
	ListView lstMenuControl;
	ProgressBar barraProgreso;
	Adaptador_Menu adaptador_Menu;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		actividad = getActivity();
		view = inflater.inflate(R.layout.template_lista, container, false);
		lstMenuControl = (ListView)view.findViewById(R.id.template_lista_listview);
		barraProgreso = (ProgressBar)view.findViewById(R.id.template_list_progress_bar);
		
		lstMenuControl.setOnItemClickListener(elementoSeleccionado);
		
		adaptador_Menu = new Adaptador_Menu(actividad, lstMenu);
		lstMenuControl.setAdapter(adaptador_Menu);
		adaptador_Menu.notifyDataSetChanged();
		
		return view;
	}
	
	OnItemClickListener elementoSeleccionado = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long id) {
			
			com.hefesoft.corpbanca.entities.Menu ElementoSeleccionado = (com.hefesoft.corpbanca.entities.Menu)lstMenuControl.getItemAtPosition(position);
			
			if(ElementoSeleccionado.id == 1)
			{
				((MenuActivity)getActivity()).closeMenu();
				((MenuActivity)getActivity()).gotoCentrosCosto();
			}
			
			else if(ElementoSeleccionado.id == 2)
			{
				((MenuActivity)getActivity()).closeMenu();
				((MenuActivity)getActivity()).gotoIngresos();
			}
			
			else if(ElementoSeleccionado.id == 3)
			{
				((MenuActivity)getActivity()).closeMenu();
				((MenuActivity)getActivity()).gotoMap();
			}
			
			else if(ElementoSeleccionado.id == 4)
			{
				((MenuActivity)getActivity()).closeMenu();
				((MenuActivity)getActivity()).gotoGraficas();
			}
			
		}
	};
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		
		
		
		lstMenu.add(new com.hefesoft.corpbanca.entities.Menu("Centros de costo", "Centro_Costo", "1",1));
		lstMenu.add(new com.hefesoft.corpbanca.entities.Menu("Empleados sindicalizados", "Empleados_Sindicalizados", "2",2));
		lstMenu.add(new com.hefesoft.corpbanca.entities.Menu("Mapa de oficinas", "Mapa_Oficinas", "3",3));
		lstMenu.add(new com.hefesoft.corpbanca.entities.Menu("Graficas", "Graficas", "4",4));
		
	}	
	
	
	

}
