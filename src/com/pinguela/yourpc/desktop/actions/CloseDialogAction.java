package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class CloseDialogAction 
extends YPCAction {
	
	private Component dialogComponent;
	
	public CloseDialogAction(Icon icon, Component dialogComponent) {
		this(null, icon, dialogComponent);
	}
	
	public CloseDialogAction(String name, Icon icon, Component dialogComponent) {
		super(name, icon);
		this.dialogComponent = dialogComponent;
	}

	@Override
	protected void doAction() {
		JDialog dialog = (JDialog) SwingUtilities.getAncestorOfClass(JDialog.class, dialogComponent);
		dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
	}

}
