package com.warehouse.batchjob.context;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
    // etl_dt = 20180531 return = 2018-0-531
    public String getFormatDay(String etl_dt){
        SimpleDateFormat sd_date = new SimpleDateFormat("yyyymmdd");
        SimpleDateFormat sd_str = new SimpleDateFormat("yyyy-mm-dd");
        try {
            return sd_str.format((sd_date.parse(etl_dt)));
        }catch (ParseException e){
            throw new RuntimeException(e);
        }
    }

}
