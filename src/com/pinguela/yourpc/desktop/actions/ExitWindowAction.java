package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;
import java.awt.Window;

import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.constants.Icons;

@SuppressWarnings("serial")
public class ExitWindowAction 
extends YPCAction {
	
	private Component component;
	
	public ExitWindowAction(String name, Component component) {
		super(name, Icons.CANCEL_ICON);
		this.component = component;
	}
	
	public ExitWindowAction(Component component) {
		super("Exit", Icons.CANCEL_ICON);
		this.component = component;
	}
	
	@Override
	protected void doAction() {
		Window window = SwingUtilities.getWindowAncestor(component);
		window.dispose();
	}

}
