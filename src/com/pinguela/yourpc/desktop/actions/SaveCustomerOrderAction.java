package com.pinguela.yourpc.desktop.actions;

import javax.swing.Action;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.CustomerOrderView;
import com.pinguela.yourpc.model.CustomerOrder;
import com.pinguela.yourpc.service.CustomerOrderService;
import com.pinguela.yourpc.service.impl.CustomerOrderServiceImpl;

@SuppressWarnings("serial")
public class SaveCustomerOrderAction 
extends SaveItemAction<CustomerOrder> {
	
	private CustomerOrderService customerOrderService;

	public SaveCustomerOrderAction(CustomerOrderView view) {
		super(view, "Save", Icons.OK_ICON);
		this.customerOrderService = new CustomerOrderServiceImpl();
	}
	
	@Override
	protected void doCreate(CustomerOrder item) throws YPCException {
		customerOrderService.create(item);
		getView().setEntity(customerOrderService.findById(item.getId()));
	}
	
	@Override
	protected void doUpdate(CustomerOrder item) throws YPCException {
		customerOrderService.update(item);
		getView().setEntity(customerOrderService.findById(item.getId()));
	}
	
	@Override
	protected Action[] getViewerActions() {
		return new Action[]{new EditItemAction<CustomerOrder>(getView())};
	}

}
