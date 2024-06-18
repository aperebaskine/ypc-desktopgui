package com.pinguela.yourpc.desktop.actions;

import java.awt.EventQueue;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.EntityView;

@SuppressWarnings("serial")
public class EditItemAction<T> 
extends ItemAction<T> {

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
