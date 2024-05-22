package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.yourpc.model.ItemType;

public class ItemTypeListCellRenderer
extends DefaultListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5516921680737029246L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		ItemType<?> itemState = (ItemType<?>) value;
		String valueString;
		
		if (itemState.getId() == null) {
			valueString = "Select a type...";
		} else {
			valueString = itemState.getName();
		}
		
		return super.getListCellRendererComponent(list, valueString, index, isSelected, cellHasFocus);
	}
}
