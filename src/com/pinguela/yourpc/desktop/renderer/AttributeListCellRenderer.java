package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.yourpc.model.AttributeValue;

public class AttributeListCellRenderer 
extends DefaultListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1153418824593398316L;
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		String attributeValue = ((AttributeValue<?>) value).getValue().toString();
		
		return super.getListCellRendererComponent(list, attributeValue, index, isSelected, cellHasFocus);
	}

}
