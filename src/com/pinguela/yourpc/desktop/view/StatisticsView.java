package com.pinguela.yourpc.desktop.view;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import com.pinguela.yourpc.desktop.actions.RetrieveStatisticsAction;
import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.model.ProductCriteria;
import com.toedter.calendar.JDateChooser;

public class StatisticsView extends YPCView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 235760785135773930L;
	
	private JPanel criteriaPanel;
	private JPanel chartPanel;
	
	private JFormattedTextField productIdField;
	private JDateChooser startDateChooser;
	private JDateChooser endDateChooser;
	private JButton searchButton;
	
	public StatisticsView() {
		initialize();
		postInitialize();
	}
	
	private void initialize() {
		criteriaPanel = new JPanel();
		add(criteriaPanel, BorderLayout.NORTH);
		GridBagLayout gbl_criteriaPanel = new GridBagLayout();
		gbl_criteriaPanel.columnWidths = new int[]{0, 100, 48, 0, 0, 0};
		gbl_criteriaPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_criteriaPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_criteriaPanel.rowWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		criteriaPanel.setLayout(gbl_criteriaPanel);
		
		JLabel dateFromLabel = new JLabel("From:");
		GridBagConstraints gbc_dateFromLabel = new GridBagConstraints();
		gbc_dateFromLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dateFromLabel.gridx = 0;
		gbc_dateFromLabel.gridy = 0;
		criteriaPanel.add(dateFromLabel, gbc_dateFromLabel);
		
		JLabel productIdLabel = new JLabel("Product ID:");
		GridBagConstraints gbc_productIdLabel = new GridBagConstraints();
		gbc_productIdLabel.anchor = GridBagConstraints.EAST;
		gbc_productIdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_productIdLabel.gridx = 3;
		gbc_productIdLabel.gridy = 0;
		criteriaPanel.add(productIdLabel, gbc_productIdLabel);
		
		productIdField = new JFormattedTextField();
		GridBagConstraints gbc_formattedTextField = new GridBagConstraints();
		gbc_formattedTextField.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField.gridx = 4;
		gbc_formattedTextField.gridy = 0;
		criteriaPanel.add(productIdField, gbc_formattedTextField);
		
		JLabel dateToLabel = new JLabel("To:");
		GridBagConstraints gbc_dateToLabel = new GridBagConstraints();
		gbc_dateToLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dateToLabel.gridx = 0;
		gbc_dateToLabel.gridy = 1;
		criteriaPanel.add(dateToLabel, gbc_dateToLabel);
	}
	
	private void postInitialize() {
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		startDateChooser = ComponentFactory.getDateChooser(c);
		GridBagConstraints gbc_startDateChooser = new GridBagConstraints();
		gbc_startDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_startDateChooser.fill = GridBagConstraints.BOTH;
		gbc_startDateChooser.gridx = 1;
		gbc_startDateChooser.gridy = 0;
		criteriaPanel.add(startDateChooser, gbc_startDateChooser);
		
		
		endDateChooser = ComponentFactory.getDateChooser(Calendar.getInstance());
		GridBagConstraints gbc_endDateChooser = new GridBagConstraints();
		gbc_endDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_endDateChooser.fill = GridBagConstraints.BOTH;
		gbc_endDateChooser.gridx = 1;
		gbc_endDateChooser.gridy = 1;
		criteriaPanel.add(endDateChooser, gbc_endDateChooser);
		
		searchButton = new JButton(new RetrieveStatisticsAction(this));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 2;
		criteriaPanel.add(searchButton, gbc_btnNewButton);
	}
	
	// TODO : Create StatisticsCriteria object
	public ProductCriteria getCriteria() {
		ProductCriteria criteria = new ProductCriteria();
		
		criteria.setId(Long.valueOf(productIdField.getText()));
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
