package com.pinguela.yourpc.desktop.components;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.model.TicketMessage;

public class TicketMessagePanel 
extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4564950873234265064L;
	
	public TicketMessagePanel(TicketMessage message) {
		setBorder(new EmptyBorder(4, 4, 4, 4));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel metadataLabel = new JLabel(formatMetadata(message));
		add(metadataLabel);
		
		JTextArea messageTextArea = new JTextArea(message.getText());
		messageTextArea.setWrapStyleWord(true);
		add(messageTextArea);
	}
	
	private String formatMetadata(TicketMessage message) {	
		String name;
		
		if (message.getLastName2() == null) {
			name = String.join(" ", message.getFirstName(), message.getLastName1());
		} else {
			name = String.join(" ", message.getFirstName(), message.getLastName1(), message.getLastName2());
		}
		return String.format("%s - %s", name, SwingUtils.formatDateTime(message.getTimestamp()));
	}

}
