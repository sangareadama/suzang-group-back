package com.appatam.Suzang_Group_Back;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	String path ;  
	InputStream inputStream;  
    
    private PropertiesReader()
    {}
     
  
    private static class SingletonHolder
    {       
        private final static PropertiesReader instance = new PropertiesReader();
    }
 
    public static PropertiesReader getInstance()
    {
        return SingletonHolder.instance;
    }
	
 
	public String getPath() throws IOException {
 
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("Erreur de chargement du fichier");
			}
 
 
			path = prop.getProperty("pathFile");
			
 
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return path;
	}
}