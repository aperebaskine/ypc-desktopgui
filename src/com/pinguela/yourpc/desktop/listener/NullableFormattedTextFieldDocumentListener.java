package com.pinguela.yourpc.desktop.listener;

import javax.swing.JFormattedTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NullableFormattedTextFieldDocumentListener 
implements DocumentListener {
	
	private JFormattedTextField textField;
	
	public NullableFormattedTextFieldDocumentListener(JFormattedTextField textField) {
		this.textField = textField;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		if (textField.getText().equals("")) {
			textField.setValue(null);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}

}
