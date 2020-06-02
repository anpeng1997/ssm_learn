package cn.pengan.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BeanFactory {
    private static Map<String, Object> map;

    static {
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
        Properties prop = new Properties();
        map = new HashMap<String, Object>();
        try {
            prop.load(resourceAsStream);
            Enumeration<Object> keys = prop.keys();
            while (keys.hasMoreElements()) {
                String keyName = (String) keys.nextElement();
                String property = prop.getProperty(keyName);
                Object instance = Class.forName(property).newInstance();
                map.put(keyName, instance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Object getInstance(String instanceName) {
        return map.get(instanceName);
    }
}
