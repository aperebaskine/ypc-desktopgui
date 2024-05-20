package com.pinguela.yourpc.desktop.actions;

import com.pinguela.yourpc.desktop.YPCWindow;

public class LogoutAction 
extends YPCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -93330937213058870L;
	
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
