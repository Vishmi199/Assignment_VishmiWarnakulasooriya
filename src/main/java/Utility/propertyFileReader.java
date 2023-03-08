package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class propertyFileReader {

        public static String readPropertyFile (String property_name, String filePath)
        {
            Properties prop = new Properties();
            try (InputStream input = new FileInputStream(filePath)) {



                // load a properties file
                prop.load(input);


            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return prop.getProperty(property_name);
        }

    }


