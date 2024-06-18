package com.pinguela.yourpc.desktop.components;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ActionPane 
extends JPanel 
implements YPCComponent {

	public ActionPane() {
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
		setOpaque(true);
	}
	
	public void addAction(Action action) {
		addAction(action, null, false);
	}
	
	public void addAction(Action action, String actionCommand) {
		addAction(action, actionCommand, false);
	}
	
	public void addAction(Action action, boolean isDefaultForRootPane) {
		addAction(action, null, isDefaultForRootPane);
	}
	
	public void addAction(Action action, String actionCommand, boolean isDefaultForRootPane) {
		JButton actionButton = new JButton(action);
		if (actionCommand != null) {
			actionButton.setActionCommand(actionCommand);
		}
		actionButton.setFocusPainted(true);
		add(actionButton, 0);
		
		if (isDefaultForRootPane) {
			EventQueue.invokeLater(() -> {
				getRootPane().setDefaultButton(actionButton);
			});
		}
	}

}
