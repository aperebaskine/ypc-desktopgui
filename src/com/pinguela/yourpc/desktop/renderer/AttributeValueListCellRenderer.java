package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.yourpc.model.AttributeValue;

public class AttributeValueListCellRenderer 
extends DefaultListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1153418824593398316L;
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		AttributeValue<?> attributeValue = (AttributeValue<?>) value;
		String valueString;
		
		if (attributeValue.getValue() == null) {
			valueString = "Select a value...";
		} else {
			valueString = (String) attributeValue.getValue();
		}
		
		return super.getListCellRendererComponent(list, valueString, index, isSelected, cellHasFocus);
	}

}
