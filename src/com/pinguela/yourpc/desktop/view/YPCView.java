package com.pinguela.yourpc.desktop.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public abstract class YPCView
extends JPanel  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3661333179156749551L;
	
	public YPCView() {
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(8, 8, 8, 8));
	}

}
