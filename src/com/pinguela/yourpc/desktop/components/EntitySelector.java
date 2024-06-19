package com.pinguela.yourpc.desktop.components;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.pinguela.yourpc.desktop.actions.SelectEntityAction;
import com.pinguela.yourpc.desktop.actions.YPCAction;

@SuppressWarnings("serial")
public abstract class EntitySelector<T> 
extends JPanel {
	
	public static final String ENTITY_PROPERTY = "entity";
	private static final String SELECTOR_CARD = "selector";
	private static final String VIEWER_CARD = "viewer";
	
	private T item;
	private JLabel itemLabel;
	private List<Action> disableableActions;
	
	private final PropertyChangeListener itemListener = (evt) -> {
		if (evt.getNewValue() == null) {
			((CardLayout) getLayout()).show(this, SELECTOR_CARD);
		} else {
			itemLabel.setText(getLabelText());
			((CardLayout) getLayout()).show(this, VIEWER_CARD);
		}
	};
	
	public EntitySelector() {
		initialize();
	}
	
	private void initialize() {
		setLayout(new CardLayout(0, 0));
		
		disableableActions = new ArrayList<Action>();
		
		JPanel selectorCard = new JPanel();
		FlowLayout flowLayout = (FlowLayout) selectorCard.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(selectorCard, SELECTOR_CARD);
		
		Action selectAction = initializeSelectAction();
		
		JButton selectButton = new JButton(selectAction);
		selectorCard.add(selectButton);
		disableableActions.add(selectAction);
		
		JPanel viewerCard = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) viewerCard.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(viewerCard, VIEWER_CARD);
		
		itemLabel = new JLabel("");
		viewerCard.add(itemLabel);
		
		Action viewAction = initializeViewAction();
		
		JButton viewButton = new JButton(viewAction);
		viewerCard.add(viewButton);
		
		JButton replaceButton = new JButton(selectAction);
		viewerCard.add(replaceButton);
		
		addPropertyChangeListener(ENTITY_PROPERTY, itemListener);
	}
	
	public T getEntity() {
		return item;
	}
	
	public void setEntity(T item) {
		T old = this.item;
		this.item = item;
		firePropertyChange(ENTITY_PROPERTY, old, item);
	}
	
	public void setEnabled(boolean isEnabled) {
		for (Action action : disableableActions) {
			action.setEnabled(isEnabled);
		}
	}
	
	protected final YPCAction initializeSelectAction() {
		return new SelectEntityAction<T>(this);
	}
	
	protected abstract YPCAction initializeViewAction();
	
	protected abstract String getLabelText();

}
