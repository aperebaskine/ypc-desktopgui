package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import com.pinguela.yourpc.desktop.util.TableUtils;
import com.pinguela.yourpc.desktop.view.CustomerOrderSearchView;

public class OpenCustomerOrderSearchTabAction extends OpenTabAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3001033840365107013L;
	
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
