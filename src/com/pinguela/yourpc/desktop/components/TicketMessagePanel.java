package com.pinguela.yourpc.desktop.components;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.model.TicketMessage;

@SuppressWarnings("serial")
public class TicketMessagePanel 
extends JPanel {
	
	public TicketMessagePanel(TicketMessage message) {
		setBorder(new EmptyBorder(4, 4, 4, 4));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel metadataLabel = new JLabel(formatMetadata(message));
		add(metadataLabel);
		
		JTextArea messageTextArea = new JTextArea(message.getText());
		messageTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
		messageTextArea.setWrapStyleWord(true);
		messageTextArea.setEditable(false);
		add(messageTextArea);
	}
	
	private String formatMetadata(TicketMessage message) {	
		
		if (message.getTimestamp() == null) {
			return String.format("You - unsent");
		}
		
		StringBuilder sender = new StringBuilder();
		
		if (message.getLastName2() == null) {
			sender.append(String.join(" ", message.getFirstName(), message.getLastName1()));
		} else {
			sender.append(String.join(" ", message.getFirstName(), message.getLastName1(), message.getLastName2()));
		}
		
		if (message.getEmployeeId() != null) {
			sender.append(" (YourPC Customer Support)");
		}
		
		return String.format("%s - %s", sender, SwingUtils.formatDateTime(message.getTimestamp()));
	}

}
