package com.pinguela.yourpc.desktop.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public abstract class YPCView
extends JPanel {
		
	public YPCView() {
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(8, 8, 8, 8));
	}

}
