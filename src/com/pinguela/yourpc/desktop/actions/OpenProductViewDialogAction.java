package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;

import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.ItemView;
import com.pinguela.yourpc.desktop.view.ProductView;
import com.pinguela.yourpc.model.Product;

public class OpenProductViewDialogAction 
extends OpenDialogAction<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8174388122343707197L;

	private ProductView dialogView;

	public OpenProductViewDialogAction() {
		super("Create...");
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		dialogView = new ProductView();
		dialogView.addPropertyChangeListener(ItemView.ITEM_PROPERTY, this);
		dialogView.addAction(new CancelEditAction<Product>(dialogView), ItemView.EDITOR_CARD);
		dialogView.addAction(new SaveProductAction(dialogView), ItemView.EDITOR_CARD);
		dialogView.addAction(new AddAttributeAction(dialogView), ItemView.EDITOR_CARD);

		YPCDialog dialog = new YPCDialog(null, dialogView);
		dialog.setTitle("Product editor");
		return dialog;
	}

	@Override
	protected boolean shouldSetRelativeLocation() {
		return false;
	}
	
	@Override
	protected void onClose() {}

}
