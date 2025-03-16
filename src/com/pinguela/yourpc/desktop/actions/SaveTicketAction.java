package com.pinguela.yourpc.desktop.actions;

import javax.swing.Action;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.util.LocaleUtils;
import com.pinguela.yourpc.desktop.view.TicketView;
import com.pinguela.yourpc.model.Ticket;
import com.pinguela.yourpc.service.TicketService;
import com.pinguela.yourpc.service.impl.TicketServiceImpl;

@SuppressWarnings("serial")
public class SaveTicketAction 
extends SaveItemAction<Ticket> {

	private TicketService ticketService;

	public SaveTicketAction(TicketView view) {
		super(view, "Save", Icons.OK_ICON);
		this.ticketService = new TicketServiceImpl();
	}
	
	@Override
	protected void doCreate(Ticket item) throws YPCException {
		ticketService.create(item);
		getView().setEntity(ticketService.findById(item.getId(), LocaleUtils.getLocale()));
	}
	
	@Override
	protected void doUpdate(Ticket item) throws YPCException {
		ticketService.update(item);
		getView().setEntity(ticketService.findById(item.getId(), LocaleUtils.getLocale()));
	}
	
	@Override
	protected Action[] getViewerActions() {
		return new Action[]{new EditItemAction<Ticket>(getView())};
	}

}
