package com.pinguela.yourpc.desktop.actions;

import javax.swing.Icon;

import com.pinguela.yourpc.desktop.view.EntityView;

public abstract class ItemAction<T>
extends YPCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9215531017595734232L;
	
	private EntityView<T> view;

	public ItemAction(EntityView<T> view) {
		this(view, null, null);
	}

	public ItemAction(EntityView<T> view, Icon icon) {
		this(view, null, icon);
	}

	public ItemAction(EntityView<T> view, String name, Icon icon) {
		super(name, icon);
		this.view = view;
	}

	public ItemAction(EntityView<T> view, String name) {
		this(view, name, null);
	}

	public EntityView<T> getView() {
		return view;
	}
	
}
