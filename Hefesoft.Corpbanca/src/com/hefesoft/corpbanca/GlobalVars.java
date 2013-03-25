package com.hefesoft.corpbanca;
import android.app.Application;
import android.content.Context;

import com.hefesoft.corpbanca.entities.User;
import com.hefesoft.corpbanca.util.UnhandledExceptionHandler;

public class GlobalVars extends Application {
	
	public static String Token = "";
	public static String UsuarioEmail = "";
	public static String Password = "";
	public static User Usuario;
	public static String urlServices = "http://190.147.9.61:8083/api/";
	public static String rutaFotos = "http://190.147.9.61:9090/ClientBin/fotos/";
	public static String cicloActual = "";
	
	public static Object objetoEntreVistas = null;
	public static Object LstEnMemoria = null;
	
	private static Context context;
	
    private static GlobalVars singleton;
    
    public static GlobalVars getInstance() {
        return singleton;
    }
    @Override
    
    public void onCreate() {
    	GlobalVars.context = getApplicationContext();        
        Thread.setDefaultUncaughtExceptionHandler(new UnhandledExceptionHandler());
    	super.onCreate();
        singleton = this;        
        
    }
    
    public static Context getAppContext() {
        return GlobalVars.context;
    }
    
    private static ConnectionChange connection;
    
    
    
    public static ConnectionChange getConnection() {
		return connection;
	}
	
    public static void setConnection(ConnectionChange connection) {
		GlobalVars.connection = connection;
		
	}
    
    public static void pushConnection(Object obj, Object obj2) {
		
		connection.changeConnection(true,false);
	}



	public static abstract class ConnectionChange{
        public abstract void changeConnection(Object obj, Object obj2);
    }

}