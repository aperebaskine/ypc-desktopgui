package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import com.pinguela.yourpc.desktop.view.AbstractPaginatedSearchView;

@SuppressWarnings("serial")
public abstract class PaginationAction
extends YPCAction {
	
	private AbstractPaginatedSearchView<?> view;
	
	public PaginationAction(AbstractPaginatedSearchView<?> view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		doAction();
	}
	
	protected final void doAction() {
		view.setPos(getNewPos(view.getResultCount(), view.getPos(), view.getPageSize()));
	}
	
	protected abstract int getNewPos(int maxPos, int currentPos, int pageSize);

}
