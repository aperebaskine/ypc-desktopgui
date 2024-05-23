package com.pinguela.yourpc.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.pinguela.yourpc.model.Address;

/**
 * TODO: Implement combo box functionality
 */
public class AddressItemView 
extends AbstractItemView<Address> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9029757229669312561L;
	
	private JTextField streetNameTextField;
	private JTextField doorTextField;
	private JTextField zipCodeTextField;
	private JComboBox cityComboBox;
	private JComboBox provinceComboBox;
	private JComboBox countryComboBox;
	private JCheckBox defaultCheckbox;
	private JCheckBox billingCheckbox;
	private JFormattedTextField floorFormattedTextField;
	
	public AddressItemView() {
		initialize();
	}
	
	private void initialize() {
		GridBagLayout gridBagLayout = (GridBagLayout) getViewPanel().getLayout();
		gridBagLayout.columnWidths = new int[]{80, 28, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		
		JLabel streetNameLabel = new JLabel("Street name:");
		GridBagConstraints gbc_streetNameLabel = new GridBagConstraints();
		gbc_streetNameLabel.anchor = GridBagConstraints.EAST;
		gbc_streetNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_streetNameLabel.gridx = 0;
		gbc_streetNameLabel.gridy = 0;
		getViewPanel().add(streetNameLabel, gbc_streetNameLabel);
		
		streetNameTextField = new JTextField();
		GridBagConstraints gbc_streetNameTextField = new GridBagConstraints();
		gbc_streetNameTextField.gridwidth = 3;
		gbc_streetNameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_streetNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_streetNameTextField.gridx = 1;
		gbc_streetNameTextField.gridy = 0;
		getViewPanel().add(streetNameTextField, gbc_streetNameTextField);
		streetNameTextField.setColumns(10);
		
		JLabel streetNumberLabel = new JLabel("Street number:");
		GridBagConstraints gbc_streetNumberLabel = new GridBagConstraints();
		gbc_streetNumberLabel.anchor = GridBagConstraints.EAST;
		gbc_streetNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_streetNumberLabel.gridx = 0;
		gbc_streetNumberLabel.gridy = 1;
		getViewPanel().add(streetNumberLabel, gbc_streetNumberLabel);
		
		JFormattedTextField streetNumberFormattedTextField = new JFormattedTextField();
		GridBagConstraints gbc_streetNumberFormattedTextField = new GridBagConstraints();
		gbc_streetNumberFormattedTextField.gridwidth = 3;
		gbc_streetNumberFormattedTextField.insets = new Insets(0, 0, 5, 0);
		gbc_streetNumberFormattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_streetNumberFormattedTextField.gridx = 1;
		gbc_streetNumberFormattedTextField.gridy = 1;
		getViewPanel().add(streetNumberFormattedTextField, gbc_streetNumberFormattedTextField);
		
		JLabel floorLabel = new JLabel("Floor:");
		GridBagConstraints gbc_floorLabel = new GridBagConstraints();
		gbc_floorLabel.anchor = GridBagConstraints.EAST;
		gbc_floorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_floorLabel.gridx = 0;
		gbc_floorLabel.gridy = 2;
		getViewPanel().add(floorLabel, gbc_floorLabel);
		
		floorFormattedTextField = new JFormattedTextField();
		GridBagConstraints gbc_floorFormattedTextField = new GridBagConstraints();
		gbc_floorFormattedTextField.gridwidth = 3;
		gbc_floorFormattedTextField.insets = new Insets(0, 0, 5, 0);
		gbc_floorFormattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_floorFormattedTextField.gridx = 1;
		gbc_floorFormattedTextField.gridy = 2;
		getViewPanel().add(floorFormattedTextField, gbc_floorFormattedTextField);
		
		JLabel doorLabel = new JLabel("Door:");
		GridBagConstraints gbc_doorLabel = new GridBagConstraints();
		gbc_doorLabel.anchor = GridBagConstraints.EAST;
		gbc_doorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_doorLabel.gridx = 0;
		gbc_doorLabel.gridy = 3;
		getViewPanel().add(doorLabel, gbc_doorLabel);
		
		doorTextField = new JTextField();
		GridBagConstraints gbc_doorTextField = new GridBagConstraints();
		gbc_doorTextField.gridwidth = 3;
		gbc_doorTextField.insets = new Insets(0, 0, 5, 0);
		gbc_doorTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_doorTextField.gridx = 1;
		gbc_doorTextField.gridy = 3;
		getViewPanel().add(doorTextField, gbc_doorTextField);
		doorTextField.setColumns(10);
		
		JLabel zipCodeLabel = new JLabel("ZIP Code:");
		GridBagConstraints gbc_zipCodeLabel = new GridBagConstraints();
		gbc_zipCodeLabel.anchor = GridBagConstraints.EAST;
		gbc_zipCodeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_zipCodeLabel.gridx = 0;
		gbc_zipCodeLabel.gridy = 4;
		getViewPanel().add(zipCodeLabel, gbc_zipCodeLabel);
		
		zipCodeTextField = new JTextField();
		GridBagConstraints gbc_zipCodeTextField = new GridBagConstraints();
		gbc_zipCodeTextField.gridwidth = 3;
		gbc_zipCodeTextField.insets = new Insets(0, 0, 5, 0);
		gbc_zipCodeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_zipCodeTextField.gridx = 1;
		gbc_zipCodeTextField.gridy = 4;
		getViewPanel().add(zipCodeTextField, gbc_zipCodeTextField);
		zipCodeTextField.setColumns(10);
		
		JLabel cityLabel = new JLabel("City:");
		GridBagConstraints gbc_cityLabel = new GridBagConstraints();
		gbc_cityLabel.anchor = GridBagConstraints.EAST;
		gbc_cityLabel.insets = new Insets(0, 0, 5, 5);
		gbc_cityLabel.gridx = 0;
		gbc_cityLabel.gridy = 5;
		getViewPanel().add(cityLabel, gbc_cityLabel);
		
		cityComboBox = new JComboBox();
		GridBagConstraints gbc_cityComboBox = new GridBagConstraints();
		gbc_cityComboBox.gridwidth = 3;
		gbc_cityComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_cityComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_cityComboBox.gridx = 1;
		gbc_cityComboBox.gridy = 5;
		getViewPanel().add(cityComboBox, gbc_cityComboBox);
		
		JLabel provinceLabel = new JLabel("Province:");
		GridBagConstraints gbc_provinceLabel = new GridBagConstraints();
		gbc_provinceLabel.anchor = GridBagConstraints.EAST;
		gbc_provinceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_provinceLabel.gridx = 0;
		gbc_provinceLabel.gridy = 6;
		getViewPanel().add(provinceLabel, gbc_provinceLabel);
		
		provinceComboBox = new JComboBox();
		GridBagConstraints gbc_provinceComboBox = new GridBagConstraints();
		gbc_provinceComboBox.gridwidth = 3;
		gbc_provinceComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_provinceComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_provinceComboBox.gridx = 1;
		gbc_provinceComboBox.gridy = 6;
		getViewPanel().add(provinceComboBox, gbc_provinceComboBox);
		
		JLabel countryLabel = new JLabel("Country:");
		GridBagConstraints gbc_countryLabel = new GridBagConstraints();
		gbc_countryLabel.anchor = GridBagConstraints.EAST;
		gbc_countryLabel.insets = new Insets(0, 0, 5, 5);
		gbc_countryLabel.gridx = 0;
		gbc_countryLabel.gridy = 7;
		getViewPanel().add(countryLabel, gbc_countryLabel);
		
		countryComboBox = new JComboBox();
		GridBagConstraints gbc_countryComboBox = new GridBagConstraints();
		gbc_countryComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_countryComboBox.gridwidth = 3;
		gbc_countryComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_countryComboBox.gridx = 1;
		gbc_countryComboBox.gridy = 7;
		getViewPanel().add(countryComboBox, gbc_countryComboBox);
		
		defaultCheckbox = new JCheckBox("Default address");
		GridBagConstraints gbc_defaultCheckbox = new GridBagConstraints();
		gbc_defaultCheckbox.insets = new Insets(0, 0, 0, 5);
		gbc_defaultCheckbox.gridx = 1;
		gbc_defaultCheckbox.gridy = 8;
		getViewPanel().add(defaultCheckbox, gbc_defaultCheckbox);
		
		billingCheckbox = new JCheckBox("Billing address");
		GridBagConstraints gbc_billingCheckbox = new GridBagConstraints();
		gbc_billingCheckbox.gridx = 3;
		gbc_billingCheckbox.gridy = 8;
		getViewPanel().add(billingCheckbox, gbc_billingCheckbox);
	}

	@Override
	public Address getModifiedItem() {
		Address address = new Address();
        address.setStreetName(streetNameTextField.getText());
        address.setFloor(Short.valueOf(floorFormattedTextField.getText()));
        address.setDoor(doorTextField.getText());
        address.setZipCode(zipCodeTextField.getText());
        address.setCity((String) cityComboBox.getSelectedItem());
        address.setProvince((String) provinceComboBox.getSelectedItem());
        address.setCountry((String) countryComboBox.getSelectedItem());
        address.setIsDefault(defaultCheckbox.isSelected());
        address.setIsBilling(billingCheckbox.isSelected());
        return address;
	}

	@Override
	public void resetFields() {
        streetNameTextField.setText("");
        floorFormattedTextField.setText("");
        doorTextField.setText("");
        zipCodeTextField.setText("");
        cityComboBox.setSelectedIndex(0);
        provinceComboBox.setSelectedIndex(0);
        countryComboBox.setSelectedIndex(0);
        defaultCheckbox.setSelected(false);
        billingCheckbox.setSelected(false);
	}

	@Override
	protected void setFieldsEditable(boolean isEditable) {
        streetNameTextField.setEditable(isEditable);
        floorFormattedTextField.setEditable(isEditable);
        doorTextField.setEditable(isEditable);
        zipCodeTextField.setEditable(isEditable);
        cityComboBox.setEnabled(isEditable);
        provinceComboBox.setEnabled(isEditable);
        countryComboBox.setEnabled(isEditable);
        defaultCheckbox.setEnabled(isEditable);
        billingCheckbox.setEnabled(isEditable);
	}

	@Override
	protected void onItemSet() {
        streetNameTextField.setText(getItem().getStreetName());
        floorFormattedTextField.setText(getItem().getFloor().toString());
        doorTextField.setText(getItem().getDoor());
        zipCodeTextField.setText(getItem().getZipCode());
        cityComboBox.setSelectedItem(getItem().getCity());
        provinceComboBox.setSelectedItem(getItem().getProvince());
        countryComboBox.setSelectedItem(getItem().getCountry());
        defaultCheckbox.setSelected(getItem().isDefault());
        billingCheckbox.setSelected(getItem().isBilling());
	}

}
