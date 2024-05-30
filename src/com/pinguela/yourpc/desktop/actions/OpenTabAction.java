package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import com.pinguela.yourpc.desktop.YPCWindow;

public abstract class OpenTabAction extends YPCAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1477001024461349414L;
	
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
