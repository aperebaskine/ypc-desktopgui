package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import com.pinguela.yourpc.desktop.view.CustomerOrderSearchView;

@SuppressWarnings("serial")
public class OpenCustomerOrderSearchTabAction
extends OpenTabAction {
	
	public OpenCustomerOrderSearchTabAction() {
		super("Customer order search");
	}

	@Override
	protected Component initializeTab() {
		CustomerOrderSearchView view = new CustomerOrderSearchView();
		YPCAction openDialogAction = new OpenCustomerOrderResultDialogAction(view);
		view.setTableActions(openDialogAction);
		return view;
	}

}
