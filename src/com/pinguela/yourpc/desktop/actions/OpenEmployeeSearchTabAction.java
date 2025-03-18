package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import com.pinguela.yourpc.desktop.view.EmployeeSearchView;

@SuppressWarnings("serial")
public class OpenEmployeeSearchTabAction
extends OpenTabAction {
	
	public OpenEmployeeSearchTabAction() {
		super("Employee search");
	}

	@Override
	protected Component initializeTab() {
		EmployeeSearchView view = new EmployeeSearchView();
		YPCAction editEmployeeDialogAction = new OpenEmployeeResultDialogAction(view);
		view.setTableActions(editEmployeeDialogAction);
		return view;
	}

}
