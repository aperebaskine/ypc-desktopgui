package com.pinguela.yourpc.desktop.actions;

import com.pinguela.yourpc.desktop.components.ImageGalleryPanel;
import com.pinguela.yourpc.desktop.constants.Icons;

public class PreviousImageAction 
extends ImageGalleryAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1665722816309418853L;

	public PreviousImageAction(ImageGalleryPanel panel) {
		super(Icons.PREVIOUS_ICON, panel);
	}

	@Override
	protected void doAction() {
		ImageGalleryPanel panel = getPanel();
		panel.setSelectionIndex(panel.getSelectionIndex() -1);
	}

}
