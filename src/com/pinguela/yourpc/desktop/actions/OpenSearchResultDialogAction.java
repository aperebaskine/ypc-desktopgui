package com.pinguela.yourpc.desktop.actions;

import javax.swing.Icon;

import com.pinguela.yourpc.desktop.view.AbstractSearchView;

@SuppressWarnings("serial")
public abstract class OpenSearchResultDialogAction<T>
extends OpenDialogAction<T> {
	
	private AbstractSearchView<T> searchView;

	public OpenSearchResultDialogAction(AbstractSearchView<T> view) {
		this(view, null, null);
	}

	public OpenSearchResultDialogAction(AbstractSearchView<T> view, Icon icon) {
		this(view, null, icon);
	}

	public OpenSearchResultDialogAction(AbstractSearchView<T> view, String name, Icon icon) {
		super(name, icon);
		this.searchView = view;
	}

	public OpenSearchResultDialogAction(AbstractSearchView<T> view, String name) {
		this(view, name, null);
	}
	
	public AbstractSearchView<T> getSearchView() {
		return searchView;
	}

	@Override
	protected void onClose() {
		searchView.doSearch();
	}

}
