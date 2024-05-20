package com.pinguela.yourpc.desktop.view;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Objects;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.pinguela.yourpc.desktop.YPCWindow;
import com.pinguela.yourpc.desktop.actions.ExitWindowAction;
import com.pinguela.yourpc.desktop.actions.LoginAction;
import com.pinguela.yourpc.desktop.actions.YPCAction;
import com.pinguela.yourpc.desktop.components.ActionPane;
import com.pinguela.yourpc.model.Employee;

public class LoginView 
extends YPCView {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2945940401511672156L;
	
	private Employee authenticatedUser;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	
	public LoginView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel logoPanel = new JPanel();
		add(logoPanel);
		logoPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon(LoginView.class.getResource("/logo.png")));
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoPanel.add(logoLabel);
		
		JPanel inputPanel = new JPanel();
		add(inputPanel);
		GridBagLayout gbl_inputPanel = new GridBagLayout();
		gbl_inputPanel.columnWidths = new int[]{0, 160, 0, 0};
		gbl_inputPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_inputPanel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_inputPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		inputPanel.setLayout(gbl_inputPanel);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 0;
		inputPanel.add(verticalStrut, gbc_verticalStrut);
		
		JLabel usernameLabel = new JLabel("Username:");
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.gridx = 1;
		gbc_usernameLabel.gridy = 1;
		inputPanel.add(usernameLabel, gbc_usernameLabel);
		
		usernameTextField = new JTextField();
		GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
		gbc_usernameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameTextField.gridx = 1;
		gbc_usernameTextField.gridy = 2;
		inputPanel.add(usernameTextField, gbc_usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password:");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 1;
		gbc_passwordLabel.gridy = 3;
		inputPanel.add(passwordLabel, gbc_passwordLabel);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 4;
		inputPanel.add(passwordField, gbc_passwordField);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut_1.gridx = 1;
		gbc_verticalStrut_1.gridy = 5;
		inputPanel.add(verticalStrut_1, gbc_verticalStrut_1);
		
		ActionPane actionPane = new ActionPane();
		add(actionPane);
		
		YPCAction exitAction = new ExitWindowAction(this);
		actionPane.addAction(exitAction, false);
		actionPane.addAction(new LoginAction(this), true);
	}
	
	public String getUsername() {
		return usernameTextField.getText();
	}
	
	public String getPassword() {
		return new String(passwordField.getPassword());
	}
	
	public Employee getAuthenticatedUser() {
		return authenticatedUser;
	}
	
	public void setAuthenticatedUser(Employee authenticatedUser) {
		this.authenticatedUser = Objects.requireNonNull(authenticatedUser);
		firePropertyChange(YPCWindow.AUTHENTICATED_USER_PROPERTY, null, authenticatedUser);
	}

}
