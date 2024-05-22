package com.pinguela.yourpc.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.pinguela.yourpc.desktop.actions.RMASearchAction;
import com.pinguela.yourpc.desktop.actions.SearchAction;
import com.pinguela.yourpc.desktop.actions.SearchActionBuilder;
import com.pinguela.yourpc.desktop.constants.DBConstants;
import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.desktop.renderer.RMATableCellRenderer;
import com.pinguela.yourpc.desktop.util.TableUtils;
import com.pinguela.yourpc.model.ItemState;
import com.pinguela.yourpc.model.RMA;
import com.pinguela.yourpc.model.RMACriteria;
import com.toedter.calendar.JDateChooser;

public class RMASearchView extends AbstractSearchView<RMA> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5162559348467135343L;

	private JTextField idTextField;
	private JTextField customerIdTextField;
	private JTextField customerEmailTextField;
	private JDateChooser dateFromChooser;
	private JDateChooser dateToChooser;
	private JComboBox<ItemState<RMA>> stateComboBox;
	private JTextField ticketIdTextField;
	private JTextField orderIdTextField;
	
	public RMASearchView() {
		this(new SearchActionBuilder<>(RMASearchAction.class));
	}
	
	public RMASearchView(SearchActionBuilder<RMA, ? extends SearchAction<RMA>> builder) {
		super(builder);
		initialize();
		postInitialize();
	}

	private void initialize() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 120, 0, 120, 48, 0, 0, 120, 0, 120, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
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
		gbc_idTextField.gridwidth = 3;
		gbc_idTextField.insets = new Insets(0, 0, 5, 5);
		gbc_idTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idTextField.gridx = 2;
		gbc_idTextField.gridy = 0;
		getCriteriaPanel().add(idTextField, gbc_idTextField);
		idTextField.setColumns(10);

		JLabel ticketIdLabel = new JLabel("Ticket ID:");
		GridBagConstraints gbc_ticketIdLabel = new GridBagConstraints();
		gbc_ticketIdLabel.anchor = GridBagConstraints.EAST;
		gbc_ticketIdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ticketIdLabel.gridx = 6;
		gbc_ticketIdLabel.gridy = 0;
		getCriteriaPanel().add(ticketIdLabel, gbc_ticketIdLabel);

		ticketIdTextField = new JTextField();
		GridBagConstraints gbc_ticketIdTextField = new GridBagConstraints();
		gbc_ticketIdTextField.gridwidth = 4;
		gbc_ticketIdTextField.insets = new Insets(0, 0, 5, 0);
		gbc_ticketIdTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ticketIdTextField.gridx = 7;
		gbc_ticketIdTextField.gridy = 0;
		getCriteriaPanel().add(ticketIdTextField, gbc_ticketIdTextField);
		ticketIdTextField.setColumns(10);

		JLabel customerIdLabel = new JLabel("Customer ID:");
		GridBagConstraints gbc_customerIdLabel = new GridBagConstraints();
		gbc_customerIdLabel.anchor = GridBagConstraints.EAST;
		gbc_customerIdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_customerIdLabel.gridx = 0;
		gbc_customerIdLabel.gridy = 1;
		getCriteriaPanel().add(customerIdLabel, gbc_customerIdLabel);

		customerIdTextField = new JTextField();
		GridBagConstraints gbc_customerIdTextField = new GridBagConstraints();
		gbc_customerIdTextField.gridwidth = 3;
		gbc_customerIdTextField.insets = new Insets(0, 0, 5, 5);
		gbc_customerIdTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_customerIdTextField.gridx = 2;
		gbc_customerIdTextField.gridy = 1;
		getCriteriaPanel().add(customerIdTextField, gbc_customerIdTextField);
		customerIdTextField.setColumns(10);

		JLabel orderIdLabel = new JLabel("Order ID:");
		GridBagConstraints gbc_orderIdLabel = new GridBagConstraints();
		gbc_orderIdLabel.anchor = GridBagConstraints.EAST;
		gbc_orderIdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_orderIdLabel.gridx = 6;
		gbc_orderIdLabel.gridy = 1;
		getCriteriaPanel().add(orderIdLabel, gbc_orderIdLabel);

		orderIdTextField = new JTextField();
		GridBagConstraints gbc_orderIdTextField = new GridBagConstraints();
		gbc_orderIdTextField.gridwidth = 4;
		gbc_orderIdTextField.insets = new Insets(0, 0, 5, 0);
		gbc_orderIdTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_orderIdTextField.gridx = 7;
		gbc_orderIdTextField.gridy = 1;
		getCriteriaPanel().add(orderIdTextField, gbc_orderIdTextField);
		orderIdTextField.setColumns(10);

		JLabel customerEmailLabel = new JLabel("Customer email:");
		GridBagConstraints gbc_customerEmailLabel = new GridBagConstraints();
		gbc_customerEmailLabel.anchor = GridBagConstraints.EAST;
		gbc_customerEmailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_customerEmailLabel.gridx = 0;
		gbc_customerEmailLabel.gridy = 2;
		getCriteriaPanel().add(customerEmailLabel, gbc_customerEmailLabel);

		customerEmailTextField = new JTextField();
		GridBagConstraints gbc_customerEmailTextField = new GridBagConstraints();
		gbc_customerEmailTextField.gridwidth = 3;
		gbc_customerEmailTextField.insets = new Insets(0, 0, 5, 5);
		gbc_customerEmailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_customerEmailTextField.gridx = 2;
		gbc_customerEmailTextField.gridy = 2;
		getCriteriaPanel().add(customerEmailTextField, gbc_customerEmailTextField);
		customerEmailTextField.setColumns(10);

		JLabel stateLabel = new JLabel("State:");
		GridBagConstraints gbc_stateLabel = new GridBagConstraints();
		gbc_stateLabel.anchor = GridBagConstraints.EAST;
		gbc_stateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_stateLabel.gridx = 6;
		gbc_stateLabel.gridy = 2;
		getCriteriaPanel().add(stateLabel, gbc_stateLabel);

		JLabel dateLabel = new JLabel("Date:");
		GridBagConstraints gbc_dateLabel = new GridBagConstraints();
		gbc_dateLabel.anchor = GridBagConstraints.EAST;
		gbc_dateLabel.insets = new Insets(0, 0, 0, 5);
		gbc_dateLabel.gridx = 0;
		gbc_dateLabel.gridy = 3;
		getCriteriaPanel().add(dateLabel, gbc_dateLabel);

		JLabel fromLabel = new JLabel("from");
		GridBagConstraints gbc_fromLabel = new GridBagConstraints();
		gbc_fromLabel.insets = new Insets(0, 0, 0, 5);
		gbc_fromLabel.gridx = 1;
		gbc_fromLabel.gridy = 3;
		getCriteriaPanel().add(fromLabel, gbc_fromLabel);

		dateFromChooser = new JDateChooser();
		GridBagConstraints gbc_dateFromChooser = new GridBagConstraints();
		gbc_dateFromChooser.insets = new Insets(0, 0, 0, 5);
		gbc_dateFromChooser.fill = GridBagConstraints.BOTH;
		gbc_dateFromChooser.gridx = 2;
		gbc_dateFromChooser.gridy = 3;
		getCriteriaPanel().add(dateFromChooser, gbc_dateFromChooser);

		JLabel toLabel = new JLabel("to");
		GridBagConstraints gbc_toLabel = new GridBagConstraints();
		gbc_toLabel.insets = new Insets(0, 0, 0, 5);
		gbc_toLabel.gridx = 3;
		gbc_toLabel.gridy = 3;
		getCriteriaPanel().add(toLabel, gbc_toLabel);

		dateToChooser = new JDateChooser();
		GridBagConstraints gbc_dateToChooser = new GridBagConstraints();
		gbc_dateToChooser.insets = new Insets(0, 0, 0, 5);
		gbc_dateToChooser.fill = GridBagConstraints.BOTH;
		gbc_dateToChooser.gridx = 4;
		gbc_dateToChooser.gridy = 3;
		getCriteriaPanel().add(dateToChooser, gbc_dateToChooser);
	}
	
	private void postInitialize() {
		stateComboBox = ComponentFactory.getComboBox(DBConstants.RMA_STATES.values(), ItemState.class);
		GridBagConstraints gbc_stateComboBox = new GridBagConstraints();
		gbc_stateComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_stateComboBox.gridwidth = 4;
		gbc_stateComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_stateComboBox.gridx = 7;
		gbc_stateComboBox.gridy = 2;
		getCriteriaPanel().add(stateComboBox, gbc_stateComboBox);
		
		JTable table = getTable();
		TableUtils.initializeActionPanes(table);
		table.setDefaultRenderer(Object.class, new RMATableCellRenderer());
	}

	@Override
	@SuppressWarnings("unchecked")
	public RMACriteria getCriteria() {
		RMACriteria criteria = new RMACriteria();

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

		if (!ticketIdTextField.getText().isEmpty()) {
			criteria.setTicketId(Long.valueOf(ticketIdTextField.getText()));
		}

		if (!orderIdTextField.getText().isEmpty()) {
			criteria.setOrderId(Long.valueOf(orderIdTextField.getText()));
		}
		
		criteria.setState(((ItemState<RMA>) stateComboBox.getSelectedItem()).getId());

		return criteria;
	}

	@Override
	public void setFieldsEnabled(boolean isEnabled) {
		customerIdTextField.setEnabled(isEnabled);
		customerEmailTextField.setEnabled(isEnabled);
		dateFromChooser.setEnabled(isEnabled);
		dateToChooser.setEnabled(isEnabled);
		stateComboBox.setEnabled(isEnabled);
		ticketIdTextField.setEnabled(isEnabled);
		orderIdTextField.setEnabled(isEnabled);
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
	    if (!stateComboBox.equals(source)) {
	        stateComboBox.setSelectedIndex(0);
	    }
	    if (!ticketIdTextField.equals(source)) {
	        ticketIdTextField.setText("");
	    }
	    if (!orderIdTextField.equals(source)) {
	        orderIdTextField.setText("");
	    }
	}

}
