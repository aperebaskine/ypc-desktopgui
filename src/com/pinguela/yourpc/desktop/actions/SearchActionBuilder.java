package com.pinguela.yourpc.desktop.actions;

import java.lang.reflect.Modifier;

import com.pinguela.yourpc.desktop.view.AbstractSearchView;

public class SearchActionBuilder<T, A extends SearchAction<T>> {

	private AbstractSearchView<T> view;
	private Class<A> targetClass;
	
	private A instance;
	
	public SearchActionBuilder(Class<A> targetClass) {
		if (Modifier.isAbstract(targetClass.getModifiers())
				|| Modifier.isInterface(targetClass.getModifiers())) {
			throw new IllegalArgumentException("Target class cannot be abstract or an interface.");
		}
		this.targetClass = targetClass;
	}
	
	public SearchActionBuilder<T, A> setView(AbstractSearchView<T> view) {
		this.view = view;
		return this;
	}
	
	public A build() {
		if (instance != null) {
			throw new IllegalStateException("Instance has already been built.");
		}
		if (view == null) {
			throw new IllegalStateException("Required parameter 'view' is missing.");
		}
		try {
			instance = targetClass.getDeclaredConstructor(AbstractSearchView.class).newInstance(view);
			return instance;
		} catch (Exception e) {
			throw new IllegalStateException(String.format(
					"Exception thrown while creating instance: %s", e.getMessage()), e);
		} 
	}
	
	public A getAfterBuild() {
		if (instance == null) {
			throw new IllegalStateException("Instance has not yet been built.");
		}
		return instance;
	}
	
}
