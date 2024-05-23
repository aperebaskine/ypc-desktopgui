package com.pinguela.yourpc.desktop.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.pinguela.yourpc.desktop.actions.CustomerOrderSearchAction;
import com.pinguela.yourpc.desktop.actions.SearchAction;
import com.pinguela.yourpc.desktop.actions.SearchActionBuilder;
import com.pinguela.yourpc.desktop.actions.SetCustomerOrderRangesAction;
import com.pinguela.yourpc.desktop.components.ExtendedDateChooser;
import com.pinguela.yourpc.desktop.constants.DBConstants;
import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.desktop.renderer.CustomerOrderTableCellRenderer;
import com.pinguela.yourpc.desktop.util.TableUtils;
import com.pinguela.yourpc.model.CustomerOrder;
import com.pinguela.yourpc.model.CustomerOrderCriteria;
import com.pinguela.yourpc.model.CustomerOrderRanges;
import com.pinguela.yourpc.model.ItemState;

import slider.RangeSlider;

public class CustomerOrderSearchView extends AbstractSearchView<CustomerOrder> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4514845996718947204L;
	private JTextField idTextField;
	private JTextField customerIdTextField;
	private JTextField customerEmailTextField;
	private ExtendedDateChooser dateFromChooser;
	private ExtendedDateChooser dateToChooser;
	private RangeSlider amountRangeSlider;
	private JComboBox<ItemState<CustomerOrder>> stateComboBox;
	private JLabel minAmountLabel;
	private JLabel maxAmountLabel;

	public CustomerOrderSearchView() {
		this(new SearchActionBuilder<>(CustomerOrderSearchAction.class));
	}

	public CustomerOrderSearchView(SearchActionBuilder<CustomerOrder, ? extends SearchAction<CustomerOrder>> builder) {
		super(builder);
		initialize();
		postInitialize();
	}

	private void initialize() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{38, 120, 48, 48, 120, 48, 0, 0, 120, 0, 120, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
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

		JLabel customerIdLabel = new JLabel("Customer ID:");
		GridBagConstraints gbc_customerIdLabel = new GridBagConstraints();
		gbc_customerIdLabel.anchor = GridBagConstraints.EAST;
		gbc_customerIdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_customerIdLabel.gridx = 3;
		gbc_customerIdLabel.gridy = 0;
		getCriteriaPanel().add(customerIdLabel, gbc_customerIdLabel);

		customerIdTextField = new JTextField();
		GridBagConstraints gbc_customerIdTextField = new GridBagConstraints();
		gbc_customerIdTextField.insets = new Insets(0, 0, 5, 5);
		gbc_customerIdTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_customerIdTextField.gridx = 4;
		gbc_customerIdTextField.gridy = 0;
		getCriteriaPanel().add(customerIdTextField, gbc_customerIdTextField);
		customerIdTextField.setColumns(10);

		JLabel dateLabel = new JLabel("Date:");
		GridBagConstraints gbc_dateLabel = new GridBagConstraints();
		gbc_dateLabel.anchor = GridBagConstraints.EAST;
		gbc_dateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dateLabel.gridx = 6;
		gbc_dateLabel.gridy = 0;
		getCriteriaPanel().add(dateLabel, gbc_dateLabel);

		JLabel fromLabel = new JLabel("from");
		GridBagConstraints gbc_fromLabel = new GridBagConstraints();
		gbc_fromLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fromLabel.gridx = 7;
		gbc_fromLabel.gridy = 0;
		getCriteriaPanel().add(fromLabel, gbc_fromLabel);

		dateFromChooser = ComponentFactory.getDateChooser();
		GridBagConstraints gbc_dateFromChooser = new GridBagConstraints();
		gbc_dateFromChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateFromChooser.fill = GridBagConstraints.BOTH;
		gbc_dateFromChooser.gridx = 8;
		gbc_dateFromChooser.gridy = 0;
		getCriteriaPanel().add(dateFromChooser, gbc_dateFromChooser);

		JLabel toLabel = new JLabel("to");
		GridBagConstraints gbc_toLabel = new GridBagConstraints();
		gbc_toLabel.insets = new Insets(0, 0, 5, 5);
		gbc_toLabel.gridx = 9;
		gbc_toLabel.gridy = 0;
		getCriteriaPanel().add(toLabel, gbc_toLabel);

		dateToChooser = ComponentFactory.getDateChooser();
		GridBagConstraints gbc_dateToChooser = new GridBagConstraints();
		gbc_dateToChooser.insets = new Insets(0, 0, 5, 0);
		gbc_dateToChooser.fill = GridBagConstraints.BOTH;
		gbc_dateToChooser.gridx = 10;
		gbc_dateToChooser.gridy = 0;
		getCriteriaPanel().add(dateToChooser, gbc_dateToChooser);

		JLabel stateLabel = new JLabel("State:");
		GridBagConstraints gbc_stateLabel = new GridBagConstraints();
		gbc_stateLabel.anchor = GridBagConstraints.EAST;
		gbc_stateLabel.insets = new Insets(0, 0, 0, 5);
		gbc_stateLabel.gridx = 0;
		gbc_stateLabel.gridy = 1;
		getCriteriaPanel().add(stateLabel, gbc_stateLabel);
		
		stateComboBox = ComponentFactory.getComboBox(DBConstants.ORDER_STATES.values(), ItemState.class);
		GridBagConstraints gbc_stateComboBox = new GridBagConstraints();
		gbc_stateComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_stateComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_stateComboBox.gridx = 1;
		gbc_stateComboBox.gridy = 1;
		getCriteriaPanel().add(stateComboBox, gbc_stateComboBox);

		JLabel customerEmailLabel = new JLabel("Customer email:");
		GridBagConstraints gbc_customerEmailLabel = new GridBagConstraints();
		gbc_customerEmailLabel.anchor = GridBagConstraints.EAST;
		gbc_customerEmailLabel.insets = new Insets(0, 0, 0, 5);
		gbc_customerEmailLabel.gridx = 3;
		gbc_customerEmailLabel.gridy = 1;
		getCriteriaPanel().add(customerEmailLabel, gbc_customerEmailLabel);

		customerEmailTextField = new JTextField();
		GridBagConstraints gbc_customerEmailTextField = new GridBagConstraints();
		gbc_customerEmailTextField.insets = new Insets(0, 0, 0, 5);
		gbc_customerEmailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_customerEmailTextField.gridx = 4;
		gbc_customerEmailTextField.gridy = 1;
		getCriteriaPanel().add(customerEmailTextField, gbc_customerEmailTextField);
		customerEmailTextField.setColumns(10);

		JLabel amountLabel = new JLabel("Amount:");
		GridBagConstraints gbc_amountLabel = new GridBagConstraints();
		gbc_amountLabel.anchor = GridBagConstraints.EAST;
		gbc_amountLabel.insets = new Insets(0, 0, 0, 5);
		gbc_amountLabel.gridx = 6;
		gbc_amountLabel.gridy = 1;
		getCriteriaPanel().add(amountLabel, gbc_amountLabel);

		JPanel priceRangePanel = new JPanel();
		GridBagConstraints gbc_priceRangePanel = new GridBagConstraints();
		gbc_priceRangePanel.gridwidth = 4;
		gbc_priceRangePanel.fill = GridBagConstraints.BOTH;
		gbc_priceRangePanel.gridx = 7;
		gbc_priceRangePanel.gridy = 1;
		getCriteriaPanel().add(priceRangePanel, gbc_priceRangePanel);
		priceRangePanel.setLayout(new BoxLayout(priceRangePanel, BoxLayout.X_AXIS));

		minAmountLabel = new JLabel("0");
		minAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		minAmountLabel.setPreferredSize(new Dimension(48, 14));
		minAmountLabel.setMinimumSize(new Dimension(48, 14));
		minAmountLabel.setMaximumSize(new Dimension(48, 14));
		priceRangePanel.add(minAmountLabel);

		amountRangeSlider = new RangeSlider(0, Integer.MAX_VALUE);
		priceRangePanel.add(amountRangeSlider);
		amountRangeSlider.setValue(0);
		amountRangeSlider.setUpperValue(Integer.MAX_VALUE);

		maxAmountLabel = new JLabel(Integer.toString(amountRangeSlider.getUpperValue()));
		maxAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		maxAmountLabel.setPreferredSize(new Dimension(48, 14));
		maxAmountLabel.setMinimumSize(new Dimension(48, 14));
		maxAmountLabel.setMaximumSize(new Dimension(48, 14));
		priceRangePanel.add(maxAmountLabel);
	}

	private void postInitialize() {	
		
		amountRangeSlider.addChangeListener((e) -> {
			minAmountLabel.setText(Integer.valueOf(amountRangeSlider.getValue()).toString());
			maxAmountLabel.setText(Integer.valueOf(amountRangeSlider.getUpperValue()).toString());
		});

		JTable table = getTable();
		TableUtils.initializeActionPanes(table);
		table.setDefaultRenderer(Object.class, new CustomerOrderTableCellRenderer());
		
		addPropertyChangeListener(CRITERIA_PROPERTY, new SetCustomerOrderRangesAction(this));
	}

	@Override
	@SuppressWarnings("unchecked")
	public CustomerOrderCriteria getCriteria() {
		CustomerOrderCriteria criteria = new CustomerOrderCriteria();

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

		criteria.setState(((ItemState<CustomerOrder>) stateComboBox.getSelectedItem()).getId());

		criteria.setMinAmount(Double.valueOf(amountRangeSlider.getValue()));
		criteria.setMaxAmount(Double.valueOf(amountRangeSlider.getUpperValue()));

		return criteria;
	}
	
	public void setRanges(CustomerOrderRanges ranges) {
		dateFromChooser.setDate(ranges.getMinDate());
		dateToChooser.setDate(ranges.getMaxDate());
		amountRangeSlider.setMinimum(ranges.getMinAmount().intValue());
		amountRangeSlider.setValue(amountRangeSlider.getMinimum());
		amountRangeSlider.setMaximum(ranges.getMaxAmount().intValue());
		amountRangeSlider.setUpperValue(amountRangeSlider.getMaximum());
	}

	@Override
	public void setFieldsEnabled(boolean isEnabled) {
		customerIdTextField.setEnabled(isEnabled);
		customerEmailTextField.setEnabled(isEnabled);
		dateFromChooser.setEnabled(isEnabled);
		dateToChooser.setEnabled(isEnabled);
		amountRangeSlider.setEnabled(isEnabled);
		stateComboBox.setEnabled(isEnabled);
	}

	@Override
	protected void doResetCriteriaFields(Object source) {
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
		if (!amountRangeSlider.equals(source)) {
			amountRangeSlider.setMinimum(0);
			amountRangeSlider.setValue(amountRangeSlider.getMinimum());
			amountRangeSlider.setMaximum(Integer.MAX_VALUE);
			amountRangeSlider.setUpperValue(amountRangeSlider.getMaximum());
		}
		if (!stateComboBox.equals(source)) {
			stateComboBox.setSelectedIndex(0);
		}
	}

}
