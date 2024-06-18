package com.pinguela.yourpc.desktop.actions;

import javax.swing.Action;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.EmployeeView;
import com.pinguela.yourpc.model.Employee;
import com.pinguela.yourpc.service.EmployeeService;
import com.pinguela.yourpc.service.impl.EmployeeServiceImpl;

@SuppressWarnings("serial")
public class SaveEmployeeAction
extends SaveItemAction<Employee> {
	
	private EmployeeService employeeService;

	public SaveEmployeeAction(EmployeeView view) {
		super(view, "Save", Icons.OK_ICON);
		this.employeeService = new EmployeeServiceImpl();
	}
	
	@Override
	protected void doCreate(Employee item) throws YPCException {
		employeeService.register(item);
		getView().setEntity(employeeService.findById(item.getId()));
	}
	
	@Override
	protected void doUpdate(Employee item) throws YPCException {
		employeeService.update(item);
		getView().setEntity(employeeService.findById(item.getId()));
	}
	
	@Override
	protected Action[] getViewerActions() {
		return new Action[]{new EditItemAction<Employee>(getView())};
	}

}
