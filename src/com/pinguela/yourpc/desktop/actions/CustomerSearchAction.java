package com.pinguela.yourpc.desktop.actions;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.CustomerTableConstants;
import com.pinguela.yourpc.desktop.model.ActionPaneListTableModel;
import com.pinguela.yourpc.desktop.view.CustomerSearchView;
import com.pinguela.yourpc.desktop.view.SearchView;
import com.pinguela.yourpc.model.Customer;
import com.pinguela.yourpc.model.CustomerCriteria;
import com.pinguela.yourpc.service.CustomerService;
import com.pinguela.yourpc.service.impl.CustomerServiceImpl;

@SuppressWarnings("serial")
public class CustomerSearchAction
extends SearchAction<Customer> {
	
	private static Logger logger = LogManager.getLogger(CustomerSearchAction.class);
	
	private CustomerService customerService;

	public CustomerSearchAction(SearchView<Customer> view) {
		super(view);
		this.customerService = new CustomerServiceImpl();
	}

	@Override
	protected TableModel fetchData() {
		
		CustomerSearchView view = (CustomerSearchView) getView();
		CustomerCriteria criteria = view.getCriteria();
		List<Customer> results = null;

		try {
			if (criteria.getId() != null) {
				results = Arrays.asList(customerService.findById(criteria.getId()));
			} else {
				results = customerService.findBy(criteria);
			}
		} catch (YPCException ypce) {
			logger.error(ypce.getMessage(), ypce);
		}

		return new ActionPaneListTableModel<Customer>(CustomerTableConstants.COLUMN_NAMES, results);
	}

}
