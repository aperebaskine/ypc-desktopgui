package com.pinguela.yourpc.desktop.components;

import java.util.Arrays;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.config.ConfigManager;
import com.pinguela.yourpc.desktop.util.ReflectionUtils;
import com.pinguela.yourpc.model.Attribute;
import com.pinguela.yourpc.service.AttributeService;
import com.pinguela.yourpc.service.impl.AttributeServiceImpl;

public abstract class AttributeValueInputPane<T>
extends InputPane<Attribute<T>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9050555982076114344L;

	private static Logger logger = LogManager.getLogger(AttributeValueInputPane.class);

	private static final String INPUT_PANE_CLASSES_PNAME = "ui.attribute.input.pane.classes";
	private static final Collection<Class<?>> INPUT_PANE_CLASSES;

	static {
		String packageName = AttributeValueInputPane.class.getPackage().getName();
		try {
			INPUT_PANE_CLASSES = ReflectionUtils.loadClassesFromPackage(packageName, Arrays.asList(getSubclassNames()));
		} catch (Throwable t) {
			logger.fatal(String.format("Exception thrown while loading classes during initialization. Message: %s",
					t.getMessage()), t);
			throw new ExceptionInInitializerError(t);
		}
	}

	private static String[] getSubclassNames() {
		return ConfigManager.getValue(INPUT_PANE_CLASSES_PNAME).split(ConfigManager.DELIMITER);
	}
	
	private AttributeService attributeService;

	private Attribute<T> editingAttribute;
	private Attribute<T> savedAttribute;

	/**
	 * Unused constructor, required for panel rendering within a WindowBuilder designer.
	 */
	@SuppressWarnings({"unused", "unchecked"})
	private AttributeValueInputPane() {
		this(Attribute.getInstance((Class<T>) String.class), AttributeService.RETURN_UNASSIGNED_VALUES, true);
	}

	protected AttributeValueInputPane(Attribute<T> attribute, boolean showUnassignedValues, boolean showActions) {
		super(String.format("Select value(s) for %s:", attribute.getName()), showActions);
		this.attributeService = new AttributeServiceImpl();
		this.editingAttribute = attribute;
		
		this.savedAttribute = findSavedValues();
		setInitialValues();
	}
	
	public Attribute<T> getAttribute() {
		return editingAttribute;
	}
	
	public static final <T> InputPane<T> getInstance(Attribute<T> attribute, boolean showUnassignedValues) {
		return getInstance(attribute, showUnassignedValues, true);
	}

	@SuppressWarnings("unchecked")
	public static final <T> InputPane<T> getInstance(Attribute<T> attribute, boolean showUnassignedValues, boolean showActions) {

		Class<?> targetClass = getClassByTypeParameter(attribute.getParameterizedTypeClass());
		try {
			return (InputPane<T>) targetClass
					.getDeclaredConstructor(Attribute.class, boolean.class, boolean.class)
					.newInstance(attribute, showUnassignedValues, showActions);
		} catch (Exception e) {
			String message = String.format("Exception thrown while instantiating %s. Message: %s",
					targetClass.getName(), e.getMessage());
			logger.error(message, e);
			throw new IllegalStateException(message, e);
		} 
	}

	private static final Class<?> getClassByTypeParameter(Class<?> typeParameter) {

		for (Class<?> subclass : INPUT_PANE_CLASSES) {
			if (ReflectionUtils.isAssignableToTypeParameter(typeParameter, subclass)) {
				return subclass;
			}	
		}
		throw new IllegalArgumentException(String.format(
				"No class found for attribute type parameter %s, cannot instantiate.", typeParameter.getName()));
	}
	
	@SuppressWarnings("unchecked")
	private Attribute<T> findSavedValues() {
		try {
			return (Attribute<T>) attributeService.findByName(editingAttribute.getName(), false);
		} catch (YPCException e) {
			logger.error(e.getMessage(), e);
			throw new IllegalStateException(e);
		} 
	}
	
	public Attribute<T> getSavedAttribute() {
		return savedAttribute;
	}
	
	@Override
	public Attribute<T> getInput() {
		Attribute<T> attribute = getAttribute().clone();
		attribute.getValues().clear();
		
		for (T value : getInputValues()) {
			attribute.addValue(null, value);
		}
		return attribute;
	}
	
	protected abstract Collection<T> getInputValues();
	
	protected abstract void setInitialValues();

}
