package com.pinguela.yourpc.desktop.actions;

import com.pinguela.yourpc.desktop.view.ProductView;
import com.pinguela.yourpc.model.Attribute;

public class AddAttributeAction
extends GetAttributeInputAction<ProductView> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5019193350081032906L;
	
	public AddAttributeAction(ProductView view) {
		super("Add...", view);
	}

	@Override
	protected Short getCategoryId() {
		return getView().getNewItem().getCategoryId();
	}

	@Override
	protected void onConfirm() {
		Attribute<?> attribute = getInput();
		getView().addAttribute(attribute);
	}

}
