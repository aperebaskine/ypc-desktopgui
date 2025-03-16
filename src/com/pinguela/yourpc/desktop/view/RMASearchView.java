package com.pinguela.yourpc.desktop.view;

import com.pinguela.yourpc.desktop.actions.RMASearchAction;
import com.pinguela.yourpc.desktop.actions.SearchAction;
import com.pinguela.yourpc.desktop.actions.SearchActionBuilder;
import com.pinguela.yourpc.desktop.components.CriteriaPanel;
import com.pinguela.yourpc.desktop.components.RMACriteriaPanel;
import com.pinguela.yourpc.desktop.renderer.RMATableCellRenderer;
import com.pinguela.yourpc.model.RMA;

@SuppressWarnings("serial")
public class RMASearchView 
extends AbstractSearchView<RMA> {

	public RMASearchView() {
		this(new SearchActionBuilder<>(RMASearchAction.class));
	}

	public RMASearchView(SearchActionBuilder<RMA, ? extends SearchAction<RMA>> builder) {
		super(builder);
		setTableCellRenderer(Object.class, new RMATableCellRenderer());

	}

	@Override
	protected CriteriaPanel<RMA> initializeCriteriaPanel() {
		return new RMACriteriaPanel();
	}

}
