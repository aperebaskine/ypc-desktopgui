package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.yourpc.desktop.constants.OrderLineTableConstants;
import com.pinguela.yourpc.model.OrderLine;

public class OrderLineTableCellRenderer 
extends DefaultTableCellRenderer
implements OrderLineTableConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 201205743278727499L;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {

		OrderLine ol = (OrderLine) value;
		Object columnValue = null;
		
		switch (column) {
		case PRODUCT_COLUMN_INDEX:
			columnValue = ol.getProductName();
			break;
		case QUANTITY_COLUMN_INDEX:
			columnValue = ol.getQuantity();
			break;
		case UNIT_PRICE_COLUMN_INDEX:
			columnValue = ol.getSalePrice();
			break;
		}
		
		return super.getTableCellRendererComponent(table, columnValue, isSelected, hasFocus, row, column);
	}

}
