package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import com.pinguela.yourpc.desktop.util.TableUtils;
import com.pinguela.yourpc.desktop.view.ProductSearchView;

public class OpenProductSearchTabAction extends OpenTabAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7319346184625553028L;

	public OpenProductSearchTabAction() {
		super("Product search");
	}

	@Override
	protected Component initializeTab() {
		ProductSearchView view = new ProductSearchView();
		YPCAction editProductDialogAction = new EditProductDialogAction(view);
		TableUtils.initializeActionPanes(view.getTable(), new DeleteProductAction(view), editProductDialogAction);
		view.getTable().addMouseListener(editProductDialogAction);
		return view;
	}
	
}
