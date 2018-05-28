package com.nursing.data;

import java.io.*;
import java.util.Properties;

/**
 * Created by Jack on 2015/12/17.
 * 系统设置
 */
public class SystemConfig {

    private static Properties sProperties;

    public Properties createProperties(){
        Properties properties=new Properties();
        File file=new File(this.getClass().getResource("/config.properties").getPath());
        try {
            FileInputStream inputStream=new FileInputStream(file);
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            properties.load(bf);
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
            SystemConfig systemConfig=new SystemConfig();
            sProperties = systemConfig.createProperties();
        }
        return sProperties;
    }


}
