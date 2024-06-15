package com.pinguela.yourpc.desktop.actions;

import java.awt.EventQueue;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.EntityView;

public class EditItemAction<T> 
extends ItemAction<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4448781359085553435L;

	public EditItemAction(EntityView<T> view) {
		this(view, EntityView.VIEW_CARD);
	}

	public EditItemAction(EntityView<T> view, String card) {
		super(view, "Edit", Icons.EDIT_ICON);
		EventQueue.invokeLater(() -> {
			view.showCard(card);
		});
	}
	
	@Override
	protected void doAction() {
		getView().showCard(EntityView.EDITOR_CARD);	
	}

}
