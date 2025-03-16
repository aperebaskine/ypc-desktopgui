package com.pinguela.yourpc.desktop.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.pinguela.yourpc.model.Criteria;
import com.pinguela.yourpc.model.Customer;
import com.pinguela.yourpc.model.CustomerCriteria;

@SuppressWarnings("serial")
public class CustomerCriteriaPanel 
extends CriteriaPanel<Customer> {
	
	private JTextField idTextField;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField documentNumberTextField;
	private JTextField phoneNumberTextField;
	private JTextField emailTextField;
	
	public CustomerCriteriaPanel() {
		initialize();
	}
	
	private void initialize() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 120, 48, 0, 120, 48, 0, 120, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel idLabel = new JLabel("ID:");
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.anchor = GridBagConstraints.EAST;
		gbc_idLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idLabel.gridx = 0;
		gbc_idLabel.gridy = 0;
		add(idLabel, gbc_idLabel);
		
		idTextField = new JTextField();
		GridBagConstraints gbc_idTextField = new GridBagConstraints();
		gbc_idTextField.insets = new Insets(0, 0, 5, 5);
		gbc_idTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idTextField.gridx = 1;
		gbc_idTextField.gridy = 0;
		add(idTextField, gbc_idTextField);
		idTextField.setColumns(10);
		
		JLabel firstNameLabel = new JLabel("First name:");
		GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
		gbc_firstNameLabel.anchor = GridBagConstraints.EAST;
		gbc_firstNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_firstNameLabel.gridx = 3;
		gbc_firstNameLabel.gridy = 0;
		add(firstNameLabel, gbc_firstNameLabel);
		
		firstNameTextField = new JTextField();
		GridBagConstraints gbc_firstNameTextField = new GridBagConstraints();
		gbc_firstNameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_firstNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstNameTextField.gridx = 4;
		gbc_firstNameTextField.gridy = 0;
		add(firstNameTextField, gbc_firstNameTextField);
		firstNameTextField.setColumns(10);
		
		JLabel documentNumberLabel = new JLabel("Document number:");
		GridBagConstraints gbc_documentNumberLabel = new GridBagConstraints();
		gbc_documentNumberLabel.anchor = GridBagConstraints.EAST;
		gbc_documentNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_documentNumberLabel.gridx = 6;
		gbc_documentNumberLabel.gridy = 0;
		add(documentNumberLabel, gbc_documentNumberLabel);
		
		documentNumberTextField = new JTextField();
		GridBagConstraints gbc_documentNumberTextField = new GridBagConstraints();
		gbc_documentNumberTextField.insets = new Insets(0, 0, 5, 0);
		gbc_documentNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_documentNumberTextField.gridx = 7;
		gbc_documentNumberTextField.gridy = 0;
		add(documentNumberTextField, gbc_documentNumberTextField);
		documentNumberTextField.setColumns(10);
		
		JLabel emailLabel = new JLabel("Email:");
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.anchor = GridBagConstraints.EAST;
		gbc_emailLabel.insets = new Insets(0, 0, 0, 5);
		gbc_emailLabel.gridx = 0;
		gbc_emailLabel.gridy = 1;
		add(emailLabel, gbc_emailLabel);
		
		emailTextField = new JTextField();
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.insets = new Insets(0, 0, 0, 5);
		gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTextField.gridx = 1;
		gbc_emailTextField.gridy = 1;
		add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);
		
		JLabel lastNameLabel = new JLabel("Last name:");
		GridBagConstraints gbc_lastNameLabel = new GridBagConstraints();
		gbc_lastNameLabel.anchor = GridBagConstraints.EAST;
		gbc_lastNameLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lastNameLabel.gridx = 3;
		gbc_lastNameLabel.gridy = 1;
		add(lastNameLabel, gbc_lastNameLabel);
		
		lastNameTextField = new JTextField();
		GridBagConstraints gbc_lastNameTextField = new GridBagConstraints();
		gbc_lastNameTextField.insets = new Insets(0, 0, 0, 5);
		gbc_lastNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastNameTextField.gridx = 4;
		gbc_lastNameTextField.gridy = 1;
		add(lastNameTextField, gbc_lastNameTextField);
		lastNameTextField.setColumns(10);
		
		JLabel phoneNumberLabel = new JLabel("Phone number:");
		GridBagConstraints gbc_phoneNumberLabel = new GridBagConstraints();
		gbc_phoneNumberLabel.anchor = GridBagConstraints.EAST;
		gbc_phoneNumberLabel.insets = new Insets(0, 0, 0, 5);
		gbc_phoneNumberLabel.gridx = 6;
		gbc_phoneNumberLabel.gridy = 1;
		add(phoneNumberLabel, gbc_phoneNumberLabel);
		
		phoneNumberTextField = new JTextField();
		GridBagConstraints gbc_phoneNumberTextField = new GridBagConstraints();
		gbc_phoneNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneNumberTextField.gridx = 7;
		gbc_phoneNumberTextField.gridy = 1;
		add(phoneNumberTextField, gbc_phoneNumberTextField);
		phoneNumberTextField.setColumns(10);
	}

	@Override
	public Criteria<Integer, Customer> getCriteria() {
		CustomerCriteria criteria = new CustomerCriteria();
		
		if (!idTextField.getText().isEmpty()) {
			criteria.setId(Integer.valueOf(idTextField.getText()));
		}
		
		if (!firstNameTextField.getText().isEmpty()) {
			criteria.setFirstName(firstNameTextField.getText());
		}
		
		if (!lastNameTextField.getText().isEmpty()) {
			criteria.setLastName1(lastNameTextField.getText());
		}
		
		if (!documentNumberTextField.getText().isEmpty()) {
			criteria.setDocumentNumber(documentNumberTextField.getText());
		}
		
		if (!phoneNumberTextField.getText().isEmpty()) {
			criteria.setPhoneNumber(phoneNumberTextField.getText());
		}
		
		if (!emailTextField.getText().isEmpty()) {
			criteria.setEmail(emailTextField.getText());
		}
		
		return criteria;
	}

	@Override
	public void setFieldsEnabled(boolean isEnabled) {
		idTextField.setEnabled(isEnabled);
		firstNameTextField.setEnabled(isEnabled);
		lastNameTextField.setEnabled(isEnabled);
		documentNumberTextField.setEnabled(isEnabled);
		phoneNumberTextField.setEnabled(isEnabled);
		emailTextField.setEnabled(isEnabled);
	}

	@Override
	public void resetFields(Object source) {
		if (!idTextField.equals(source)) {
			idTextField.setText("");
		}
		
		if (!firstNameTextField.equals(source)) {
			firstNameTextField.setText("");
		}
		
		if (!lastNameTextField.equals(source)) {
			lastNameTextField.setText("");
		}
		
		if (!documentNumberTextField.equals(source)) {
			documentNumberTextField.setText("");
		}
		
		if (!phoneNumberTextField.equals(source)) {
			phoneNumberTextField.setText("");
		}
		
		if (!emailTextField.equals(source)) {
			emailTextField.setText("");
		}	
	}

}
