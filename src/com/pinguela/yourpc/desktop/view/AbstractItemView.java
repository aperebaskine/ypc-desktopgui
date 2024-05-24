package com.pinguela.yourpc.desktop.view;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.pinguela.yourpc.desktop.components.ActionPane;

public abstract class AbstractItemView<T> 
extends YPCView 
implements ItemView<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 525919481888365709L;
	
	protected static final String CARD_PROPERTY = "card";
	
	private PropertyChangeListener editorListener = (evt) -> {
		boolean isEditable = ItemView.EDITOR_CARD.equals(evt.getNewValue());
		setFieldsEditable(isEditable);
	};

	private PropertyChangeListener itemListener = (evt) -> {
		if (evt.getNewValue() == null) {
			resetFields();
		} else {
			onItemSet();
		}
		
	};

	private T item;

	private JPanel centerPanel;
	private JPanel southPanel;

	private Map<String, ActionPane> cards;
	
	public AbstractItemView() {
		centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_viewerPanel = new GridBagLayout();
		gbl_viewerPanel.columnWidths = new int[]{0};
		gbl_viewerPanel.rowHeights = new int[]{0};
		gbl_viewerPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_viewerPanel.rowWeights = new double[]{Double.MIN_VALUE};
		centerPanel.setLayout(gbl_viewerPanel);

		cards = new HashMap<String, ActionPane>();
		
		addPropertyChangeListener(CARD_PROPERTY, editorListener);
		addPropertyChangeListener(ITEM_PROPERTY, itemListener);
	}

	private void initializeSouthPanel() {
		southPanel = new JPanel();
		southPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		southPanel.setLayout(new CardLayout(0, 0));
		add(southPanel, BorderLayout.SOUTH);
	}

	private void initializeCard(String name) {
		ActionPane actionPane = new ActionPane();
		FlowLayout fl_actionPane = (FlowLayout) actionPane.getLayout();
		fl_actionPane.setAlignment(FlowLayout.RIGHT);
		cards.put(name, actionPane);
		
		southPanel.add(actionPane, name);
		if (cards.size() == 1) {
			showCard(name);
		}
	}

	@Override
	public T getItem() {
		return item;
	}

	@Override
	public void setItem(T item) {
		T old = this.item;
		this.item = item;
		firePropertyChange(ITEM_PROPERTY, old, item);
	}

	protected JPanel getViewPanel() {
		return centerPanel;
	}

	@Override
	public void addAction(Action action) {
		addAction(action, VIEW_CARD); 
	}

	@Override
	public void addAction(Action action, String card) {
		if (southPanel == null) {
			initializeSouthPanel();
		}
		
		if (!cards.containsKey(card)) {	
			initializeCard(card);
		}
		
		cards.get(card).addAction(action);
	}

	@Override
	public boolean showCard(String cardName) {
		if (!cards.containsKey(cardName)) {
			return false;
		}
		((CardLayout) southPanel.getLayout()).show(southPanel, cardName);
		firePropertyChange(CARD_PROPERTY, null, cardName);
		return true;
	}
	
	public boolean isEditable() {
		return ItemView.EDITOR_CARD.equals(southPanel.getComponent(0).getName());
	}
	
	protected abstract void setFieldsEditable(boolean isEditable);
	
	protected abstract void onItemSet();

}
