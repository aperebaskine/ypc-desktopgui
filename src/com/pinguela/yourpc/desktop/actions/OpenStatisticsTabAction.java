package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import com.pinguela.yourpc.desktop.view.ProductStatisticsSearchView;

@SuppressWarnings("serial")
public class OpenStatisticsTabAction
extends OpenTabAction {

	public OpenStatisticsTabAction() {
		super("Statistics");
	}
	
	@Override
	protected Component initializeTab() {
		return new ProductStatisticsSearchView();
	}

}
