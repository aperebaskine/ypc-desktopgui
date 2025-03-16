package com.pinguela.yourpc.desktop.util;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

public class SwingUtils {

	// During resizing, specify whether to check the horizontal or vertical margin size
	public static final int HORIZONTAL_MARGIN = 0;
	public static final int VERTICAL_MARGIN = 1;

	public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
		Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
		BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		return outputImage;
	}

	public static BufferedImage resizeImage(BufferedImage image, JPanel targetPanel) {

		int width;
		int height;

		int panelWidth = targetPanel.getWidth();
		int panelHeight = targetPanel.getHeight();

		// TODO: factorize
		if (panelHeight > panelWidth) {
			width = panelWidth - getMarginSize(targetPanel, HORIZONTAL_MARGIN);
			height = image.getHeight() * width / image.getWidth();
			if (height > panelHeight - getMarginSize(targetPanel, VERTICAL_MARGIN)) {
				height = panelHeight - getMarginSize(targetPanel, VERTICAL_MARGIN);
				width = image.getWidth() * height / image.getHeight();
			}
		} else {
			height = panelHeight - getMarginSize(targetPanel, VERTICAL_MARGIN);
			width = image.getWidth() * height / image.getHeight();
			if (width > panelWidth - getMarginSize(targetPanel, HORIZONTAL_MARGIN)) {
				width = panelWidth - getMarginSize(targetPanel, HORIZONTAL_MARGIN);
				height = image.getHeight() * width / image.getWidth();
			}
		}

		try {
			return SwingUtils.resizeImage(image, width, height);
		} catch (IOException e) {
			return null;
		}	
	}

	public static int getMarginSize(JPanel panel, int orientation) {
		Insets insets = panel.getBorder().getBorderInsets(panel);
		int margin = orientation == VERTICAL_MARGIN ? insets.top + insets.bottom : insets.left + insets.right;
		return margin;
	}

	public static void centerOnScreen(Window window) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (screenSize.width - window.getWidth()) / 2;
		int y = (screenSize.height - window.getHeight()) /2;

		window.setBounds(x, y, window.getWidth(), window.getHeight());
	}

	@SuppressWarnings("unchecked")
	public static <T> ComboBoxModel<T> createComboBoxModel(Collection<T> content, Class<? super T> targetClass) {
		T[] items = (T[]) Array.newInstance(targetClass, content.size()+1);
		items[0] = null; // Add blank object as the first value
	
		Iterator<T> iterator = content.iterator();
		for (int i = 1; i < items.length; i++) {
			items[i] = iterator.next();
		}
	
		return new DefaultComboBoxModel<T>(items);
	}

}
