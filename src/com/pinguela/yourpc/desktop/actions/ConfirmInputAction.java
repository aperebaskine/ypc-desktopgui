package com.pinguela.yourpc.desktop.actions;

import java.awt.Window;
import java.awt.event.WindowEvent;

import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.components.InputPane;
import com.pinguela.yourpc.desktop.constants.Icons;

public class ConfirmInputAction 
extends YPCAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -554990450599129073L;
	
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
