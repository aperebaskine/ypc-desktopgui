package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;

import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.ItemView;
import com.pinguela.yourpc.desktop.view.TicketView;
import com.pinguela.yourpc.model.Ticket;

public class OpenTicketViewDialogAction 
extends OpenDialogAction<Ticket> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7386088204113948230L;
	
	public OpenTicketViewDialogAction() {
		super("Create...");
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		TicketView dialogView = new TicketView();
		dialogView.addPropertyChangeListener(ItemView.ITEM_PROPERTY, this);
		dialogView.addAction(new CancelEditAction<Ticket>(dialogView), ItemView.EDITOR_CARD);
		dialogView.addAction(new SaveTicketAction(dialogView), ItemView.EDITOR_CARD);
		dialogView.addAction(new AddTicketMessageAction(dialogView), ItemView.EDITOR_CARD);

		YPCDialog dialog = new YPCDialog(null, dialogView);
		dialog.setTitle("Ticket editor");
		return dialog;
	}

	@Override
	protected boolean shouldSetRelativeLocation() {
		return false;
	}

	@Override
	protected void onClose() {}

}
