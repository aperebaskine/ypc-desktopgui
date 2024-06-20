package com.pinguela.yourpc.desktop.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.view.ProductSearchView;
import com.pinguela.yourpc.model.ProductCriteria;
import com.pinguela.yourpc.service.AttributeService;
import com.pinguela.yourpc.service.ProductService;
import com.pinguela.yourpc.service.impl.AttributeServiceImpl;
import com.pinguela.yourpc.service.impl.ProductServiceImpl;

@SuppressWarnings("serial")
public class SetProductCriteriaForCategoryAction 
extends YPCAction {

	private static Logger logger = LogManager.getLogger(SetProductCriteriaForCategoryAction.class);

	private ProductService productService;
	private AttributeService attributeService;

	private ProductSearchView view;

	public SetProductCriteriaForCategoryAction(ProductSearchView view) {
		this.productService = new ProductServiceImpl();
		this.attributeService = new AttributeServiceImpl();
		this.view = view;
		doAction();
	}

	@Override
	protected void doAction() {
		try {
			
			ProductCriteria criteria = view.getCriteria();
			view.setRanges(productService.getRanges(criteria));
			view.setCategoryAttributes(attributeService.findByCategory(criteria.getCategoryId(), AttributeService.NO_UNASSIGNED_VALUES));
		} catch (YPCException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
