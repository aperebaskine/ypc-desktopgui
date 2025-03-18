package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import com.pinguela.yourpc.desktop.view.ProductSearchView;

@SuppressWarnings("serial")
public class OpenProductSearchTabAction
extends OpenTabAction {

	public OpenProductSearchTabAction() {
		super("Product search");
	}

	@Override
	protected Component initializeTab() {
		ProductSearchView view = new ProductSearchView();
		YPCAction editProductDialogAction = new OpenProductResultDialogAction(view);
		view.setTableActions(new DeleteProductAction(view), editProductDialogAction);
		return view;
	}
	
}
