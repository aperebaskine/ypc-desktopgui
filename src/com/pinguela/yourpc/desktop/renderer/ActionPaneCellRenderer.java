package com.pinguela.yourpc.desktop.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.pinguela.yourpc.desktop.components.ActionPaneTableCell;

@SuppressWarnings("serial")
public class ActionPaneCellRenderer
extends ActionPaneTableCell
implements TableCellRenderer {
	
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
