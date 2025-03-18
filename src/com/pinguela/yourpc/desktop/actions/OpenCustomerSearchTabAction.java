package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import com.pinguela.yourpc.desktop.view.CustomerSearchView;

@SuppressWarnings("serial")
public class OpenCustomerSearchTabAction
extends OpenTabAction {

	public OpenCustomerSearchTabAction() {
		super("Customer search");
	}

	@Override
	protected Component initializeTab() {
		CustomerSearchView view = new CustomerSearchView();
		YPCAction editCustomerDialogAction = new OpenCustomerResultDialogAction(view);
		view.setTableActions(new DeleteCustomerAction(view), editCustomerDialogAction);
		return view;
	}

}
