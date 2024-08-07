package com.pinguela.yourpc.desktop.components;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.pinguela.yourpc.desktop.util.SwingUtils;

@SuppressWarnings("serial")
public class ImagePanel
extends JPanel 
implements YPCComponent {

	private static final String IMAGE_PROPERTY = "image";

	private static final JLabel NO_IMAGE_LABEL = new JLabel("No image to display", SwingConstants.CENTER);

	private BufferedImage image;
	private final PropertyChangeListener imagePropertyListener = (evt) -> {
		EventQueue.invokeLater(() -> {
			updateDisplay();
		});
	};

	public ImagePanel(Dimension size) {
		setPreferredSize(size);
		setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new EmptyBorder(2, 2, 2, 2)));
		setLayout(new GridLayout(0, 1, 0, 0));
		add(NO_IMAGE_LABEL);

		addPropertyChangeListener(IMAGE_PROPERTY, imagePropertyListener);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				onResize();
			}
		});
	}

	private BufferedImage resize(BufferedImage input) {
		return SwingUtils.resizeImage(image, this);
	}

	private boolean shouldResizeImage() {
		
		JLabel label = (JLabel) getComponent(0);

		if (NO_IMAGE_LABEL.equals(label)) {
			return false;
		}
		
		Icon icon = label.getIcon();

		int panelWidth = getWidth();
		int panelHeight = getHeight();
		
		int iconWidth = icon.getIconWidth();
		int iconHeight = icon.getIconHeight();

		// If image still fully fills the panel, resizing isn't required
		if ((panelHeight > panelWidth && panelWidth - SwingUtils.getMarginSize(this, SwingUtils.HORIZONTAL_MARGIN) == iconWidth)
				|| ((panelWidth >= panelHeight && panelHeight - SwingUtils.getMarginSize(this, SwingUtils.VERTICAL_MARGIN) == iconHeight))) {
			return false;
		} 

		return true;
	}

	private void onResize() {
		if (shouldResizeImage()) {
			updateDisplay();
		}
	}

	public void setImage(BufferedImage image) {
		BufferedImage old = this.image;
		this.image = image;
		firePropertyChange(IMAGE_PROPERTY, old, image);
	}

	public void removeImage() {
		setImage(null);
	}
	
	private void updateDisplay() {
		remove(0);
		add(image == null ? 
				NO_IMAGE_LABEL : 
					new JLabel(new ImageIcon(resize(image))));
		revalidate();
		repaint();	
	}

}
