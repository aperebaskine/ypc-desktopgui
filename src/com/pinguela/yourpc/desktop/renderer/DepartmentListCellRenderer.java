package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.yourpc.model.Department;

@SuppressWarnings("serial")
public class DepartmentListCellRenderer 
extends DefaultListCellRenderer {
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		Department d = (Department) value;
		String valueString = null;

		if (d.getId() == null) {
			valueString = "Select a department...";
		} else {
			valueString = d.getName();
		}	
		return super.getListCellRendererComponent(list, valueString, index, isSelected, cellHasFocus);
	}

}
