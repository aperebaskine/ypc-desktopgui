package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.yourpc.model.Department;

public class DepartmentListCellRenderer 
extends DefaultListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7798562949587865760L;
	
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
