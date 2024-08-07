package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.yourpc.desktop.constants.CustomerTableConstants;
import com.pinguela.yourpc.desktop.util.FormattingUtils;
import com.pinguela.yourpc.model.Customer;

@SuppressWarnings("serial")
public class CustomerTableCellRenderer 
extends DefaultTableCellRenderer
implements CustomerTableConstants {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Customer customer = (Customer) value;
		Object columnValue = null;

		switch (column) {
		case ID_COLUMN_INDEX:
			columnValue = customer.getId();
			break;
		case FIRST_NAME_COLUMN_INDEX:
			columnValue = customer.getFirstName();
			break;
		case LAST_NAMES_COLUMN_INDEX:
			columnValue = FormattingUtils.formatLastNames(customer);
			break;
		case DOCUMENT_NUMBER_COLUMN_INDEX:
			columnValue = customer.getDocumentNumber();
			break;
		case PHONE_NUMBER_COLUMN_INDEX:
			columnValue = customer.getPhoneNumber();
			break;
		case EMAIL_COLUMN_INDEX:
			columnValue = customer.getEmail();
			break;
		}

		return super.getTableCellRendererComponent(table, columnValue, isSelected, hasFocus, row, column);
	}

}
