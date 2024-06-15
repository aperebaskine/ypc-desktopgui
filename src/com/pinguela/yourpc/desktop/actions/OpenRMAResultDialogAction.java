package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.EntityView;
import com.pinguela.yourpc.desktop.view.RMASearchView;
import com.pinguela.yourpc.desktop.view.RMAView;
import com.pinguela.yourpc.model.RMA;

public class OpenRMAResultDialogAction 
extends OpenSearchResultDialogAction<RMA> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2430812179643361360L;
	
	private RMAView dialogView;
	private RMA rma;

	public OpenRMAResultDialogAction(RMASearchView view) {
		super(view, Icons.EDIT_ICON);
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		JTable table = getSearchView().getTable();
		int row = table.getSelectedRow();
		int column = table.getSelectedColumn();

		dialogView = new RMAView();
		dialogView.addPropertyChangeListener(EntityView.ITEM_PROPERTY, this);
		rma = (RMA) table.getValueAt(row, column);
		dialogView.setItem(rma);

		boolean isEditing = ActionCommands.TABLE_BUTTON.equals(e.getActionCommand());
		if (!isEditing) {
			dialogView.addAction(new EditItemAction<RMA>(dialogView, EntityView.VIEW_CARD));
		}
		dialogView.addAction(new CancelEditAction<RMA>(dialogView), EntityView.EDITOR_CARD);
		dialogView.addAction(new SaveRMAAction(dialogView), EntityView.EDITOR_CARD);

		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(table);
		YPCDialog dialog = new YPCDialog(frame, dialogView);
		dialog.setTitle("RMA editor");
		return dialog;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		RMA old = (RMA) evt.getOldValue();
		RMA product = (RMA) evt.getNewValue();

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
