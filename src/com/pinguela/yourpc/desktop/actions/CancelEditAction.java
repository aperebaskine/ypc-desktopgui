package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.EntityView;

@SuppressWarnings("serial")
public class CancelEditAction<T> 
extends ItemAction<T> {

	public CancelEditAction(EntityView<T> view) {
		super(view, "Cancel", Icons.CANCEL_ICON);
	}

	@Override
	protected void doAction() {	
		
		EntityView<T> view = getView();
		
		if (!view.showCard(EntityView.VIEW_CARD)) {
			SwingUtilities.getWindowAncestor((Component) getView()).dispose();
		} else {
			view.toDefaultState();
		}
	}

}
