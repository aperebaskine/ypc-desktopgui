package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.yourpc.model.dto.CategoryDTO;

@SuppressWarnings("serial")
public class CategoryDTOListCellRenderer
extends DefaultListCellRenderer {

	public CategoryDTOListCellRenderer() {
	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		CategoryDTO c = (CategoryDTO) value;
		String valueString = null;

		if (c == null) {
			valueString = "Select a category...";
		} else {
			Short id = c.getId().shortValue();
			valueString = String.format("%d - %s", id, c.getName());
		}	
		return super.getListCellRendererComponent(list, valueString, index, isSelected, cellHasFocus);
	}

}
