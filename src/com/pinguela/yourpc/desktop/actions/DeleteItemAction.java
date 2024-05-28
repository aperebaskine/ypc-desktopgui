package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.view.ItemView;
import com.pinguela.yourpc.desktop.view.SearchView;

public abstract class DeleteItemAction<T> 
extends YPCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6391546410855771841L;

	private DeleteActionDelegate delegate;

	public DeleteItemAction(ItemView<T> source) {
		delegate = new ItemViewDeleteActionDelegate(source);
	}

	public DeleteItemAction(SearchView<T> source) {
		delegate = new SearchViewDeleteActionDelegate(source);
	}

	@Override
	protected void doAction() {
		int option = JOptionPane.showConfirmDialog(delegate.getSource(), 
				"Are you sure you want to delete this item?", "Confirm deletion", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (shouldDeleteFromDatabase()) {
				deleteFromDatabase(delegate.getItem());
			}
			delegate.onConfirm();
		}
	}

	protected abstract boolean shouldDeleteFromDatabase();

	protected abstract void deleteFromDatabase(T item);

	private abstract class DeleteActionDelegate {

		protected abstract void onConfirm();

		protected abstract Component getSource();

		protected abstract T getItem();

	}

	private class ItemViewDeleteActionDelegate extends DeleteActionDelegate {

		private ItemView<T> view;

		private ItemViewDeleteActionDelegate(ItemView<T> view) {
			this.view = view;
		}

		@Override
		protected void onConfirm() {
			SwingUtilities.getWindowAncestor((Component) view).dispose();
		}

		@Override
		protected Component getSource() {
			return (Component) view;
		}
		
		@Override
		protected T getItem() {
			return view.getItem();
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
