package com.pinguela.yourpc.desktop.components;

import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.pinguela.yourpc.desktop.util.SwingUtils;

public class ImagePanel
extends JPanel 
implements YPCComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6873380702577151479L;

	private static final JLabel NO_IMAGE_LABEL = new JLabel("No image to display");

	private BufferedImage image;

	public ImagePanel() {
		setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new EmptyBorder(2, 2, 2, 2)));
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{640, 0};
		gbl_centerPanel.rowHeights = new int[]{480, 0};
		gbl_centerPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gbl_centerPanel);
		add(NO_IMAGE_LABEL);

		AutoImageResizer resizer = new AutoImageResizer();
		addComponentListener(resizer);
	}
	
	private void displayImage(BufferedImage image) {
		remove(0);
		add(image == null ? NO_IMAGE_LABEL : new JLabel(new ImageIcon(resize(image))));
		revalidate();
		repaint();
	}

	private BufferedImage resize(BufferedImage input) {
		return SwingUtils.resize(image, this);
	}
	
	private boolean shouldResizeImage() {
		JLabel label = (JLabel) getComponent(0);
		
		if (NO_IMAGE_LABEL.equals(label)) {
			return false;
		}

		int width = getWidth();
		int height = getHeight();

		// If image still fully fills the panel, resizing isn't required
		if ((height > width	&& width - SwingUtils.getMarginSize(this, SwingUtils.HORIZONTAL_MARGIN) == label.getWidth())
				|| ((width >= height && height - SwingUtils.getMarginSize(this, SwingUtils.VERTICAL_MARGIN) == label.getHeight()))) {
			return false;
		} 

		return true;
	}
	
	
	private void onResize() {
		if (shouldResizeImage()) {
			displayImage(resize(image));
		}
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		displayImage(image);
	}

	public void removeImage() {
		this.image = null;
		add(NO_IMAGE_LABEL);
	}

	private class AutoImageResizer extends ComponentAdapter {

		@Override
		public void componentResized(ComponentEvent e) {
			onResize();
		}
	}

}
