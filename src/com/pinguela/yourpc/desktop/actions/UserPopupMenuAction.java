package com.pinguela.yourpc.desktop.actions;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPopupMenu;

import com.pinguela.yourpc.desktop.components.UserPopupMenu;
import com.pinguela.yourpc.desktop.constants.Icons;

public class UserPopupMenuAction 
extends YPCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3543006462085100144L;

	public UserPopupMenuAction() {
		super(Icons.USER_ICON);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton userMenuButton = (JButton) e.getSource();
		JPopupMenu userPopupMenu = new UserPopupMenu();

		EventQueue.invokeLater(() -> {
			userPopupMenu.setVisible(true);
			int x = userMenuButton.getX()+userMenuButton.getWidth()-userPopupMenu.getWidth();
			int y = userMenuButton.getY()+userMenuButton.getHeight();
			userPopupMenu.show(userMenuButton, x, y);
		});
	}

	@Override
	protected void doAction() {	
	}

}
