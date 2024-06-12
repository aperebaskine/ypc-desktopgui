package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.ItemView;

public class CancelEditAction<T> 
extends ItemAction<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3158272366624004627L;

	public CancelEditAction(ItemView<T> view) {
		super(view, "Cancel", Icons.CANCEL_ICON);
	}

	@Override
	protected void doAction() {	
		
		ItemView<T> view = getView();
		
		if (!view.showCard(ItemView.VIEW_CARD)) {
			SwingUtilities.getWindowAncestor((Component) getView()).dispose();
		} else {
			view.toDefaultState();
		}
	}

}
