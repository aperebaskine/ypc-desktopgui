package com.pinguela.yourpc.desktop.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.pinguela.yourpc.desktop.components.ImagePanel;
import com.pinguela.yourpc.model.Employee;

public class EmployeeView extends AbstractItemView<Employee> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5614891033513128985L;
	
	private JTextField firstNameTextField;
	private JTextField lastName1TextField;
	private JTextField lastName2TextField;
	private JTextField textField;
	private JTextField usernameTextField;
	private JFormattedTextField phoneNumberFormattedTextField;
	private JLabel idValueLabel;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	public EmployeeView() {
		initialize();
	}
	
	private void initialize() {
		getViewPanel().setLayout(new BorderLayout(0, 0));
		
		ImagePanel imagePanel = new ImagePanel();
		GridBagLayout gridBagLayout = (GridBagLayout) imagePanel.getLayout();
		gridBagLayout.columnWidths = new int[]{240};
		gridBagLayout.columnWeights = new double[]{0.0};
		getViewPanel().add(imagePanel, BorderLayout.WEST);
		
		JPanel centerPanel = new JPanel();
		getViewPanel().add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 1.0};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		centerPanel.setLayout(gbl_centerPanel);
		
		JLabel idLabel = new JLabel("ID:");
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.anchor = GridBagConstraints.EAST;
		gbc_idLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idLabel.gridx = 0;
		gbc_idLabel.gridy = 0;
		centerPanel.add(idLabel, gbc_idLabel);
		
		idValueLabel = new JLabel("0");
		GridBagConstraints gbc_idValueLabel = new GridBagConstraints();
		gbc_idValueLabel.anchor = GridBagConstraints.WEST;
		gbc_idValueLabel.insets = new Insets(0, 0, 5, 0);
		gbc_idValueLabel.gridx = 1;
		gbc_idValueLabel.gridy = 0;
		centerPanel.add(idValueLabel, gbc_idValueLabel);
		
		JLabel usernameLabel = new JLabel("Username:");
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.anchor = GridBagConstraints.EAST;
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.gridx = 0;
		gbc_usernameLabel.gridy = 1;
		centerPanel.add(usernameLabel, gbc_usernameLabel);
		
		usernameTextField = new JTextField();
		GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
		gbc_usernameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_usernameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameTextField.gridx = 1;
		gbc_usernameTextField.gridy = 1;
		centerPanel.add(usernameTextField, gbc_usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel firstNameLabel = new JLabel("First name:");
		GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
		gbc_firstNameLabel.anchor = GridBagConstraints.EAST;
		gbc_firstNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_firstNameLabel.gridx = 0;
		gbc_firstNameLabel.gridy = 2;
		centerPanel.add(firstNameLabel, gbc_firstNameLabel);
		
		firstNameTextField = new JTextField();
		GridBagConstraints gbc_firstNameTextField = new GridBagConstraints();
		gbc_firstNameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_firstNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstNameTextField.gridx = 1;
		gbc_firstNameTextField.gridy = 2;
		centerPanel.add(firstNameTextField, gbc_firstNameTextField);
		firstNameTextField.setColumns(10);
		
		JLabel lastNameLabel = new JLabel("Last name:");
		GridBagConstraints gbc_lastNameLabel = new GridBagConstraints();
		gbc_lastNameLabel.anchor = GridBagConstraints.EAST;
		gbc_lastNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lastNameLabel.gridx = 0;
		gbc_lastNameLabel.gridy = 3;
		centerPanel.add(lastNameLabel, gbc_lastNameLabel);
		
		lastName1TextField = new JTextField();
		GridBagConstraints gbc_lastName1TextField = new GridBagConstraints();
		gbc_lastName1TextField.insets = new Insets(0, 0, 5, 0);
		gbc_lastName1TextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastName1TextField.gridx = 1;
		gbc_lastName1TextField.gridy = 3;
		centerPanel.add(lastName1TextField, gbc_lastName1TextField);
		lastName1TextField.setColumns(10);
		
		JLabel lastName2Label = new JLabel("Second last name:");
		GridBagConstraints gbc_lastName2Label = new GridBagConstraints();
		gbc_lastName2Label.anchor = GridBagConstraints.EAST;
		gbc_lastName2Label.insets = new Insets(0, 0, 5, 5);
		gbc_lastName2Label.gridx = 0;
		gbc_lastName2Label.gridy = 4;
		centerPanel.add(lastName2Label, gbc_lastName2Label);
		
		lastName2TextField = new JTextField();
		GridBagConstraints gbc_lastName2TextField = new GridBagConstraints();
		gbc_lastName2TextField.insets = new Insets(0, 0, 5, 0);
		gbc_lastName2TextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastName2TextField.gridx = 1;
		gbc_lastName2TextField.gridy = 4;
		centerPanel.add(lastName2TextField, gbc_lastName2TextField);
		lastName2TextField.setColumns(10);
		
		JLabel documentTypeLabel = new JLabel("Document type:");
		GridBagConstraints gbc_documentTypeLabel = new GridBagConstraints();
		gbc_documentTypeLabel.anchor = GridBagConstraints.EAST;
		gbc_documentTypeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_documentTypeLabel.gridx = 0;
		gbc_documentTypeLabel.gridy = 5;
		centerPanel.add(documentTypeLabel, gbc_documentTypeLabel);
		
		JComboBox documentTypeComboBox = new JComboBox();
		GridBagConstraints gbc_documentTypeComboBox = new GridBagConstraints();
		gbc_documentTypeComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_documentTypeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_documentTypeComboBox.gridx = 1;
		gbc_documentTypeComboBox.gridy = 5;
		centerPanel.add(documentTypeComboBox, gbc_documentTypeComboBox);
		
		JLabel documentNumberLabel = new JLabel("Document number:");
		GridBagConstraints gbc_documentNumberLabel = new GridBagConstraints();
		gbc_documentNumberLabel.anchor = GridBagConstraints.EAST;
		gbc_documentNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_documentNumberLabel.gridx = 0;
		gbc_documentNumberLabel.gridy = 6;
		centerPanel.add(documentNumberLabel, gbc_documentNumberLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 6;
		centerPanel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel phoneNumberLabel = new JLabel("Phone number:");
		GridBagConstraints gbc_phoneNumberLabel = new GridBagConstraints();
		gbc_phoneNumberLabel.anchor = GridBagConstraints.EAST;
		gbc_phoneNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_phoneNumberLabel.gridx = 0;
		gbc_phoneNumberLabel.gridy = 7;
		centerPanel.add(phoneNumberLabel, gbc_phoneNumberLabel);
		
		phoneNumberFormattedTextField = new JFormattedTextField();
		GridBagConstraints gbc_phoneNumberFormattedTextField = new GridBagConstraints();
		gbc_phoneNumberFormattedTextField.insets = new Insets(0, 0, 5, 0);
		gbc_phoneNumberFormattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneNumberFormattedTextField.gridx = 1;
		gbc_phoneNumberFormattedTextField.gridy = 7;
		centerPanel.add(phoneNumberFormattedTextField, gbc_phoneNumberFormattedTextField);
		
		JLabel emailLabel = new JLabel("Email:");
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.anchor = GridBagConstraints.EAST;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 0;
		gbc_emailLabel.gridy = 8;
		centerPanel.add(emailLabel, gbc_emailLabel);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 8;
		centerPanel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel ibanLabel = new JLabel("IBAN:");
		GridBagConstraints gbc_ibanLabel = new GridBagConstraints();
		gbc_ibanLabel.anchor = GridBagConstraints.EAST;
		gbc_ibanLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ibanLabel.gridx = 0;
		gbc_ibanLabel.gridy = 9;
		centerPanel.add(ibanLabel, gbc_ibanLabel);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 9;
		centerPanel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel bicLabel = new JLabel("BIC:");
		GridBagConstraints gbc_bicLabel = new GridBagConstraints();
		gbc_bicLabel.anchor = GridBagConstraints.EAST;
		gbc_bicLabel.insets = new Insets(0, 0, 0, 5);
		gbc_bicLabel.gridx = 0;
		gbc_bicLabel.gridy = 10;
		centerPanel.add(bicLabel, gbc_bicLabel);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 10;
		centerPanel.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JPanel eastPanel = new JPanel();
		getViewPanel().add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel labelPanel = new JPanel();
		eastPanel.add(labelPanel, BorderLayout.NORTH);
		
		JLabel addressLabel = new JLabel("Address:");
		labelPanel.add(addressLabel);
		
		AddressView addressView = new AddressView();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) addressView.getViewPanel().getLayout();
		gridBagLayout_1.columnWidths = new int[]{0, 90, 0, 90};
		eastPanel.add(addressView, BorderLayout.CENTER);
	}

	@Override
	public Employee getModifiedItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetFields() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setFieldsEditable(boolean isEditable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onItemSet() {
		// TODO Auto-generated method stub
		
	}

}
