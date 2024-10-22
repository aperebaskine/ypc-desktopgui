package com.pinguela.yourpc.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.components.CustomerAddressSelector;
import com.pinguela.yourpc.desktop.components.CustomerSelector;
import com.pinguela.yourpc.desktop.constants.DBConstants;
import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.desktop.util.DialogUtils;
import com.pinguela.yourpc.model.Address;
import com.pinguela.yourpc.model.Customer;
import com.pinguela.yourpc.model.CustomerOrder;
import com.pinguela.yourpc.model.OrderState;
import com.pinguela.yourpc.service.AddressService;
import com.pinguela.yourpc.service.CustomerService;
import com.pinguela.yourpc.service.impl.AddressServiceImpl;
import com.pinguela.yourpc.service.impl.CustomerServiceImpl;

@SuppressWarnings("serial")
public class CustomerOrderView 
extends AbstractEntityView<CustomerOrder> {

	private static Logger logger = LogManager.getLogger(CustomerOrderView.class);

	private CustomerService customerService;
	private AddressService addressService;

	private JLabel idValueLabel;
	private JComboBox<OrderState> stateComboBox;
	private OrderLineListView orderLineListView;
	private CustomerSelector customerSelector;
	private CustomerAddressSelector billingAddressSelector;
	private CustomerAddressSelector shippingAddressSelector;

	private final PropertyChangeListener customerSelectionListener = (evt) -> {
		Customer customer = (Customer) evt.getNewValue();
		
		billingAddressSelector.setEnabled(customer != null);
		shippingAddressSelector.setEnabled(customer != null);
		
		if (getCurrentEntity() == null && customer != null) {
			for (Address address : customer.getAddresses()) {
				if (address.isDefault()) {
					shippingAddressSelector.setEntity(address);
				}
				if (address.isBilling()) {
					billingAddressSelector.setEntity(address);
				}
			}
		}
	};
	private JTextField trackingNumberTextField;

	public CustomerOrderView() {
		initialize();
		this.customerService = new CustomerServiceImpl();
		this.addressService = new AddressServiceImpl(); 
	}

	private void initialize() {
		
		JPanel viewPanel = getViewPanel();
		
		GridBagLayout gridBagLayout = (GridBagLayout) viewPanel.getLayout();
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 28, 0, 0};
		gridBagLayout.columnWidths = new int[]{0, 200, 48, 0, 200};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0};

		JLabel idLabel = new JLabel("ID:");
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.anchor = GridBagConstraints.EAST;
		gbc_idLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idLabel.gridx = 0;
		gbc_idLabel.gridy = 0;
		viewPanel.add(idLabel, gbc_idLabel);

		idValueLabel = new JLabel("0");
		GridBagConstraints gbc_idValueLabel = new GridBagConstraints();
		gbc_idValueLabel.anchor = GridBagConstraints.WEST;
		gbc_idValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idValueLabel.gridx = 1;
		gbc_idValueLabel.gridy = 0;
		viewPanel.add(idValueLabel, gbc_idValueLabel);

		JLabel customerLabel = new JLabel("Customer:");
		GridBagConstraints gbc_customerLabel = new GridBagConstraints();
		gbc_customerLabel.anchor = GridBagConstraints.EAST;
		gbc_customerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_customerLabel.gridx = 0;
		gbc_customerLabel.gridy = 1;
		viewPanel.add(customerLabel, gbc_customerLabel);

		customerSelector = new CustomerSelector();
		customerSelector.addPropertyChangeListener(CustomerSelector.ENTITY_PROPERTY, customerSelectionListener);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		viewPanel.add(customerSelector, gbc_panel);
		
		JLabel stateLabel = new JLabel("State:");
		GridBagConstraints gbc_stateLabel = new GridBagConstraints();
		gbc_stateLabel.anchor = GridBagConstraints.EAST;
		gbc_stateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_stateLabel.gridx = 3;
		gbc_stateLabel.gridy = 1;
		viewPanel.add(stateLabel, gbc_stateLabel);
		
		stateComboBox = ComponentFactory.createComboBox(DBConstants.ORDER_STATES.values(), OrderState.class);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 1;
		viewPanel.add(stateComboBox, gbc_comboBox);

		JLabel billingAddressLabel = new JLabel("Billing address:");
		GridBagConstraints gbc_billingAddressLabel = new GridBagConstraints();
		gbc_billingAddressLabel.anchor = GridBagConstraints.EAST;
		gbc_billingAddressLabel.insets = new Insets(0, 0, 5, 5);
		gbc_billingAddressLabel.gridx = 0;
		gbc_billingAddressLabel.gridy = 2;
		viewPanel.add(billingAddressLabel, gbc_billingAddressLabel);

		billingAddressSelector = new CustomerAddressSelector(customerSelector);
		billingAddressSelector.setEnabled(false);
		GridBagConstraints gbc_billingAddressActionPane;
		gbc_billingAddressActionPane = new GridBagConstraints();
		gbc_billingAddressActionPane.insets = new Insets(0, 0, 5, 5);
		gbc_billingAddressActionPane.fill = GridBagConstraints.BOTH;
		gbc_billingAddressActionPane.gridx = 1;
		gbc_billingAddressActionPane.gridy = 2;
		viewPanel.add(billingAddressSelector, gbc_billingAddressActionPane);
		
		JLabel trackingNumberLabel = new JLabel("Tracking number:");
		GridBagConstraints gbc_trackingNumberLabel = new GridBagConstraints();
		gbc_trackingNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_trackingNumberLabel.anchor = GridBagConstraints.EAST;
		gbc_trackingNumberLabel.gridx = 3;
		gbc_trackingNumberLabel.gridy = 2;
		viewPanel.add(trackingNumberLabel, gbc_trackingNumberLabel);
		
		trackingNumberTextField = new JTextField();
		GridBagConstraints gbc_trackingNumberTextField = new GridBagConstraints();
		gbc_trackingNumberTextField.insets = new Insets(0, 0, 5, 0);
		gbc_trackingNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_trackingNumberTextField.gridx = 4;
		gbc_trackingNumberTextField.gridy = 2;
		viewPanel.add(trackingNumberTextField, gbc_trackingNumberTextField);
		trackingNumberTextField.setColumns(10);

		JLabel shippingAddressLabel = new JLabel("Shipping address:");
		GridBagConstraints gbc_shippingAddressLabel = new GridBagConstraints();
		gbc_shippingAddressLabel.anchor = GridBagConstraints.EAST;
		gbc_shippingAddressLabel.insets = new Insets(0, 0, 5, 5);
		gbc_shippingAddressLabel.gridx = 0;
		gbc_shippingAddressLabel.gridy = 3;
		viewPanel.add(shippingAddressLabel, gbc_shippingAddressLabel);

		shippingAddressSelector = new CustomerAddressSelector(customerSelector);
		shippingAddressSelector.setEnabled(false);
		GridBagConstraints gbc_shippingAddressActionPane = new GridBagConstraints();
		gbc_shippingAddressActionPane.insets = new Insets(0, 0, 5, 5);
		gbc_shippingAddressActionPane.fill = GridBagConstraints.BOTH;
		gbc_shippingAddressActionPane.gridx = 1;
		gbc_shippingAddressActionPane.gridy = 3;
		viewPanel.add(shippingAddressSelector, gbc_shippingAddressActionPane);

		JLabel orderLineListLabel = new JLabel("Products:");
		GridBagConstraints gbc_orderLineListLabel = new GridBagConstraints();
		gbc_orderLineListLabel.insets = new Insets(0, 0, 5, 5);
		gbc_orderLineListLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_orderLineListLabel.gridx = 0;
		gbc_orderLineListLabel.gridy = 4;
		viewPanel.add(orderLineListLabel, gbc_orderLineListLabel);

		orderLineListView = new OrderLineListView();
		orderLineListView.setBorder(new EmptyBorder(0, 0, 0, 0));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 4;
		gbc_panel_2.gridheight = 5;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 4;
		viewPanel.add(orderLineListView, gbc_panel_2);
	}

	@Override
	@SuppressWarnings("unchecked")
	public CustomerOrder getDTOFromFields() {
		CustomerOrder order = new CustomerOrder();
		CustomerOrder updating = getCurrentEntity();
	
		if (updating != null) {
			order.setId(updating.getId());
			order.setOrderDate(updating.getOrderDate());
		}
		
		order.setOrderLines(orderLineListView.getCurrentEntity());
		order.setState(((OrderState) stateComboBox.getSelectedItem()).getId());
		order.setBillingAddressId(billingAddressSelector.getEntity().getId());
		order.setShippingAddressId(shippingAddressSelector.getEntity().getId());
		order.setTrackingNumber(trackingNumberTextField.getText());
		
		return order;
	}

	@Override
	public void resetFields() {

		if (getCurrentEntity() == null) {
			customerSelector.setEntity(null);
			billingAddressSelector.setEntity(null);
			shippingAddressSelector.setEntity(null);
		}
		idValueLabel.setText("");	
		orderLineListView.resetFields();
		stateComboBox.setSelectedIndex(0);
		trackingNumberTextField.setText("");
	}

	@Override
	protected void setFieldsEditable(boolean isEditable) {
		orderLineListView.setFieldsEditable(isEditable);
		stateComboBox.setEnabled(isEditable);
		trackingNumberTextField.setEditable(isEditable);
	}

	@Override
	protected void loadItemData() {
		CustomerOrder order = getCurrentEntity();

		idValueLabel.setText(order.getId() != null ? order.getId().toString() : "");
		orderLineListView.setEntity(order.getOrderLines());
		stateComboBox.setSelectedItem(DBConstants.ORDER_STATES.get(order.getState()));
		trackingNumberTextField.setText(order.getTrackingNumber());
		
		try {
			customerSelector.setEntity(customerService.findById(order.getCustomerId()));
			billingAddressSelector.setEntity(addressService.findById(order.getBillingAddressId()));
			shippingAddressSelector.setEntity(addressService.findById(order.getShippingAddressId()));
		} catch (YPCException e) {
			logger.error(e.getMessage(), e);
			DialogUtils.showDatabaseAccessErrorDialog(this);
		} 
	}

}
