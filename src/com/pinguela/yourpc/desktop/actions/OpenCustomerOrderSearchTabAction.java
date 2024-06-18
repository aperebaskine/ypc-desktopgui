package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import com.pinguela.yourpc.desktop.util.TableUtils;
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
		TableUtils.initializeActionPanes(view.getTable(), openDialogAction);
		view.getTable().addMouseListener(openDialogAction);
		return view;
	}

}
