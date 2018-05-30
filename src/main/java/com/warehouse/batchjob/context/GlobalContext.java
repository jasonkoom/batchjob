package com.warehouse.batchjob.context;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GlobalContext {
    private static final String PROPERTIES_NAME = "config.properties";

    private GlobalContext()  {
            try{
                InputStream is =this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_NAME);
                props.clear();
                props.load(is);
            }catch (IOException e){
                throw new RuntimeException(e);
            }

        }
    private static GlobalContext instance = new GlobalContext();
    public static GlobalContext getInstance() {
        return instance;
    }

    private Properties props = new Properties();
    public void init() throws IOException {
       /* InputStream is =this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_NAME);
        props.clear();
        props.load(is);*/
     }

    public String getStringValue(String name) {
        if (props.containsKey(name)) {
            return props.getProperty(name);
        } else
            return "";
    }

    public int getIntegerValue(String name) {
        if (props.containsKey(name)) {
            return Integer.parseInt(props.getProperty(name));
        } else
            return -1;
    }

    public long getLongValue(String name) {
        if (props.containsKey(name)) {
            return Long.parseLong(props.getProperty(name));
        } else
            return -1;
    }

    public boolean getBooleanValue(String name) {
        if (props.containsKey(name)) {
            return Boolean.parseBoolean(props.getProperty(name));
        } else
            return false;
    }
}
