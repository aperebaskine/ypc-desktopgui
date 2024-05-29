package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.yourpc.desktop.constants.EmployeeTableConstants;
import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.model.Employee;

public class EmployeeTableCellRenderer 
extends DefaultTableCellRenderer
implements EmployeeTableConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2935566157851244188L;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Employee employee = (Employee) value;
		Object columnValue = null;
		
		switch (column) {
		case ID_COLUMN_INDEX:
			columnValue = employee.getId();
			break;
		case USERNAME_COLUMN_INDEX:
			columnValue = employee.getUsername();
			break;
		case FIRST_NAME_COLUMN_INDEX:
			columnValue = employee.getFirstName();
			break;
		case LAST_NAMES_COLUMN_INDEX:
			columnValue = SwingUtils.formatLastNames(employee);
			break;
		case DOCUMENT_NUMBER_COLUMN_INDEX:
			columnValue = employee.getDocumentNumber();
			break;
		case PHONE_NUMBER_COLUMN_INDEX:
			columnValue = employee.getPhoneNumber();
			break;
		case EMAIL_COLUMN_INDEX:
			columnValue = employee.getEmail();
			break;
		}
		
		return super.getTableCellRendererComponent(table, columnValue, isSelected, hasFocus, row, column);
	}

}
