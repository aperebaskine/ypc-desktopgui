package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import com.pinguela.yourpc.desktop.view.RMASearchView;

@SuppressWarnings("serial")
public class OpenRMASearchTabAction
extends OpenTabAction {
	
	public OpenRMASearchTabAction() {
		super("RMA search");
	}

	@Override
	protected Component initializeTab() {
		RMASearchView view = new RMASearchView();
		YPCAction openEditDialogAction = new OpenRMAResultDialogAction(view);
		
		view.setTableActions(openEditDialogAction);
		
		return view;
	}

}
