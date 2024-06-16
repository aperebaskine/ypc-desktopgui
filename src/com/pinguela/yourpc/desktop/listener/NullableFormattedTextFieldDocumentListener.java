package com.pinguela.yourpc.desktop.listener;

import javax.swing.JFormattedTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

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
		Document d = e.getDocument();
		
		if (e.getLength() > 1 || e.getOffset() > 0) {
			return;
		}
		
		try {
			if ("\n".equals(d.getText(e.getOffset(), e.getLength()))) {
				textField.setValue(null);
			}
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}

}
