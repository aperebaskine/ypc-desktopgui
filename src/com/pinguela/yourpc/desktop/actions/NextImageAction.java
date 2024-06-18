package com.pinguela.yourpc.desktop.actions;

import com.pinguela.yourpc.desktop.components.ImageGalleryPanel;
import com.pinguela.yourpc.desktop.constants.Icons;

@SuppressWarnings("serial")
public class NextImageAction
extends ImageGalleryAction {
	
	public NextImageAction(ImageGalleryPanel panel) {
		super(Icons.NEXT_ICON, panel);
	}	

	@Override
	protected void doAction() {
		ImageGalleryPanel panel = getPanel();
		panel.setSelectionIndex(panel.getSelectionIndex() +1);
	}

}
