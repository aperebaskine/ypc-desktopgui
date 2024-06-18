package com.pinguela.yourpc.desktop.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.view.CustomerOrderSearchView;
import com.pinguela.yourpc.service.CustomerOrderService;
import com.pinguela.yourpc.service.impl.CustomerOrderServiceImpl;

@SuppressWarnings("serial")
public class SetCustomerOrderRangesAction 
extends YPCAction {
	
	private static Logger logger = LogManager.getLogger(SetCustomerOrderRangesAction.class);

	private CustomerOrderService productService;
	private CustomerOrderSearchView view;

	public SetCustomerOrderRangesAction(CustomerOrderSearchView view) {
		this.productService = new CustomerOrderServiceImpl();
		this.view = view;
		doAction();
	}

	@Override
	protected void doAction() {
		try {
			view.setRanges(productService.getRanges(view.getCriteria()));
		} catch (YPCException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
