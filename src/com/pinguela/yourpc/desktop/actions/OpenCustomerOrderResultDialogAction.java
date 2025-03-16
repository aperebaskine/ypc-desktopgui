package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.CustomerOrderSearchView;
import com.pinguela.yourpc.desktop.view.CustomerOrderView;
import com.pinguela.yourpc.desktop.view.EntityView;
import com.pinguela.yourpc.model.CustomerOrder;

@SuppressWarnings("serial")
public class OpenCustomerOrderResultDialogAction
extends OpenSearchResultDialogAction<CustomerOrder> {
	
	private CustomerOrderView dialogView;
	private CustomerOrder order;

	public OpenCustomerOrderResultDialogAction(CustomerOrderSearchView view) {
		super(view, Icons.EDIT_ICON);
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		Object[][] tableSelection = getSearchView().getTableSelection();

		dialogView = new CustomerOrderView();
		dialogView.addPropertyChangeListener(EntityView.ITEM_PROPERTY, this);
		order = (CustomerOrder) tableSelection[0][0];
		dialogView.setEntity(order);

		boolean isEditing = ActionCommands.TABLE_BUTTON.equals(e.getActionCommand());
		if (!isEditing) {
			dialogView.addAction(new EditItemAction<CustomerOrder>(dialogView, EntityView.VIEW_CARD));
		}
		dialogView.addAction(new CancelEditAction<CustomerOrder>(dialogView), EntityView.EDITOR_CARD);
		dialogView.addAction(new SaveCustomerOrderAction(dialogView), EntityView.EDITOR_CARD);

		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) getSearchView());
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
