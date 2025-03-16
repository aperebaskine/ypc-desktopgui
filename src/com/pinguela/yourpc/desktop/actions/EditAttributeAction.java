package com.pinguela.yourpc.desktop.actions;

import javax.swing.JTable;

import com.pinguela.yourpc.desktop.components.AttributeValueInputPane;
import com.pinguela.yourpc.desktop.components.InputPane;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.model.MapTableModel;
import com.pinguela.yourpc.model.dto.AttributeDTO;

@SuppressWarnings("serial")
public class EditAttributeAction 
extends GetInputAction<AttributeDTO<?>> {

	private JTable table;
	private AttributeDTO<?> editingAttribute;
	
	private Integer handlingMode;
	private boolean showUnassignedValues;
	
	
	public EditAttributeAction(JTable table, boolean showUnassignedValues) {
		this(table, null, showUnassignedValues);
	}

	public EditAttributeAction(JTable table, Integer handlingMode, boolean showUnassignedValues) {
		super(Icons.EDIT_ICON);
		this.table = table;
		this.handlingMode = handlingMode;
		this.showUnassignedValues = showUnassignedValues;
	}

	@Override
	protected InputPane<AttributeDTO<?>> initializeInputPane() {
		this.editingAttribute = (AttributeDTO<?>) table.getCellEditor().getCellEditorValue();
		return new AttributeValueInputPane(editingAttribute, handlingMode, showUnassignedValues);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void onConfirm() {
		MapTableModel<String, AttributeDTO<?>> model =
				(MapTableModel<String, AttributeDTO<?>>) table.getModel();
		model.updateRow(getInput(), model.getIndexOf(editingAttribute.getName()));
	}

}
