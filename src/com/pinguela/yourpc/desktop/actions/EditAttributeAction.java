package com.pinguela.yourpc.desktop.actions;

import javax.swing.JTable;

import com.pinguela.yourpc.desktop.components.AttributeValueInputPane;
import com.pinguela.yourpc.desktop.components.InputPane;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.model.ActionPaneMapTableModel;
import com.pinguela.yourpc.model.Attribute;

public class EditAttributeAction 
extends GetInputAction<Attribute<?>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 454485739077763180L;

	private JTable table;
	private boolean showUnassignedValues;
	private Attribute<?> editingAttribute;

	public EditAttributeAction(JTable table, boolean showUnassignedValues) {
		super(Icons.EDIT_ICON);
		this.table = table;
		this.showUnassignedValues = showUnassignedValues;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected InputPane<Attribute<?>> initializeInputPane() {
		this.editingAttribute = (Attribute<?>) table.getCellEditor().getCellEditorValue();
		return (InputPane<Attribute<?>>) AttributeValueInputPane.getInstance(editingAttribute, showUnassignedValues);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void onConfirm() {
		ActionPaneMapTableModel<String, Attribute<?>> model =
				(ActionPaneMapTableModel<String, Attribute<?>>) table.getModel();
		model.updateRow(getInput(), model.getIndexOf(editingAttribute.getName()));
	}

}
