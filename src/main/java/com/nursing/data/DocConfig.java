package com.nursing.data;

import java.io.*;
import java.util.Properties;

/**
 * Created by Jack on 2015/12/17.
 * 移动查房版本更新配置文件
 */
public class DocConfig {

    private static Properties sProperties;

    public Properties createProperties(){
        Properties properties=new Properties();
        File file=new File(this.getClass().getResource("/doc.properties").getPath());
        try {
            FileInputStream inputStream=new FileInputStream(file);
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            properties.load(bf);
            inputStream.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return properties;
    }

    public static Properties getProperties(){
        if (sProperties==null){
            DocConfig systemConfig=new DocConfig();
            sProperties = systemConfig.createProperties();
        }
        return sProperties;
    }


}
