package com.pinguela.yourpc.desktop.actions;

import javax.swing.Icon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.SearchView;

public abstract class SearchAction<T>
extends YPCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7178913665166802817L;

	private static final String LABEL = "Search...";

	private static final int DELAY = 150;

	private SearchView<T> view;
	private long lastSearchTime = 0;

	public SearchAction(SearchView<T> view) {
		this(view, LABEL, Icons.SEARCH_ICON);
	}

	public SearchAction(SearchView<T> view, Icon icon) {
		this(view, LABEL, icon);
	}

	public SearchAction(SearchView<T> view, String name) {
		this(view, name, Icons.SEARCH_ICON);
	}

	public SearchAction(SearchView<T> view, String name, Icon icon) {
		super(name, icon);
		this.view = view;
	}

	protected SearchView<T> getView() {
		return view;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		textChanged(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		textChanged(e);
	}


	protected final void textChanged(DocumentEvent e) {
		if (e.getDocument().getLength() > 2) {
			doAction();	
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		doAction();
	}


	@Override
	protected void doAction() {
		long now = System.currentTimeMillis();
		if (now-lastSearchTime>DELAY) { // Cuando estés trabajando lo mejoras con un synchronize			
			view.setModel(fetchData());			
			this.lastSearchTime = System.currentTimeMillis();
		}
	}
	
	protected abstract TableModel fetchData();

}
