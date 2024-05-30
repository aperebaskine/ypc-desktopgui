package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import com.pinguela.yourpc.desktop.util.TableUtils;
import com.pinguela.yourpc.desktop.view.CustomerSearchView;

public class OpenCustomerSearchTabAction extends OpenTabAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2747965007695889177L;

	public OpenCustomerSearchTabAction() {
		super("Customer search");
	}

	@Override
	protected Component initializeTab() {
		CustomerSearchView view = new CustomerSearchView();
		YPCAction editCustomerDialogAction = new EditCustomerDialogAction(view);
		TableUtils.initializeActionPanes(view.getTable(), new DeleteCustomerAction(view), editCustomerDialogAction);
		view.getTable().addMouseListener(editCustomerDialogAction);
		return view;
	}

}
