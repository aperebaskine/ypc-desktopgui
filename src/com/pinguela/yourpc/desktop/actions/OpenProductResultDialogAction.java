package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.EntityView;
import com.pinguela.yourpc.desktop.view.ProductSearchView;
import com.pinguela.yourpc.desktop.view.ProductView;
import com.pinguela.yourpc.model.dto.LocalizedProductDTO;

@SuppressWarnings("serial")
public class OpenProductResultDialogAction 
extends OpenSearchResultDialogAction<LocalizedProductDTO> {
	
	private ProductView dialogView;
	private LocalizedProductDTO p;

	public OpenProductResultDialogAction(ProductSearchView view) {
		super(view, Icons.EDIT_ICON);
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		Object[][] tableSelection = getSearchView().getTableSelection();

		dialogView = new ProductView();
		dialogView.addPropertyChangeListener(EntityView.ITEM_PROPERTY, this);

		boolean isEditing = ActionCommands.TABLE_BUTTON.equals(e.getActionCommand());
		if (!isEditing) {
			dialogView.addAction(new DeleteProductAction(dialogView));
			dialogView.addAction(new EditItemAction<LocalizedProductDTO>(dialogView, EntityView.VIEW_CARD));
		}
		dialogView.addAction(new CancelEditAction<LocalizedProductDTO>(dialogView), EntityView.EDITOR_CARD);
		dialogView.addAction(new SaveProductAction(dialogView), EntityView.EDITOR_CARD);

		p = (LocalizedProductDTO) tableSelection[0][0];
		dialogView.setEntity(p);

		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) getSearchView());
		YPCDialog dialog = new YPCDialog(frame, dialogView);
		dialog.setTitle("Product editor");
		return dialog;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		LocalizedProductDTO old = (LocalizedProductDTO) evt.getOldValue();
		LocalizedProductDTO product = (LocalizedProductDTO) evt.getNewValue();

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
