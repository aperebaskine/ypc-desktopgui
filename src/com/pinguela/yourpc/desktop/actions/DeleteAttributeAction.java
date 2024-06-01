package com.pinguela.yourpc.desktop.actions;

import javax.swing.JTable;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.model.ActionPaneMapTableModel;
import com.pinguela.yourpc.model.Attribute;

public class DeleteAttributeAction 
extends YPCAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4005145724650194421L;
	
	private JTable table;

	public DeleteAttributeAction(JTable table) {
		super(Icons.DELETE_ICON);
		this.table = table;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void doAction() {
		Attribute<?> attribute = (Attribute<?>) table.getCellEditor().getCellEditorValue();
		((ActionPaneMapTableModel<String, Attribute<?>>) table.getModel()).removeRow(attribute.getName());
	}

}
