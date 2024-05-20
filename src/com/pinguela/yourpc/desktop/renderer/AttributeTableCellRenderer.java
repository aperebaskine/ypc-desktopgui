package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.yourpc.desktop.constants.AttributeTableConstants;
import com.pinguela.yourpc.desktop.util.AttributeUtils;
import com.pinguela.yourpc.model.Attribute;

public class AttributeTableCellRenderer 
extends DefaultTableCellRenderer 
implements AttributeTableConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3701485012074252858L;
	
	public AttributeTableCellRenderer() {
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Attribute<?> attribute = (Attribute<?>) value;
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
