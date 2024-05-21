package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.yourpc.desktop.constants.ProductTableConstants;
import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.model.Product;
import com.pinguela.yourpc.util.CategoryUtils;

public class ProductTableCellRenderer 
extends DefaultTableCellRenderer 
implements ProductTableConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4317028850422353727L;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {

		Product p = (Product) value;
		Object columnValue = null;
		
		switch (column) {
		case ID_COLUMN_INDEX:
			columnValue = p.getId();
			break;
		case NAME_COLUMN_INDEX:
			columnValue = p.getName();
			break;
		case CATEGORY_COLUMN_INDEX:
			columnValue = CategoryUtils.CATEGORIES.get(p.getCategoryId()).getName();
			break;
		case LAUNCH_DATE_COLUMN_INDEX:
			columnValue = SwingUtils.formatDate(p.getLaunchDate());
			break;
		case STOCK_COLUMN_INDEX:
			columnValue = p.getStock();
			break;
		case PURCHASE_PRICE_COLUMN_INDEX:
			columnValue = p.getPurchasePrice();
			break;
		case SALE_PRICE_COLUMN_INDEX:
			columnValue = p.getSalePrice();
		}
		
		return super.getTableCellRendererComponent(table, columnValue, isSelected, hasFocus, row, column);
	}

}
