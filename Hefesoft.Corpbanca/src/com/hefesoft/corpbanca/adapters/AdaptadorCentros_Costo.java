package com.hefesoft.corpbanca.adapters;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hefesoft.corpbanca.R;
import com.hefesoft.corpbanca.entities.Centros_Costo;

@SuppressWarnings("rawtypes")
public class AdaptadorCentros_Costo extends ArrayAdapter {
	 
    Activity context;
    List<Centros_Costo> datos;
   
    
        @SuppressWarnings("unchecked")
		public
        AdaptadorCentros_Costo(Activity context, List<Centros_Costo> datos ) {
            super(context, R.layout.activity_centros__costo, datos);
            this.context = context;
            this.datos = datos;
            
        }
 
        public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View  item = inflater.inflate(R.layout.template_centros_costo, null);
            
        TextView lblTitulo = (TextView)item.findViewById(R.id.template_centros_costo_nombre);
    	lblTitulo.setText(((Centros_Costo) datos.toArray()[position]).getCentroCosto());
       	
        return(item);
    }
}

