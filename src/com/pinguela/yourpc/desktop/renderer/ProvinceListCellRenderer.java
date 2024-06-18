package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.yourpc.model.Province;

@SuppressWarnings("serial")
public class ProvinceListCellRenderer
extends DefaultListCellRenderer {
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		Province province = (Province) value;
		String valueString;
		
		if (province.getId() == null) {
			valueString = "Select a province...";
		} else {
			valueString = province.getName();
		}
		
		return super.getListCellRendererComponent(list, valueString, index, isSelected, cellHasFocus);
	}

}
