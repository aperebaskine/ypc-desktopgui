package com.pinguela.yourpc.desktop.actions;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import com.pinguela.yourpc.desktop.components.UserPopupMenu;
import com.pinguela.yourpc.desktop.constants.Icons;

public class OpenUserPopupMenuAction 
extends YPCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3543006462085100144L;
	
	private UserPopupMenu menu;

	public OpenUserPopupMenuAction() {
		super(Icons.USER_ICON);
		menu = new UserPopupMenu();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton userMenuButton = (JButton) e.getSource();

		EventQueue.invokeLater(() -> {
			menu.setVisible(true);
			int x = userMenuButton.getX()+userMenuButton.getWidth()-menu.getWidth();
			int y = userMenuButton.getY()+userMenuButton.getHeight();
			menu.show(userMenuButton, x, y);
		});
	}

	@Override
	protected void doAction() {	
	}

}
