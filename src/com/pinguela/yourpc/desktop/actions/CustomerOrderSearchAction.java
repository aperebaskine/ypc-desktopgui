package com.pinguela.yourpc.desktop.actions;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.CustomerOrderTableConstants;
import com.pinguela.yourpc.desktop.model.ListTableModel;
import com.pinguela.yourpc.desktop.util.LocaleUtils;
import com.pinguela.yourpc.desktop.view.AbstractSearchView;
import com.pinguela.yourpc.desktop.view.CustomerOrderSearchView;
import com.pinguela.yourpc.model.CustomerOrder;
import com.pinguela.yourpc.model.CustomerOrderCriteria;
import com.pinguela.yourpc.service.CustomerOrderService;
import com.pinguela.yourpc.service.impl.CustomerOrderServiceImpl;

@SuppressWarnings("serial")
public class CustomerOrderSearchAction 
extends SearchAction<CustomerOrder> {

	private static Logger logger = LogManager.getLogger(CustomerOrderSearchAction.class);

	private CustomerOrderService customerOrderService;

	public CustomerOrderSearchAction(AbstractSearchView<CustomerOrder> view) {
		super(view);
		customerOrderService = new CustomerOrderServiceImpl();
	}

	@Override
	protected TableModel fetchData() {

		CustomerOrderSearchView view = (CustomerOrderSearchView) getView();
		CustomerOrderCriteria criteria = (CustomerOrderCriteria) view.getCriteria();
		List<CustomerOrder> results = null;

		try {
			if (criteria.getId() != null) {
				results = Arrays.asList(customerOrderService.findById(criteria.getId(), LocaleUtils.getLocale()));
			} else {
				results = customerOrderService.findBy(criteria, LocaleUtils.getLocale());
			}
		} catch (YPCException ypce) {
			logger.error(ypce.getMessage(), ypce);
		}

		return new ListTableModel<CustomerOrder>(CustomerOrderTableConstants.COLUMN_NAMES, results);
	}

}
