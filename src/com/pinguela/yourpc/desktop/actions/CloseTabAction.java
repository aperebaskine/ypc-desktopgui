package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import javax.swing.JTabbedPane;

import com.pinguela.yourpc.desktop.constants.Icons;

public class CloseTabAction 
extends YPCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5598887511439804614L;
	
	private JTabbedPane container;
	private Component tab;
	
	public CloseTabAction(JTabbedPane container, Component tab) {
		super(Icons.CANCEL_ICON);
		this.container = container;
		this.tab = tab;
	}
	
	@Override
	protected void doAction() {
		container.remove(tab);
	}

}
