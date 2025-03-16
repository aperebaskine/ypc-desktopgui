package com.pinguela.yourpc.desktop.view;

import com.pinguela.yourpc.desktop.actions.EmployeeSearchAction;
import com.pinguela.yourpc.desktop.actions.SearchAction;
import com.pinguela.yourpc.desktop.actions.SearchActionBuilder;
import com.pinguela.yourpc.desktop.components.CriteriaPanel;
import com.pinguela.yourpc.desktop.components.EmployeeCriteriaPanel;
import com.pinguela.yourpc.desktop.renderer.EmployeeTableCellRenderer;
import com.pinguela.yourpc.model.Employee;

@SuppressWarnings("serial")
public class EmployeeSearchView 
extends AbstractSearchView<Employee> {
	
	public EmployeeSearchView() {
		this(new SearchActionBuilder<>(EmployeeSearchAction.class));
	}

	public EmployeeSearchView(SearchActionBuilder<Employee, ? extends SearchAction<Employee>> builder) {
		super(builder);
		setTableCellRenderer(Object.class, new EmployeeTableCellRenderer());
	}

	@Override
	protected CriteriaPanel<Employee> initializeCriteriaPanel() {
		return new EmployeeCriteriaPanel();
	}
	
}
