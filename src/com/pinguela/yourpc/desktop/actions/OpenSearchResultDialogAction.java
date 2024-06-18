package com.pinguela.yourpc.desktop.actions;

import javax.swing.Icon;

import com.pinguela.yourpc.desktop.view.SearchView;

@SuppressWarnings("serial")
public abstract class OpenSearchResultDialogAction<T>
extends OpenDialogAction<T> {
	
	private SearchView<T> searchView;

	public OpenSearchResultDialogAction(SearchView<T> view) {
		this(view, null, null);
	}

	public OpenSearchResultDialogAction(SearchView<T> view, Icon icon) {
		this(view, null, icon);
	}

	public OpenSearchResultDialogAction(SearchView<T> view, String name, Icon icon) {
		super(name, icon);
		this.searchView = view;
	}

	public OpenSearchResultDialogAction(SearchView<T> view, String name) {
		this(view, name, null);
	}
	
	public SearchView<T> getSearchView() {
		return searchView;
	}

	@Override
	protected void onClose() {
		searchView.doSearch();
	}

}
