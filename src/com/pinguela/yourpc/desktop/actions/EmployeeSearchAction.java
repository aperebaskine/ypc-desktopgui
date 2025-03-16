package com.pinguela.yourpc.desktop.actions;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.EmployeeTableConstants;
import com.pinguela.yourpc.desktop.model.ListTableModel;
import com.pinguela.yourpc.desktop.view.EmployeeSearchView;
import com.pinguela.yourpc.desktop.view.AbstractSearchView;
import com.pinguela.yourpc.model.Employee;
import com.pinguela.yourpc.model.EmployeeCriteria;
import com.pinguela.yourpc.service.EmployeeService;
import com.pinguela.yourpc.service.impl.EmployeeServiceImpl;

@SuppressWarnings("serial")
public class EmployeeSearchAction
extends SearchAction<Employee> {
	
	private static Logger logger = LogManager.getLogger(EmployeeSearchAction.class);
	
	private EmployeeService employeeService;

	public EmployeeSearchAction(AbstractSearchView<Employee> view) {
		super(view);
		this.employeeService = new EmployeeServiceImpl();
	}

	@Override
	protected TableModel fetchData() {
		
		EmployeeSearchView view = (EmployeeSearchView) getView();
		EmployeeCriteria criteria = (EmployeeCriteria) view.getCriteria();
		List<Employee> results = null;

		try {
			if (criteria.getId() != null) {
				results = Arrays.asList(employeeService.findById(criteria.getId()));
			} else {
				results = employeeService.findBy(criteria);
			}
		} catch (YPCException ypce) {
			logger.error(ypce.getMessage(), ypce);
		}

		return new ListTableModel<Employee>(EmployeeTableConstants.COLUMN_NAMES, results);
	}

}
