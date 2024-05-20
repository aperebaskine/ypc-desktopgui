package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.pinguela.yourpc.desktop.components.ActionPaneTableCell;

public class ActionPaneCellRenderer
extends ActionPaneTableCell
implements TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1449550974721130337L;
	
	/**
	 * Constructs a button cell renderer with edit and remove buttons.
	 */
	public ActionPaneCellRenderer() {
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());

			if (hasFocus) {
				setBorder(HIGHLIGHT_BORDER);
			} else {
				setBorder(NO_HIGHLIGHT_BORDER);
			}
		} else {
			setForeground(table.getForeground());
			setBackground(table.getBackground());
			setBorder(NO_HIGHLIGHT_BORDER);
		}
		return this;
	}

}
