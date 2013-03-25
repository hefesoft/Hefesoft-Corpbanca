package com.hefesoft.corpbanca.adapters;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hefesoft.corpbanca.GlobalVars;
import com.hefesoft.corpbanca.R;
import com.hefesoft.corpbanca.entities.Retiros_Ingresos;
import com.hefesoft.corpbanca.util.ImageLoader;

@SuppressWarnings("rawtypes")
public class AdaptadorSindicatos_Ingresos extends ArrayAdapter {
	 
    Activity context;
    List<Retiros_Ingresos> datos;
   
    
        @SuppressWarnings("unchecked")
		public
        AdaptadorSindicatos_Ingresos(Activity context, List<Retiros_Ingresos> datos ) {
            super(context, R.layout.activity_ingresos, datos);
            this.context = context;
            this.datos = datos;
            
        }
 
        public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View  item = inflater.inflate(R.layout.template_sindicatos_ingresos, null);
 

            
        TextView lblTitulo = (TextView)item.findViewById(R.id.Sindicato_Ingreso_Nombre);
    	lblTitulo.setText(((Retiros_Ingresos) datos.toArray()[position]).getNombre());
    	
    	TextView vicepresidencia = (TextView)item.findViewById(R.id.Sindicato_Ingreso_Vicepresidencia);
    	vicepresidencia.setText(((Retiros_Ingresos) datos.toArray()[position]).getVicePresidencia());
    	
    	TextView cargo = (TextView)item.findViewById(R.id.Sindicato_Ingreso_Cargo);
    	cargo.setText(((Retiros_Ingresos) datos.toArray()[position]).getNombreCargo());
    	
    	TextView sindicato = (TextView)item.findViewById(R.id.Sindicato_Ingreso_Sindicato);
    	sindicato.setText(((Retiros_Ingresos) datos.toArray()[position]).getNombreSindicato());
    	
    	
    	ImageView imagen = (ImageView)item.findViewById(R.id.Sindicato_Ingreso_Imagen);
    	
    	String image_url = GlobalVars.rutaFotos + ((Retiros_Ingresos) datos.toArray()[position]).getCedula() + "_1.jpg";
    	 
    	
    	 int loader = R.drawable.ic_launcher;
    	
         // ImageLoader class instance
         ImageLoader imgLoader = new ImageLoader(context.getApplicationContext());
  
         // whenever you want to load an image from url
         // call DisplayImage function
         // url - image url to load
         // loader - loader image, will be displayed before getting image
         // image - ImageView
         imgLoader.DisplayImage(image_url, loader, imagen);
       	
        return(item);
    }
}

