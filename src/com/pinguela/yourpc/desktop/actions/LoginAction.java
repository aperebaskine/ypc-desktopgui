package com.pinguela.yourpc.desktop.actions;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.InvalidLoginCredentialsException;
import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.LoginView;
import com.pinguela.yourpc.model.Employee;
import com.pinguela.yourpc.service.EmployeeService;
import com.pinguela.yourpc.service.impl.EmployeeServiceImpl;

@SuppressWarnings("serial")
public class LoginAction 
extends YPCAction {

	private static final int MAX_ATTEMPTS = 3;

	private static Logger logger = LogManager.getLogger(LoginAction.class);
	
	private EmployeeService employeeService;

	private int loginAttemptCount = 0;

	private LoginView view;

	public LoginAction(LoginView view) {
		super("Confirm", Icons.LOGIN_ICON);
		this.employeeService = new EmployeeServiceImpl();
		this.view = view;
	}

	private void onInvalidCredentials() {
		loginAttemptCount++;
		if (loginAttemptCount >= MAX_ATTEMPTS) {
			JOptionPane.showMessageDialog(view, "Too many incorrect login attempts. Exiting...", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		} else {
			JOptionPane.showMessageDialog(view, "Invalid login or password.", "Error", JOptionPane.WARNING_MESSAGE);
		}
	}

	@Override
	protected void doAction() {
		Employee employee = null;

		try {
			employee = employeeService.login(view.getUsername(), view.getPassword());
			view.setAuthenticatedUser(employee);
		} catch (InvalidLoginCredentialsException e1) {
			onInvalidCredentials();
		} catch (YPCException e1) {
			logger.fatal(String.format("Exception thrown while attempting to authenticate: %s", e1.getMessage()), e1);
			JOptionPane.showMessageDialog(view, "Unable to authenticate. Exiting application...", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

}
