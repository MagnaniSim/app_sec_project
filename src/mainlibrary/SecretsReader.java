/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainlibrary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author MagnaniSim
 */
public class SecretsReader {
    public boolean getPropValues() throws IOException {
        InputStream inputStream = null;
        boolean result = true;
	try {
            Properties prop = new Properties();
            String secretPropFileName = "secrets.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(secretPropFileName);
 
            if (inputStream != null) {
		prop.load(inputStream);
            } else {
		throw new FileNotFoundException("Secret property file '" + secretPropFileName + "' not found in the classpath");
            }

            // get the secrets...
            String dbUsername = prop.getProperty("DB_USERNAME");
            String dbPassword = prop.getProperty("DB_PASSWORD");
            String pwdPepper = prop.getProperty("PWD_PEPPER");
 
            //...and make them global variables
            System.setProperty("dbUsername",dbUsername);
            System.setProperty("dbPassword",dbPassword);
            System.setProperty("pwdPepper", pwdPepper);
            
//            System.out.println(System.getProperty("dbUsername"));
//            System.out.println(System.getProperty("dbPassword"));
            
            result = false;      
	} catch (Exception e) {
            System.out.println("Exception: " + e);
	} finally {
            if (inputStream != null) {
                inputStream.close();
            }
	}
        
	return result;
	}
}
