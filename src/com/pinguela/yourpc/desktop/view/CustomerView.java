package com.pinguela.yourpc.desktop.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.CustomerAddressTableConstants;
import com.pinguela.yourpc.desktop.constants.CustomerOrderTableConstants;
import com.pinguela.yourpc.desktop.constants.DBConstants;
import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.desktop.model.ActionPaneListTableModel;
import com.pinguela.yourpc.desktop.renderer.CustomerAddressTableCellRenderer;
import com.pinguela.yourpc.desktop.renderer.CustomerOrderTableCellRenderer;
import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.desktop.util.TableUtils;
import com.pinguela.yourpc.model.Customer;
import com.pinguela.yourpc.model.DocumentType;
import com.pinguela.yourpc.service.CustomerOrderService;
import com.pinguela.yourpc.service.impl.CustomerOrderServiceImpl;

public class CustomerView 
extends AbstractEntityView<Customer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9033122820930954981L;
	
	private static Logger logger = LogManager.getLogger(CustomerView.class);
	
	private CustomerOrderService customerOrderService;

	private JTextField firstNameTextField;
	private JTextField lastName1TextField;
	private JTextField lastName2TextField;
	private JComboBox<DocumentType> documentTypeComboBox;
	private JTextField documentNumberTextField;
	private JFormattedTextField phoneNumberFormattedTextField;
	private JLabel idValueLabel;
	private JTextField emailTextField;

	private JPanel centerPanel;
	private JTable addressTable;
	
	private JLabel recentOrdersLabel;
	private JScrollPane recentOrderScrollPane;
	private JTable recentOrderTable;
	private JScrollPane addressesScrollPane;
	private JLabel addressesLabel;

	public CustomerView() {
		initialize();
		postInitialize();
		initServices();
	}

	private void initialize() {
		centerPanel.setLayout(new BorderLayout(0, 0));

		centerPanel = new JPanel();
		centerPanel.add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0, 288, 24, 0, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
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
		gbc_idValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idValueLabel.gridx = 1;
		gbc_idValueLabel.gridy = 0;
		centerPanel.add(idValueLabel, gbc_idValueLabel);

		recentOrdersLabel = new JLabel("Recent orders:");
		recentOrdersLabel.setVisible(false);
		GridBagConstraints gbc_recentOrdersLabel = new GridBagConstraints();
		gbc_recentOrdersLabel.insets = new Insets(0, 0, 5, 5);
		gbc_recentOrdersLabel.gridx = 3;
		gbc_recentOrdersLabel.gridy = 0;
		centerPanel.add(recentOrdersLabel, gbc_recentOrdersLabel);
		
		JLabel emailLabel = new JLabel("Email:");
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.anchor = GridBagConstraints.EAST;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 0;
		gbc_emailLabel.gridy = 1;
		centerPanel.add(emailLabel, gbc_emailLabel);

		emailTextField = new JTextField();
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.insets = new Insets(0, 0, 5, 5);
		gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTextField.gridx = 1;
		gbc_emailTextField.gridy = 1;
		centerPanel.add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);

		JLabel firstNameLabel = new JLabel("First name:");
		GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
		gbc_firstNameLabel.anchor = GridBagConstraints.EAST;
		gbc_firstNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_firstNameLabel.gridx = 0;
		gbc_firstNameLabel.gridy = 2;
		centerPanel.add(firstNameLabel, gbc_firstNameLabel);

		firstNameTextField = new JTextField();
		GridBagConstraints gbc_firstNameTextField = new GridBagConstraints();
		gbc_firstNameTextField.insets = new Insets(0, 0, 5, 5);
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
		gbc_lastName1TextField.insets = new Insets(0, 0, 5, 5);
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
		gbc_lastName2TextField.insets = new Insets(0, 0, 5, 5);
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
		gbc_documentNumberTextField.insets = new Insets(0, 0, 5, 5);
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
		gbc_phoneNumberFormattedTextField.insets = new Insets(0, 0, 5, 5);
		gbc_phoneNumberFormattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneNumberFormattedTextField.gridx = 1;
		gbc_phoneNumberFormattedTextField.gridy = 7;
		centerPanel.add(phoneNumberFormattedTextField, gbc_phoneNumberFormattedTextField);
	}

	private void postInitialize() {
		documentTypeComboBox = ComponentFactory.createComboBox(DBConstants.DOCUMENT_TYPES.values(), DocumentType.class);
		GridBagConstraints gbc_documentTypeComboBox = new GridBagConstraints();
		gbc_documentTypeComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_documentTypeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_documentTypeComboBox.gridx = 1;
		gbc_documentTypeComboBox.gridy = 5;
		centerPanel.add(documentTypeComboBox, gbc_documentTypeComboBox);

		addressesLabel = new JLabel("Addresses:");
		addressesLabel.setVisible(false);
		GridBagConstraints gbc_addressesLabel = new GridBagConstraints();
		gbc_addressesLabel.anchor = GridBagConstraints.EAST;
		gbc_addressesLabel.insets = new Insets(0, 0, 5, 5);
		gbc_addressesLabel.gridx = 0;
		gbc_addressesLabel.gridy = 8;
		centerPanel.add(addressesLabel, gbc_addressesLabel);

		addressesScrollPane = new JScrollPane();
		addressesScrollPane.setVisible(false);
		GridBagConstraints gbc_addressesScrollPane = new GridBagConstraints();
		gbc_addressesScrollPane.gridheight = 2;
		gbc_addressesScrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_addressesScrollPane.fill = GridBagConstraints.BOTH;
		gbc_addressesScrollPane.gridx = 1;
		gbc_addressesScrollPane.gridy = 8;
		centerPanel.add(addressesScrollPane, gbc_addressesScrollPane);

		addressTable = new JTable();
		addressTable.setDefaultRenderer(Object.class, new CustomerAddressTableCellRenderer());
		TableUtils.initializeActionPanes(addressTable);
		addressesScrollPane.setViewportView(addressTable);
		
		recentOrderScrollPane = new JScrollPane();
		recentOrderScrollPane.setVisible(false);
		GridBagConstraints gbc_recentOrderScrollPane = new GridBagConstraints();
		gbc_recentOrderScrollPane.gridheight = 10;
		gbc_recentOrderScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_recentOrderScrollPane.fill = GridBagConstraints.BOTH;
		gbc_recentOrderScrollPane.gridx = 4;
		gbc_recentOrderScrollPane.gridy = 0;
		centerPanel.add(recentOrderScrollPane, gbc_recentOrderScrollPane);

		recentOrderTable = new JTable();
		recentOrderTable.setDefaultRenderer(Object.class, new CustomerOrderTableCellRenderer());
		TableUtils.initializeActionPanes(recentOrderTable);
		recentOrderScrollPane.setViewportView(recentOrderTable);
		
	}

	private void initServices() {
		this.customerOrderService = new CustomerOrderServiceImpl();
	}
	
	@Override
	public Customer getNewItem() {
		Customer old = getItem();
		Customer customer = new Customer();
		
		if (old != null) {
			customer.setId(old.getId());
			customer.setCreationDate(old.getCreationDate());
			customer.setEncryptedPassword(old.getEncryptedPassword());
		}

		customer.setFirstName(firstNameTextField.getText());
		customer.setLastName1(lastName1TextField.getText());
		
		if (lastName2TextField.getText().isEmpty()) {
			customer.setLastName2(null);
		} else {
			customer.setLastName2(lastName2TextField.getText());
		}
		
		customer.setDocumentTypeId(((DocumentType) documentTypeComboBox.getSelectedItem()).getId());
		customer.setDocumentNumber(documentNumberTextField.getText());
		customer.setPhoneNumber(phoneNumberFormattedTextField.getText());
		customer.setEmail(emailTextField.getText());

		return customer;
	}

	@Override
	public void resetFields() {
		firstNameTextField.setText("");
		lastName1TextField.setText("");
		lastName2TextField.setText("");
		documentTypeComboBox.setSelectedIndex(0);
		documentNumberTextField.setText("");
		phoneNumberFormattedTextField.setText("");
		idValueLabel.setText("");
		emailTextField.setText("");
	}

	@Override
	protected void setFieldsEditable(boolean isEditable) {
		firstNameTextField.setEditable(isEditable);
		lastName1TextField.setEditable(isEditable);
		lastName2TextField.setEditable(isEditable);
		documentTypeComboBox.setEnabled(isEditable);
		documentNumberTextField.setEditable(isEditable);
		phoneNumberFormattedTextField.setEditable(isEditable);
		emailTextField.setEditable(isEditable);
	}

	@Override
	protected void loadItemData() {
		recentOrdersLabel.setVisible(true);
		recentOrderScrollPane.setVisible(true);
		addressesLabel.setVisible(true);
		addressesScrollPane.setVisible(true);
		
		JDialog dialog = (JDialog) SwingUtilities.getAncestorOfClass(JDialog.class, this);
		if (dialog != null) {
			dialog.pack();
		}
		
		idValueLabel.setText(getItem().getId() != null ? getItem().getId().toString() : "");
		firstNameTextField.setText(getItem().getFirstName());
		lastName1TextField.setText(getItem().getLastName1());
		lastName2TextField.setText(getItem().getLastName2());
		documentTypeComboBox.setSelectedItem(DBConstants.DOCUMENT_TYPES.get(getItem().getDocumentTypeId()));
		documentNumberTextField.setText(getItem().getDocumentNumber());
		phoneNumberFormattedTextField.setText(getItem().getPhoneNumber());
		emailTextField.setText(getItem().getEmail());
		
		addressTable.setModel(new ActionPaneListTableModel<>(CustomerAddressTableConstants.COLUMN_NAMES, getItem().getAddresses()));
		
		try {
			recentOrderTable.setModel(new ActionPaneListTableModel<>(CustomerOrderTableConstants.COLUMN_NAMES,
					customerOrderService.findByCustomer(getItem().getId())));
		} catch (YPCException e) {
			logger.error(e.getMessage(), e);
			SwingUtils.showDatabaseAccessErrorDialog(this);
		}
	}

}
