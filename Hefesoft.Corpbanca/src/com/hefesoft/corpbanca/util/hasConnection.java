package com.hefesoft.corpbanca.util;

import com.hefesoft.corpbanca.GlobalVars;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class hasConnection {

	  
	public static boolean validateConnection() {
		    ConnectivityManager cm = (ConnectivityManager) GlobalVars.getAppContext().getSystemService(
		        Context.CONNECTIVITY_SERVICE);

		    NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		    NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		    
		    if (wifiNetwork != null && wifiNetwork.isConnected()) {
		      return true;
		    }

		    
		    else if (mobileNetwork != null && mobileNetwork.isConnected()) {
		      return true;
		    }

		    
		    else if (activeNetwork != null && activeNetwork.isConnected()) {
		      return true;
		    }
		    else
		    {
		    	return false;
		    }
		  }
	
}
