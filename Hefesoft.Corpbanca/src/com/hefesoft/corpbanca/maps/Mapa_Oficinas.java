package com.hefesoft.corpbanca.maps;

import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hefesoft.corpbanca.R;
import com.hefesoft.corpbanca.asynctask.AsyncTaskCompleteListener;
import com.hefesoft.corpbanca.asynctask.ObtenerCordenadasAsync;
import com.hefesoft.corpbanca.entities.Cordenadas;
import com.hefesoft.corpbanca.sindicalizados.Ingresos;
import com.hefesoft.corpbanca.slider.MenuActivity;
import com.hefesoft.corpbanca.slider.SampleActionbarActivity;
import com.korovyansk.android.slideout.SlideoutActivity;

public class Mapa_Oficinas extends Activity {

	
	MapFragment mMapFragment;
	GoogleMap mMap;
	Uri uri;
	String rutaActual;
	String rutaDestino;
	LatLng MiUbicacion;
	LatLng MiDestino;
	ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapa__oficinas);
		
		GoogleMapOptions opciones = new GoogleMapOptions();
        opciones.mapType(GoogleMap.MAP_TYPE_NORMAL);
        
        mMapFragment = MapFragment.newInstance(opciones);
        
        FragmentTransaction fragmentTransaction =  getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.map, mMapFragment, "Mapa");
        fragmentTransaction.commit();
        
        actionBar = getActionBar();	
	    actionBar.setHomeButtonEnabled(true);
        
        getActionBar().setDisplayHomeAsUpEnabled(true);

	}
	
	
	AsyncTaskCompleteListener<List<Cordenadas>> cordenadasObtenidas = new AsyncTaskCompleteListener<List<Cordenadas>>() {
		
		@Override
		public void onTaskComplete(List<Cordenadas> result) {
			
			List<Cordenadas> lst = (List<Cordenadas>)result;			

			
			for(Cordenadas cordenada : lst)
			{	
				double  val1= Double.parseDouble(cordenada.getCordenada_X());
				double  val2= Double.parseDouble(cordenada.getCordenada_Y());
				
			
				MarkerOptions marcador = new MarkerOptions()
		        .position(new LatLng(val1,val2))
		        .title(cordenada.getCentro_Conto());
				
				mMap.addMarker(marcador);
				
				
			}
		
			try {
				Location Ubicacion = mMap.getMyLocation(); 				
				MiUbicacion = new LatLng(Ubicacion.getLatitude(), Ubicacion.getLongitude());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
			try {
				CameraUpdate camUpd1 =
					    CameraUpdateFactory.newLatLngZoom(MiUbicacion,mMap.getMaxZoomLevel() -8);	
				
				mMap.moveCamera(camUpd1);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			
			try {
				mMap.setOnMarkerClickListener(marcadorClick);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		private OnClickListener calcularRutaEvento = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					uri =Uri.parse("http://maps.google.com/maps?&saddr=" + MiUbicacion.latitude + "," +  MiUbicacion.longitude + "&daddr="
							+ MiDestino.latitude + "," + MiDestino.longitude);
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		OnMarkerClickListener marcadorClick = new OnMarkerClickListener() {		
			
			@Override
			public boolean onMarkerClick(Marker marker) {
				
				
				
				MiDestino = marker.getPosition();
				
				Dialog settingsDialog = new Dialog(Mapa_Oficinas.this); 
				settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE); 
				View v = getLayoutInflater().inflate(R.layout.template_dialog_mapas, null);
				
				Button calcularRuta = (Button)v.findViewById(R.id.template_dialog_mapas_calcular_ruta);
				calcularRuta.setOnClickListener(calcularRutaEvento);
				
				settingsDialog.setContentView(v); 
				settingsDialog.show(); 
				
				return false;
			}
		};
	};

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		mMapFragment = ((MapFragment) getFragmentManager().findFragmentByTag("Mapa"));
		mMap = mMapFragment.getMap();
		
		
		mMap.setIndoorEnabled(true);
		mMap.setMyLocationEnabled(true);
		
		ObtenerCordenadasAsync obtenerCordenadas = new ObtenerCordenadasAsync(Mapa_Oficinas.this, cordenadasObtenidas);
        obtenerCordenadas.execute(this);
	
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:			
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
			SlideoutActivity.prepare(Mapa_Oficinas.this, R.id.map, width);
			startActivity(new Intent(Mapa_Oficinas.this, MenuActivity.class));
			overridePendingTransition(0, 0);
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_mapa__oficinas, menu);
		return true;
	}

}
