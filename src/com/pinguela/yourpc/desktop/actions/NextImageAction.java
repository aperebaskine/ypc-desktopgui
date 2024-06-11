package com.pinguela.yourpc.desktop.actions;

import com.pinguela.yourpc.desktop.components.ImageGalleryPanel;
import com.pinguela.yourpc.desktop.constants.Icons;

public class NextImageAction extends ImageGalleryAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1386405205236629283L;
	
	public NextImageAction(ImageGalleryPanel panel) {
		super(Icons.NEXT_ICON, panel);
	}	

	@Override
	protected void doAction() {
		ImageGalleryPanel panel = getPanel();
		panel.setSelectionIndex(panel.getSelectionIndex() +1);
	}

}
