package com.pinguela.yourpc.desktop.actions;

import com.pinguela.yourpc.desktop.components.CustomerAddressSelector;
import com.pinguela.yourpc.desktop.view.AddressView;
import com.pinguela.yourpc.desktop.view.EntityView;
import com.pinguela.yourpc.model.Address;

@SuppressWarnings("serial")
public class OpenCustomerAddressViewDialogAction
extends OpenEntityViewDialogAction<Address> {

	public OpenCustomerAddressViewDialogAction(CustomerAddressSelector selector) {
		super(AddressView.class, selector);
	}
	
	@Override
	protected EntityView<Address> initializeView() {
		return new AddressView(AddressView.CUSTOMER);
	}

}
