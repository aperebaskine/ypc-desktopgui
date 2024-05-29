package com.pinguela.yourpc.desktop.actions;

import javax.swing.Action;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.EmployeeView;
import com.pinguela.yourpc.model.Employee;
import com.pinguela.yourpc.service.EmployeeService;
import com.pinguela.yourpc.service.impl.EmployeeServiceImpl;

public class SaveEmployeeAction extends SaveItemAction<Employee> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4146700807343435074L;
	
	private EmployeeService employeeService;

	public SaveEmployeeAction(EmployeeView view) {
		super(view, "Save", Icons.OK_ICON);
		this.employeeService = new EmployeeServiceImpl();
	}

	@Override
	protected void doSave() throws YPCException {
		Employee newEmployee = getView().getNewItem();
		employeeService.update(newEmployee);
		getView().setItem(employeeService.findById(newEmployee.getId()));
	}
	
	@Override
	protected Action[] getViewerActions() {
		return new Action[]{new EditItemAction<Employee>(getView())};
	}

}
