package com.pinguela.yourpc.desktop.components;

import com.pinguela.yourpc.desktop.actions.OpenEntityViewDialogAction;
import com.pinguela.yourpc.desktop.actions.YPCAction;
import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.desktop.view.CustomerView;
import com.pinguela.yourpc.model.Customer;

@SuppressWarnings("serial")
public class CustomerSelector 
extends EntitySelector<Customer> {

	@Override
	protected YPCAction initializeViewAction() {
		return new OpenEntityViewDialogAction<>(CustomerView.class, this);
	}

	@Override
	protected String getLabelText() {
		Customer c = getEntity();
		return String.format("%s - %s", c.getId(), SwingUtils.formatFullName(c));
	}
}