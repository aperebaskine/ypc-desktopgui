package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import com.pinguela.yourpc.desktop.YPCWindow;

@SuppressWarnings("serial")
public abstract class OpenTabAction
extends YPCAction {
	
	private String title;
	
	public OpenTabAction(String title) {
		this.title = title;
	}
	
	@Override
	protected void doAction() {
		YPCWindow.getInstance().addCloseableTab(title, initializeTab());
	}
	
	protected abstract Component initializeTab();

}
