package com.pinguela.yourpc.desktop.view;

import com.pinguela.yourpc.desktop.actions.ProductStatisticsSearchAction;
import com.pinguela.yourpc.desktop.actions.SearchActionBuilder;
import com.pinguela.yourpc.desktop.components.CriteriaPanel;
import com.pinguela.yourpc.desktop.components.SalesStatisticsCriteriaPanel;
import com.pinguela.yourpc.model.ProductStatistics;

@SuppressWarnings("serial")
public class ProductStatisticsSearchView
extends AbstractSearchView<ProductStatistics> {

	public ProductStatisticsSearchView() {
		super(new SearchActionBuilder<>(ProductStatisticsSearchAction.class));
	}

	@Override
	protected CriteriaPanel<ProductStatistics> initializeCriteriaPanel() {
		return new SalesStatisticsCriteriaPanel();
	}

}
