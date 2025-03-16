package com.pinguela.yourpc.desktop.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.components.ProductCriteriaPanel;
import com.pinguela.yourpc.desktop.util.DialogUtils;
import com.pinguela.yourpc.desktop.util.LocaleUtils;
import com.pinguela.yourpc.model.Criteria;
import com.pinguela.yourpc.model.ProductCriteria;
import com.pinguela.yourpc.service.AttributeService;
import com.pinguela.yourpc.service.impl.AttributeServiceImpl;

@SuppressWarnings("serial")
public class GetAttributeCriteriaAction 
extends YPCAction {
	
	private static Logger logger = LogManager.getLogger(GetAttributeCriteriaAction.class);
	
	private ProductCriteriaPanel panel;
	private AttributeService attributeService;

	public GetAttributeCriteriaAction(ProductCriteriaPanel panel) {
		this.panel = panel;
		this.attributeService = new AttributeServiceImpl();
	}
	@Override
	protected void doAction() {
		Short categoryId = ((ProductCriteria) (Criteria) panel.getCriteria()).getCategoryId();
		
		if (categoryId == null) {
			return;
		}
		
		try {
			panel.setCategoryAttributes(attributeService.findByCategory(categoryId, LocaleUtils.getLocale(), AttributeService.NO_UNASSIGNED_VALUES));
		} catch (YPCException e) {
			logger.error(e.getMessage(), e);
			DialogUtils.showDatabaseAccessErrorDialog(panel);
		} 
	}

}
