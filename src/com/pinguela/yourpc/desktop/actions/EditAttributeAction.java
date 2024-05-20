package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.components.AttributeInputPane;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.model.ActionPaneMapTableModel;
import com.pinguela.yourpc.model.Attribute;

public class EditAttributeAction 
extends AbstractDialogAction<Attribute<?>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 454485739077763180L;

	private JTable table;

	public EditAttributeAction(JTable table) {
		super(Icons.EDIT_ICON);
		this.table = table;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected YPCDialog createDialog(ActionEvent e) {
		ActionPaneMapTableModel<String, Attribute<?>> model = (ActionPaneMapTableModel<String, Attribute<?>>) table.getModel();
		return new YPCDialog((JFrame) SwingUtilities.getWindowAncestor(table),
				AttributeInputPane.getInstance((Attribute<?>) model.getValueAt(table.getSelectedRow(), 0)));
	}
	
	@Override
	protected boolean shouldSetRelativeLocation() {
		return true;
	}
	
	@Override
	protected void onClose() {	
	}

}
