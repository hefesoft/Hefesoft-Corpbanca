package com.hefesoft.corpbanca.slider;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

import com.hefesoft.corpbanca.maps.Mapa_Oficinas;
import com.hefesoft.corpbanca.sindicalizados.Ingresos;
import com.korovyansk.android.slideout.SlideoutHelper;

public class MenuCentrosCostoActivity extends FragmentActivity{

	private ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	   
	    actionBar = getActionBar();	
	    
	    mSlideoutHelper = new SlideoutHelper(this);
	    mSlideoutHelper.activate();
	    getSupportFragmentManager().beginTransaction().add(com.korovyansk.android.slideout.R.id.slideout_placeholder, new MenuFragment_Centros_Costo(), "menu").commit();
	    
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
	
	public void closeMenu()
	{
		actionBar.hide();
		mSlideoutHelper.close();
	}

}
