package com.pinguela.yourpc.desktop.actions;

import com.pinguela.yourpc.desktop.view.AbstractPaginatedSearchView;

public class PreviousPageAction extends PaginationAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1729512972675956314L;
	
	public PreviousPageAction(AbstractPaginatedSearchView<?> view) {
		super(view);
	}
	
	@Override
	protected int getNewPos(int maxPos, int currentPos, int pageSize) {
		return currentPos - pageSize;
	}

}
