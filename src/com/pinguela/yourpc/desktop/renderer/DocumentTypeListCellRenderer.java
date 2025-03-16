package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.yourpc.model.DocumentType;

@SuppressWarnings("serial")
public class DocumentTypeListCellRenderer
extends DefaultListCellRenderer {
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		DocumentType documentType = (DocumentType) value;
		String valueString;
		
		if (documentType == null) {
			valueString = "Select a document type...";
		} else {
			valueString = documentType.getName();
		}
		
		return super.getListCellRendererComponent(list, valueString, index, isSelected, cellHasFocus);
	}

}
