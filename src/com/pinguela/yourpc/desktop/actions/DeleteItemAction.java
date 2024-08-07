package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.DataException;
import com.pinguela.ServiceException;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.util.DialogUtils;
import com.pinguela.yourpc.desktop.view.EntityView;
import com.pinguela.yourpc.desktop.view.SearchView;

@SuppressWarnings("serial")
public abstract class DeleteItemAction<T> 
extends YPCAction {
	
	private static Logger logger = LogManager.getLogger(DeleteItemAction.class);

	private DeleteActionDelegate delegate;

	public DeleteItemAction(EntityView<T> source) {
		super("Delete", Icons.DELETE_ICON);
		delegate = new ItemViewDeleteActionDelegate(source);
	}

	public DeleteItemAction(SearchView<T> source) {
		super("Delete", Icons.DELETE_ICON);
		delegate = new SearchViewDeleteActionDelegate(source);
	}

	@Override
	protected void doAction() {
		int option = JOptionPane.showConfirmDialog(delegate.getSource(), 
				String.format("Are you sure you want to delete %s?", 
						getItemName(delegate.getItem())), "Confirm deletion", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (shouldDeleteFromDatabase()) {
				try {
					deleteFromDatabase(delegate.getItem());
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					DialogUtils.showDatabaseAccessErrorDialog(delegate.getSource());
				}
			}
			delegate.onConfirm();
		}
	}
	
	protected abstract String getItemName(T item);

	protected abstract boolean shouldDeleteFromDatabase();

	protected abstract void deleteFromDatabase(T item) throws ServiceException, DataException;

	private abstract class DeleteActionDelegate {

		protected abstract void onConfirm();

		protected abstract Component getSource();

		protected abstract T getItem();

	}

	private class ItemViewDeleteActionDelegate extends DeleteActionDelegate {

		private EntityView<T> view;

		private ItemViewDeleteActionDelegate(EntityView<T> view) {
			this.view = view;
		}

		@Override
		protected void onConfirm() {
			YPCDialog dialog = (YPCDialog) SwingUtilities.getAncestorOfClass(YPCDialog.class, (Component) view);
			if (dialog != null) {
				dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
			}
		}

		@Override
		protected Component getSource() {
			return (Component) view;
		}
		
		@Override
		protected T getItem() {
			return view.getCurrentEntity();
		}

	}

	private class SearchViewDeleteActionDelegate extends DeleteActionDelegate {

		private SearchView<T> view;

		private SearchViewDeleteActionDelegate(SearchView<T> view) {
			this.view = view;
		}

		@Override
		protected void onConfirm() {
			view.doSearch();
		}

		@Override
		protected Component getSource() {
			return (Component) view;
		}

		@Override
		@SuppressWarnings("unchecked")
		protected T getItem() {
			return (T) view.getTable().getCellEditor().getCellEditorValue();
		}

	}

}
