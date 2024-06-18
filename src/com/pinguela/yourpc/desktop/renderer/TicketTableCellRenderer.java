package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.pinguela.yourpc.desktop.constants.DBConstants;
import com.pinguela.yourpc.desktop.constants.TicketTableConstants;
import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.model.Ticket;
import com.pinguela.yourpc.model.TicketMessage;

@SuppressWarnings("serial")
public class TicketTableCellRenderer
extends DefaultTableCellRenderer
implements TicketTableConstants {
	
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Ticket ticket = (Ticket) value;
        Object columnValue = null;

        switch (column) {
            case ID_COLUMN_INDEX:
                columnValue = ticket.getId();
                break;
            case TYPE_COLUMN_INDEX:
                columnValue = DBConstants.TICKET_TYPES.get(ticket.getType()).getName();
                break;
            case STATE_COLUMN_INDEX:
                columnValue = DBConstants.TICKET_STATES.get(ticket.getState()).getName();
                break;
            case CUSTOMER_ID_COLUMN_INDEX:
                columnValue = ticket.getCustomerId();
                break;
            case CREATION_DATE_COLUMN_INDEX:
                columnValue = SwingUtils.formatDateTime(ticket.getCreationDate());
                break;
            case LAST_UPDATE_DATE_COLUMN_INDEX:
            	List<TicketMessage> messages = ticket.getMessageList();
                columnValue = SwingUtils.formatDateTime(messages.get(messages.size()-1).getTimestamp());
                break;
        }
        
        return super.getTableCellRendererComponent(table, columnValue, isSelected, hasFocus, row, column);
    }

}
