package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.yourpc.desktop.constants.CustomerOrderTableConstants;
import com.pinguela.yourpc.desktop.constants.DBConstants;
import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.model.CustomerOrder;

public class CustomerOrderTableCellRenderer
extends DefaultTableCellRenderer
implements CustomerOrderTableConstants {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8805619131464854429L;

	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        CustomerOrder order = (CustomerOrder) value;
        Object columnValue = null;

        switch (column) {
            case ID_COLUMN_INDEX:
                columnValue = order.getId();
                break;
            case STATE_COLUMN_INDEX:
                columnValue = DBConstants.ORDER_STATES.get(order.getState());
                break;
            case CUSTOMER_ID_COLUMN_INDEX:
                columnValue = order.getCustomerId();
                break;
            case DATE_COLUMN_INDEX:
                columnValue = SwingUtils.formatDateTime(order.getOrderDate());
                break;
            case TRACKING_NUMBER_COLUMN_INDEX:
                columnValue = order.getTrackingNumber();
                break;
            case AMOUNT_COLUMN_INDEX:
                columnValue = order.getTotalPrice();
                break;
        }

        return super.getTableCellRendererComponent(table, columnValue, isSelected, hasFocus, row, column);
    }
}

