package com.pinguela.yourpc.desktop.actions;

import javax.swing.Icon;

import com.pinguela.yourpc.desktop.view.SearchView;

public abstract class AbstractSearchViewDialogAction<T> extends AbstractDialogAction<T> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2858488688561912610L;
	
	private SearchView<T> searchView;

	public AbstractSearchViewDialogAction(SearchView<T> view) {
		this(view, null, null);
	}

	public AbstractSearchViewDialogAction(SearchView<T> view, Icon icon) {
		this(view, null, icon);
	}

	public AbstractSearchViewDialogAction(SearchView<T> view, String name, Icon icon) {
		super(name, icon);
		this.searchView = view;
	}

	public AbstractSearchViewDialogAction(SearchView<T> view, String name) {
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
