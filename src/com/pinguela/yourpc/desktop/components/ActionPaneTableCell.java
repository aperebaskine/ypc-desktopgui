package com.pinguela.yourpc.desktop.components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class ActionPaneTableCell 
extends ActionPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3749105802930406609L;
	
	public static final Border HIGHLIGHT_BORDER = UIManager.getBorder("Table.focusCellHighlightBorder");
	public static final Border NO_HIGHLIGHT_BORDER = UIManager.getBorder("Table.cellNoFocusBorder");


	private static final Dimension BUTTON_DIMENSIONS = new Dimension(24, 24);
	private static final int MARGIN = 1;

	private static final ContainerAdapter BUTTON_FORMATTER = new ContainerAdapter() {
		@Override
		public void componentAdded(ContainerEvent e) {
			ActionPaneTableCell cell = (ActionPaneTableCell) e.getSource();
			JButton button = (JButton) e.getChild();
			button.setText("");
			button.setPreferredSize(BUTTON_DIMENSIONS);
			cell.recalcWidth();
		}
	};

	public ActionPaneTableCell() {
		super();
		addContainerListener(BUTTON_FORMATTER);
		FlowLayout layout = (FlowLayout) getLayout();
		layout.setAlignment(FlowLayout.CENTER);
		layout.setHgap(MARGIN);
		layout.setVgap(MARGIN);
	}

	private void recalcWidth() {
		int width = (BUTTON_DIMENSIONS.width * getComponentCount()) + (MARGIN * getComponentCount()) + 1;
		setPreferredSize(new Dimension(width, 26));
	}
	
}
