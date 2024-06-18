package com.pinguela.yourpc.desktop.actions;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.ProductSearchView;
import com.pinguela.yourpc.model.Attribute;
import com.pinguela.yourpc.model.ProductCriteria;

@SuppressWarnings("serial")
public class AddAttributeCriteriaAction 
extends GetAttributeInputAction<ProductSearchView> {
	
	public AddAttributeCriteriaAction(ProductSearchView view) {
		super("Add...", Icons.ADD_ICON, view);
	}

	@Override
	protected Short getCategoryId() {
		ProductCriteria criteria = getView().getCriteria();
		return criteria.getCategoryId();
	}

	@Override
	protected void onConfirm() {
		Attribute<?> attribute = getInput();
		getView().addAttribute(attribute);
	}

}
