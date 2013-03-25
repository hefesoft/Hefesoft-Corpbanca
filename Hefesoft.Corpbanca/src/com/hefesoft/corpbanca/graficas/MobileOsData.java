
package com.hefesoft.corpbanca.graficas;

import java.util.HashMap;
import java.util.Map;


public class MobileOsData {
    
    private final HashMap<String, Float> mobileOsUseWorldwide;

    public MobileOsData() {
        mobileOsUseWorldwide = new HashMap<String, Float>();
        mobileOsUseWorldwide.put("Hombres 807", 807f);
        mobileOsUseWorldwide.put("Mujeres 759", 759f);
    }

    public Map<String, Float> getMobileOsUseWorldwideData() {
        return mobileOsUseWorldwide;
    }
}
