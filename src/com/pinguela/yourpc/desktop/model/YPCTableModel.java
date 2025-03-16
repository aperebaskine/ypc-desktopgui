package com.pinguela.yourpc.desktop.model;

import javax.swing.table.AbstractTableModel;

import com.pinguela.yourpc.desktop.components.ActionPane;

/**
 * Generic table model for for displaying a table containing an action pane as the last column.
 * TODO: Decouple action pane from model and add it to column model
 */
@SuppressWarnings("serial")
public abstract class YPCTableModel
extends AbstractTableModel {
	
	public static final String ACTION_PANE_COLUMN_NAME = "Actions";
	
	private final String[] columnNames;
	
	public YPCTableModel(String[] columnNames) {
		this.columnNames = columnNames;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length+1;
	}
	
	private boolean isActionPaneColumn(int columnIndex) {
		return columnIndex == getColumnCount()-1;
	}
	
	@Override
	public String getColumnName(int column) {

		if (isActionPaneColumn(column)) {
			return ACTION_PANE_COLUMN_NAME;
		}
		return columnNames[column];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		if (isActionPaneColumn(columnIndex)) {
			return ActionPane.class;
		} 
		return Object.class;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return isActionPaneColumn(columnIndex);
	}
	
	public abstract int getIndexOf(Object o);
	
}
