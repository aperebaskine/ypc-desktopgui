package com.pinguela.yourpc.desktop.listener;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.actions.YPCAction;
import com.pinguela.yourpc.desktop.view.ProductSearchView;
import com.pinguela.yourpc.model.ProductCriteria;
import com.pinguela.yourpc.service.AttributeService;
import com.pinguela.yourpc.service.impl.AttributeServiceImpl;

public class SetAttributeCriteriaAction
extends YPCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3228435100642492498L;

	private static Logger logger = LogManager.getLogger(SetAttributeCriteriaAction.class);

	private AttributeService attributeService;
	private ProductSearchView view;

	public SetAttributeCriteriaAction(ProductSearchView view) {
		this.attributeService = new AttributeServiceImpl();
		this.view = view;
	}

	@Override
	protected void doAction() {
		ProductCriteria criteria = view.getCriteria();

		if (criteria.getCategoryId() == null) {
			return;
		}

		try {
			view.addAttributes(attributeService.findByCategory(criteria.getCategoryId()	, false));
		} catch (YPCException e1) {
			logger.error(String.format("An error occured while fetching attributes: %s", e1.getMessage()), e1);
			JOptionPane.showMessageDialog(view, "An error occured. Contact system administrador", "Error", JOptionPane.ERROR_MESSAGE);
		} 
	}

}
