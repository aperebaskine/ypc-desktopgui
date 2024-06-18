package com.pinguela.yourpc.desktop.actions;

import com.pinguela.yourpc.desktop.YPCWindow;

@SuppressWarnings("serial")
public class LogoutAction 
extends YPCAction {
	
	public LogoutAction() {
		this("Log out");
	}

	public LogoutAction(String name) {
		super(name);
	}

	@Override
	protected void doAction() {
		YPCWindow.getInstance().logout();
	}

}
