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
	
	private boolean closeOnCancel = false;
	
	public CancelEditAction(ItemView<T> view) {
		this(view, false);
	}

	public CancelEditAction(ItemView<T> view, boolean closeWindow) {
		super(view, "Cancel", Icons.CANCEL_ICON);
		this.closeOnCancel = closeWindow;
	}

	@Override
	protected void doAction() {
		if (closeOnCancel) {
			SwingUtilities.getWindowAncestor((Component) getView()).dispose();
		} else {
			getView().resetFields();
			getView().showCard(ItemView.ACTION_CARD);
		}		
	}

}
