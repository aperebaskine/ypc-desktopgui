package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import com.pinguela.yourpc.desktop.view.TicketSearchView;

@SuppressWarnings("serial")
public class OpenTicketSearchTabAction
extends OpenTabAction {
	
	public OpenTicketSearchTabAction() {
		super("Ticket search");
	}

	@Override
	protected Component initializeTab() {
		TicketSearchView view = new TicketSearchView();
		view.setTableActions(true, new OpenTicketResultDialogAction(view));
		return view;
	}

}
