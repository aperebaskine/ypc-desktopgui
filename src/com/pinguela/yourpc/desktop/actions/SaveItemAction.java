package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.view.ItemView;

public abstract class SaveItemAction<T> 
extends ItemAction<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1974717185749303944L;

	private static Logger logger = LogManager.getLogger(SaveItemAction.class);

	public SaveItemAction(ItemView<T> view, Icon icon) {
		super(view, icon);
	}

	public SaveItemAction(ItemView<T> view, String name, Icon icon) {
		super(view, name, icon);
	}

	public SaveItemAction(ItemView<T> view, String name) {
		super(view, name);
	}

	public SaveItemAction(ItemView<T> view) {
		super(view);
	}

	@Override
	protected void doAction() {
		try {
			doSave();
			if (!getView().showCard(ItemView.VIEW_CARD)) {
				getView().addAction(new ItemEditAction<T>(getView()), ItemView.VIEW_CARD);
				getView().showCard(ItemView.VIEW_CARD);
			} 
		} catch (YPCException e) {
			logger.error(e.getMessage(), e);
			JOptionPane.showMessageDialog((Component) getView(), 
					"An error occured while updating the product.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected abstract void doSave() throws YPCException;

}
