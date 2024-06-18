package com.pinguela.yourpc.desktop.components;

import java.awt.ComponentOrientation;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.pinguela.yourpc.desktop.actions.LogoutAction;
import com.pinguela.yourpc.desktop.actions.OpenUserProfileDialogAction;

@SuppressWarnings("serial")
public class UserPopupMenu 
extends JPopupMenu {

	public UserPopupMenu() {
		super();
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		JMenuItem profileMenuItem = new JMenuItem(new OpenUserProfileDialogAction());
		add(profileMenuItem);

		JMenuItem logOutMenuItem = new JMenuItem(new LogoutAction());
		add(logOutMenuItem);
	}
	
}
