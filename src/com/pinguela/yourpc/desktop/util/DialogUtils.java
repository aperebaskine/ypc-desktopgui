package com.pinguela.yourpc.desktop.util;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class DialogUtils {

	public static void showDatabaseAccessErrorDialog(Component source) {
		JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(source), 
				"Could not connect to database.", "Error", JOptionPane.ERROR_MESSAGE);
	}

}
