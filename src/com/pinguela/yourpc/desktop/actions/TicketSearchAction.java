package com.pinguela.yourpc.desktop.actions;

import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.TicketTableConstants;
import com.pinguela.yourpc.desktop.model.ListTableModel;
import com.pinguela.yourpc.desktop.util.LocaleUtils;
import com.pinguela.yourpc.desktop.view.AbstractSearchView;
import com.pinguela.yourpc.desktop.view.TicketSearchView;
import com.pinguela.yourpc.model.Results;
import com.pinguela.yourpc.model.Ticket;
import com.pinguela.yourpc.model.TicketCriteria;
import com.pinguela.yourpc.service.TicketService;
import com.pinguela.yourpc.service.impl.TicketServiceImpl;

@SuppressWarnings("serial")
public class TicketSearchAction
extends SearchAction<Ticket> {
	
	private static Logger logger = LogManager.getLogger(TicketSearchAction.class);
	
	private TicketService ticketService;

	public TicketSearchAction(AbstractSearchView<Ticket> view) {
		super(view);
		this.ticketService = new TicketServiceImpl();
	}

	@Override
	protected TableModel fetchData() {

		TicketSearchView view = (TicketSearchView) getView();
		TicketCriteria criteria = (TicketCriteria) view.getCriteria();
		Results<Ticket> results = null;

		try {
			if (criteria.getId() != null) {
				results = Results.singleEntry(ticketService.findById(criteria.getId(), LocaleUtils.getLocale()));
			} else {
				results = ticketService.findBy(criteria, LocaleUtils.getLocale(), view.getPos(), view.getPageSize());
			}
		} catch (YPCException ypce) {
			logger.error(ypce.getMessage(), ypce);
		}

		view.setResultCount(results.getResultCount());
		return new ListTableModel<>(TicketTableConstants.COLUMN_NAMES, results.getPage());
	}

}
