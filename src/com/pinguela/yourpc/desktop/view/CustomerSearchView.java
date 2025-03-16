package com.pinguela.yourpc.desktop.view;

import com.pinguela.yourpc.desktop.actions.CustomerSearchAction;
import com.pinguela.yourpc.desktop.actions.SearchAction;
import com.pinguela.yourpc.desktop.actions.SearchActionBuilder;
import com.pinguela.yourpc.desktop.components.CriteriaPanel;
import com.pinguela.yourpc.desktop.components.CustomerCriteriaPanel;
import com.pinguela.yourpc.desktop.renderer.CustomerTableCellRenderer;
import com.pinguela.yourpc.model.Customer;

@SuppressWarnings("serial")
public class CustomerSearchView
extends AbstractSearchView<Customer> {
	
	public CustomerSearchView() {
		this(new SearchActionBuilder<>(CustomerSearchAction.class));
	}

	public CustomerSearchView(SearchActionBuilder<Customer, ? extends SearchAction<Customer>> builder) {
		super(builder);
		setTableCellRenderer(Object.class, new CustomerTableCellRenderer());
	}

	@Override
	protected CriteriaPanel<Customer> initializeCriteriaPanel() {
		return new CustomerCriteriaPanel();
	}

}
