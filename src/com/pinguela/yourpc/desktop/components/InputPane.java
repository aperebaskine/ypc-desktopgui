package com.pinguela.yourpc.desktop.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.pinguela.yourpc.desktop.actions.ConfirmInputAction;
import com.pinguela.yourpc.desktop.actions.ExitWindowAction;

@SuppressWarnings("serial")
public abstract class InputPane<T> 
extends JPanel 
implements YPCComponent {
	
	public static final int CONFIRM = 0;
	public static final int CANCEL = 1;
	
	private int exitCode = 1;
	
	private JLabel messageLabel;
	private JPanel contentPane;
	private ActionPane actionPane;
	
	public InputPane(String message) {

		setBorder(new EmptyBorder(8, 8, 8, 8));
		setLayout(new BorderLayout(0, 0));
		
		JPanel messagePanel = new JPanel();
		add(messagePanel, BorderLayout.NORTH);
		
		Component messagePanelLeftHorizontalStrut = Box.createHorizontalStrut(20);
		messagePanelLeftHorizontalStrut.setMinimumSize(new Dimension(40, 0));
		messagePanel.add(messagePanelLeftHorizontalStrut);
		
		messageLabel = new JLabel(message);
		messagePanel.add(messageLabel);
		
		Component messagePanelRightHorizontalStrut = Box.createHorizontalStrut(20);
		messagePanelRightHorizontalStrut.setMinimumSize(new Dimension(40, 0));
		messagePanel.add(messagePanelRightHorizontalStrut);
		
		contentPane = initializeContentPane();
		add(contentPane, BorderLayout.CENTER);
		
		actionPane = new ActionPane();
		add(actionPane, BorderLayout.SOUTH);
		actionPane.addAction(new ExitWindowAction("Cancel", this));
		actionPane.addAction(new ConfirmInputAction(this));
	}
	
	protected abstract JPanel initializeContentPane();
	
	public abstract T getInput();
	
	public int getExitCode() {
		return exitCode;
	}
	
	public void setExitCode(int exitCode) {
		this.exitCode = exitCode;
	}
	
}
