package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import javax.swing.JTable;

import com.pinguela.yourpc.desktop.util.TableUtils;
import com.pinguela.yourpc.desktop.view.TicketSearchView;

public class OpenTicketSearchTabAction extends OpenTabAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6244202534586759L;
	
	public OpenTicketSearchTabAction() {
		super("Ticket search");
	}

	@Override
	protected Component initializeTab() {
		TicketSearchView view = new TicketSearchView();

		YPCAction editTicketDialogAction = new EditTicketDialogAction(view);
		
		JTable table = view.getTable();
		TableUtils.initializeActionPanes(table, editTicketDialogAction);
		table.addMouseListener(editTicketDialogAction);
		
		return view;
	}

}
