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
import com.pinguela.yourpc.desktop.constants.DBConstants;
import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.model.DocumentType;
import com.pinguela.yourpc.model.Employee;

public class EmployeeView extends AbstractItemView<Employee> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5614891033513128985L;
	
	private JTextField firstNameTextField;
	private JTextField lastName1TextField;
	private JTextField lastName2TextField;
	private JComboBox<DocumentType> documentTypeComboBox;
	private JTextField documentNumberTextField;
	private JTextField usernameTextField;
	private JFormattedTextField phoneNumberFormattedTextField;
	private JLabel idValueLabel;
	private JTextField emailTextField;
	private JTextField ibanTextField;
	private JTextField bicTextField;
	
	private JPanel centerPanel;
	private AddressView addressView;
	
	public EmployeeView() {
		initialize();
		postInitialize();
	}
	
	private void initialize() {
		getViewPanel().setLayout(new BorderLayout(0, 0));
		
		ImagePanel imagePanel = new ImagePanel();
		GridBagLayout gridBagLayout = (GridBagLayout) imagePanel.getLayout();
		gridBagLayout.columnWidths = new int[]{240};
		gridBagLayout.columnWeights = new double[]{0.0};
		getViewPanel().add(imagePanel, BorderLayout.WEST);
		
		centerPanel = new JPanel();
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
		
		JLabel documentNumberLabel = new JLabel("Document number:");
		GridBagConstraints gbc_documentNumberLabel = new GridBagConstraints();
		gbc_documentNumberLabel.anchor = GridBagConstraints.EAST;
		gbc_documentNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_documentNumberLabel.gridx = 0;
		gbc_documentNumberLabel.gridy = 6;
		centerPanel.add(documentNumberLabel, gbc_documentNumberLabel);
		
		documentNumberTextField = new JTextField();
		GridBagConstraints gbc_documentNumberTextField = new GridBagConstraints();
		gbc_documentNumberTextField.insets = new Insets(0, 0, 5, 0);
		gbc_documentNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_documentNumberTextField.gridx = 1;
		gbc_documentNumberTextField.gridy = 6;
		centerPanel.add(documentNumberTextField, gbc_documentNumberTextField);
		documentNumberTextField.setColumns(10);
		
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
		
		emailTextField = new JTextField();
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.insets = new Insets(0, 0, 5, 0);
		gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTextField.gridx = 1;
		gbc_emailTextField.gridy = 8;
		centerPanel.add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);
		
		JLabel ibanLabel = new JLabel("IBAN:");
		GridBagConstraints gbc_ibanLabel = new GridBagConstraints();
		gbc_ibanLabel.anchor = GridBagConstraints.EAST;
		gbc_ibanLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ibanLabel.gridx = 0;
		gbc_ibanLabel.gridy = 9;
		centerPanel.add(ibanLabel, gbc_ibanLabel);
		
		ibanTextField = new JTextField();
		GridBagConstraints gbc_ibanTextField = new GridBagConstraints();
		gbc_ibanTextField.insets = new Insets(0, 0, 5, 0);
		gbc_ibanTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ibanTextField.gridx = 1;
		gbc_ibanTextField.gridy = 9;
		centerPanel.add(ibanTextField, gbc_ibanTextField);
		ibanTextField.setColumns(10);
		
		JLabel bicLabel = new JLabel("BIC:");
		GridBagConstraints gbc_bicLabel = new GridBagConstraints();
		gbc_bicLabel.anchor = GridBagConstraints.EAST;
		gbc_bicLabel.insets = new Insets(0, 0, 0, 5);
		gbc_bicLabel.gridx = 0;
		gbc_bicLabel.gridy = 10;
		centerPanel.add(bicLabel, gbc_bicLabel);
		
		bicTextField = new JTextField();
		GridBagConstraints gbc_bicTextField = new GridBagConstraints();
		gbc_bicTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_bicTextField.gridx = 1;
		gbc_bicTextField.gridy = 10;
		centerPanel.add(bicTextField, gbc_bicTextField);
		bicTextField.setColumns(10);
		
		JPanel eastPanel = new JPanel();
		getViewPanel().add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel labelPanel = new JPanel();
		eastPanel.add(labelPanel, BorderLayout.NORTH);
		
		JLabel addressLabel = new JLabel("Address:");
		labelPanel.add(addressLabel);
		
		addressView = new AddressView(AddressView.EMPLOYEE);
		GridBagLayout gridBagLayout_1 = (GridBagLayout) addressView.getViewPanel().getLayout();
		gridBagLayout_1.columnWidths = new int[]{0, 90, 0, 90};
		eastPanel.add(addressView, BorderLayout.CENTER);
	}
	
	private void postInitialize() {
		documentTypeComboBox = ComponentFactory.createComboBox(DBConstants.DOCUMENT_TYPES.values(), DocumentType.class);
		GridBagConstraints gbc_documentTypeComboBox = new GridBagConstraints();
		gbc_documentTypeComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_documentTypeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_documentTypeComboBox.gridx = 1;
		gbc_documentTypeComboBox.gridy = 5;
		centerPanel.add(documentTypeComboBox, gbc_documentTypeComboBox);
	}

	@Override
	public Employee getNewItem() {
        Employee employee = new Employee();
        
        employee.setFirstName(firstNameTextField.getText());
        employee.setLastName1(lastName1TextField.getText());
        employee.setLastName2(lastName2TextField.getText());
        employee.setDocumentTypeId(((DocumentType) documentTypeComboBox.getSelectedItem()).getId());
        employee.setDocumentNumber(documentNumberTextField.getText());
        employee.setUsername(usernameTextField.getText());
        employee.setPhoneNumber(phoneNumberFormattedTextField.getText());
        employee.setEmail(emailTextField.getText());
        employee.setIban(ibanTextField.getText());
        employee.setBic(bicTextField.getText());
        employee.setAddress(addressView.getNewItem());
        
        return employee;
	}

	@Override
	public void resetFields() {
        firstNameTextField.setText("");
        lastName1TextField.setText("");
        lastName2TextField.setText("");
        documentTypeComboBox.setSelectedIndex(0);
        documentNumberTextField.setText("");
        usernameTextField.setText("");
        phoneNumberFormattedTextField.setText("");
        idValueLabel.setText("");
        emailTextField.setText("");
        ibanTextField.setText("");
        bicTextField.setText("");
	}

	@Override
	protected void setFieldsEditable(boolean isEditable) {
        firstNameTextField.setEditable(isEditable);
        lastName1TextField.setEditable(isEditable);
        lastName2TextField.setEditable(isEditable);
        documentTypeComboBox.setEnabled(isEditable);
        documentNumberTextField.setEditable(isEditable);
        usernameTextField.setEditable(isEditable);
        phoneNumberFormattedTextField.setEditable(isEditable);
        emailTextField.setEditable(isEditable);
        ibanTextField.setEditable(isEditable);
        bicTextField.setEditable(isEditable);
	}

	@Override
	protected void onItemSet() {
	    Employee employee = getItem();

	    if (employee != null) {
	        firstNameTextField.setText(employee.getFirstName());
	        lastName1TextField.setText(employee.getLastName1());
	        lastName2TextField.setText(employee.getLastName2());
	        documentTypeComboBox.setSelectedItem(employee.getDocumentType());
	        documentNumberTextField.setText(employee.getDocumentNumber());
	        usernameTextField.setText(employee.getUsername());
	        phoneNumberFormattedTextField.setText(employee.getPhoneNumber());
	        idValueLabel.setText(employee.getId() != null ? employee.getId().toString() : "");
	        emailTextField.setText(employee.getEmail());
	        ibanTextField.setText(employee.getIban());
	        bicTextField.setText(employee.getBic());
	    } else {
	        resetFields();
	    }
	}

}
