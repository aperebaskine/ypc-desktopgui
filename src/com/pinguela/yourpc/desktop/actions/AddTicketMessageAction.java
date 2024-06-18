package com.pinguela.yourpc.desktop.actions;

import com.pinguela.yourpc.desktop.components.InputPane;
import com.pinguela.yourpc.desktop.components.TicketMessageInputPane;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.TicketView;
import com.pinguela.yourpc.model.TicketMessage;

@SuppressWarnings("serial")
public class AddTicketMessageAction 
extends GetInputAction<TicketMessage> {
	
	private TicketView view;
	
	public AddTicketMessageAction(TicketView view) {
		super("Add message", Icons.ADD_ICON);
		this.view = view;
	}

	@Override
	protected InputPane<TicketMessage> initializeInputPane() {
		return new TicketMessageInputPane();
	}

	@Override
	protected void onConfirm() {
		TicketMessage tm = getInput();
		view.addMessage(tm);
	}

}
