package com.pinguela.yourpc.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.pinguela.yourpc.desktop.actions.EmployeeSearchAction;
import com.pinguela.yourpc.desktop.actions.SearchAction;
import com.pinguela.yourpc.desktop.actions.SearchActionBuilder;
import com.pinguela.yourpc.desktop.constants.DBConstants;
import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.desktop.renderer.EmployeeTableCellRenderer;
import com.pinguela.yourpc.model.Department;
import com.pinguela.yourpc.model.Employee;
import com.pinguela.yourpc.model.EmployeeCriteria;

public class EmployeeSearchView extends AbstractSearchView<Employee> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6158379077559032488L;
	private JTextField idTextField;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField documentNumberTextField;
	private JTextField phoneNumberTextField;
	private JTextField emailTextField;
	private JLabel usernameLabel;
	private JTextField usernameTextField;
	private JLabel departmentLabel;
	private JComboBox<Department> departmentComboBox;
	
	public EmployeeSearchView() {
		this(new SearchActionBuilder<>(EmployeeSearchAction.class));
	}

	public EmployeeSearchView(SearchActionBuilder<Employee, ? extends SearchAction<Employee>> builder) {
		super(builder);
		initialize();
		postinitialize();
	}
	
	private void initialize() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 120, 48, 0, 120, 48, 0, 120, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		getCriteriaPanel().setLayout(gridBagLayout);
		
		JLabel idLabel = new JLabel("ID:");
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.anchor = GridBagConstraints.EAST;
		gbc_idLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idLabel.gridx = 0;
		gbc_idLabel.gridy = 0;
		getCriteriaPanel().add(idLabel, gbc_idLabel);
		
		idTextField = new JTextField();
		GridBagConstraints gbc_idTextField = new GridBagConstraints();
		gbc_idTextField.insets = new Insets(0, 0, 5, 5);
		gbc_idTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idTextField.gridx = 1;
		gbc_idTextField.gridy = 0;
		getCriteriaPanel().add(idTextField, gbc_idTextField);
		idTextField.setColumns(10);
		
		JLabel firstNameLabel = new JLabel("First name:");
		GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
		gbc_firstNameLabel.anchor = GridBagConstraints.EAST;
		gbc_firstNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_firstNameLabel.gridx = 3;
		gbc_firstNameLabel.gridy = 0;
		getCriteriaPanel().add(firstNameLabel, gbc_firstNameLabel);
		
		firstNameTextField = new JTextField();
		GridBagConstraints gbc_firstNameTextField = new GridBagConstraints();
		gbc_firstNameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_firstNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstNameTextField.gridx = 4;
		gbc_firstNameTextField.gridy = 0;
		getCriteriaPanel().add(firstNameTextField, gbc_firstNameTextField);
		firstNameTextField.setColumns(10);
		
		JLabel phoneNumberLabel = new JLabel("Phone number:");
		GridBagConstraints gbc_phoneNumberLabel = new GridBagConstraints();
		gbc_phoneNumberLabel.anchor = GridBagConstraints.EAST;
		gbc_phoneNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_phoneNumberLabel.gridx = 6;
		gbc_phoneNumberLabel.gridy = 0;
		getCriteriaPanel().add(phoneNumberLabel, gbc_phoneNumberLabel);
		
		phoneNumberTextField = new JTextField();
		GridBagConstraints gbc_phoneNumberTextField = new GridBagConstraints();
		gbc_phoneNumberTextField.insets = new Insets(0, 0, 5, 0);
		gbc_phoneNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneNumberTextField.gridx = 7;
		gbc_phoneNumberTextField.gridy = 0;
		getCriteriaPanel().add(phoneNumberTextField, gbc_phoneNumberTextField);
		phoneNumberTextField.setColumns(10);
		
		usernameLabel = new JLabel("Username:");
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.anchor = GridBagConstraints.EAST;
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.gridx = 0;
		gbc_usernameLabel.gridy = 1;
		getCriteriaPanel().add(usernameLabel, gbc_usernameLabel);
		
		usernameTextField = new JTextField();
		GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
		gbc_usernameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameTextField.gridx = 1;
		gbc_usernameTextField.gridy = 1;
		getCriteriaPanel().add(usernameTextField, gbc_usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel lastNameLabel = new JLabel("Last name:");
		GridBagConstraints gbc_lastNameLabel = new GridBagConstraints();
		gbc_lastNameLabel.anchor = GridBagConstraints.EAST;
		gbc_lastNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lastNameLabel.gridx = 3;
		gbc_lastNameLabel.gridy = 1;
		getCriteriaPanel().add(lastNameLabel, gbc_lastNameLabel);
		
		lastNameTextField = new JTextField();
		GridBagConstraints gbc_lastNameTextField = new GridBagConstraints();
		gbc_lastNameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_lastNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastNameTextField.gridx = 4;
		gbc_lastNameTextField.gridy = 1;
		getCriteriaPanel().add(lastNameTextField, gbc_lastNameTextField);
		lastNameTextField.setColumns(10);
		
		JLabel emailLabel = new JLabel("Email:");
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.anchor = GridBagConstraints.EAST;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 6;
		gbc_emailLabel.gridy = 1;
		getCriteriaPanel().add(emailLabel, gbc_emailLabel);
		
		emailTextField = new JTextField();
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.insets = new Insets(0, 0, 5, 0);
		gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTextField.gridx = 7;
		gbc_emailTextField.gridy = 1;
		getCriteriaPanel().add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);
		
		departmentLabel = new JLabel("Department:");
		GridBagConstraints gbc_departmentLabel = new GridBagConstraints();
		gbc_departmentLabel.anchor = GridBagConstraints.EAST;
		gbc_departmentLabel.insets = new Insets(0, 0, 0, 5);
		gbc_departmentLabel.gridx = 0;
		gbc_departmentLabel.gridy = 2;
		getCriteriaPanel().add(departmentLabel, gbc_departmentLabel);
		
		JLabel documentNumberLabel = new JLabel("Document number:");
		GridBagConstraints gbc_documentNumberLabel = new GridBagConstraints();
		gbc_documentNumberLabel.anchor = GridBagConstraints.EAST;
		gbc_documentNumberLabel.insets = new Insets(0, 0, 0, 5);
		gbc_documentNumberLabel.gridx = 3;
		gbc_documentNumberLabel.gridy = 2;
		getCriteriaPanel().add(documentNumberLabel, gbc_documentNumberLabel);
		
		documentNumberTextField = new JTextField();
		GridBagConstraints gbc_documentNumberTextField = new GridBagConstraints();
		gbc_documentNumberTextField.insets = new Insets(0, 0, 0, 5);
		gbc_documentNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_documentNumberTextField.gridx = 4;
		gbc_documentNumberTextField.gridy = 2;
		getCriteriaPanel().add(documentNumberTextField, gbc_documentNumberTextField);
		documentNumberTextField.setColumns(10);
	}
	
	private void postinitialize() {
		departmentComboBox = ComponentFactory.createComboBox(DBConstants.DEPARTMENTS.values(), Department.class);
		GridBagConstraints gbc_departmentComboBox = new GridBagConstraints();
		gbc_departmentComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_departmentComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_departmentComboBox.gridx = 1;
		gbc_departmentComboBox.gridy = 2;
		getCriteriaPanel().add(departmentComboBox, gbc_departmentComboBox);
		
		JTable table = getTable();
		table.setDefaultRenderer(Object.class, new EmployeeTableCellRenderer());
	}
	
	@Override
	public EmployeeCriteria getCriteria() {
		EmployeeCriteria criteria = new EmployeeCriteria();
		
		if (!idTextField.getText().isEmpty()) {
			criteria.setId(Integer.valueOf(idTextField.getText()));
		}
		
		if (!usernameTextField.getText().isEmpty()) {
			criteria.setUsername(usernameTextField.getText());
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
		
		criteria.setDepartmentId(((Department) departmentComboBox.getSelectedItem()).getId());
		
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
	protected void doResetCriteriaFields(Object source) {
		
		if (!idTextField.equals(source)) {
			idTextField.setText("");
		}
		
		if (!usernameTextField.equals(source)) {
			usernameTextField.setText("");
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
		
		if (!departmentComboBox.equals(source)) {
			departmentComboBox.setSelectedIndex(0);
		}	
	}

}
