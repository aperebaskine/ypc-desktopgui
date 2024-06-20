package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import com.pinguela.yourpc.desktop.view.AttributeStatisticsView;

@SuppressWarnings("serial")
public class OpenAttributeStatisticsTabAction
extends OpenTabAction {

	public OpenAttributeStatisticsTabAction() {
		super("Attribute statistics");
	}

	@Override
	protected Component initializeTab() {
		return new AttributeStatisticsView();
	}
	
}
