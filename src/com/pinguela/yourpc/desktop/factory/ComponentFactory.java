package com.pinguela.yourpc.desktop.factory;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Constructor;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Collection;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.text.NumberFormatter;

import com.pinguela.yourpc.desktop.components.ExtendedDateChooser;
import com.pinguela.yourpc.desktop.util.ReflectionUtils;
import com.pinguela.yourpc.desktop.util.SwingUtils;

public class ComponentFactory {

	private static final String RENDERER_PACKAGE = "com.pinguela.yourpc.desktop.renderer";

	private static final String FOREGROUND_PROPERTY = "foreground";
	private static final Color TEXT_FIELD_FOREGROUND = UIManager.getColor("TextField.foreground");
	private static final PropertyChangeListener DATE_CHOOSER_FOREGROUND_LISTENER = (evt) -> {
		if (evt.getNewValue().equals(Color.BLACK) || !((JTextField) evt.getSource()).isEditable()) {
			((Component) evt.getSource()).setForeground(TEXT_FIELD_FOREGROUND);
		}
	};

	/**
	 * Create a combo box containing the elements contained within the collection, with a blank instance of the object 
	 * as the first value. Instantiate a &lt;TargetClassName&gt;ListCellRenderer from the renderer 
	 * package using the default constructor, and set it as the default renderer.
	 * @param <T> Target type parameter for the combo box and list cell renderer
	 * @param content Collection containing the elements to be added to the combo box model
	 * @param targetClass Class of the elements to be added to the combo box model
	 * @param constructorParameters Constructor arguments required to create an instance of the target class
	 * @return The resulting combo box instance
	 */
	@SuppressWarnings("unchecked") 
	public static <T> JComboBox<T> createComboBox(Collection<T> content, Class<? super T> targetClass) {
		JComboBox<T> comboBox = new JComboBox<T>();

		try {
			Constructor<?> rendererConstructor = Object.class.equals(targetClass)?
					DefaultListCellRenderer.class.getConstructor() :
						ReflectionUtils.getConstructor(Class.forName(
								String.format("%s.%sListCellRenderer", RENDERER_PACKAGE, targetClass.getSimpleName())));

			comboBox.setRenderer((ListCellRenderer<T>) rendererConstructor.newInstance());
		} catch (Exception e) {
			throw new IllegalStateException(String.format("Exception thrown while creating instance: %s", e.getMessage()), e);
		}

		ComboBoxModel<T> model = SwingUtils.createComboBoxModel(content, targetClass);
		comboBox.setModel(model);	
		return comboBox;
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
