package com.project.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project.exceptionhandler.TestFrameworkException;

public class DataProvider {

	private static Properties properties;
	private String configFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";
	private static DataProvider dataProvider;
	private static Logger logger = LogManager.getLogger(DataProvider.class);
	
	private DataProvider(){
		
		File configFile = new File(configFilePath);
		try {
			FileInputStream fileInputStream = new FileInputStream(configFile);
			properties = new Properties();
			try {
				properties.load(fileInputStream);
				logger.info("Properties file loaded successfully");
				logger.debug("Properties file loaded successfull {}", properties.stringPropertyNames());
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("Unable to load the properties file: ", e);
				throw new TestFrameworkException("Unable to load the properties file");
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			throw new TestFrameworkException("Incorrect config file path: "+e.getMessage());
		}
	}	
	
	public static String getProperty(String property) {
		
		if (dataProvider == null) 
			dataProvider = new DataProvider();
		else
			logger.warn("The data provider is not null and cannot create new instance");
		String propertyValue = properties.getProperty(property);
		logger.debug("Property {} readed successfully", propertyValue);
		return propertyValue;
	}
}
