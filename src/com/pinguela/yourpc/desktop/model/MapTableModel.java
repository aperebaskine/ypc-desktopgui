package com.pinguela.yourpc.desktop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@SuppressWarnings("serial")
public class MapTableModel<K extends Comparable<K>, V> 
extends YPCTableModel {
	
	protected List<K> tableIndex;
	protected Map<K, V> tableData;
	
	public MapTableModel(String[] columnNames) {
		this(columnNames, new HashMap<K, V>());
	}
	
	public MapTableModel(String[] columnNames, Map<K, V> map) {
		super(columnNames);
		this.tableIndex = new ArrayList<K>(map.keySet());
		this.tableData = new TreeMap<K, V>(map);
	}
	
	@Override
	public int getRowCount() {
		return tableData.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return tableData.get(tableIndex.get(rowIndex));
	}
	
	@Override
	public int getIndexOf(Object key) {
		return tableIndex.indexOf(key);
	}
	
	public void addRow(K key, V value) {
		
		if (tableData.containsKey(key)) {
			updateRow(value, tableIndex.indexOf(key));
			return;
		}

		tableIndex.add(key);
		Collections.sort(tableIndex);
		tableData.put(key, value);
		
		int index = tableIndex.indexOf(key);
		fireTableRowsInserted(index, index);
	}

	public void updateRow(V value, int index) {
		K key = tableIndex.get(index);
		tableData.put(key, value);
		fireTableRowsUpdated(index, index);
	}

	public void removeRow(K key) {
		int index = tableIndex.indexOf(key);
		tableData.remove(key);
		tableIndex.remove(index);
		fireTableRowsDeleted(index, index);
	}
	
	public void removeRow(int rowIndex) {
		removeRow(tableIndex.get(rowIndex));
	}
	
	public void clear() {
		tableIndex.removeAll(tableIndex);
		tableData.clear();
		fireTableDataChanged();
	}
	
	public Map<K, V> getData() {
		return tableData;
	}
	
	public void setData(Map<K, V> tableData) {
		this.tableIndex = new ArrayList<K>(tableData.keySet());
		this.tableData.clear();
		this.tableData.putAll(tableData);
		fireTableDataChanged();
	}

}
