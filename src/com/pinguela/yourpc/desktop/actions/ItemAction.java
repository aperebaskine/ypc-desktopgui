package com.pinguela.yourpc.desktop.actions;

import javax.swing.Icon;

import com.pinguela.yourpc.desktop.view.ItemView;

public abstract class ItemAction<T>
extends YPCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9215531017595734232L;
	
	private ItemView<T> view;

	public ItemAction(ItemView<T> view) {
		this(view, null, null);
	}

	public ItemAction(ItemView<T> view, Icon icon) {
		this(view, null, icon);
	}

	public ItemAction(ItemView<T> view, String name, Icon icon) {
		super(name, icon);
		this.view = view;
	}

	public ItemAction(ItemView<T> view, String name) {
		this(view, name, null);
	}

	public ItemView<T> getView() {
		return view;
	}
	
}
