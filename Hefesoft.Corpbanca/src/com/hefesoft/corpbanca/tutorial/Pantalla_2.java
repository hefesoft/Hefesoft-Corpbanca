package com.hefesoft.corpbanca.tutorial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hefesoft.corpbanca.R;

public class Pantalla_2  extends Fragment
{

	View view;
	ImageView image;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.template_tutorial, container, false);
		image = (ImageView)view.findViewById(R.id.imagen_tutorial);
		image.setImageResource(R.drawable.pantalla_2);
		
		return view;
	}

}
