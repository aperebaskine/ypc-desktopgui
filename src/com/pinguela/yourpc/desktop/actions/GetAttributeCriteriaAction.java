package com.pinguela.yourpc.desktop.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.desktop.view.ProductSearchView;
import com.pinguela.yourpc.service.AttributeService;
import com.pinguela.yourpc.service.impl.AttributeServiceImpl;

@SuppressWarnings("serial")
public class GetAttributeCriteriaAction 
extends YPCAction {
	
	private static Logger logger = LogManager.getLogger(GetAttributeCriteriaAction.class);
	
	private ProductSearchView view;
	private AttributeService attributeService;

	public GetAttributeCriteriaAction(ProductSearchView view) {
		this.view = view;
		this.attributeService = new AttributeServiceImpl();
	}
	@Override
	protected void doAction() {
		Short categoryId = view.getCriteria().getCategoryId();
		
		if (categoryId == null) {
			return;
		}
		
		try {
			view.setCategoryAttributes(attributeService.findByCategory(categoryId, AttributeService.NO_UNASSIGNED_VALUES));
		} catch (YPCException e) {
			logger.error(e.getMessage(), e);
			SwingUtils.showDatabaseAccessErrorDialog(view);
		} 
	}

}
