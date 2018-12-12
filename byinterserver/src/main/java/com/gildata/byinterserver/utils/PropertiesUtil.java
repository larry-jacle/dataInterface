package com.gildata.byinterserver.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by chenchen on 2018/11/16.
 */
public class PropertiesUtil {

    public static Properties getProps(String fileName) {
        Properties p = new Properties();
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);

        try {
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        return p;
    }

    public static Properties getProperties(String fileName) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(fileName));
            return props;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setProperties(String fileName, String key, String value) {
        Properties prop = new Properties();
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileName, true);
            prop.setProperty(key, value);
            prop.store(out, "");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
