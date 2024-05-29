package com.pinguela.yourpc.desktop.actions;

import javax.swing.Action;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.TicketView;
import com.pinguela.yourpc.model.Ticket;
import com.pinguela.yourpc.service.TicketService;
import com.pinguela.yourpc.service.impl.TicketServiceImpl;

public class SaveTicketAction 
extends SaveItemAction<Ticket> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7296590252331794074L;

	private TicketService ticketService;

	public SaveTicketAction(TicketView view) {
		super(view, "Save", Icons.OK_ICON);
		this.ticketService = new TicketServiceImpl();
	}

	@Override
	protected void doSave() throws YPCException {
		Ticket newTicket = getView().getNewItem();
		ticketService.update(newTicket);
		getView().setItem(ticketService.findById(newTicket.getId()));
	}
	
	@Override
	protected Action[] getViewerActions() {
		return new Action[]{new EditItemAction<Ticket>(getView())};
	}

}
