package com.pinguela.yourpc.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.pinguela.yourpc.desktop.components.ActionPane;
import com.pinguela.yourpc.model.CustomerOrder;

public class CustomerOrderView 
extends AbstractItemView<CustomerOrder> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4439375266039375673L;
	
	private JTextField customerIdTextField;
	private JLabel idValueLabel;
	private JLabel orderLineListLabel;
	private OrderLineListView orderLineListView;
	private ActionPane billingAddressActionPane;
	private ActionPane shippingAddressActionPane;
	private AddressView billingAddressView;
	private AddressView shippingAddressView;
	
	public CustomerOrderView() {
		initialize();
	}
	
	private void initialize() {
		GridBagLayout gridBagLayout = (GridBagLayout) getViewPanel().getLayout();
		gridBagLayout.rowHeights = new int[]{0, 0, 28};
		gridBagLayout.columnWidths = new int[]{0, 200, 48, 0, 200, 0, 0, 0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
			
		JLabel idLabel = new JLabel("ID:");
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.anchor = GridBagConstraints.EAST;
		gbc_idLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idLabel.gridx = 0;
		gbc_idLabel.gridy = 0;
		getViewPanel().add(idLabel, gbc_idLabel);
		
		idValueLabel = new JLabel("0");
		GridBagConstraints gbc_idValueLabel = new GridBagConstraints();
		gbc_idValueLabel.anchor = GridBagConstraints.WEST;
		gbc_idValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idValueLabel.gridx = 1;
		gbc_idValueLabel.gridy = 0;
		getViewPanel().add(idValueLabel, gbc_idValueLabel);
		
		JLabel customerIdLabel = new JLabel("Customer ID:");
		GridBagConstraints gbc_customerIdLabel = new GridBagConstraints();
		gbc_customerIdLabel.anchor = GridBagConstraints.EAST;
		gbc_customerIdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_customerIdLabel.gridx = 3;
		gbc_customerIdLabel.gridy = 0;
		getViewPanel().add(customerIdLabel, gbc_customerIdLabel);
		
		customerIdTextField = new JTextField();
		GridBagConstraints gbc_customerIdTextField = new GridBagConstraints();
		gbc_customerIdTextField.insets = new Insets(0, 0, 5, 5);
		gbc_customerIdTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_customerIdTextField.gridx = 4;
		gbc_customerIdTextField.gridy = 0;
		getViewPanel().add(customerIdTextField, gbc_customerIdTextField);
		customerIdTextField.setColumns(10);
		
		orderLineListLabel = new JLabel("Products:");
		GridBagConstraints gbc_orderLineListLabel = new GridBagConstraints();
		gbc_orderLineListLabel.insets = new Insets(0, 0, 5, 5);
		gbc_orderLineListLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_orderLineListLabel.gridx = 6;
		gbc_orderLineListLabel.gridy = 0;
		getViewPanel().add(orderLineListLabel, gbc_orderLineListLabel);
		
		orderLineListView = new OrderLineListView();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 3;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 7;
		gbc_panel_2.gridy = 0;
		getViewPanel().add(orderLineListView, gbc_panel_2);
		
		JLabel billingAddressLabel = new JLabel("Billing address:");
		GridBagConstraints gbc_billingAddressLabel = new GridBagConstraints();
		gbc_billingAddressLabel.anchor = GridBagConstraints.EAST;
		gbc_billingAddressLabel.insets = new Insets(0, 0, 5, 5);
		gbc_billingAddressLabel.gridx = 0;
		gbc_billingAddressLabel.gridy = 1;
		getViewPanel().add(billingAddressLabel, gbc_billingAddressLabel);
		
		billingAddressActionPane = new ActionPane();
		GridBagConstraints gbc_billingAddressActionPane;
		gbc_billingAddressActionPane = new GridBagConstraints();
		gbc_billingAddressActionPane.insets = new Insets(0, 0, 5, 5);
		gbc_billingAddressActionPane.fill = GridBagConstraints.BOTH;
		gbc_billingAddressActionPane.gridx = 1;
		gbc_billingAddressActionPane.gridy = 1;
		getViewPanel().add(billingAddressActionPane, gbc_billingAddressActionPane);
		
		JLabel shippingAddressLabel = new JLabel("Shipping address:");
		GridBagConstraints gbc_shippingAddressLabel = new GridBagConstraints();
		gbc_shippingAddressLabel.anchor = GridBagConstraints.EAST;
		gbc_shippingAddressLabel.insets = new Insets(0, 0, 5, 5);
		gbc_shippingAddressLabel.gridx = 3;
		gbc_shippingAddressLabel.gridy = 1;
		getViewPanel().add(shippingAddressLabel, gbc_shippingAddressLabel);
		
		shippingAddressActionPane = new ActionPane();
		GridBagConstraints gbc_shippingAddressActionPane = new GridBagConstraints();
		gbc_shippingAddressActionPane.insets = new Insets(0, 0, 5, 5);
		gbc_shippingAddressActionPane.fill = GridBagConstraints.BOTH;
		gbc_shippingAddressActionPane.gridx = 4;
		gbc_shippingAddressActionPane.gridy = 1;
		getViewPanel().add(shippingAddressActionPane, gbc_shippingAddressActionPane);
		
		billingAddressView = new AddressView(AddressView.CUSTOMER);
		GridBagConstraints gbc_billingAddressView;
		gbc_billingAddressView = new GridBagConstraints();
		gbc_billingAddressView.insets = new Insets(0, 0, 0, 5);
		gbc_billingAddressView.fill = GridBagConstraints.BOTH;
		gbc_billingAddressView.gridx = 1;
		gbc_billingAddressView.gridy = 2;
		getViewPanel().add(billingAddressView, gbc_billingAddressView);
		
		shippingAddressView = new AddressView(AddressView.CUSTOMER);
		GridBagConstraints gbc_shippingAddressView = new GridBagConstraints();
		gbc_shippingAddressView.insets = new Insets(0, 0, 0, 5);
		gbc_shippingAddressView.fill = GridBagConstraints.BOTH;
		gbc_shippingAddressView.gridx = 4;
		gbc_shippingAddressView.gridy = 2;
		getViewPanel().add(shippingAddressView, gbc_shippingAddressView);
	}

	@Override
	public CustomerOrder getNewItem() {
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
