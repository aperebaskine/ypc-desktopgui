package com.pinguela.yourpc.desktop.actions;

import com.pinguela.DataException;
import com.pinguela.ServiceException;
import com.pinguela.yourpc.desktop.util.FormattingUtils;
import com.pinguela.yourpc.desktop.view.EntityView;
import com.pinguela.yourpc.desktop.view.SearchView;
import com.pinguela.yourpc.model.Customer;
import com.pinguela.yourpc.service.CustomerService;
import com.pinguela.yourpc.service.impl.CustomerServiceImpl;

@SuppressWarnings("serial")
public class DeleteCustomerAction
extends DeleteItemAction<Customer> {

	private CustomerService customerService;
	
	{
		customerService = new CustomerServiceImpl();
	}
	
	public DeleteCustomerAction(EntityView<Customer> source) {
		super(source);
	}

	public DeleteCustomerAction(SearchView<Customer> source) {
		super(source);
	}
	
	@Override
	protected String getItemName(Customer item) {
		return FormattingUtils.formatFullName(item);
	}

	@Override
	protected boolean shouldDeleteFromDatabase() {
		return true;
	}

	@Override
	protected void deleteFromDatabase(Customer item) throws ServiceException, DataException {
		customerService.delete(item.getId());
	}

}
