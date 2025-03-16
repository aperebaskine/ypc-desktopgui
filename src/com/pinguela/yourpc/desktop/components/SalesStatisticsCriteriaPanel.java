package com.pinguela.yourpc.desktop.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.model.ProductStatistics;
import com.pinguela.yourpc.model.ProductStatisticsCriteria;
import com.pinguela.yourpc.model.dto.CategoryDTO;
import com.pinguela.yourpc.util.CategoryUtils;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class SalesStatisticsCriteriaPanel
extends CriteriaPanel<ProductStatistics> {

	private JDateChooser startDateChooser;
	private JDateChooser endDateChooser;
	private JComboBox<String> comboBox;
	private JLabel categoryLabel;
	private JComboBox<CategoryDTO> categoryComboBox;

	public SalesStatisticsCriteriaPanel() {
		initialize();
	}

	private void initialize() {
		GridBagLayout gbl_criteriaPanel = (GridBagLayout) getLayout();
		gbl_criteriaPanel.columnWidths = new int[]{28, 100, 16, 96, 48, 0, 0, 48, 41, 148, 97, 0};
		gbl_criteriaPanel.rowHeights = new int[]{33, 0};
		gbl_criteriaPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_criteriaPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gbl_criteriaPanel);

		JLabel dateFromLabel = new JLabel("From:");
		GridBagConstraints gbc_dateFromLabel = new GridBagConstraints();
		gbc_dateFromLabel.anchor = GridBagConstraints.WEST;
		gbc_dateFromLabel.insets = new Insets(0, 0, 0, 5);
		gbc_dateFromLabel.gridx = 0;
		gbc_dateFromLabel.gridy = 0;
		add(dateFromLabel, gbc_dateFromLabel);

		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -6);

		startDateChooser = ComponentFactory.getDateChooser(c);
		GridBagConstraints gbc_startDateChooser = new GridBagConstraints();
		gbc_startDateChooser.anchor = GridBagConstraints.WEST;
		gbc_startDateChooser.insets = new Insets(0, 0, 0, 5);
		gbc_startDateChooser.gridx = 1;
		gbc_startDateChooser.gridy = 0;
		add(startDateChooser, gbc_startDateChooser);

		JLabel dateToLabel = new JLabel("to:");
		GridBagConstraints gbc_dateToLabel = new GridBagConstraints();
		gbc_dateToLabel.anchor = GridBagConstraints.WEST;
		gbc_dateToLabel.insets = new Insets(0, 0, 0, 5);
		gbc_dateToLabel.gridx = 2;
		gbc_dateToLabel.gridy = 0;
		add(dateToLabel, gbc_dateToLabel);

		endDateChooser = ComponentFactory.getDateChooser(Calendar.getInstance());
		GridBagConstraints gbc_endDateChooser = new GridBagConstraints();
		gbc_endDateChooser.anchor = GridBagConstraints.WEST;
		gbc_endDateChooser.insets = new Insets(0, 0, 0, 5);
		gbc_endDateChooser.gridx = 3;
		gbc_endDateChooser.gridy = 0;
		add(endDateChooser, gbc_endDateChooser);

		categoryLabel = new JLabel("Category:");
		GridBagConstraints gbc_categoryLabel = new GridBagConstraints();
		gbc_categoryLabel.anchor = GridBagConstraints.EAST;
		gbc_categoryLabel.insets = new Insets(0, 0, 0, 5);
		gbc_categoryLabel.gridx = 5;
		gbc_categoryLabel.gridy = 0;
		add(categoryLabel, gbc_categoryLabel);

		categoryComboBox = ComponentFactory.createComboBox(CategoryUtils.CATEGORIES.values(), CategoryDTO.class);
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 6;
		gbc_comboBox_1.gridy = 0;
		add(categoryComboBox, gbc_comboBox_1);

		JLabel findLabel = new JLabel("Find:");
		GridBagConstraints gbc_findLabel = new GridBagConstraints();
		gbc_findLabel.insets = new Insets(0, 0, 0, 5);
		gbc_findLabel.gridx = 8;
		gbc_findLabel.gridy = 0;
		add(findLabel, gbc_findLabel);

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Highest sales", "Highest return rate"}));

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 9;
		gbc_comboBox.gridy = 0;
		add(comboBox, gbc_comboBox);
	}

	public int getMode() {
		return comboBox.getSelectedIndex();
	}

	@Override
	public ProductStatisticsCriteria getCriteria() {
		ProductStatisticsCriteria criteria = new ProductStatisticsCriteria();
		criteria.setProductCategoryId(((CategoryDTO) categoryComboBox.getSelectedItem()).getId());
		criteria.setStartDate(startDateChooser.getDate());
		criteria.setEndDate(endDateChooser.getDate());

		// TODO: Remove switch statement
		switch (comboBox.getSelectedIndex()) {
		case 0:
			criteria.setOrderBy(null);
			break;
		case 1:
			criteria.setOrderBy(null);
			break;
		}

		return criteria;
	}

	@Override
	public void setFieldsEnabled(boolean isEnabled) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetFields(Object source) {
		// TODO Auto-generated method stub

	}

}
