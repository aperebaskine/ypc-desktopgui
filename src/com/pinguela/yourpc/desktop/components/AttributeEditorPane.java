package com.pinguela.yourpc.desktop.components;

import java.util.Arrays;
import java.util.Collection;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.config.ConfigManager;
import com.pinguela.yourpc.desktop.util.LocaleUtils;
import com.pinguela.yourpc.desktop.util.ReflectionUtils;
import com.pinguela.yourpc.model.dto.AttributeDTO;
import com.pinguela.yourpc.service.AttributeService;
import com.pinguela.yourpc.service.impl.AttributeServiceImpl;

@SuppressWarnings("serial")
public abstract class AttributeEditorPane<T>
extends JPanel {

	private static Logger logger = LogManager.getLogger(AttributeEditorPane.class);

	private static final String EDITOR_PANES_PNAME = "ui.attribute.editor.panes";
	private static final Collection<Class<?>> EDITOR_CLASSES;

	static {
		String packageName = AttributeEditorPane.class.getPackage().getName();
		try {
			EDITOR_CLASSES = ReflectionUtils.loadClassesFromPackage(packageName, Arrays.asList(getSubclassNames()));
		} catch (Throwable t) {
			logger.fatal(String.format("Exception thrown while loading classes during initialization. Message: %s",
					t.getMessage()), t);
			throw new ExceptionInInitializerError(t);
		}
	}

	private static String[] getSubclassNames() {
		return ConfigManager.getParameter(EDITOR_PANES_PNAME).split(ConfigManager.DELIMITER);
	}

	private AttributeService attributeService;

	private AttributeDTO<T> editingAttribute;
	private AttributeDTO<T> savedAttribute;

	private boolean showUnassignedValues;

	/**
	 * Unused constructor, required for panel rendering within a WindowBuilder designer.
	 */
	@SuppressWarnings({"unused", "unchecked"})
	private AttributeEditorPane() {
		this(AttributeDTO.getInstance((Class<T>) String.class), null, AttributeService.RETURN_UNASSIGNED_VALUES);
	}

	protected AttributeEditorPane(AttributeDTO<T> attribute, Integer handlingMode, boolean showUnassignedValues) {
		this.attributeService = new AttributeServiceImpl();
		this.editingAttribute = attribute;

		this.showUnassignedValues = showUnassignedValues;

		this.savedAttribute = findSavedValues();
	}

	public AttributeDTO<T> getEditingAttribute() {
		return editingAttribute;
	}

	public AttributeDTO<T> getSavedAttribute() {
		return savedAttribute;
	}

	protected boolean shouldShowUnassignedValues() {
		return showUnassignedValues;
	}

	public static final <T> AttributeEditorPane<T> getInstance(AttributeDTO<T> attribute) {
		return getInstance(attribute, null, AttributeService.RETURN_UNASSIGNED_VALUES);
	}

	public static final <T> AttributeEditorPane<T> getInstance(AttributeDTO<T> attribute, boolean showUnassignedValues) {
		return getInstance(attribute, null, showUnassignedValues);
	}

	@SuppressWarnings("unchecked")
	public static final <T> AttributeEditorPane<T> getInstance(AttributeDTO<T> attribute, 
			Integer handlingMode, boolean showUnassignedValues) {

		Class<?> instanceClass = getClassMatchingTypeParameter(attribute.getTypeParameterClass());
		try {
			return (AttributeEditorPane<T>) instanceClass
					.getDeclaredConstructor(AttributeDTO.class, Integer.class, boolean.class)
					.newInstance(attribute, handlingMode, showUnassignedValues);
		} catch (Exception e) {
			String message = String.format("Exception thrown while instantiating %s. Message: %s",
					instanceClass.getName(), e.getMessage());
			logger.error(message, e);
			throw new IllegalStateException(message, e);
		} 
	}

	private static final Class<?> getClassMatchingTypeParameter(Class<?> typeParameter) {

		for (Class<?> subclass : EDITOR_CLASSES) {
			if (ReflectionUtils.isTypeParameterAssignable(typeParameter, subclass)) {
				return subclass;
			}	
		}
		throw new IllegalArgumentException(String.format(
				"No class found for attribute type parameter %s, cannot instantiate.", typeParameter.getName()));
	}

	@SuppressWarnings("unchecked")
	private AttributeDTO<T> findSavedValues() {
		try {
			return (AttributeDTO<T>) attributeService.findByName(editingAttribute.getName(), LocaleUtils.getLocale(), shouldShowUnassignedValues(), null);
		} catch (YPCException e) {
			logger.error(e.getMessage(), e);
			throw new IllegalStateException(e);
		} 
	}

	protected abstract Collection<T> getEditorValues();

}
