package com.senla.di;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

//import com.senla.di.annotation.Injection;

public class DependencyInjection {

	private static final Logger logger = Logger.getLogger(DependencyInjection.class);

	private static final String ERROR_DEPENDENCY_INJECTION_LOADING = "Error dependency injection property file load.";
	private static final String ERROR_DEPENDENCY_INJECTION_NO_FILE = "Error. Dependency injection property file not found.";

	private static final String INSTANCE_PATH = "./dependency.properties";
	private static DependencyInjection instance;

	private Map<String, String> dependencies = new HashMap<>();

	private DependencyInjection() {
		if (!loadDependencies()) {
			// For test period
			System.out.println(ERROR_DEPENDENCY_INJECTION_LOADING);
		}
	}

	public static DependencyInjection getInstance() {
		if (instance == null)
			instance = new DependencyInjection();
		return instance;
	}

	@SuppressWarnings("unchecked")
	private boolean loadDependencies() {
		boolean result = false;
		Properties properties = new Properties();
		File file = new File(INSTANCE_PATH);
		if (file.exists()) {
			try (FileInputStream fileInputStream = new FileInputStream(file)) {
				properties.load(fileInputStream);
				dependencies = (Map<String, String>) properties.clone();
				result = true;
			} catch (IOException e) {
				logger.error(ERROR_DEPENDENCY_INJECTION_LOADING, e);
			}
		} else {
			logger.error(ERROR_DEPENDENCY_INJECTION_NO_FILE);
		}
		return result;
	}

	public Object getInstance(Class<?> type) {

		Class<?> cl = null;
		try {
			cl = Class.forName(dependencies.get(type.getName()));

//			if (cl.isAnnotationPresent(Injection.class)) {
//				return cl.getMethod("getInstance").invoke(null);
//			} else {
			return cl.newInstance();
//			}
		} catch (ClassNotFoundException e) {
			logger.error(e);
			return null;
		} catch (InstantiationException | IllegalAccessException e) {
			// newInstance
			logger.error(e);
			return null;
		} catch (IllegalArgumentException
//				| InvocationTargetException 
//				| NoSuchMethodException 
				| SecurityException e) {
			// getMethod
			logger.error(e);
			return null;
		}
	}
}
