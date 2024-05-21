package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.yourpc.desktop.constants.CustomerTableConstants;
import com.pinguela.yourpc.model.Customer;

public class CustomerTableCellRenderer 
extends DefaultTableCellRenderer
implements CustomerTableConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8966795309404312716L;

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
			columnValue = 
			customer.getLastName2() == null ?
					customer.getLastName1() :
						String.join(" ", customer.getLastName1(), customer.getLastName2());
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
