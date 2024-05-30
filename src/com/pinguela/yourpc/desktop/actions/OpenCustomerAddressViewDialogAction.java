package com.pinguela.yourpc.desktop.actions;

import com.pinguela.yourpc.desktop.components.CustomerAddressSelector;
import com.pinguela.yourpc.desktop.view.AddressView;
import com.pinguela.yourpc.desktop.view.ItemView;
import com.pinguela.yourpc.model.Address;

public class OpenCustomerAddressViewDialogAction
extends OpenItemViewDialogAction<Address> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8375494613046015981L;

	public OpenCustomerAddressViewDialogAction(CustomerAddressSelector selector) {
		super(AddressView.class, selector);
	}
	
	@Override
	protected ItemView<Address> initializeView() {
		return new AddressView(AddressView.CUSTOMER);
	}

}
