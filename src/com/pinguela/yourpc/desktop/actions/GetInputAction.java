package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;

import javax.swing.Icon;

import com.pinguela.yourpc.desktop.components.InputPane;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;

@SuppressWarnings("serial")
public abstract class GetInputAction<T>
extends OpenDialogAction<T> {
	
	private InputPane<T> inputPane;

	public GetInputAction() {
		super();
	}

	public GetInputAction(Icon icon) {
		super(icon);
	}

	public GetInputAction(String name, Icon icon) {
		super(name, icon);
	}

	public GetInputAction(String name) {
		super(name);
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		inputPane = initializeInputPane();
		return new YPCDialog(null, inputPane);
	}

	@Override
	protected boolean shouldSetRelativeLocation() {
		return true;
	}

	@Override
	protected final void onClose() {
		if (inputPane.getExitCode() == InputPane.CONFIRM) {
			onConfirm();
		} 
	}
	
	protected T getInput() {
		return inputPane.getInput();
	}
	
	protected abstract InputPane<T> initializeInputPane();
	
	protected abstract void onConfirm();

}
