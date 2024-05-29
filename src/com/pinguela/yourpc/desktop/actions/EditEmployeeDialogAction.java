package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.view.EmployeeSearchView;
import com.pinguela.yourpc.desktop.view.EmployeeView;
import com.pinguela.yourpc.desktop.view.ItemView;
import com.pinguela.yourpc.model.Employee;

public class EditEmployeeDialogAction 
extends AbstractSearchViewDialogAction<Employee> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3610533513809502632L;
	
	private EmployeeView dialogView;
	private Employee employee;

	public EditEmployeeDialogAction(EmployeeSearchView view) {
		super(view, Icons.EDIT_ICON);
	}

	@Override
	protected YPCDialog createDialog(ActionEvent e) {
		JTable table = getSearchView().getTable();
		int row = table.getSelectedRow();
		int column = table.getSelectedColumn();

		dialogView = new EmployeeView();
		dialogView.addPropertyChangeListener(ItemView.ITEM_PROPERTY, this);
		employee = (Employee) table.getValueAt(row, column);
		dialogView.setItem(employee);

		boolean isEditing = ActionCommands.TABLE_BUTTON.equals(e.getActionCommand());
		if (!isEditing) {
			dialogView.addAction(new EditItemAction<Employee>(dialogView, ItemView.VIEW_CARD));
		}
		dialogView.addAction(new CancelEditAction<Employee>(dialogView), ItemView.EDITOR_CARD);
		dialogView.addAction(new SaveEmployeeAction(dialogView), ItemView.EDITOR_CARD);

		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(table);
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
