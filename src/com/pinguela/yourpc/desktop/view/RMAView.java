package com.pinguela.yourpc.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.pinguela.yourpc.desktop.constants.DBConstants;
import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.model.ItemState;
import com.pinguela.yourpc.model.RMA;

public class RMAView 
extends AbstractItemView<RMA> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2240496953079459574L;

	private JLabel idValueLabel;
	private JTextField customerIdTextField;
	private JTextField trackingNumberTextField;
	private JComboBox<ItemState<RMA>> stateComboBox;
	private OrderLineListView orderLineListView;

	public RMAView() {
		initialize();
		postInitialize();
	}

	private void initialize() {
		GridBagLayout gridBagLayout = (GridBagLayout) getViewPanel().getLayout();
		gridBagLayout.columnWidths = new int[]{0, 152, 48, 0, 240};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};

		JLabel idLabel = new JLabel("ID:");
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idLabel.anchor = GridBagConstraints.EAST;
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

		JLabel orderLineListLabel = new JLabel("Products:");
		GridBagConstraints gbc_orderLineListLabel = new GridBagConstraints();
		gbc_orderLineListLabel.insets = new Insets(0, 0, 5, 5);
		gbc_orderLineListLabel.gridx = 3;
		gbc_orderLineListLabel.gridy = 0;
		getViewPanel().add(orderLineListLabel, gbc_orderLineListLabel);

		JScrollPane messageListScrollPane = new JScrollPane();
		GridBagConstraints gbc_messageListScrollPane = new GridBagConstraints();
		gbc_messageListScrollPane.gridheight = 7;
		gbc_messageListScrollPane.fill = GridBagConstraints.BOTH;
		gbc_messageListScrollPane.gridx = 4;
		gbc_messageListScrollPane.gridy = 0;
		getViewPanel().add(messageListScrollPane, gbc_messageListScrollPane);

		orderLineListView = new OrderLineListView();
		messageListScrollPane.setViewportView(orderLineListView);

		JLabel creationDateLabel = new JLabel("Creation Date:");
		GridBagConstraints gbc_creationDateLabel = new GridBagConstraints();
		gbc_creationDateLabel.anchor = GridBagConstraints.EAST;
		gbc_creationDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_creationDateLabel.gridx = 0;
		gbc_creationDateLabel.gridy = 1;
		getViewPanel().add(creationDateLabel, gbc_creationDateLabel);

		JLabel creationDateValueLabel = new JLabel("01/01/1970");
		GridBagConstraints gbc_creationDateValueLabel = new GridBagConstraints();
		gbc_creationDateValueLabel.anchor = GridBagConstraints.WEST;
		gbc_creationDateValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_creationDateValueLabel.gridx = 1;
		gbc_creationDateValueLabel.gridy = 1;
		getViewPanel().add(creationDateValueLabel, gbc_creationDateValueLabel);

		JLabel customerIdLabel = new JLabel("Customer ID:");
		GridBagConstraints gbc_customerIdLabel = new GridBagConstraints();
		gbc_customerIdLabel.anchor = GridBagConstraints.EAST;
		gbc_customerIdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_customerIdLabel.gridx = 0;
		gbc_customerIdLabel.gridy = 2;
		getViewPanel().add(customerIdLabel, gbc_customerIdLabel);

		customerIdTextField = new JTextField();
		GridBagConstraints gbc_customerIdTextField = new GridBagConstraints();
		gbc_customerIdTextField.insets = new Insets(0, 0, 5, 5);
		gbc_customerIdTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_customerIdTextField.gridx = 1;
		gbc_customerIdTextField.gridy = 2;
		getViewPanel().add(customerIdTextField, gbc_customerIdTextField);
		customerIdTextField.setColumns(10);

		JLabel stateLabel = new JLabel("State:");
		GridBagConstraints gbc_stateLabel = new GridBagConstraints();
		gbc_stateLabel.anchor = GridBagConstraints.EAST;
		gbc_stateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_stateLabel.gridx = 0;
		gbc_stateLabel.gridy = 3;
		getViewPanel().add(stateLabel, gbc_stateLabel);

		JLabel trackingNumberLabel = new JLabel("Tracking number:");
		GridBagConstraints gbc_trackingNumberLabel = new GridBagConstraints();
		gbc_trackingNumberLabel.anchor = GridBagConstraints.EAST;
		gbc_trackingNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_trackingNumberLabel.gridx = 0;
		gbc_trackingNumberLabel.gridy = 4;
		getViewPanel().add(trackingNumberLabel, gbc_trackingNumberLabel);

		trackingNumberTextField = new JTextField();
		GridBagConstraints gbc_titleTextField = new GridBagConstraints();
		gbc_titleTextField.insets = new Insets(0, 0, 5, 5);
		gbc_titleTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleTextField.gridx = 1;
		gbc_titleTextField.gridy = 4;
		getViewPanel().add(trackingNumberTextField, gbc_titleTextField);
		trackingNumberTextField.setColumns(10);
	}

	private void postInitialize() {

		stateComboBox = ComponentFactory.createComboBox(DBConstants.RMA_STATES.values(), ItemState.class);
		GridBagConstraints gbc_stateComboBox = new GridBagConstraints();
		gbc_stateComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_stateComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_stateComboBox.gridx = 1;
		gbc_stateComboBox.gridy = 3;
		getViewPanel().add(stateComboBox, gbc_stateComboBox);
	}

	@Override
	@SuppressWarnings("unchecked")
	public RMA getNewItem() {
		RMA old = getItem();
		RMA rma = new RMA();
		
		if (old != null) {
			rma.setId(old.getId());
			rma.setCreationDate(old.getCreationDate());
		}

        if (!idValueLabel.getText().isEmpty()) {
            rma.setId(Long.parseLong(idValueLabel.getText()));
        }

        if (!customerIdTextField.getText().isEmpty()) {
            rma.setCustomerId(Integer.parseInt(customerIdTextField.getText()));
        }

        if (!trackingNumberTextField.getText().isEmpty()) {
        	rma.setTrackingNumber(trackingNumberTextField.getText());
        }

        rma.setState(((ItemState<RMA>) stateComboBox.getSelectedItem()).getId());

        rma.setOrderLines(orderLineListView.getItem());

        return rma;
	}

	@Override
	public void resetFields() {
		idValueLabel.setText("");
		customerIdTextField.setText("");
		trackingNumberTextField.setText("");
		stateComboBox.setSelectedIndex(0);
		orderLineListView.resetFields();
	}

	@Override
	protected void setFieldsEditable(boolean isEditable) {
		customerIdTextField.setEditable(isEditable);
		trackingNumberTextField.setEditable(isEditable);
		stateComboBox.setEnabled(isEditable);
		orderLineListView.setFieldsEditable(isEditable);
	}

	@Override
	protected void onItemSet() {

		RMA rma = getItem();
		
		idValueLabel.setText(rma.getId() != null ? rma.getId().toString() : "");
		customerIdTextField.setText(rma.getCustomerId() != null ? rma.getCustomerId().toString() : "");
		trackingNumberTextField.setText(rma.getTrackingNumber());
		stateComboBox.setSelectedItem(DBConstants.RMA_STATES.get(rma.getState()));
		orderLineListView.setItem(rma.getOrderLines());
	}

}
