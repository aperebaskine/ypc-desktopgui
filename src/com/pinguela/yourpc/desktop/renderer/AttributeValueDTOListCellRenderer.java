package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.yourpc.model.dto.AttributeValueDTO;

@SuppressWarnings("serial")
public class AttributeValueDTOListCellRenderer 
extends DefaultListCellRenderer {
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		AttributeValueDTO<?> attributeValue = (AttributeValueDTO<?>) value;
		String valueString;
		
		if (attributeValue == null) {
			valueString = "Select a value...";
		} else {
			valueString = (String) attributeValue.getValue();
		}
		
		return super.getListCellRendererComponent(list, valueString, index, isSelected, cellHasFocus);
	}

}
