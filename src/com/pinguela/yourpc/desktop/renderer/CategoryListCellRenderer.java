package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.yourpc.model.Category;

public class CategoryListCellRenderer
extends DefaultListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6617147380548790845L;

	public CategoryListCellRenderer() {
		super();
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
