package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;

import com.pinguela.yourpc.desktop.YPCWindow;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.EmployeeView;
import com.pinguela.yourpc.model.Employee;

public class OpenUserProfileDialogAction 
extends OpenDialogAction<Employee> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1584273910933127322L;

	public OpenUserProfileDialogAction() {
		super("Profile");
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		EmployeeView view = new EmployeeView();
		view.setItem(YPCWindow.getInstance().getAuthenticatedUser());
		YPCDialog dialog = new YPCDialog(null, view);
		return dialog;
	}

	@Override
	protected boolean shouldSetRelativeLocation() {
		return false;
	}

	@Override
	protected void onClose() {}
	
}
