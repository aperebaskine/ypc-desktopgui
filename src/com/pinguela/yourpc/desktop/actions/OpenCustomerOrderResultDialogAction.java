package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.CustomerOrderSearchView;
import com.pinguela.yourpc.desktop.view.CustomerOrderView;
import com.pinguela.yourpc.desktop.view.ItemView;
import com.pinguela.yourpc.model.CustomerOrder;

public class OpenCustomerOrderResultDialogAction extends OpenSearchResultDialogAction<CustomerOrder> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4798996272514762277L;
	
	private CustomerOrderView dialogView;
	private CustomerOrder order;

	public OpenCustomerOrderResultDialogAction(CustomerOrderSearchView view) {
		super(view, Icons.EDIT_ICON);
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		JTable table = getSearchView().getTable();
		int row = table.getSelectedRow();
		int column = table.getSelectedColumn();

		dialogView = new CustomerOrderView();
		dialogView.addPropertyChangeListener(ItemView.ITEM_PROPERTY, this);
		order = (CustomerOrder) table.getValueAt(row, column);
		dialogView.setItem(order);

		boolean isEditing = ActionCommands.TABLE_BUTTON.equals(e.getActionCommand());
		if (!isEditing) {
			dialogView.addAction(new EditItemAction<CustomerOrder>(dialogView, ItemView.VIEW_CARD));
		}
		dialogView.addAction(new CancelEditAction<CustomerOrder>(dialogView), ItemView.EDITOR_CARD);
		dialogView.addAction(new SaveCustomerOrderAction(dialogView), ItemView.EDITOR_CARD);

		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(table);
		YPCDialog dialog = new YPCDialog(frame, dialogView);
		dialog.setTitle("Customer order editor");
		return dialog;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		CustomerOrder old = (CustomerOrder) evt.getOldValue();
		CustomerOrder product = (CustomerOrder) evt.getNewValue();

		if (old != null && old.getId() == product.getId()) { // Product was updated
			getSearchView().doSearch();
			return;
		}
	}

	@Override
	protected boolean shouldSetRelativeLocation() {
		return false;
	}

}
