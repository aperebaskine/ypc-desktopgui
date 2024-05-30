package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;

import com.pinguela.yourpc.desktop.util.TableUtils;
import com.pinguela.yourpc.desktop.view.EmployeeSearchView;

public class OpenEmployeeSearchTabAction extends OpenTabAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6355572183571931124L;
	
	public OpenEmployeeSearchTabAction() {
		super("Employee search");
	}

	@Override
	protected Component initializeTab() {
		EmployeeSearchView view = new EmployeeSearchView();
		YPCAction editEmployeeDialogAction = new EditEmployeeDialogAction(view);
		TableUtils.initializeActionPanes(view.getTable(), editEmployeeDialogAction);
		view.getTable().addMouseListener(editEmployeeDialogAction);
		return view;
	}

}
