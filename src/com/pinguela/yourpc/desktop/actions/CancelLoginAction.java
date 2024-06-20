package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

@SuppressWarnings("serial")
public class CancelLoginAction
extends ExitWindowAction {

	public CancelLoginAction(Component component) {
		super(component);
	}
	
	@Override
	protected void doAction() {
		super.doAction();
		System.exit(0);
	}

}
