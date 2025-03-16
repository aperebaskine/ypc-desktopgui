package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.yourpc.desktop.constants.AttributeTableConstants;
import com.pinguela.yourpc.desktop.util.AttributeUtils;
import com.pinguela.yourpc.model.dto.AttributeDTO;

@SuppressWarnings("serial")
public class AttributeTableCellRenderer 
extends DefaultTableCellRenderer 
implements AttributeTableConstants {
	
	public AttributeTableCellRenderer() {
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		AttributeDTO<?> attribute = (AttributeDTO<?>) value;
		Object columnValue = null;

		switch (column) {
		case NAME_COLUMN_INDEX:
			columnValue = attribute.getName();
			break;
		case VALUE_COLUMN_INDEX:
			columnValue = AttributeUtils.formatValueList(attribute);
			break;
		}
		return super.getTableCellRendererComponent(table, columnValue, isSelected, hasFocus, row, column);
	}

}
