package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.yourpc.model.Category;

@SuppressWarnings("serial")
public class CategoryListCellRenderer
extends DefaultListCellRenderer {

	public CategoryListCellRenderer() {
	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		Category c = (Category) value;
		String valueString = null;

		if (c.getId() == null) {
			valueString = "Select a category...";
		} else {
			valueString = String.format("%d - %s", c.getId(), c.getName());
		}	
		return super.getListCellRendererComponent(list, valueString, index, isSelected, cellHasFocus);
	}

}
