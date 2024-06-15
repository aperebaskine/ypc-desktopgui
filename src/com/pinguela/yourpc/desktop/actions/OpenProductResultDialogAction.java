package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.EntityView;
import com.pinguela.yourpc.desktop.view.ProductSearchView;
import com.pinguela.yourpc.desktop.view.ProductView;
import com.pinguela.yourpc.model.Product;

public class OpenProductResultDialogAction 
extends OpenSearchResultDialogAction<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6020214024891107072L;
	
	private ProductView dialogView;
	private Product p;

	public OpenProductResultDialogAction(ProductSearchView view) {
		super(view, Icons.EDIT_ICON);
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		JTable table = getSearchView().getTable();
		int row = table.getSelectedRow();
		int column = table.getSelectedColumn();

		dialogView = new ProductView();
		dialogView.addPropertyChangeListener(EntityView.ITEM_PROPERTY, this);

		boolean isEditing = ActionCommands.TABLE_BUTTON.equals(e.getActionCommand());
		if (!isEditing) {
			dialogView.addAction(new DeleteProductAction(dialogView));
			dialogView.addAction(new EditItemAction<Product>(dialogView, EntityView.VIEW_CARD));
		}
		dialogView.addAction(new CancelEditAction<Product>(dialogView), EntityView.EDITOR_CARD);
		dialogView.addAction(new SaveProductAction(dialogView), EntityView.EDITOR_CARD);
		dialogView.addAttributeAction(new AddAttributeAction(dialogView));

		p = (Product) table.getValueAt(row, column);
		dialogView.setItem(p);

		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(table);
		YPCDialog dialog = new YPCDialog(frame, dialogView);
		dialog.setTitle("Product editor");
		return dialog;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Product old = (Product) evt.getOldValue();
		Product product = (Product) evt.getNewValue();

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
