package com.hefesoft.corpbanca.slider;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuFragment extends ListFragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);		
		
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, new String[] { " Ver mapa de oficinas"
			, " Empleados sindicalizados"
			, " Centros de costo"
			}));
		getListView().setCacheColorHint(0);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		((MenuActivity)getActivity()).getSlideoutHelper().close();
		((MenuActivity)getActivity()).closeMenu();
		
		if(position == 0)
		{
			((MenuActivity)getActivity()).gotoMap();
		}
		else if(position == 1)
		{
			((MenuActivity)getActivity()).gotoIngresos();
		}
		else if(position == 2)
		{
			((MenuActivity)getActivity()).gotoCentrosCosto();
		}
	}

	
}
