package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.yourpc.model.ItemState;

@SuppressWarnings("serial")
public class ItemStateListCellRenderer 
extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		ItemState<?> itemState = (ItemState<?>) value;
		String valueString;
		
		if (itemState == null) {
			valueString = "Select a state...";
		} else {
			valueString = itemState.getName();
		}
		
		return super.getListCellRendererComponent(list, valueString, index, isSelected, cellHasFocus);
	}
}
