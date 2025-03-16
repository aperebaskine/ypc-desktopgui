package com.pinguela.yourpc.desktop.model;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ListTableModel<E> 
extends YPCTableModel {
	
	private List<E> tableData;
	
	public ListTableModel(String[] columnNames) {
		this(columnNames, new ArrayList<E>());
	}
	
	public ListTableModel(String[] columnNames, List<E> tableData) {
		super(columnNames);
		this.tableData = new ArrayList<E>(tableData);
	}
	
	@Override
	public int getRowCount() {
		return tableData.size();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return tableData.get(rowIndex);
	}
	
	@Override
	public int getIndexOf(Object o) {
		return tableData.indexOf(o);
	}
	
	public void addRow(E e) {
		addRow(tableData.size(), e);
	}
	
	public final void addRow(int index, E e) {
		tableData.add(index, e);
		fireTableRowsInserted(index, index);
	}
	
	public final void updateRow(int index, E e) {
		tableData.remove(index);
		tableData.add(index, e);
		fireTableRowsUpdated(index, index);
	}

	public final void removeRow(int rowIndex) {
		tableData.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	public List<E> getData() {
		return tableData;
	}
	
	public void setData(List<E> data) {
		this.tableData.removeAll(this.tableData);
		this.tableData.addAll(data);
		fireTableDataChanged();
	}

}
