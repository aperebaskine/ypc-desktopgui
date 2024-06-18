package com.pinguela.yourpc.desktop.components;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.pinguela.yourpc.desktop.actions.YPCAction;

@SuppressWarnings("serial")
public abstract class ItemSelector<T> extends JPanel {
	
	public static final String ITEM_PROPERTY = "item";
	private static final String SELECTOR_CARD = "selector";
	private static final String VIEWER_CARD = "viewer";
	
	private T item;
	private JLabel itemLabel;
	private List<Component> disableableComponents;
	
	private final PropertyChangeListener itemListener = (evt) -> {
		if (evt.getNewValue() == null) {
			((CardLayout) getLayout()).show(this, SELECTOR_CARD);
		} else {
			itemLabel.setText(formatItemLabel());
			((CardLayout) getLayout()).show(this, VIEWER_CARD);
		}
	};
	
	public ItemSelector() {
		initialize();
	}
	
	private void initialize() {
		setLayout(new CardLayout(0, 0));
		
		disableableComponents = new ArrayList<Component>();
		
		JPanel selectorCard = new JPanel();
		FlowLayout flowLayout = (FlowLayout) selectorCard.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(selectorCard, SELECTOR_CARD);
		
		JButton selectButton = new JButton(initializeSelectAction());
		selectorCard.add(selectButton);
		disableableComponents.add(selectButton);
		
		JPanel viewerCard = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) viewerCard.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(viewerCard, VIEWER_CARD);
		
		itemLabel = new JLabel("");
		viewerCard.add(itemLabel);
		
		JButton viewButton = new JButton(initializeViewAction());
		viewerCard.add(viewButton);
		disableableComponents.add(viewButton);
		
		addPropertyChangeListener(ITEM_PROPERTY, itemListener);
	}
	
	public T getItem() {
		return item;
	}
	
	public void setItem(T item) {
		T old = this.item;
		this.item = item;
		firePropertyChange(ITEM_PROPERTY, old, item);
	}
	
	public void setEnabled(boolean isEnabled) {
		for (Component component : disableableComponents) {
			component.setEnabled(isEnabled);
		}
	}
	
	protected abstract YPCAction initializeSelectAction();
	
	protected abstract YPCAction initializeViewAction();
	
	protected abstract String formatItemLabel();

}
