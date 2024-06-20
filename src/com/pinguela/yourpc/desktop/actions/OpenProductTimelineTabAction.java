package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import com.pinguela.yourpc.desktop.view.ProductTimelineView;

@SuppressWarnings("serial")
public class OpenProductTimelineTabAction 
extends OpenTabAction {

	public OpenProductTimelineTabAction() {
		super("Product timeline");
	}

	@Override
	protected Component initializeTab() {
		return new ProductTimelineView();
	}

}
