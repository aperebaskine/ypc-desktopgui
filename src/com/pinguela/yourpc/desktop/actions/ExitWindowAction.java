package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;
import java.awt.Window;

import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.constants.Icons;

public class ExitWindowAction 
extends YPCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5170390935857403076L;
	
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
