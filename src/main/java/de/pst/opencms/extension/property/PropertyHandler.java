package de.pst.opencms.extension.property;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class PropertyHandler {
    private static HashMap<String,String> properties;

    private static void init(){
        properties = new HashMap<String,String>();
        Properties props = new Properties();
        try {
            props.load(PropertyHandler.class.getResourceAsStream("/repomanager.properties"));
            for(Object prop : props.keySet()){
                String key = (String)prop;
                properties.put(key, props.getProperty(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        init();
    }

    public static String get(String key){
        return properties.get(key);
    }

}
