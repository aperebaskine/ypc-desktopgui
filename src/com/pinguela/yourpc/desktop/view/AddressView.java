package com.pinguela.yourpc.desktop.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.desktop.util.DialogUtils;
import com.pinguela.yourpc.model.AbstractTerritory;
import com.pinguela.yourpc.model.Address;
import com.pinguela.yourpc.model.City;
import com.pinguela.yourpc.model.Country;
import com.pinguela.yourpc.model.Province;
import com.pinguela.yourpc.service.CityService;
import com.pinguela.yourpc.service.CountryService;
import com.pinguela.yourpc.service.ProvinceService;
import com.pinguela.yourpc.service.impl.CityServiceImpl;
import com.pinguela.yourpc.service.impl.CountryServiceImpl;
import com.pinguela.yourpc.service.impl.ProvinceServiceImpl;

@SuppressWarnings("serial")
public class AddressView 
extends AbstractEntityView<Address> {

	private static Logger logger = LogManager.getLogger(AddressView.class);

	public static final int CUSTOMER = 0;
	public static final int EMPLOYEE = 1;

	private int ownerType;

	private CountryService countryService;
	private ProvinceService provinceService;
	private CityService cityService;

	{
		countryService = new CountryServiceImpl();
		provinceService = new ProvinceServiceImpl();
		cityService = new CityServiceImpl();
	}

	private JTextField streetNameTextField;
	private JFormattedTextField floorFormattedTextField;
	private JTextField doorTextField;
	private JTextField zipCodeTextField;
	private JComboBox<City> cityComboBox;
	private JComboBox<Province> provinceComboBox;
	private JComboBox<Country> countryComboBox;
	private JCheckBox defaultCheckbox;
	private JCheckBox billingCheckbox;

	private ItemListener countryListener = (e) -> {
		
		cityComboBox.setSelectedIndex(0);
		cityComboBox.setEnabled(false);
		
		Country c = (Country) e.getItem();

		if (c.getId() == null)  {
			provinceComboBox.setSelectedIndex(0);
			provinceComboBox.setEnabled(false);
			return;
		}

		try {	
			List<Province> provinces = provinceService.findByCountry(c.getId());
			provinces.add(0, new Province());
			provinceComboBox.setModel(new DefaultComboBoxModel<Province>(provinces.toArray(new Province[provinces.size()])));
			provinceComboBox.setSelectedIndex(0);
			provinceComboBox.setEnabled(isEditable());
		} catch (YPCException e1) {
			logger.error(e1.getMessage(), e1);
			DialogUtils.showDatabaseAccessErrorDialog(this);
		}
	};

	private ItemListener provinceListener = (e) -> {
		Province p = (Province) e.getItem();

		if (p.getId() == null) {
			cityComboBox.setSelectedIndex(0);
			cityComboBox.setEnabled(false);
			return;
		}

		try {
			List<City> cities = cityService.findByProvince(p.getId());
			cities.add(0, new City());
			cityComboBox.setModel(new DefaultComboBoxModel<City>(cities.toArray(new City[cities.size()])));
			cityComboBox.setSelectedIndex(0);
			cityComboBox.setEnabled(isEditable());
		} catch (YPCException e1) {
			logger.error(e1.getMessage(), e1);
			DialogUtils.showDatabaseAccessErrorDialog(this);
		}
	};

	private JFormattedTextField streetNumberFormattedTextField;

	public AddressView(int ownerType) {
		this.ownerType = ownerType;
		initialize();
		postInitialize();
	}

	private void initialize() {
		
		JPanel viewPanel = getViewPanel();
		
		GridBagLayout gridBagLayout = (GridBagLayout) viewPanel.getLayout();
		gridBagLayout.columnWidths = new int[]{80, 28, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

		JLabel streetNameLabel = new JLabel("Street name:");
		GridBagConstraints gbc_streetNameLabel = new GridBagConstraints();
		gbc_streetNameLabel.anchor = GridBagConstraints.EAST;
		gbc_streetNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_streetNameLabel.gridx = 0;
		gbc_streetNameLabel.gridy = 0;
		viewPanel.add(streetNameLabel, gbc_streetNameLabel);

		streetNameTextField = new JTextField();
		GridBagConstraints gbc_streetNameTextField = new GridBagConstraints();
		gbc_streetNameTextField.gridwidth = 3;
		gbc_streetNameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_streetNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_streetNameTextField.gridx = 1;
		gbc_streetNameTextField.gridy = 0;
		viewPanel.add(streetNameTextField, gbc_streetNameTextField);
		streetNameTextField.setColumns(10);

		JLabel streetNumberLabel = new JLabel("Street number:");
		GridBagConstraints gbc_streetNumberLabel = new GridBagConstraints();
		gbc_streetNumberLabel.anchor = GridBagConstraints.EAST;
		gbc_streetNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_streetNumberLabel.gridx = 0;
		gbc_streetNumberLabel.gridy = 1;
		viewPanel.add(streetNumberLabel, gbc_streetNumberLabel);

		streetNumberFormattedTextField = new JFormattedTextField();
		GridBagConstraints gbc_streetNumberFormattedTextField = new GridBagConstraints();
		gbc_streetNumberFormattedTextField.gridwidth = 3;
		gbc_streetNumberFormattedTextField.insets = new Insets(0, 0, 5, 0);
		gbc_streetNumberFormattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_streetNumberFormattedTextField.gridx = 1;
		gbc_streetNumberFormattedTextField.gridy = 1;
		viewPanel.add(streetNumberFormattedTextField, gbc_streetNumberFormattedTextField);

		JLabel floorLabel = new JLabel("Floor:");
		GridBagConstraints gbc_floorLabel = new GridBagConstraints();
		gbc_floorLabel.anchor = GridBagConstraints.EAST;
		gbc_floorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_floorLabel.gridx = 0;
		gbc_floorLabel.gridy = 2;
		viewPanel.add(floorLabel, gbc_floorLabel);

		floorFormattedTextField = new JFormattedTextField();
		GridBagConstraints gbc_floorFormattedTextField = new GridBagConstraints();
		gbc_floorFormattedTextField.gridwidth = 3;
		gbc_floorFormattedTextField.insets = new Insets(0, 0, 5, 0);
		gbc_floorFormattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_floorFormattedTextField.gridx = 1;
		gbc_floorFormattedTextField.gridy = 2;
		viewPanel.add(floorFormattedTextField, gbc_floorFormattedTextField);

		JLabel doorLabel = new JLabel("Door:");
		GridBagConstraints gbc_doorLabel = new GridBagConstraints();
		gbc_doorLabel.anchor = GridBagConstraints.EAST;
		gbc_doorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_doorLabel.gridx = 0;
		gbc_doorLabel.gridy = 3;
		viewPanel.add(doorLabel, gbc_doorLabel);

		doorTextField = new JTextField();
		GridBagConstraints gbc_doorTextField = new GridBagConstraints();
		gbc_doorTextField.gridwidth = 3;
		gbc_doorTextField.insets = new Insets(0, 0, 5, 0);
		gbc_doorTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_doorTextField.gridx = 1;
		gbc_doorTextField.gridy = 3;
		viewPanel.add(doorTextField, gbc_doorTextField);
		doorTextField.setColumns(10);

		JLabel zipCodeLabel = new JLabel("ZIP Code:");
		GridBagConstraints gbc_zipCodeLabel = new GridBagConstraints();
		gbc_zipCodeLabel.anchor = GridBagConstraints.EAST;
		gbc_zipCodeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_zipCodeLabel.gridx = 0;
		gbc_zipCodeLabel.gridy = 4;
		viewPanel.add(zipCodeLabel, gbc_zipCodeLabel);

		zipCodeTextField = new JTextField();
		GridBagConstraints gbc_zipCodeTextField = new GridBagConstraints();
		gbc_zipCodeTextField.gridwidth = 3;
		gbc_zipCodeTextField.insets = new Insets(0, 0, 5, 0);
		gbc_zipCodeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_zipCodeTextField.gridx = 1;
		gbc_zipCodeTextField.gridy = 4;
		viewPanel.add(zipCodeTextField, gbc_zipCodeTextField);
		zipCodeTextField.setColumns(10);

		JLabel cityLabel = new JLabel("City:");
		GridBagConstraints gbc_cityLabel = new GridBagConstraints();
		gbc_cityLabel.anchor = GridBagConstraints.EAST;
		gbc_cityLabel.insets = new Insets(0, 0, 5, 5);
		gbc_cityLabel.gridx = 0;
		gbc_cityLabel.gridy = 5;
		viewPanel.add(cityLabel, gbc_cityLabel);

		JLabel provinceLabel = new JLabel("Province:");
		GridBagConstraints gbc_provinceLabel = new GridBagConstraints();
		gbc_provinceLabel.anchor = GridBagConstraints.EAST;
		gbc_provinceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_provinceLabel.gridx = 0;
		gbc_provinceLabel.gridy = 6;
		viewPanel.add(provinceLabel, gbc_provinceLabel);

		JLabel countryLabel = new JLabel("Country:");
		GridBagConstraints gbc_countryLabel = new GridBagConstraints();
		gbc_countryLabel.anchor = GridBagConstraints.EAST;
		gbc_countryLabel.insets = new Insets(0, 0, 5, 5);
		gbc_countryLabel.gridx = 0;
		gbc_countryLabel.gridy = 7;
		viewPanel.add(countryLabel, gbc_countryLabel);
	}

	private void postInitialize() {
		
		JPanel viewPanel = getViewPanel();
		
		if (CUSTOMER == ownerType) {
			defaultCheckbox = new JCheckBox("Default address");
			GridBagConstraints gbc_defaultCheckbox = new GridBagConstraints();
			gbc_defaultCheckbox.insets = new Insets(0, 0, 0, 5);
			gbc_defaultCheckbox.gridx = 1;
			gbc_defaultCheckbox.gridy = 8;
			viewPanel.add(defaultCheckbox, gbc_defaultCheckbox);

			billingCheckbox = new JCheckBox("Billing address");
			GridBagConstraints gbc_billingCheckbox = new GridBagConstraints();
			gbc_billingCheckbox.gridx = 3;
			gbc_billingCheckbox.gridy = 8;
			viewPanel.add(billingCheckbox, gbc_billingCheckbox);
		}

		try {
			countryComboBox = ComponentFactory.createComboBox(countryService.findAll(), Country.class);
			GridBagConstraints gbc_countryComboBox = new GridBagConstraints();
			gbc_countryComboBox.insets = new Insets(0, 0, 5, 0);
			gbc_countryComboBox.gridwidth = 3;
			gbc_countryComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_countryComboBox.gridx = 1;
			gbc_countryComboBox.gridy = 7;
			viewPanel.add(countryComboBox, gbc_countryComboBox);
		} catch (YPCException e) {
			logger.error(e.getMessage(), e);
			JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), 
					"Could not fetch country data.", "Error", JOptionPane.ERROR_MESSAGE);
		} 

		provinceComboBox = ComponentFactory.createComboBox(Arrays.asList(new Province[0]), Province.class);
		provinceComboBox.setEnabled(false);
		GridBagConstraints gbc_provinceComboBox = new GridBagConstraints();
		gbc_provinceComboBox.gridwidth = 3;
		gbc_provinceComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_provinceComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_provinceComboBox.gridx = 1;
		gbc_provinceComboBox.gridy = 6;
		viewPanel.add(provinceComboBox, gbc_provinceComboBox);

		cityComboBox = ComponentFactory.createComboBox(Arrays.asList(new City[0]), City.class);
		cityComboBox.setEnabled(false);
		GridBagConstraints gbc_cityComboBox = new GridBagConstraints();
		gbc_cityComboBox.gridwidth = 3;
		gbc_cityComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_cityComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_cityComboBox.gridx = 1;
		gbc_cityComboBox.gridy = 5;
		viewPanel.add(cityComboBox, gbc_cityComboBox);

		countryComboBox.addItemListener(countryListener);
		provinceComboBox.addItemListener(provinceListener);
	}

	@Override
	public Address getDTOFromFields() {
		Address address = new Address();

		if (getCurrentEntity() != null) {
			address.setId(getCurrentEntity().getId());
			address.setCustomerId(getCurrentEntity().getCustomerId());
			address.setEmployeeId(getCurrentEntity().getEmployeeId());
		}

		address.setStreetName(streetNameTextField.getText());
		address.setStreetNumber(Short.valueOf(streetNumberFormattedTextField.getText()));
		address.setFloor(Short.valueOf(floorFormattedTextField.getText()));
		address.setDoor(doorTextField.getText());
		address.setZipCode(zipCodeTextField.getText());
		address.setCityId(((City) cityComboBox.getSelectedItem()).getId());
		if (ownerType == CUSTOMER) {
			address.setIsDefault(defaultCheckbox.isSelected());
			address.setIsBilling(billingCheckbox.isSelected());
		}
		return address;
	}

	@Override
	public void resetFields() {
		streetNameTextField.setText("");
		streetNumberFormattedTextField.setValue(null);
		floorFormattedTextField.setText("");
		doorTextField.setText("");
		zipCodeTextField.setText("");
		cityComboBox.setSelectedIndex(0);
		provinceComboBox.setSelectedIndex(0);
		countryComboBox.setSelectedIndex(0);
		if (ownerType == CUSTOMER) {
			defaultCheckbox.setSelected(false);
			billingCheckbox.setSelected(false);
		}
	}

	@Override
	protected void setFieldsEditable(boolean isEditable) {
		streetNameTextField.setEditable(isEditable);
		streetNumberFormattedTextField.setEditable(isEditable);
		floorFormattedTextField.setEditable(isEditable);
		doorTextField.setEditable(isEditable);
		zipCodeTextField.setEditable(isEditable);
		countryComboBox.setEnabled(isEditable);
		provinceComboBox.setEnabled(isEditable && countryComboBox.getSelectedIndex() > 0);
		cityComboBox.setEnabled(isEditable && provinceComboBox.getSelectedIndex() > 0);
		if (ownerType == CUSTOMER) {
			defaultCheckbox.setEnabled(isEditable);
			billingCheckbox.setEnabled(isEditable);
		}
	}

	@Override
	protected void loadItemData() {
		Address address = getCurrentEntity();
		
		streetNameTextField.setText(address.getStreetName());
		if (address.getStreetNumber() == null) {
			streetNumberFormattedTextField.setText(null);
		} else {
			streetNumberFormattedTextField.setText(address.getStreetNumber().toString());
		}
		
		if (address.getFloor() == null) {
			floorFormattedTextField.setText("");
		} else {
			floorFormattedTextField.setText(address.getFloor().toString());
		}
		
		doorTextField.setText(address.getDoor());
		zipCodeTextField.setText(address.getZipCode());
		selectItemById(countryComboBox, address.getCountryId());
		selectItemById(provinceComboBox, address.getProvinceId());
		selectItemById(cityComboBox, address.getCityId());
		
		if (ownerType == CUSTOMER) {
			defaultCheckbox.setSelected(address.isDefault());
			billingCheckbox.setSelected(address.isBilling());
		}
	}
	
	private static <PK extends Comparable<PK>> void selectItemById(JComboBox<? extends AbstractTerritory<PK>> comboBox, PK id) {
		
		if (id == null) {
			comboBox.setSelectedIndex(0);
			return;
		}
		
		for (int i = 0; i < comboBox.getItemCount(); i++) {
			AbstractTerritory<PK> territory = comboBox.getItemAt(i);
			if (id.equals(territory.getId())) {
				comboBox.setSelectedItem(territory);
				return;
			}
		}
	}

}
