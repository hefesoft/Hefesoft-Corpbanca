package com.hefesoft.corpbanca.graficas;

import java.lang.reflect.Field;
import java.util.Calendar;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import com.hefesoft.corpbanca.GlobalVars;
import com.hefesoft.corpbanca.R;
import com.hefesoft.corpbanca.adapters.SectionsPagerAdapter;
import com.hefesoft.corpbanca.slider.MenuActivity;
import com.hefesoft.corpbanca.slider.MenuCentrosCostoActivity;
import com.korovyansk.android.slideout.SlideoutActivity;

public class Graficas extends FragmentActivity implements ActionBar.TabListener {

	SectionsPagerAdapter mSectionsPagerAdapter;
	ActionBar actionBar;
    ViewPager mViewPager;
    Calendar dateAndTime=Calendar.getInstance();
    private int anio = 0;
	private int mes = 0;
	int idCentroCosto = 1;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graficas);

		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());  
		
        actionBar = getActionBar();	
	    actionBar.setHomeButtonEnabled(true);
	    
	    // Set up the action bar.
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager_Graficas);
        
        
        
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
		
        mSectionsPagerAdapter.addFragment(new com.hefesoft.corpbanca.graficas.Datos_Plantilla_Centro_Costo(), "Plantilla");
        //mSectionsPagerAdapter.addFragment(new com.hefesoft.corpbanca.graficas.Datos_Plantilla_Centro_Costo_Shinobbi_Pie(), "Shinobbi");
        
        
		mSectionsPagerAdapter.notifyDataSetChanged();
		generarMenu();
		
		
        
	}
	
	private void generarMenu() {
		
		getActionBar().removeAllTabs();
		
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {        
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
	}
	
	@Override
	protected void onRestart() {
		
			try {
				if(GlobalVars.objetoEntreVistas != null)
				{
					idCentroCosto = Integer.parseInt(((com.hefesoft.corpbanca.entities.Centros_Costo)GlobalVars.objetoEntreVistas).getId());
					if(mViewPager.getCurrentItem() == 0)
					{
						if(anio == 0)
						{
							anio = 2012;
						}
						
						if(mes == 0)
						{
							mes = 12;
						}
						
						((com.hefesoft.corpbanca.graficas.Datos_Plantilla_Centro_Costo)mSectionsPagerAdapter.getItem(mViewPager.getCurrentItem())).graficarOtroMes(anio, mes, idCentroCosto);
					}
					super.onRestart();
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_graficas__costo, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			mostrarMenu();
		}
		
		else if(item.getItemId() == R.id.menu_graficas_Cambiar_Centro_Costo){
			listadoCentrosCosto();
		}
		
		
		
		else if(item.getItemId() == R.id.graficas_seleccionar_mes)
		{
			DatePickerDialog  dialog = createDialogWithoutDateField();
			dialog.setTitle("Visualizar para el mes");
			dialog.show();
			return true;
		}
		
		return true;
	}

	private void mostrarMenu() {
		int Orientation = getResources().getConfiguration().orientation;
		int width = 0;
		
		if(android.content.res.Configuration.ORIENTATION_LANDSCAPE == Orientation)
		{
			width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, getResources().getDisplayMetrics());
		}
		
		else if(android.content.res.Configuration.ORIENTATION_PORTRAIT == Orientation)
		{
			width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
		}
		
		else 
		{
			width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
		}
		
		
		SlideoutActivity.prepare(Graficas.this, R.id.pager_Graficas, width);
		startActivity(new Intent(Graficas.this, MenuActivity.class));
		overridePendingTransition(0, 0);
	}
	
	private void listadoCentrosCosto() {
		int Orientation = getResources().getConfiguration().orientation;
		int width = 0;
		
		if(android.content.res.Configuration.ORIENTATION_LANDSCAPE == Orientation)
		{
			width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, getResources().getDisplayMetrics());
		}
		
		else if(android.content.res.Configuration.ORIENTATION_PORTRAIT == Orientation)
		{
			width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
		}
		
		else 
		{
			width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
		}
		
		SlideoutActivity.prepare(Graficas.this, R.id.pager_Graficas, width);
		startActivity(new Intent(Graficas.this, MenuCentrosCostoActivity.class));
		overridePendingTransition(0, 0);
	}

	
	private DatePickerDialog createDialogWithoutDateField(){		
	    DatePickerDialog dpd = new DatePickerDialog(Graficas.this, ExpiryDateSetListener,dateAndTime.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH), dateAndTime.get(Calendar.DAY_OF_MONTH));
	    try{
	    Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
	    for (Field datePickerDialogField : datePickerDialogFields) { 
	        if (datePickerDialogField.getName().equals("mDatePicker")) {
	            datePickerDialogField.setAccessible(true);
	            DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);
	            Field datePickerFields[] = datePickerDialogField.getType().getDeclaredFields();
	            for (Field datePickerField : datePickerFields) {
	               if ("mDaySpinner".equals(datePickerField.getName())
	            		   ||
	            	  "mCalendarView".equals(datePickerField.getName())
	            		   ) {
	                  datePickerField.setAccessible(true);
	                  Object dayPicker = new Object();
	                  dayPicker = datePickerField.get(datePicker);
	                  ((View) dayPicker).setVisibility(View.GONE);
	               }
	            }
	         }
	      }
	    }catch(Exception ex){
	    }
	  return dpd;
	}
	
	 public OnDateSetListener ExpiryDateSetListener = new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				
				anio = year;
				mes = monthOfYear+1;

				if(idCentroCosto == -1)
				{
					idCentroCosto = 10000; 
				}
				
				if(mViewPager.getCurrentItem() == 0)
				{
					((com.hefesoft.corpbanca.graficas.Datos_Plantilla_Centro_Costo)mSectionsPagerAdapter.getItem(mViewPager.getCurrentItem())).graficarOtroMes(year, monthOfYear+1, idCentroCosto);
				}
				
			}
		};

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

}
