package com.pinguela.yourpc.desktop.components;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.pinguela.yourpc.desktop.YPCWindow;
import com.pinguela.yourpc.model.TicketMessage;

@SuppressWarnings("serial")
public class TicketMessageInputPane 
extends InputPane<TicketMessage> {
	
	private JTextArea messageTextArea;
	
	public TicketMessageInputPane() {
		super("Enter message:");
		setPreferredSize(new Dimension(480, 360));
	}

	@Override
	protected JPanel initializeContentPane() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		
		messageTextArea = new JTextArea();
		messageTextArea.setWrapStyleWord(true);
		scrollPane.setViewportView(messageTextArea);
		return contentPanel;
	}
	
	public TicketMessage getInput() {
		TicketMessage tm = new TicketMessage();
		tm.setEmployeeId(YPCWindow.getInstance().getAuthenticatedUser().getId());
		tm.setText(messageTextArea.getText());
		return tm;
	}

}
