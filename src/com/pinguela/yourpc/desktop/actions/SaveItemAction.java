package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.view.EntityView;

public abstract class SaveItemAction<T> 
extends ItemAction<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1974717185749303944L;

	private static Logger logger = LogManager.getLogger(SaveItemAction.class);

	public SaveItemAction(EntityView<T> view, Icon icon) {
		super(view, icon);
	}

	public SaveItemAction(EntityView<T> view, String name, Icon icon) {
		super(view, name, icon);
	}

	public SaveItemAction(EntityView<T> view, String name) {
		super(view, name);
	}

	public SaveItemAction(EntityView<T> view) {
		super(view);
	}

	@Override
	protected void doAction() {
		try {
			if (getView().getItem() == null) {
				doCreate(getView().getNewItem());
			} else {
				doUpdate(getView().getNewItem());
			}
			if (!getView().showCard(EntityView.VIEW_CARD)) {
				for (Action action : getViewerActions()) {
					getView().addAction(action, EntityView.VIEW_CARD);
				}
				getView().showCard(EntityView.VIEW_CARD);
			} 
		} catch (YPCException e) {
			logger.error(e.getMessage(), e);
			JOptionPane.showMessageDialog((Component) getView(), 
					"An error occured while communicating with the database.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	protected abstract Action[] getViewerActions();

	protected abstract void doCreate(T item) throws YPCException;
	
	protected abstract void doUpdate(T item) throws YPCException;

}
