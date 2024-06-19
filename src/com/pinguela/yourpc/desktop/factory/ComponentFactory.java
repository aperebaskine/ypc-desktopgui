package com.pinguela.yourpc.desktop.factory;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Constructor;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.text.NumberFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.yourpc.desktop.components.ExtendedDateChooser;
import com.pinguela.yourpc.desktop.util.ReflectionUtils;

public class ComponentFactory {

	private static final String RENDERER_PACKAGE = "com.pinguela.yourpc.desktop.renderer";

	private static final String FOREGROUND_PROPERTY = "foreground";
	private static final Color TEXT_FIELD_FOREGROUND = UIManager.getColor("TextField.foreground");
	private static final PropertyChangeListener DATE_CHOOSER_FOREGROUND_LISTENER = (evt) -> {
		if (evt.getNewValue().equals(Color.BLACK) || !((JTextField) evt.getSource()).isEditable()) {
			((Component) evt.getSource()).setForeground(TEXT_FIELD_FOREGROUND);
		}
	};
	
	private static Logger logger = LogManager.getLogger(ComponentFactory.class);

	/**
	 * Create a combo box containing the elements contained within the collection, with a blank instance of the object 
	 * as the first value. Instantiate a &lt;TargetClassName&gt;ListCellRenderer from the renderer 
	 * package using the default constructor, and set it as the default renderer.
	 * @param <T> Target type parameter for the combo box and list cell renderer
	 * @param content Collection containing the elements to be added to the combo box model
	 * @param targetClass Class of the elements to be added to the combo box model
	 * @param targetClassInitArgs Constructor arguments required to create an instance of the target class
	 * @return The resulting combo box instance
	 */
	@SuppressWarnings("unchecked") 
	public static <T> JComboBox<T> createComboBox(Collection<T> content, Class<?> targetClass, Object... targetClassInitArgs) {
		JComboBox<T> comboBox = new JComboBox<T>();
		T[] comboBoxValues = (T[]) new Object[content.size()+1];

		try {
			comboBoxValues[0] = (T) getNullObjectOrInstance(targetClass, targetClassInitArgs); // Add blank object as the first value
			Constructor<?> rendererConstructor = ReflectionUtils.getConstructor(
					Class.forName(String.format("%s.%sListCellRenderer", RENDERER_PACKAGE, targetClass.getSimpleName())));
			comboBox.setRenderer((ListCellRenderer<T>) rendererConstructor.newInstance());
		} catch (Exception e) {
			throw new IllegalStateException(String.format("Exception thrown while creating instance: %s", e.getMessage()), e);
		}

		Iterator<T> iterator = content.iterator();
		for (int i = 1; i < comboBoxValues.length; i++) {
			comboBoxValues[i] = iterator.next();
		}

		DefaultComboBoxModel<T> model = new DefaultComboBoxModel<T>(comboBoxValues);
		comboBox.setModel(model);	
		return comboBox;
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T getNullObject(Class<?> targetClass, Object... initArgs) {
		String packageName = targetClass.getPackage().getName();
		String className = targetClass.getSimpleName();
		
		String fullyQualifiedNullSubclassName = 
				String.format("%s.Null%s", packageName, className);
		
		T instance = null;
		
		try {
			instance = (T) Class.forName(fullyQualifiedNullSubclassName)
					.getDeclaredConstructor(ReflectionUtils.loadClassesFromObjects(initArgs))
					.newInstance(initArgs);
		} catch (Exception e) {
			// No action required
		}
		
		return instance;
	}
	
	private static <T> T getNullObjectOrInstance(Class<T> targetClass, Object... initArgs) {
		T object = getNullObject(targetClass, initArgs);
		
		if (object == null) {
			try {
				object = (T) targetClass.getDeclaredConstructor(ReflectionUtils.loadClassesFromObjects(initArgs)).newInstance(initArgs);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new IllegalStateException(
						String.format("Cannot create instance from class %s and arguments %s.", targetClass.getName(), initArgs));
			} 
		}
		
		return object;
	}

	public static JFormattedTextField createNullableNumberTextField(Class<? extends Number> target) {

		NumberFormatter formatter = new NumberFormatter(new DecimalFormat());
		formatter.setValueClass(target);

		JFormattedTextField textField = new JFormattedTextField(formatter);
		// TODO: Create working listener
		// textField.getDocument().addDocumentListener(new NullableFormattedTextFieldDocumentListener(textField));

		return textField;
	}

	public static ExtendedDateChooser getDateChooser() {
		return getDateChooser(null);
	}

	public static ExtendedDateChooser getDateChooser(Calendar c) {

		// TODO Handle visual bug when re-enabling chooser
		ExtendedDateChooser dateChooser = new ExtendedDateChooser(c == null ? null : c.getTime());
		dateChooser.setPreferredSize(new Dimension(96, 22));
		dateChooser.setDateFormatString("dd/MM/yyyy");

		JTextField dateEditor =
				(JTextField) dateChooser.getDateEditor();
		JTextField yearEditor =
				(JTextField) ((JSpinner) dateChooser.getJCalendar().getYearChooser().getSpinner()).getEditor();

		yearEditor.setForeground(TEXT_FIELD_FOREGROUND);
		dateEditor.setForeground(TEXT_FIELD_FOREGROUND);

		dateEditor.addPropertyChangeListener(FOREGROUND_PROPERTY, DATE_CHOOSER_FOREGROUND_LISTENER);
		yearEditor.addPropertyChangeListener(FOREGROUND_PROPERTY, DATE_CHOOSER_FOREGROUND_LISTENER);

		return dateChooser;
	}

}
