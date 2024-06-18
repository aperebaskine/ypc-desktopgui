package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;

import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.EntityView;
import com.pinguela.yourpc.desktop.view.TicketView;
import com.pinguela.yourpc.model.Ticket;

@SuppressWarnings("serial")
public class OpenTicketViewDialogAction 
extends OpenDialogAction<Ticket> {
	
	public OpenTicketViewDialogAction() {
		super("Create...");
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		TicketView dialogView = new TicketView();
		dialogView.addPropertyChangeListener(EntityView.ITEM_PROPERTY, this);
		dialogView.addAction(new CancelEditAction<Ticket>(dialogView), EntityView.EDITOR_CARD);
		dialogView.addAction(new SaveTicketAction(dialogView), EntityView.EDITOR_CARD);
		dialogView.addAction(new AddTicketMessageAction(dialogView), EntityView.EDITOR_CARD);

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
