package com.pinguela.yourpc.desktop.components;

import java.awt.ComponentOrientation;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.pinguela.yourpc.desktop.actions.LogoutAction;

public class UserPopupMenu 
extends JPopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3043465924262820466L;

	public UserPopupMenu() {
		super();
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		JMenuItem profileMenuItem = new JMenuItem("Profile");
		add(profileMenuItem);

		JMenuItem logOutMenuItem = new JMenuItem(new LogoutAction());
		add(logOutMenuItem);
	}
	
}
