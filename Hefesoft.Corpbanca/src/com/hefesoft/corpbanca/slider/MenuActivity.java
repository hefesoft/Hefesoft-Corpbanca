package com.hefesoft.corpbanca.slider;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

import com.hefesoft.corpbanca.centros_costo.Centros_Costo;
import com.hefesoft.corpbanca.graficas.Graficas;
import com.hefesoft.corpbanca.maps.Mapa_Oficinas;
import com.hefesoft.corpbanca.sindicalizados.Ingresos;
import com.korovyansk.android.slideout.SlideoutHelper;

public class MenuActivity extends FragmentActivity{

	private ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	   
	     actionBar = getActionBar();	
	    
	    mSlideoutHelper = new SlideoutHelper(this);
	    mSlideoutHelper.activate();
	    getSupportFragmentManager().beginTransaction().add(com.korovyansk.android.slideout.R.id.slideout_placeholder, new Menu_Fragment(), "menu").commit();
	    
	    actionBar.hide();	    
	    mSlideoutHelper.open();
	}

	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			mSlideoutHelper.close();
			actionBar.show();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}


	public SlideoutHelper getSlideoutHelper(){
		return mSlideoutHelper;
	}
	
	private SlideoutHelper mSlideoutHelper;
	
	public void gotoMap()
	{
		Intent in = new Intent(MenuActivity.this, Mapa_Oficinas.class);
		startActivity(in);
	}
	
	public void gotoIngresos()
	{
		Intent in = new Intent(MenuActivity.this, Ingresos.class);
		startActivity(in);
	}
	
	public void gotoCentrosCosto()
	{
		Intent in = new Intent(MenuActivity.this, Centros_Costo.class);
		startActivity(in);
	}
	
	public void closeMenu()
	{
		mSlideoutHelper.close();
		actionBar.hide();
	}


	public void gotoGraficas() {
		Intent in = new Intent(MenuActivity.this, Graficas.class);
		startActivity(in);
	}

}
