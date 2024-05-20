package com.pinguela.yourpc.desktop.actions;

import com.pinguela.yourpc.desktop.view.AbstractPaginatedSearchView;

public class FirstPageAction
extends PaginationAction {

	public FirstPageAction(AbstractPaginatedSearchView<?> view) {
		super(view);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2402827040623229106L;

	@Override
	protected int getNewPos(int maxPos, int currentPos, int pageSize) {
		return 1;
	}

}
