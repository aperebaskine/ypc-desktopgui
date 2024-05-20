package com.pinguela.yourpc.desktop.actions;

import com.pinguela.yourpc.desktop.view.AbstractPaginatedSearchView;

public class NextPageAction extends PaginationAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7736215535368298315L;

	public NextPageAction(AbstractPaginatedSearchView<?> view) {
		super(view);
	}
	
	@Override
	protected int getNewPos(int maxPos, int currentPos, int pageSize) {
		return currentPos + pageSize;
	}

}
