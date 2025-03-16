package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.EmployeeSearchView;
import com.pinguela.yourpc.desktop.view.EmployeeView;
import com.pinguela.yourpc.desktop.view.EntityView;
import com.pinguela.yourpc.model.Employee;

@SuppressWarnings("serial")
public class OpenEmployeeResultDialogAction 
extends OpenSearchResultDialogAction<Employee> {
	
	private EmployeeView dialogView;
	private Employee employee;

	public OpenEmployeeResultDialogAction(EmployeeSearchView view) {
		super(view, Icons.EDIT_ICON);
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		Object[][] tableSelection = getSearchView().getTableSelection();

		dialogView = new EmployeeView();
		dialogView.addPropertyChangeListener(EntityView.ITEM_PROPERTY, this);
		employee = (Employee) tableSelection[0][0];
		dialogView.setEntity(employee);

		boolean isEditing = ActionCommands.TABLE_BUTTON.equals(e.getActionCommand());
		if (!isEditing) {
			dialogView.addAction(new EditItemAction<Employee>(dialogView, EntityView.VIEW_CARD));
		}
		dialogView.addAction(new CancelEditAction<Employee>(dialogView), EntityView.EDITOR_CARD);
		dialogView.addAction(new SaveEmployeeAction(dialogView), EntityView.EDITOR_CARD);

		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) getSearchView());
		YPCDialog dialog = new YPCDialog(frame, dialogView);
		dialog.setTitle("Employee editor");
		return dialog;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Employee old = (Employee) evt.getOldValue();
		Employee employee = (Employee) evt.getNewValue();

		if (old != null && old.getId() == employee.getId()) { // Product was updated
			getSearchView().doSearch();
			return;
		}
	}

	@Override
	protected boolean shouldSetRelativeLocation() {
		return false;
	}

}
