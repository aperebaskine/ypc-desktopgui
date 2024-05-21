package com.pinguela.yourpc.desktop.actions;

import java.awt.EventQueue;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.ItemView;

public class ItemEditAction<T> 
extends ItemAction<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4448781359085553435L;

	public ItemEditAction(ItemView<T> view) {
		this(view, ItemView.VIEW_CARD);
	}

	public ItemEditAction(ItemView<T> view, String card) {
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
