package com.pinguela.yourpc.desktop.actions;

import com.pinguela.yourpc.desktop.components.ImageGalleryPanel;
import com.pinguela.yourpc.desktop.constants.Icons;

@SuppressWarnings("serial")
public class PreviousImageAction 
extends ImageGalleryAction {

	public PreviousImageAction(ImageGalleryPanel panel) {
		super(Icons.PREVIOUS_ICON, panel);
	}

	@Override
	protected void doAction() {
		ImageGalleryPanel panel = getPanel();
		panel.setSelectionIndex(panel.getSelectionIndex() -1);
	}

}
