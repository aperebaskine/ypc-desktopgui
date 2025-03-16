package com.pinguela.yourpc.desktop.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.components.ProductCriteriaPanel;
import com.pinguela.yourpc.desktop.util.LocaleUtils;
import com.pinguela.yourpc.model.Criteria;
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

	private ProductCriteriaPanel panel;

	public SetProductCriteriaForCategoryAction(ProductCriteriaPanel panel) {
		this.productService = new ProductServiceImpl();
		this.attributeService = new AttributeServiceImpl();
		this.panel = panel;
		doAction();
	}

	@Override
	protected void doAction() {
		try {
			
			ProductCriteria criteria = // Quick and dirty hack that should work for now
					(ProductCriteria) (Criteria) panel.getCriteria(); 
			panel.setRanges(productService.getRanges(criteria, LocaleUtils.getLocale()));
			panel.setCategoryAttributes(attributeService.findByCategory(criteria.getCategoryId(), LocaleUtils.getLocale(), AttributeService.NO_UNASSIGNED_VALUES));
		} catch (YPCException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
