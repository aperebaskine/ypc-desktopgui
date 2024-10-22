package com.pinguela.yourpc.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.pinguela.yourpc.desktop.actions.SearchAction;
import com.pinguela.yourpc.desktop.actions.SearchActionBuilder;
import com.pinguela.yourpc.desktop.actions.TicketSearchAction;
import com.pinguela.yourpc.desktop.components.ExtendedDateChooser;
import com.pinguela.yourpc.desktop.constants.DBConstants;
import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.desktop.renderer.TicketTableCellRenderer;
import com.pinguela.yourpc.model.Ticket;
import com.pinguela.yourpc.model.TicketCriteria;
import com.pinguela.yourpc.model.TicketState;
import com.pinguela.yourpc.model.TicketType;

@SuppressWarnings("serial")
public class TicketSearchView
extends AbstractPaginatedSearchView<Ticket> {

	private JTextField idTextField;
	private JTextField customerIdTextField;
	private JTextField customerEmailTextField;
	private ExtendedDateChooser dateFromChooser;
	private ExtendedDateChooser dateToChooser;
	private JComboBox<TicketType> typeComboBox;
	private JComboBox<TicketState> stateComboBox;

	public TicketSearchView() {
		this(new SearchActionBuilder<>(TicketSearchAction.class));
	}

	public TicketSearchView(SearchActionBuilder<Ticket, ? extends SearchAction<Ticket>> builder) {
		super(builder);
		initialize();
		postInitialize();
	}

	private void initialize() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 2, 60, 4, 60, 48, 31, 120, 48, -6, 120, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
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
		gbc_idTextField.gridwidth = 4;
		gbc_idTextField.insets = new Insets(0, 0, 5, 5);
		gbc_idTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idTextField.gridx = 1;
		gbc_idTextField.gridy = 0;
		getCriteriaPanel().add(idTextField, gbc_idTextField);
		idTextField.setColumns(10);

		JLabel customerIdLabel = new JLabel("Customer ID:");
		GridBagConstraints gbc_customerIdLabel = new GridBagConstraints();
		gbc_customerIdLabel.anchor = GridBagConstraints.EAST;
		gbc_customerIdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_customerIdLabel.gridx = 6;
		gbc_customerIdLabel.gridy = 0;
		getCriteriaPanel().add(customerIdLabel, gbc_customerIdLabel);

		customerIdTextField = new JTextField();
		GridBagConstraints gbc_customerIdTextField = new GridBagConstraints();
		gbc_customerIdTextField.insets = new Insets(0, 0, 5, 5);
		gbc_customerIdTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_customerIdTextField.gridx = 7;
		gbc_customerIdTextField.gridy = 0;
		getCriteriaPanel().add(customerIdTextField, gbc_customerIdTextField);
		customerIdTextField.setColumns(10);

		JLabel typeLabel = new JLabel("Type:");
		GridBagConstraints gbc_typeLabel = new GridBagConstraints();
		gbc_typeLabel.anchor = GridBagConstraints.EAST;
		gbc_typeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_typeLabel.gridx = 9;
		gbc_typeLabel.gridy = 0;
		getCriteriaPanel().add(typeLabel, gbc_typeLabel);

		JLabel dateLabel = new JLabel("Date:");
		GridBagConstraints gbc_dateLabel = new GridBagConstraints();
		gbc_dateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dateLabel.gridx = 0;
		gbc_dateLabel.gridy = 1;
		getCriteriaPanel().add(dateLabel, gbc_dateLabel);

		JLabel fromLabel = new JLabel("from");
		GridBagConstraints gbc_fromLabel = new GridBagConstraints();
		gbc_fromLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fromLabel.gridx = 1;
		gbc_fromLabel.gridy = 1;
		getCriteriaPanel().add(fromLabel, gbc_fromLabel);

		dateFromChooser = ComponentFactory.getDateChooser();
		GridBagConstraints gbc_dateFromChooser = new GridBagConstraints();
		gbc_dateFromChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateFromChooser.fill = GridBagConstraints.BOTH;
		gbc_dateFromChooser.gridx = 2;
		gbc_dateFromChooser.gridy = 1;
		getCriteriaPanel().add(dateFromChooser, gbc_dateFromChooser);

		JLabel toLabel = new JLabel("to");
		GridBagConstraints gbc_toLabel = new GridBagConstraints();
		gbc_toLabel.insets = new Insets(0, 0, 5, 5);
		gbc_toLabel.gridx = 3;
		gbc_toLabel.gridy = 1;
		getCriteriaPanel().add(toLabel, gbc_toLabel);

		dateToChooser = ComponentFactory.getDateChooser();
		GridBagConstraints gbc_dateToChooser = new GridBagConstraints();
		gbc_dateToChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateToChooser.fill = GridBagConstraints.BOTH;
		gbc_dateToChooser.gridx = 4;
		gbc_dateToChooser.gridy = 1;
		getCriteriaPanel().add(dateToChooser, gbc_dateToChooser);

		JLabel customerEmailLabel = new JLabel("Customer email:");
		GridBagConstraints gbc_customerEmailLabel = new GridBagConstraints();
		gbc_customerEmailLabel.anchor = GridBagConstraints.EAST;
		gbc_customerEmailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_customerEmailLabel.gridx = 6;
		gbc_customerEmailLabel.gridy = 1;
		getCriteriaPanel().add(customerEmailLabel, gbc_customerEmailLabel);

		customerEmailTextField = new JTextField();
		GridBagConstraints gbc_customerEmailTextField = new GridBagConstraints();
		gbc_customerEmailTextField.insets = new Insets(0, 0, 5, 5);
		gbc_customerEmailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_customerEmailTextField.gridx = 7;
		gbc_customerEmailTextField.gridy = 1;
		getCriteriaPanel().add(customerEmailTextField, gbc_customerEmailTextField);
		customerEmailTextField.setColumns(10);

		JLabel stateLabel = new JLabel("State:");
		GridBagConstraints gbc_stateLabel = new GridBagConstraints();
		gbc_stateLabel.anchor = GridBagConstraints.EAST;
		gbc_stateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_stateLabel.gridx = 9;
		gbc_stateLabel.gridy = 1;
		getCriteriaPanel().add(stateLabel, gbc_stateLabel);
	}

	private void postInitialize() {

		typeComboBox = ComponentFactory.createComboBox(DBConstants.TICKET_TYPES.values(), TicketType.class);
		GridBagConstraints gbc_typeComboBox = new GridBagConstraints();
		gbc_typeComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_typeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_typeComboBox.gridx = 10;
		gbc_typeComboBox.gridy = 0;
		getCriteriaPanel().add(typeComboBox, gbc_typeComboBox);

		stateComboBox = ComponentFactory.createComboBox(DBConstants.TICKET_STATES.values(), TicketState.class);
		GridBagConstraints gbc_stateComboBox = new GridBagConstraints();
		gbc_stateComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_stateComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_stateComboBox.gridx = 10;
		gbc_stateComboBox.gridy = 1;
		getCriteriaPanel().add(stateComboBox, gbc_stateComboBox);

		JTable table = getTable();
		table.setDefaultRenderer(Object.class, new TicketTableCellRenderer());
	}

	public TicketCriteria getCriteria() {
		TicketCriteria criteria = new TicketCriteria();

		if (!idTextField.getText().isEmpty()) {
			criteria.setId(Long.valueOf(idTextField.getText()));
		}

		if (!customerIdTextField.getText().isEmpty()) {
			criteria.setCustomerId(Integer.valueOf(customerIdTextField.getText()));
		}

		if (!customerEmailTextField.getText().isEmpty()) {
			criteria.setCustomerEmail(customerEmailTextField.getText());
		}

		if (dateFromChooser.getDate() != null) {
			criteria.setMinDate(dateFromChooser.getDate());
		}

		if (dateToChooser.getDate() != null) {
			criteria.setMaxDate(dateToChooser.getDate());
		}

		criteria.setType(((TicketType) typeComboBox.getSelectedItem()).getId());
		criteria.setState(((TicketState) stateComboBox.getSelectedItem()).getId());

		return criteria;
	}

	@Override
	public void setCriteriaFieldsEnabled(boolean isEnabled) {
		customerIdTextField.setEnabled(isEnabled);
		customerEmailTextField.setEnabled(isEnabled);
		dateFromChooser.setEnabled(isEnabled);
		dateToChooser.setEnabled(isEnabled);
		typeComboBox.setEnabled(isEnabled);
		stateComboBox.setEnabled(isEnabled);
	}

	@Override
	protected void doResetCriteriaFields(Object source) {
		if (!idTextField.equals(source)) {
			idTextField.setText("");
		}
		if (!customerIdTextField.equals(source)) {
			customerIdTextField.setText("");
		}
		if (!customerEmailTextField.equals(source)) {
			customerEmailTextField.setText("");
		}
		if (!dateFromChooser.equals(source)) {
			dateFromChooser.setDate(null);
		}
		if (!dateToChooser.equals(source)) {
			dateToChooser.setDate(null);
		}
		if (!typeComboBox.equals(source)) {
			typeComboBox.setSelectedIndex(0);
		}
		if (!stateComboBox.equals(source)) {
			stateComboBox.setSelectedIndex(0);
		}	
	}

}
