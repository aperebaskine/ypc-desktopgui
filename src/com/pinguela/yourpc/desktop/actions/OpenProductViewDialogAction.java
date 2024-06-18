package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;

import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.EntityView;
import com.pinguela.yourpc.desktop.view.ProductView;
import com.pinguela.yourpc.model.Product;

@SuppressWarnings("serial")
public class OpenProductViewDialogAction 
extends OpenDialogAction<Product> {

	public OpenProductViewDialogAction() {
		super("Create...");
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		ProductView dialogView = new ProductView();
		dialogView.addPropertyChangeListener(EntityView.ITEM_PROPERTY, this);
		dialogView.addAction(new CancelEditAction<Product>(dialogView), EntityView.EDITOR_CARD);
		dialogView.addAction(new SaveProductAction(dialogView), EntityView.EDITOR_CARD);

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
