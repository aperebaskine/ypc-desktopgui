package com.pinguela.yourpc.desktop.view;

import javax.swing.Action;

public interface EntityView<T> {
	
	String ITEM_PROPERTY = "item";
	
	int VIEWING = 0;
	int EDITING = 1;

	String VIEW_CARD = "actionCard";
	String EDITOR_CARD = "editorCard";
	
	T getCurrentEntity();
	
	T getDTOFromFields();

	void setEntity(T entity);
	
	void addAction(Action action);

	void addAction(Action action, String card);

	boolean showCard(String cardName);
	
	void resetFields();
	
	void toDefaultState();

}