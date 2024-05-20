package com.pinguela.yourpc.desktop.actions;

import com.pinguela.yourpc.desktop.view.AbstractPaginatedSearchView;

public class LastPageAction extends PaginationAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3449481090249464307L;
	
	public LastPageAction(AbstractPaginatedSearchView<?> view) {
		super(view);
	}

	@Override
	protected int getNewPos(int maxPos, int currentPos, int pageSize) {
		int lastPageSize = maxPos % pageSize;
		return maxPos - lastPageSize - (lastPageSize == 0 ? pageSize : 0) + 1;
	}

}
