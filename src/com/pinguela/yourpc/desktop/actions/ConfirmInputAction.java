package com.pinguela.yourpc.desktop.actions;

import java.awt.Window;
import java.awt.event.WindowEvent;

import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.components.InputPane;
import com.pinguela.yourpc.desktop.constants.Icons;

@SuppressWarnings("serial")
public class ConfirmInputAction 
extends YPCAction {
	
	private InputPane<?> inputPane;

	public ConfirmInputAction(InputPane<?> inputPane) {
		super("Confirm", Icons.OK_ICON);
		this.inputPane = inputPane;
	}
	
	@Override
	protected void doAction() {
		inputPane.setExitCode(InputPane.CONFIRM);
		Window window = (Window) SwingUtilities.getWindowAncestor(inputPane);
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	}

}
