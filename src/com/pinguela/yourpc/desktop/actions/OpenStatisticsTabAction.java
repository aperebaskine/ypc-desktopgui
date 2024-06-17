package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import com.pinguela.yourpc.desktop.view.StatisticsView;

public class OpenStatisticsTabAction extends OpenTabAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5348369215394982879L;

	public OpenStatisticsTabAction() {
		super("Statistics");
	}
	
	@Override
	protected Component initializeTab() {
		return new StatisticsView();
	}

}
