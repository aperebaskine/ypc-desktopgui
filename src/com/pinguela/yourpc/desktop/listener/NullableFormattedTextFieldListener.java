package com.pinguela.yourpc.desktop.listener;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFormattedTextField;

public class NullableFormattedTextFieldListener 
extends FocusAdapter {
	
	private JFormattedTextField textField;
	
	public NullableFormattedTextFieldListener(JFormattedTextField textField) {
		this.textField = textField;
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		if (textField.getText().isEmpty()) {
			textField.setValue(null);
		}
	}

}
