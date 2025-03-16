package com.pinguela.yourpc.desktop.actions;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.ProductView;
import com.pinguela.yourpc.model.dto.AttributeDTO;
import com.pinguela.yourpc.service.AttributeService;

@SuppressWarnings("serial")
public class AddAttributeAction
extends GetAttributeInputAction<ProductView> {
	
	public AddAttributeAction(ProductView view, Integer forcedHandlingMode) {
		super(forcedHandlingMode, "Add...", Icons.ADD_ICON, view);
	}
	
	@Override
	protected boolean shouldReturnUnassignedValues() {
		return AttributeService.RETURN_UNASSIGNED_VALUES;
	}

	@Override
	protected Short getCategoryId() {
		return getView().getEntityFromFields().getCategoryId();
	}

	@Override
	protected void onConfirm() {
		AttributeDTO<?> attribute = getInput();
		getView().addAttribute(attribute);
	}

}
