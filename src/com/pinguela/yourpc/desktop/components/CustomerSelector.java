package com.pinguela.yourpc.desktop.components;

import com.pinguela.yourpc.desktop.actions.OpenItemViewDialogAction;
import com.pinguela.yourpc.desktop.actions.SelectCustomerAction;
import com.pinguela.yourpc.desktop.actions.YPCAction;
import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.desktop.view.CustomerView;
import com.pinguela.yourpc.model.Customer;

@SuppressWarnings("serial")
public class CustomerSelector 
extends EntitySelector<Customer> {

	@Override
	protected YPCAction initializeSelectAction() {
		return new SelectCustomerAction(this);
	}

	@Override
	protected YPCAction initializeViewAction() {
		return new OpenItemViewDialogAction<>(CustomerView.class, this);
	}

	@Override
	protected String formatItemLabel() {
		Customer c = getItem();
		return String.format("%s - %s", c.getId(), SwingUtils.formatFullName(c));
	}
	
	public Integer getCustomerId() {
		return getItem().getId();
	}

}