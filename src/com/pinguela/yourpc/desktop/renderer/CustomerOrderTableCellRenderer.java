package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.yourpc.desktop.constants.CustomerOrderTableConstants;
import com.pinguela.yourpc.desktop.constants.DBConstants;
import com.pinguela.yourpc.desktop.util.FormattingUtils;
import com.pinguela.yourpc.model.CustomerOrder;

@SuppressWarnings("serial")
public class CustomerOrderTableCellRenderer
extends DefaultTableCellRenderer
implements CustomerOrderTableConstants {

	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        CustomerOrder order = (CustomerOrder) value;
        Object columnValue = null;

        switch (column) {
            case ID_COLUMN_INDEX:
                columnValue = order.getId();
                break;
            case STATE_COLUMN_INDEX:
                columnValue = DBConstants.ORDER_STATES.get(order.getState()).getName();
                break;
            case CUSTOMER_ID_COLUMN_INDEX:
                columnValue = order.getCustomerId();
                break;
            case DATE_COLUMN_INDEX:
                columnValue = FormattingUtils.formatDateTime(order.getOrderDate());
                break;
            case AMOUNT_COLUMN_INDEX:
                columnValue = order.getTotalPrice();
                break;
        }

        return super.getTableCellRendererComponent(table, columnValue, isSelected, hasFocus, row, column);
    }
}

