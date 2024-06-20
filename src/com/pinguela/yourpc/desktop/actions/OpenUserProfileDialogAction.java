package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;

import com.pinguela.yourpc.desktop.YPCWindow;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.EmployeeView;
import com.pinguela.yourpc.model.Employee;

@SuppressWarnings("serial")
public class OpenUserProfileDialogAction 
extends OpenDialogAction<Employee> {

	public OpenUserProfileDialogAction() {
		super("Profile");
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		EmployeeView view = new EmployeeView();
		view.setEntity(YPCWindow.getInstance().getAuthenticatedUser());
		YPCDialog dialog = new YPCDialog(null, view);
		return dialog;
	}

	@Override
	protected boolean shouldSetRelativeLocation() {
		return true;
	}

	@Override
	protected void onClose() {}
	
}
