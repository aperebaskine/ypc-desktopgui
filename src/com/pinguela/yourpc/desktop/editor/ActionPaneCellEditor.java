package com.pinguela.yourpc.desktop.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellEditor;

import com.pinguela.yourpc.desktop.components.ActionPaneTableCell;

@SuppressWarnings("serial")
public class ActionPaneCellEditor 
extends AbstractCellEditor 
implements TableCellEditor {

	private ActionPaneTableCell cell;
	private Object value;

	public ActionPaneCellEditor() {
		super();

		cell = new ActionPaneTableCell();
		cell.setBorder(ActionPaneTableCell.NO_HIGHLIGHT_BORDER);
		// In order to avoid visual bugs, fall back to renderer if background is clicked
		cell.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				fireEditingCanceled();
			}
		});
		cell.addContainerListener(new CellButtonMouseListener());
	}

	public void addAction(Action action) {
		cell.addAction(action);
	}

	public void addAction(Action action, String actionCommand) {
		cell.addAction(action, actionCommand);
	}

	public void setBorder(Border border) {
		cell.setBorder(border);
	}

	public void setForeground(Color fg) {
		cell.setForeground(fg);
	}

	public void setBackground(Color bg) {
		cell.setBackground(bg);
	}

	@Override
	public Object getCellEditorValue() {
		return value;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.value = value;
		return cell;
	}

	private class CellButtonMouseListener extends MouseAdapter implements ContainerListener {

		@Override
		public void mouseReleased(MouseEvent e) {
			JButton button = (JButton) e.getSource();
			if (button.getComponentAt(e.getPoint()) == null) {
				fireEditingCanceled();
			} else {
				// TODO delegate to action
				fireEditingStopped();
			}
		}

		@Override
		public void componentAdded(ContainerEvent e) {
			JButton button = (JButton) e.getChild();
			button.addMouseListener(this);
		}

		@Override
		public void componentRemoved(ContainerEvent e) {}

	}

}
