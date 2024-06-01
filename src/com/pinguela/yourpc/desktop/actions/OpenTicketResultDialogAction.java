package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.ItemView;
import com.pinguela.yourpc.desktop.view.TicketSearchView;
import com.pinguela.yourpc.desktop.view.TicketView;
import com.pinguela.yourpc.model.Ticket;

public class OpenTicketResultDialogAction 
extends OpenSearchResultDialogAction<Ticket> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7730348390083143906L;
	
	private TicketView dialogView;
	
	private Ticket t;

	public OpenTicketResultDialogAction(TicketSearchView view) {
		super(view, Icons.EDIT_ICON);
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		JTable table = getSearchView().getTable();
		int row = table.getSelectedRow();
		int column = table.getSelectedColumn();

		dialogView = new TicketView();
		dialogView.addPropertyChangeListener(ItemView.ITEM_PROPERTY, this);
		t = (Ticket) table.getValueAt(row, column);
		dialogView.setItem(t);

		boolean isEditing = ActionCommands.TABLE_BUTTON.equals(e.getActionCommand());
		if (!isEditing) {
			dialogView.addAction(new EditItemAction<Ticket>(dialogView, ItemView.VIEW_CARD));
		}
		dialogView.addAction(new CancelEditAction<Ticket>(dialogView), ItemView.EDITOR_CARD);
		dialogView.addAction(new SaveTicketAction(dialogView), ItemView.EDITOR_CARD);
		dialogView.addAction(new AddTicketMessageAction(dialogView), ItemView.EDITOR_CARD);

		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(table);
		YPCDialog dialog = new YPCDialog(frame, dialogView);
		dialog.setTitle("Ticket editor");
		return dialog;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Ticket old = (Ticket) evt.getOldValue();
		Ticket ticket = (Ticket) evt.getNewValue();

		if (old != null && old.getId() == ticket.getId()) {
			getSearchView().doSearch();
			return;
		}
	}

	@Override
	protected boolean shouldSetRelativeLocation() {
		return false;
	}

}
