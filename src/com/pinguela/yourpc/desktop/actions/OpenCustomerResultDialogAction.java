package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.CustomerSearchView;
import com.pinguela.yourpc.desktop.view.CustomerView;
import com.pinguela.yourpc.desktop.view.EntityView;
import com.pinguela.yourpc.model.Customer;

public class OpenCustomerResultDialogAction 
extends OpenSearchResultDialogAction<Customer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5139663434924027636L;
	
	private CustomerView dialogView;
	
	private Customer c;
	
	public OpenCustomerResultDialogAction(CustomerSearchView view) {
		super(view, Icons.EDIT_ICON);
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		JTable table = getSearchView().getTable();
		int row = table.getSelectedRow();
		int column = table.getSelectedColumn();

		dialogView = new CustomerView();
		dialogView.addPropertyChangeListener(EntityView.ITEM_PROPERTY, this);
		c = (Customer) table.getValueAt(row, column);
		dialogView.setEntity(c);

		boolean editOnOpen = ActionCommands.TABLE_BUTTON.equals(e.getActionCommand());
		if (!editOnOpen) {
			dialogView.addAction(new DeleteCustomerAction(dialogView));
			dialogView.addAction(new EditItemAction<Customer>(dialogView, EntityView.VIEW_CARD));
		}
		dialogView.addAction(new CancelEditAction<Customer>(dialogView), EntityView.EDITOR_CARD);
		dialogView.addAction(new SaveCustomerAction(dialogView), EntityView.EDITOR_CARD);

		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(table);
		YPCDialog dialog = new YPCDialog(frame, dialogView);
		dialog.setTitle("Customer editor");
		return dialog;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Customer old = (Customer) evt.getOldValue();
		Customer customer = (Customer) evt.getNewValue();

		if (old != null && old.getId() == customer.getId()) {
			getSearchView().doSearch();
			return;
		}
	}
	
	protected CustomerView getDialogView() {
		return dialogView;
	}

	@Override
	protected boolean shouldSetRelativeLocation() {
		return false;
	}

}
