package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.EntityView;
import com.pinguela.yourpc.desktop.view.RMASearchView;
import com.pinguela.yourpc.desktop.view.RMAView;
import com.pinguela.yourpc.model.RMA;

@SuppressWarnings("serial")
public class OpenRMAResultDialogAction 
extends OpenSearchResultDialogAction<RMA> {
	
	private RMAView dialogView;
	private RMA rma;

	public OpenRMAResultDialogAction(RMASearchView view) {
		super(view, Icons.EDIT_ICON);
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		Object[][] tableSelection = getSearchView().getTableSelection();
		
		dialogView = new RMAView();
		dialogView.addPropertyChangeListener(EntityView.ITEM_PROPERTY, this);
		rma = (RMA) tableSelection[0][0];
		dialogView.setEntity(rma);

		boolean isEditing = ActionCommands.TABLE_BUTTON.equals(e.getActionCommand());
		if (!isEditing) {
			dialogView.addAction(new EditItemAction<RMA>(dialogView, EntityView.VIEW_CARD));
		}
		dialogView.addAction(new CancelEditAction<RMA>(dialogView), EntityView.EDITOR_CARD);
		dialogView.addAction(new SaveRMAAction(dialogView), EntityView.EDITOR_CARD);

		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) getSearchView());
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
