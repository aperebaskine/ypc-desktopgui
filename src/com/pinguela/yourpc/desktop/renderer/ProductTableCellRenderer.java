package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.yourpc.desktop.constants.ProductTableConstants;
import com.pinguela.yourpc.desktop.util.FormattingUtils;
import com.pinguela.yourpc.model.Product;
import com.pinguela.yourpc.util.CategoryUtils;

@SuppressWarnings("serial")
public class ProductTableCellRenderer 
extends DefaultTableCellRenderer 
implements ProductTableConstants {
	
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
			columnValue = FormattingUtils.formatDate(p.getLaunchDate());
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
