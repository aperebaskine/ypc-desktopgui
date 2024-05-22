package com.pinguela.yourpc.desktop.actions;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.RMATableConstants;
import com.pinguela.yourpc.desktop.model.ActionPaneListTableModel;
import com.pinguela.yourpc.desktop.view.RMASearchView;
import com.pinguela.yourpc.desktop.view.SearchView;
import com.pinguela.yourpc.model.RMA;
import com.pinguela.yourpc.model.RMACriteria;
import com.pinguela.yourpc.service.RMAService;
import com.pinguela.yourpc.service.impl.RMAServiceImpl;

public class RMASearchAction extends SearchAction<RMA> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9036772407709735842L;
	
	private static Logger logger = LogManager.getLogger(RMASearchAction.class);
	
	private RMAService rmaService;

	public RMASearchAction(SearchView<RMA> view) {
		super(view);
		this.rmaService = new RMAServiceImpl();
	}

	@Override
	protected TableModel fetchData() {
		RMASearchView view = (RMASearchView) getView();
		RMACriteria criteria = view.getCriteria();
		List<RMA> results = null;

		try {
			if (criteria.getId() != null) {
				results = Arrays.asList(rmaService.findById(criteria.getId()));
			} else {
				results = rmaService.findBy(criteria);
			}
		} catch (YPCException ypce) {
			logger.error(ypce.getMessage(), ypce);
		}

		return new ActionPaneListTableModel<RMA>(RMATableConstants.COLUMN_NAMES, results);
	}

}
