package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.yourpc.model.ItemType;

@SuppressWarnings("serial")
public class ItemTypeListCellRenderer
extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		ItemType<?> itemState = (ItemType<?>) value;
		String valueString;
		
		if (itemState == null) {
			valueString = "Select a type...";
		} else {
			valueString = itemState.getName();
		}
		
		return super.getListCellRendererComponent(list, valueString, index, isSelected, cellHasFocus);
	}
}
