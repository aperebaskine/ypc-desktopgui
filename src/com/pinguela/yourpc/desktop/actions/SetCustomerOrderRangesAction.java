package com.pinguela.yourpc.desktop.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.components.CustomerOrderCriteriaPanel;
import com.pinguela.yourpc.service.CustomerOrderService;
import com.pinguela.yourpc.service.impl.CustomerOrderServiceImpl;

@SuppressWarnings("serial")
public class SetCustomerOrderRangesAction 
extends YPCAction {
	
	private static Logger logger = LogManager.getLogger(SetCustomerOrderRangesAction.class);

	private CustomerOrderService productService;
	private CustomerOrderCriteriaPanel panel;

	public SetCustomerOrderRangesAction(CustomerOrderCriteriaPanel panel) {
		this.productService = new CustomerOrderServiceImpl();
		this.panel = panel;
		doAction();
	}

	@Override
	protected void doAction() {
		try {
			panel.setRanges(productService.getRanges(panel.getCriteria()));
		} catch (YPCException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
