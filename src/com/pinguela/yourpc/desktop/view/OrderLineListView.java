package com.pinguela.yourpc.desktop.view;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.pinguela.yourpc.desktop.constants.OrderLineTableConstants;
import com.pinguela.yourpc.desktop.model.ListTableModel;
import com.pinguela.yourpc.desktop.renderer.OrderLineTableCellRenderer;
import com.pinguela.yourpc.model.OrderLine;

@SuppressWarnings("serial")
public class OrderLineListView 
extends AbstractEntityView<List<OrderLine>> {

	private JTable orderLineTable;

	public OrderLineListView() {
		
		JPanel viewPanel = getViewPanel();
		
		viewPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane orderLineTableScrollPane = new JScrollPane();
		viewPanel.add(orderLineTableScrollPane);
		
		orderLineTable = new JTable();
		orderLineTable.setDefaultRenderer(Object.class, new OrderLineTableCellRenderer());
		orderLineTableScrollPane.setViewportView(orderLineTable);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<OrderLine> getEntityFromFields() {
		return ((ListTableModel<OrderLine>) orderLineTable.getModel()).getData();
	}

	@Override
	public void resetFields() {
		orderLineTable.setModel(new ListTableModel<OrderLine>(OrderLineTableConstants.COLUMN_NAMES));
	}

	@Override
	protected void setFieldsEditable(boolean isEditable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void loadItemData() {
		orderLineTable.setModel(new ListTableModel<>(OrderLineTableConstants.COLUMN_NAMES, getCurrentEntity()));
	}

}
