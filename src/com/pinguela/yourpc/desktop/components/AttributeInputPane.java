package com.pinguela.yourpc.desktop.components;

import java.util.Arrays;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.yourpc.config.ConfigManager;
import com.pinguela.yourpc.desktop.util.ReflectionUtils;
import com.pinguela.yourpc.model.Attribute;

public abstract class AttributeInputPane<T>
extends InputPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9050555982076114344L;

	private static Logger logger = LogManager.getLogger(AttributeInputPane.class);

	private static final String INPUT_PANE_CLASSES_PNAME = "ui.attribute.input.pane.classes";
	private static Collection<Class<?>> inputPaneClasses;

	static {
		String packageName = AttributeInputPane.class.getPackage().getName();
		try {
			inputPaneClasses = ReflectionUtils.loadClassesFromPackage(packageName, Arrays.asList(getSubclassNames()));
		} catch (Throwable t) {
			logger.fatal(String.format("Exception thrown while loading classes during initialization. Message: %s",
					t.getMessage()), t);
			throw new ExceptionInInitializerError(t);
		}
	}

	private static String[] getSubclassNames() {
		return ConfigManager.getValue(INPUT_PANE_CLASSES_PNAME).split(ConfigManager.DELIMITER);
	}

	private Attribute<T> attribute;

	/**
	 * Unused constructor, required for panel rendering within a WindowBuilder designer.
	 */
	@SuppressWarnings({"unused", "unchecked"})
	private AttributeInputPane() {
		this(Attribute.getInstance((Class<T>) String.class));
	}

	protected AttributeInputPane(Attribute<T> attribute) {
		this.attribute = attribute;

		String message = String.format("Select value(s) for %s:", attribute.getName());
		setMessage(message);
		
		setInitialValues();
	}
	
	public Attribute<T> getAttribute() {
		return attribute;
	}

	public static final InputPane getInstance(Attribute<?> attribute) {

		Class<?> targetClass = getClassByTypeParameter(attribute.getParameterizedTypeClass());
		try {
			return (InputPane) targetClass.getDeclaredConstructor(Attribute.class).newInstance(attribute);
		} catch (Exception e) {
			String message = String.format("Exception thrown while instantiating %s. Message: %s",
					targetClass.getName(), e.getMessage());
			logger.error(message, e);
			throw new IllegalStateException(message, e);
		} 
	}

	private static final Class<?> getClassByTypeParameter(Class<?> typeParameter) {

		for (Class<?> subclass : inputPaneClasses) {
			if (ReflectionUtils.isAssignableToTypeParameter(typeParameter, subclass)) {
				return subclass;
			}	
		}
		throw new IllegalArgumentException(String.format(
				"No corresponding class returned for type parameter %s, cannot instantiate.", typeParameter.getName()));
	}
	
	protected abstract void setInitialValues();

}
