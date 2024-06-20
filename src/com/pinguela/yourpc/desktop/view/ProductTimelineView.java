package com.pinguela.yourpc.desktop.view;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import com.pinguela.yourpc.desktop.actions.ShowProductTimelineAction;
import com.pinguela.yourpc.desktop.components.ProductSelector;
import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.model.ProductCriteria;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class ProductTimelineView
extends YPCView {

	private JPanel criteriaPanel;
	private JPanel chartPanel;

	private ProductSelector productSelector;
	private JDateChooser startDateChooser;
	private JDateChooser endDateChooser;
	private JButton searchButton;

	public ProductTimelineView() {
		initialize();
	}

	private void initialize() {
		criteriaPanel = new JPanel();
		add(criteriaPanel, BorderLayout.NORTH);
		GridBagLayout gbl_criteriaPanel = new GridBagLayout();
		gbl_criteriaPanel.columnWidths = new int[]{28, 100, 16, 96, 48, 41, 148, 97, 0};
		gbl_criteriaPanel.rowHeights = new int[]{33, 0, 24, 0};
		gbl_criteriaPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_criteriaPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		criteriaPanel.setLayout(gbl_criteriaPanel);

		JLabel dateFromLabel = new JLabel("From:");
		GridBagConstraints gbc_dateFromLabel = new GridBagConstraints();
		gbc_dateFromLabel.anchor = GridBagConstraints.WEST;
		gbc_dateFromLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dateFromLabel.gridx = 0;
		gbc_dateFromLabel.gridy = 0;
		criteriaPanel.add(dateFromLabel, gbc_dateFromLabel);
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -6);
		
		startDateChooser = ComponentFactory.getDateChooser(c);
		GridBagConstraints gbc_startDateChooser = new GridBagConstraints();
		gbc_startDateChooser.anchor = GridBagConstraints.WEST;
		gbc_startDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_startDateChooser.gridx = 1;
		gbc_startDateChooser.gridy = 0;
		criteriaPanel.add(startDateChooser, gbc_startDateChooser);

		JLabel dateToLabel = new JLabel("to:");
		GridBagConstraints gbc_dateToLabel = new GridBagConstraints();
		gbc_dateToLabel.anchor = GridBagConstraints.WEST;
		gbc_dateToLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dateToLabel.gridx = 2;
		gbc_dateToLabel.gridy = 0;
		criteriaPanel.add(dateToLabel, gbc_dateToLabel);

		endDateChooser = ComponentFactory.getDateChooser(Calendar.getInstance());
		GridBagConstraints gbc_endDateChooser = new GridBagConstraints();
		gbc_endDateChooser.anchor = GridBagConstraints.WEST;
		gbc_endDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_endDateChooser.gridx = 3;
		gbc_endDateChooser.gridy = 0;
		criteriaPanel.add(endDateChooser, gbc_endDateChooser);

		JLabel productIdLabel = new JLabel("Product:");
		GridBagConstraints gbc_productIdLabel = new GridBagConstraints();
		gbc_productIdLabel.anchor = GridBagConstraints.WEST;
		gbc_productIdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_productIdLabel.gridx = 5;
		gbc_productIdLabel.gridy = 0;
		criteriaPanel.add(productIdLabel, gbc_productIdLabel);

		productSelector = new ProductSelector();
		GridBagConstraints gbc_productSelector = new GridBagConstraints();
		gbc_productSelector.anchor = GridBagConstraints.NORTHWEST;
		gbc_productSelector.insets = new Insets(0, 0, 5, 5);
		gbc_productSelector.gridx = 6;
		gbc_productSelector.gridy = 0;
		criteriaPanel.add(productSelector, gbc_productSelector);

		searchButton = new JButton(new ShowProductTimelineAction(this));
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.insets = new Insets(0, 0, 5, 0);
		gbc_searchButton.anchor = GridBagConstraints.WEST;
		gbc_searchButton.gridx = 7;
		gbc_searchButton.gridy = 1;
		criteriaPanel.add(searchButton, gbc_searchButton);
	}

	// TODO : Create StatisticsCriteria object
	public ProductCriteria getCriteria() {
		ProductCriteria criteria = new ProductCriteria();

		criteria.setId(Long.valueOf(productSelector.getEntity().getId()));
		criteria.setLaunchDateMin(startDateChooser.getDate());
		criteria.setLaunchDateMax(endDateChooser.getDate());

		return criteria;

	}

	public void showChart(JFreeChart chart) {
		if (chartPanel != null) {
			remove(chartPanel);
		}
		chartPanel = new ChartPanel(chart);
		add(chartPanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}

}
