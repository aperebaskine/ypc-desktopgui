package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.yourpc.desktop.constants.CustomerAddressTableConstants;
import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.model.Address;

public class CustomerAddressTableCellRenderer 
extends DefaultTableCellRenderer 
implements CustomerAddressTableConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8433379604113474071L;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {

		Address a = (Address) value;
		Object columnValue = null;
		
		switch (column) {
		case ID_COLUMN_INDEX:
			columnValue = a.getId();
			break;
		case CREATION_DATE_COLUMN_INDEX:
			columnValue = SwingUtils.formatDateTime(a.getCreationDate());
			break;
		}
		
		return super.getTableCellRendererComponent(table, columnValue, isSelected, hasFocus, row, column);
	}

}
