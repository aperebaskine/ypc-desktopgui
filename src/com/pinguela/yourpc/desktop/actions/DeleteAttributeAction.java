package com.pinguela.yourpc.desktop.actions;

import javax.swing.JTable;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.model.MapTableModel;
import com.pinguela.yourpc.model.dto.AttributeDTO;

@SuppressWarnings("serial")
public class DeleteAttributeAction 
extends YPCAction {
	
	private JTable table;

	public DeleteAttributeAction(JTable table) {
		super(Icons.DELETE_ICON);
		this.table = table;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void doAction() {
		AttributeDTO<?> attribute = (AttributeDTO<?>) table.getCellEditor().getCellEditorValue();
		((MapTableModel<String, AttributeDTO<?>>) table.getModel()).removeRow(attribute.getName());
	}

}
