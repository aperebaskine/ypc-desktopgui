package com.pinguela.yourpc.desktop.actions;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.CustomerView;
import com.pinguela.yourpc.model.Customer;
import com.pinguela.yourpc.service.CustomerService;
import com.pinguela.yourpc.service.impl.CustomerServiceImpl;

public class SaveCustomerAction 
extends SaveItemAction<Customer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2287828731774496866L;

	private CustomerService customerService;

	public SaveCustomerAction(CustomerView view) {
		super(view, "Save", Icons.OK_ICON);
		this.customerService = new CustomerServiceImpl();
	}

	@Override
	protected void doSave() throws YPCException {
		Customer newCustomer = getView().getNewItem();
		customerService.update(newCustomer);
		getView().setItem(customerService.findById(newCustomer.getId()));
	}
	
}
