package com.eagle.cloud.route.utils;



import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
 
/**
 * 
 * @ClassName:Config_Road
 * @Description:读取配置文件工具类
 */
@EnableAutoConfiguration
public class Config_Road {
    // 静态块中不能有非静态属性，所以加static
    private static Properties prop = null;
 
    //静态块中的内容会在类别加载的时候先被执行
    static {
        try {
            prop = new Properties();
            // prop.load(new FileInputStream(new File("C:\\jdbc.properties")));
            prop.load(Config_Road.class.getClassLoader().getResourceAsStream("config/Road.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    //静态方法可以被类名直接调用
    public static String getString(String key) {
        return prop.getProperty(key);
    }
}
