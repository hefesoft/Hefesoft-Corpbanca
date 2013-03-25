package com.hefesoft.corpbanca.graficas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;

import com.hefesoft.corpbanca.entities.Graficas_Plantilla;

public class LineGraph{

	public GraphicalView getIntent(Context context,
			List<Graficas_Plantilla> lst,
			int anio
			) {
		
		this.anio = anio;
		
		createData(lst);
		

		
		org.achartengine.model.XYValueSeries series = new org.achartengine.model.XYValueSeries(String.valueOf(anio)); 
		
		for(Graficas_Plantilla pivote : anio1 )
		{
			series.add(pivote.IdMes, Double.parseDouble(pivote.idEmpleado));
		}
		
		
		org.achartengine.model.XYValueSeries series2 = new org.achartengine.model.XYValueSeries(String.valueOf(anio-1)); 
		
		for(Graficas_Plantilla pivote : anio2 )
		{
			series2.add(pivote.IdMes, Double.parseDouble(pivote.idEmpleado));
		}
		
		org.achartengine.model.XYValueSeries series3 = new org.achartengine.model.XYValueSeries(String.valueOf(anio-2)); 
		
		for(Graficas_Plantilla pivote : anio3 )
		{
			series3.add(pivote.IdMes, Double.parseDouble(pivote.idEmpleado));
		}

		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		
		dataset.addSeries(series);
		dataset.addSeries(series2);
		dataset.addSeries(series3);
		
		
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); // Holds a collection of XYSeriesRenderer and customizes the graph
		XYSeriesRenderer renderer = new XYSeriesRenderer(); // This will be used to customize line 1
		XYSeriesRenderer renderer2 = new XYSeriesRenderer(); // This will be used to customize line 2
		XYSeriesRenderer renderer3 = new XYSeriesRenderer(); // This will be used to customize line 2
		
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.addSeriesRenderer(renderer2);
		mRenderer.addSeriesRenderer(renderer3);
		
		// Customization time for line 1!
		renderer.setColor(Color.GRAY);
		renderer.setPointStyle(PointStyle.SQUARE);
		renderer.setFillPoints(true);
	
		// Customization time for line 2!
		renderer2.setColor(Color.YELLOW);
		renderer2.setPointStyle(PointStyle.DIAMOND);
		renderer2.setFillPoints(true);
		
		// Customization time for line 3!
		renderer2.setColor(Color.GREEN);
		renderer2.setPointStyle(PointStyle.DIAMOND);
		renderer2.setFillPoints(true);
		
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.WHITE);
		mRenderer.setMarginsColor(Color.WHITE);
		mRenderer.setYAxisAlign(Align.LEFT, 0);   
		mRenderer.setYLabelsAlign(Align.LEFT, 0);		
		mRenderer.setAxesColor(Color.BLACK);
		mRenderer.setLabelsColor(Color.BLACK);
		mRenderer.setXLabelsColor(Color.BLUE);
		mRenderer.setYLabelsColor(0,Color.BLUE);
		
		mRenderer.setChartTitle("Plantilla para " + String.valueOf(anio) + " - " + String.valueOf(anio-2));
		
	
		
		
		return ChartFactory.getLineChartView(context, dataset, mRenderer);
		
	}
	
	private void createData(List<Graficas_Plantilla> L) {
        
        for(Graficas_Plantilla pivote : L)
        {
        	if(Integer.parseInt(pivote.Anio) == anio)
        	{
        		anio3.add(pivote);
        	}
        	else if(Integer.parseInt(pivote.Anio) == (anio -1))
        	{
        		anio2.add(pivote);
        	}
        	else if(Integer.parseInt(pivote.Anio) == (anio -2))
        	{
        		anio1.add(pivote);
        	}	
        }	
        
        Collections.sort(anio1, new com.hefesoft.corpbanca.util.Ordenar_Meses_Graficas());
        Collections.sort(anio2, new com.hefesoft.corpbanca.util.Ordenar_Meses_Graficas());
        Collections.sort(anio3, new com.hefesoft.corpbanca.util.Ordenar_Meses_Graficas());
	}

	
	private List<Graficas_Plantilla> anio1 =new ArrayList<Graficas_Plantilla>();
	private List<Graficas_Plantilla> anio2 =new ArrayList<Graficas_Plantilla>();
	private List<Graficas_Plantilla> anio3 =new ArrayList<Graficas_Plantilla>();
	int anio;
	
}
