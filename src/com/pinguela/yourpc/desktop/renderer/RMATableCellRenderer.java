package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.yourpc.desktop.constants.DBConstants;
import com.pinguela.yourpc.desktop.constants.RMATableConstants;
import com.pinguela.yourpc.desktop.util.FormattingUtils;
import com.pinguela.yourpc.model.RMA;

@SuppressWarnings("serial")
public class RMATableCellRenderer
extends DefaultTableCellRenderer
implements RMATableConstants {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		RMA rma = (RMA) value;
		Object columnValue = null;

		switch (column) {
		case ID_COLUMN_INDEX:
			columnValue = rma.getId();
			break;
		case CUSTOMER_ID_COLUMN_INDEX:
			columnValue = rma.getCustomerId();
			break;
		case STATE_COLUMN_INDEX:
			columnValue = DBConstants.RMA_STATES.get(rma.getState()).getName();
			break;
		case CREATION_DATE_COLUMN_INDEX:
			columnValue = FormattingUtils.formatDate(rma.getCreationDate());
			break;
		case TRACKING_NUMBER_COLUMN_INDEX:
			columnValue = rma.getTrackingNumber();
			break;
		}

		return super.getTableCellRendererComponent(table, columnValue, isSelected, hasFocus, row, column);
	}

}
