package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.yourpc.model.Attribute;

public class AttributeListCellRenderer 
extends DefaultListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1153418824593398316L;
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		Attribute<?> attribute = (Attribute<?>) value;
		String valueString;
		
		if (attribute.getName() == null) {
			valueString = "Select an attribute...";
		} else {
			valueString = attribute.getName();
		}
		
		return super.getListCellRendererComponent(list, valueString, index, isSelected, cellHasFocus);
	}

}
