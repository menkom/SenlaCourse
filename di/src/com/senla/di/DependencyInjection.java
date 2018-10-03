package com.senla.di;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DependencyInjection {

	private static final String ERROR_INVOKED_METHOD = "Exception thrown by an invoked method or constructor.";
	private static final String ERROR_ILLEGAL_ARGUMENT = "Error. Method has been passed an illegal or inappropriate argument.";

	private static final Logger logger = Logger.getLogger(DependencyInjection.class);

	private static final String ERROR_DEPENDENCY_INJECTION_LOADING = "Error dependency injection property file load.";
	private static final String ERROR_DEPENDENCY_INJECTION_NO_FILE = "Error. Dependency injection property file not found.";
	private static final String ERROR_DEPENDENCY_INJECTION_NO_PAIR = "Such dependency pair not found.";
	private static final String ERROR_DEPENDENCY_INJECTION_NEW_INSTANCE = "Error during instance creation using dependency injection.";

	private static final String INSTANCE_PATH = "./dependency.properties";
	private static DependencyInjection instance;

	private Map<String, String> dependencies = new HashMap<>();

	private Map<Class<?>, Object> instances = new HashMap<>();

	private DependencyInjection() {
		if (!loadDependencies()) {
			logger.error(ERROR_DEPENDENCY_INJECTION_LOADING);
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

	@SuppressWarnings("unchecked")
	public <T> T getInterfacePair(Class<T> type) {
		Class<?> cl = null;
		try {
			if (dependencies.containsKey(type.getName())) {
				cl = Class.forName(dependencies.get(type.getName()));
			} else {
				logger.error(ERROR_DEPENDENCY_INJECTION_NO_PAIR);
			}
			if (!instances.containsKey(cl)) {
				Constructor<?>[] constr = cl.getDeclaredConstructors();
				constr[0].setAccessible(true);
				Object result = constr[0].newInstance();
				constr[0].setAccessible(false);
				instances.put(cl, result);
			}
			return (T) instances.get(cl);
		} catch (ClassNotFoundException e) {
			logger.error(ERROR_DEPENDENCY_INJECTION_NO_PAIR, e);
			return null;
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error(ERROR_DEPENDENCY_INJECTION_NEW_INSTANCE, e);
			return null;
		} catch (IllegalArgumentException e) {
			logger.error(ERROR_ILLEGAL_ARGUMENT, e);
			return null;
		} catch (InvocationTargetException e) {
			logger.error(ERROR_INVOKED_METHOD, e);
			return null;
		}
	}
}
