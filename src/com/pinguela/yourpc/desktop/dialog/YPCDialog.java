package com.pinguela.yourpc.desktop.dialog;

import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.pinguela.yourpc.desktop.view.YPCView;

public class YPCDialog
extends JDialog {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the dialog.
	 */
	public YPCDialog(JFrame owner, JPanel contentPane) {
		super(owner);
		if (owner != null) {
			Rectangle ownerBounds = owner.getBounds();
			setBounds(ownerBounds.x + 50, ownerBounds.y + 50, 450, 300);
		}
		setContentPane(contentPane);
		pack();
	}
	
	public void setView(YPCView view) {
		setContentPane(view);
		pack();
		validate();
		repaint();
	}
	
}
