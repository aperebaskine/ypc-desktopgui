package com.pinguela.yourpc.desktop.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class InputPane 
extends JPanel 
implements YPCComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8995455659391296114L;
	
	private JLabel messageLabel;
	private JPanel contentPane;
	private ActionPane actionPane;
	
	public InputPane() {

		setLayout(new BorderLayout(0, 0));
		
		JPanel messagePanel = new JPanel();
		add(messagePanel, BorderLayout.NORTH);
		
		Component messagePanelLeftHorizontalStrut = Box.createHorizontalStrut(20);
		messagePanelLeftHorizontalStrut.setMinimumSize(new Dimension(40, 0));
		messagePanel.add(messagePanelLeftHorizontalStrut);
		
		messageLabel = new JLabel();
		messagePanel.add(messageLabel);
		
		Component messagePanelRightHorizontalStrut = Box.createHorizontalStrut(20);
		messagePanelRightHorizontalStrut.setMinimumSize(new Dimension(40, 0));
		messagePanel.add(messagePanelRightHorizontalStrut);
		
		contentPane = initializeContentPane();
		add(contentPane, BorderLayout.CENTER);
		
		actionPane = new ActionPane();
		add(actionPane, BorderLayout.SOUTH);
	}

	public final void setMessage(String message) {
		messageLabel.setText(message);
	}
	
	protected abstract JPanel initializeContentPane();
	
}
