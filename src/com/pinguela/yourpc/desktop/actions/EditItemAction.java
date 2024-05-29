package com.pinguela.yourpc.desktop.actions;

import java.awt.EventQueue;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.ItemView;

public class EditItemAction<T> 
extends ItemAction<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4448781359085553435L;

	public EditItemAction(ItemView<T> view) {
		this(view, ItemView.VIEW_CARD);
	}

	public EditItemAction(ItemView<T> view, String card) {
		super(view, "Edit", Icons.EDIT_ICON);
		EventQueue.invokeLater(() -> {
			view.showCard(card);
		});
	}
	
	@Override
	protected void doAction() {
		getView().showCard(ItemView.EDITOR_CARD);	
	}

}
