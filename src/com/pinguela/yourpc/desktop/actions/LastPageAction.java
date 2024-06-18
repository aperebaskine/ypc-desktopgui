package com.pinguela.yourpc.desktop.actions;

import com.pinguela.yourpc.desktop.view.AbstractPaginatedSearchView;

@SuppressWarnings("serial")
public class LastPageAction
extends PaginationAction {
	
	public LastPageAction(AbstractPaginatedSearchView<?> view) {
		super(view);
	}

	@Override
	protected int getNewPos(int maxPos, int currentPos, int pageSize) {
		int lastPageSize = maxPos % pageSize;
		return maxPos - lastPageSize - (lastPageSize == 0 ? pageSize : 0) + 1;
	}

}
