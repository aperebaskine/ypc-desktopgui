package com.pinguela.yourpc.desktop.editor;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ClampingSpinnerNumberEditor 
extends JSpinner.NumberEditor 
implements YPCEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7627031255129036951L;

	private Comparable<Object> minValue = null;
	private Comparable<Object> maxValue = null;

	private String formattedMinValue = null;
	private String formattedMaxValue = null;

	/**
	 * Unused constructor required for rendering within a WindowBuilder designer.
	 */
	private ClampingSpinnerNumberEditor() {
		super(new JSpinner());
	}	

	public ClampingSpinnerNumberEditor(JSpinner spinner) {
		super(spinner);
		initialize(spinner);
	}

	public ClampingSpinnerNumberEditor(JSpinner spinner, String decimalFormatPattern) {
		super(spinner, decimalFormatPattern);
		initialize(spinner);
	}

	@SuppressWarnings("unchecked")
	private void initialize(JSpinner spinner) {

		SpinnerNumberModel model = (SpinnerNumberModel) spinner.getModel();

		minValue = (Comparable<Object>) model.getMinimum();
		maxValue = (Comparable<Object>) model.getMaximum();

		formattedMinValue = getFormat().format(minValue);
		formattedMaxValue = getFormat().format(maxValue);

		getTextField().addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				clampValue();
			}
		});
	}

	@Override
	public void commitEdit() throws ParseException {
		
		try {
			super.commitEdit();
		} catch (ParseException pe) { // Input is either out of bounds or not a number
			clampValue();
			throw pe; // Propagate the exception in order to retain the superclass' logic
		}
	}

	private void clampValue() {

		Number value;
		
		try {
			value = (Number) getFormat().parseObject(getTextField().getText());
		} catch (ParseException pe) { // Input is not a number, no action necessary
			return;
		}

		if (minValue.compareTo(value) > 0) {
			getTextField().setText(formattedMinValue);
			getTextField().setValue(minValue);
		} else if (maxValue.compareTo(value) < 0) {
			getTextField().setText(formattedMaxValue);
			getTextField().setValue(maxValue);
		}
	}

}
