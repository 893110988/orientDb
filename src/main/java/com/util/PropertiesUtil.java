package com.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2017\12\7 0007.
 */
public class PropertiesUtil {
    public static Properties p = new Properties();
    static {
        InputStream in = null;
        try {
            String path=new File("").getCanonicalPath();
            in = new BufferedInputStream(new FileInputStream(path+"\\target\\classes\\com\\skieer\\orientGraph.properties"));
            p.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
