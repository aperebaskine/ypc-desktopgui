package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;

import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.EmployeeView;
import com.pinguela.yourpc.desktop.view.EntityView;
import com.pinguela.yourpc.model.Employee;

public class OpenEmployeeViewDialogAction 
extends OpenDialogAction<Employee> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8768640695470145782L;

	private EmployeeView dialogView;

	public OpenEmployeeViewDialogAction() {
		super("Create...");
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		dialogView = new EmployeeView();
		dialogView.addPropertyChangeListener(EntityView.ITEM_PROPERTY, this);
		dialogView.addAction(new CancelEditAction<Employee>(dialogView), EntityView.EDITOR_CARD);
		dialogView.addAction(new SaveEmployeeAction(dialogView), EntityView.EDITOR_CARD);

		YPCDialog dialog = new YPCDialog(null, dialogView);
		dialog.setTitle("Employee editor");
		return dialog;
	}

	@Override
	protected boolean shouldSetRelativeLocation() {
		return false;
	}
	
	@Override
	protected void onClose() {}

}
