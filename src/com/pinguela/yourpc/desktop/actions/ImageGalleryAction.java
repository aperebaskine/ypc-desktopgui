package com.pinguela.yourpc.desktop.actions;

import javax.swing.Icon;

import com.pinguela.yourpc.desktop.components.ImageGalleryPanel;

@SuppressWarnings("serial")
public abstract class ImageGalleryAction 
extends YPCAction {

	private ImageGalleryPanel panel;

    public ImageGalleryAction(String name, Icon icon, ImageGalleryPanel panel) {
        super(name, icon);
        this.panel = panel;
    }

    public ImageGalleryAction(Icon icon, ImageGalleryPanel panel) {
        this(null, icon, panel);
    }

    public ImageGalleryAction(String name, ImageGalleryPanel panel) {
        this(name, null, panel);
    }

    public ImageGalleryAction(ImageGalleryPanel panel) {
        this(null, null, panel);
    }
	
	protected ImageGalleryPanel getPanel() {
		return panel;
	}

}
