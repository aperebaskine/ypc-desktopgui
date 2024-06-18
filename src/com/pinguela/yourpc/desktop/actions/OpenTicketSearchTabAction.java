package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import javax.swing.JTable;

import com.pinguela.yourpc.desktop.util.TableUtils;
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

		YPCAction editTicketDialogAction = new OpenTicketResultDialogAction(view);
		
		JTable table = view.getTable();
		TableUtils.initializeActionPanes(table, editTicketDialogAction);
		table.addMouseListener(editTicketDialogAction);
		
		return view;
	}

}
