package com.hefesoft.corpbanca.tutorial;

import com.hefesoft.corpbanca.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Pantalla_1  extends Fragment
{

	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.template_tutorial, container, false);		
		return view;
	}

}
