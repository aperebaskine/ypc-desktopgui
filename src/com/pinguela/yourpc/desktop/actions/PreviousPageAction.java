package com.pinguela.yourpc.desktop.actions;

import com.pinguela.yourpc.desktop.view.AbstractPaginatedSearchView;

@SuppressWarnings("serial")
public class PreviousPageAction
extends PaginationAction {
	
	public PreviousPageAction(AbstractPaginatedSearchView<?> view) {
		super(view);
	}
	
	@Override
	protected int getNewPos(int maxPos, int currentPos, int pageSize) {
		return currentPos - pageSize;
	}

}
