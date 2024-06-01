package com.pinguela.yourpc.desktop.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;

import com.pinguela.yourpc.model.AbstractPerson;

public class SwingUtils {

	// During resizing, specify whether to check the horizontal or vertical margin size
	public static final int HORIZONTAL_MARGIN = 0;
	public static final int VERTICAL_MARGIN = 1;

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/YYYY");
	private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");

	public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
		Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
		BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		return outputImage;
	}

	public static BufferedImage resize(BufferedImage image, JPanel targetPanel) {

		int width;
		int height;

		int panelWidth = targetPanel.getWidth();
		int panelHeight = targetPanel.getHeight();

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
		return margin % 2 == 0 ? margin + 1 : margin;
	}

	public static String formatDate(Date date) {
		return DATE_FORMAT.format(date);
	}	
	
	public static String formatDateTime(Date date) {
		return DATETIME_FORMAT.format(date);
	}	

	public static boolean isDocumentEmpty(Document d) {
		return d.getLength() < 1;
	}

	public static void centerOnScreen(Window window) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (screenSize.width - window.getWidth()) / 2;
		int y = (screenSize.height - window.getHeight()) /2;

		window.setBounds(x, y, window.getWidth(), window.getHeight());
	}
	
	public static void showDatabaseAccessErrorDialog(Component source) {
		JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(source), 
				"Could not connect to database.", "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static String formatFullName(AbstractPerson person) {
		return String.join(" ", person.getFirstName(), formatLastNames(person));
	}
	
	public static String formatLastNames(AbstractPerson person) {
		return person.getLastName2() == null ? person.getLastName1() :
					String.join(" ", person.getLastName1(), person.getLastName2());
	}

}
