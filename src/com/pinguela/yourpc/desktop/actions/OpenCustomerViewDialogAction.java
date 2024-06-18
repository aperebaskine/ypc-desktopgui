package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;

import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.CustomerView;
import com.pinguela.yourpc.desktop.view.EntityView;
import com.pinguela.yourpc.model.Customer;

@SuppressWarnings("serial")
public class OpenCustomerViewDialogAction 
extends OpenDialogAction<Customer> {
	
	private CustomerView dialogView;

	public OpenCustomerViewDialogAction() {
		super("Create...");
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		dialogView = new CustomerView();
		dialogView.addPropertyChangeListener(EntityView.ITEM_PROPERTY, this);
		dialogView.addAction(new CancelEditAction<Customer>(dialogView), EntityView.EDITOR_CARD);
		dialogView.addAction(new SaveCustomerAction(dialogView), EntityView.EDITOR_CARD);

		YPCDialog dialog = new YPCDialog(null, dialogView);
		dialog.setTitle("Customer editor");
		return dialog;
	}

	@Override
	protected boolean shouldSetRelativeLocation() {
		return false;
	}
	
	@Override
	protected void onClose() {}

}
