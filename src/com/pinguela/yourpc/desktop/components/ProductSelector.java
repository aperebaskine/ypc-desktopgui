package com.pinguela.yourpc.desktop.components;

import com.pinguela.yourpc.desktop.actions.OpenEntityViewDialogAction;
import com.pinguela.yourpc.desktop.actions.YPCAction;
import com.pinguela.yourpc.desktop.view.ProductView;
import com.pinguela.yourpc.model.dto.LocalizedProductDTO;

@SuppressWarnings("serial")
public class ProductSelector 
extends EntitySelector<LocalizedProductDTO> {
	
	public ProductSelector() {
		super(true);
	}

	@Override
	protected YPCAction initializeViewAction() {
		return new OpenEntityViewDialogAction<>(ProductView.class, this);
	}

	@Override
	protected String getLabelText() {
		LocalizedProductDTO p = getEntity();
		return String.format("%s - %s", p.getId(), p.getName());
	}

}
