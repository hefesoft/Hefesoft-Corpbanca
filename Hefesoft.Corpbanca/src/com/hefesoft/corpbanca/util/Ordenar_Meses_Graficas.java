package com.hefesoft.corpbanca.util;

import java.util.Comparator;

public class Ordenar_Meses_Graficas implements Comparator<Object>
{

	@Override
	public int compare(Object obj1, Object obj2) {
		int mes1 = ((com.hefesoft.corpbanca.entities.Graficas_Plantilla)obj1).getIdMes();
		int mes2 = ((com.hefesoft.corpbanca.entities.Graficas_Plantilla)obj2).getIdMes(); 
		
		if(mes1>mes2) return 1;
		else if(mes1<mes2) return -1;
		else return 0;
	}

}
