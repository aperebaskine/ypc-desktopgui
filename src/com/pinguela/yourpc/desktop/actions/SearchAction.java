package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.AbstractSearchView;

@SuppressWarnings("serial")
public abstract class SearchAction<T>
extends YPCAction {

	private static final String LABEL = "Search...";

	private static final int DELAY = 75;

	private AbstractSearchView<T> view;
	
	private Timer timer;
	private final ActionListener timerActionListener = (e) -> {
		view.setTableModel(fetchData());
	};

	public SearchAction(AbstractSearchView<T> view) {
		this(view, LABEL, Icons.SEARCH_ICON);
		timer = new Timer(DELAY, timerActionListener);
		timer.setRepeats(false);
	}

	public SearchAction(AbstractSearchView<T> view, Icon icon) {
		this(view, LABEL, icon);
	}

	public SearchAction(AbstractSearchView<T> view, String name) {
		this(view, name, Icons.SEARCH_ICON);
	}

	public SearchAction(AbstractSearchView<T> view, String name, Icon icon) {
		super(name, icon);
		this.view = view;
	}

	protected AbstractSearchView<T> getView() {
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
		if (timer.isRunning()) {
			timer.restart();
		} else {
			timer.start();
		}
	}
	
	protected abstract TableModel fetchData();

}
