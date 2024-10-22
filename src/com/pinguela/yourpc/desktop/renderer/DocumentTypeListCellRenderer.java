package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.yourpc.model.IDType;

@SuppressWarnings("serial")
public class DocumentTypeListCellRenderer
extends DefaultListCellRenderer {
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		IDType documentType = (IDType) value;
		String valueString;
		
		if (documentType.getId() == null) {
			valueString = "Select a document type...";
		} else {
			valueString = documentType.getName();
		}
		
		return super.getListCellRendererComponent(list, valueString, index, isSelected, cellHasFocus);
	}

}
