package com.pinguela.yourpc.desktop.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.view.ProductSearchView;
import com.pinguela.yourpc.service.ProductService;
import com.pinguela.yourpc.service.impl.ProductServiceImpl;

public class SetProductRangesAction 
extends YPCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3510016415541527075L;

	private static Logger logger = LogManager.getLogger(SetProductRangesAction.class);

	private ProductService productService;
	private ProductSearchView view;

	public SetProductRangesAction(ProductSearchView view) {
		this.productService = new ProductServiceImpl();
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
