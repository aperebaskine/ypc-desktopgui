package com.pinguela.yourpc.desktop.view;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.pinguela.yourpc.desktop.constants.OrderLineTableConstants;
import com.pinguela.yourpc.desktop.model.ActionPaneListTableModel;
import com.pinguela.yourpc.model.OrderLine;

public class OrderLineListView 
extends AbstractItemView<List<OrderLine>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2943904871193327312L;

	private JTable orderLineTable;

	public OrderLineListView() {
		getViewPanel().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane orderLineTableScrollPane = new JScrollPane();
		getViewPanel().add(orderLineTableScrollPane);
		
		orderLineTable = new JTable();
		orderLineTableScrollPane.setViewportView(orderLineTable);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<OrderLine> getNewItem() {
		return ((ActionPaneListTableModel<OrderLine>) orderLineTable.getModel()).getData();
	}

	@Override
	public void resetFields() {
		orderLineTable.setModel(new ActionPaneListTableModel<OrderLine>(OrderLineTableConstants.COLUMN_NAMES));
	}

	@Override
	protected void setFieldsEditable(boolean isEditable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onItemSet() {
		orderLineTable.setModel(new ActionPaneListTableModel<>(OrderLineTableConstants.COLUMN_NAMES, getItem()));
	}

}
