package com.pinguela.yourpc.desktop.view;

import com.pinguela.yourpc.desktop.actions.SearchAction;
import com.pinguela.yourpc.desktop.actions.SearchActionBuilder;
import com.pinguela.yourpc.desktop.actions.TicketSearchAction;
import com.pinguela.yourpc.desktop.components.CriteriaPanel;
import com.pinguela.yourpc.desktop.components.TicketCriteriaPanel;
import com.pinguela.yourpc.desktop.renderer.TicketTableCellRenderer;
import com.pinguela.yourpc.model.Ticket;

@SuppressWarnings("serial")
public class TicketSearchView
extends AbstractPaginatedSearchView<Ticket> {

	public TicketSearchView() {
		this(new SearchActionBuilder<>(TicketSearchAction.class));
	}

	public TicketSearchView(SearchActionBuilder<Ticket, ? extends SearchAction<Ticket>> builder) {
		super(builder);
		setTableCellRenderer(Object.class, new TicketTableCellRenderer());
	}

	@Override
	protected CriteriaPanel<Ticket> initializeCriteriaPanel() {
		return new TicketCriteriaPanel();
	}

}
