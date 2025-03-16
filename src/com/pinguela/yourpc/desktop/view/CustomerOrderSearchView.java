package com.pinguela.yourpc.desktop.view;

import com.pinguela.yourpc.desktop.actions.CustomerOrderSearchAction;
import com.pinguela.yourpc.desktop.actions.SearchAction;
import com.pinguela.yourpc.desktop.actions.SearchActionBuilder;
import com.pinguela.yourpc.desktop.components.CriteriaPanel;
import com.pinguela.yourpc.desktop.components.CustomerOrderCriteriaPanel;
import com.pinguela.yourpc.desktop.renderer.CustomerOrderTableCellRenderer;
import com.pinguela.yourpc.model.CustomerOrder;

@SuppressWarnings("serial")
public class CustomerOrderSearchView
extends AbstractSearchView<CustomerOrder> {

	public CustomerOrderSearchView() {
		this(new SearchActionBuilder<>(CustomerOrderSearchAction.class));
	}

	public CustomerOrderSearchView(SearchActionBuilder<CustomerOrder, ? extends SearchAction<CustomerOrder>> builder) {
		super(builder);
		setTableCellRenderer(Object.class, new CustomerOrderTableCellRenderer());
	}

	@Override
	protected CriteriaPanel<CustomerOrder> initializeCriteriaPanel() {
		return new CustomerOrderCriteriaPanel();
	}

}
