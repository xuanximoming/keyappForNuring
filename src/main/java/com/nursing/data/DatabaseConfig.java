package com.nursing.data;

import java.io.*;
import java.util.Properties;

/**
 * Created by Jack on 2015/12/17.
 */
public class DatabaseConfig {

    private static Properties sProperties;

    public Properties createProperties(){
        Properties properties=new Properties();
        File file=new File(this.getClass().getResource("/dbconfig.properties").getPath());
        try {
            FileInputStream inputStream=new FileInputStream(file);
            properties.load(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static Properties getProperties(){
        if (sProperties==null){
            DatabaseConfig databaseConfig=new DatabaseConfig();
            sProperties = databaseConfig.createProperties();
        }
        return sProperties;
    }


}
