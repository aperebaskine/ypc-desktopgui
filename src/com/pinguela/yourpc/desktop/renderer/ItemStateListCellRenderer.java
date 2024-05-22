package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.pinguela.yourpc.model.ItemState;

public class ItemStateListCellRenderer 
extends DefaultListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -496226041329808777L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		ItemState<?> itemState = (ItemState<?>) value;
		String valueString;
		
		if (itemState.getId() == null) {
			valueString = "Select a state...";
		} else {
			valueString = itemState.getName();
		}
		
		return super.getListCellRendererComponent(list, valueString, index, isSelected, cellHasFocus);
	}
}
