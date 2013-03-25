package com.hefesoft.corpbanca.adapters;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hefesoft.corpbanca.R;

@SuppressWarnings("rawtypes")
public class Adaptador_Menu extends ArrayAdapter {
	 
    Activity context;
    List<com.hefesoft.corpbanca.entities.Menu> datos;
   
    
        @SuppressWarnings("unchecked")
		public
        Adaptador_Menu(Activity context, List<com.hefesoft.corpbanca.entities.Menu> datos ) {
            super(context, R.layout.activity_home, datos);
            this.context = context;
            this.datos = datos;
            
        }
 
        public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View  item = inflater.inflate(R.layout.template_menu, null);
 


        if(((com.hefesoft.corpbanca.entities.Menu) datos.toArray()[position]).getId() == 1)
        {
	        TextView lblTitulo = (TextView)item.findViewById(R.id.template_menu_nombre);
	    	lblTitulo.setText(((com.hefesoft.corpbanca.entities.Menu) datos.toArray()[position]).getNombre());    	
	       	
	    	ImageView imagen = (ImageView)item.findViewById(R.id.image_Menu);
	    	imagen.setImageResource(R.drawable.centro_costo);
        }
        
        else if(((com.hefesoft.corpbanca.entities.Menu) datos.toArray()[position]).getId() == 2)
        {
	        TextView lblTitulo = (TextView)item.findViewById(R.id.template_menu_nombre);
	    	lblTitulo.setText(((com.hefesoft.corpbanca.entities.Menu) datos.toArray()[position]).getNombre());    	
	       	
	    	ImageView imagen = (ImageView)item.findViewById(R.id.image_Menu);
	    	imagen.setImageResource(R.drawable.sindicato);
        }
        
        else if(((com.hefesoft.corpbanca.entities.Menu) datos.toArray()[position]).getId() == 3)
        {
	        TextView lblTitulo = (TextView)item.findViewById(R.id.template_menu_nombre);
	    	lblTitulo.setText(((com.hefesoft.corpbanca.entities.Menu) datos.toArray()[position]).getNombre());    	
	       	
	    	ImageView imagen = (ImageView)item.findViewById(R.id.image_Menu);
	    	imagen.setImageResource(R.drawable.map);
        }
        
        else if(((com.hefesoft.corpbanca.entities.Menu) datos.toArray()[position]).getId() == 4)
        {
	        TextView lblTitulo = (TextView)item.findViewById(R.id.template_menu_nombre);
	    	lblTitulo.setText(((com.hefesoft.corpbanca.entities.Menu) datos.toArray()[position]).getNombre());    	
	       	
	    	ImageView imagen = (ImageView)item.findViewById(R.id.image_Menu);
	    	imagen.setPadding(8, 0, 0, 0);
	    	imagen.setImageResource(R.drawable.ic_action_chart_line);
        }
        
        return(item);
    }
}

