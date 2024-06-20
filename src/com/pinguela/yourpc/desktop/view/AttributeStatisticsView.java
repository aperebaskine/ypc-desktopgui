package com.pinguela.yourpc.desktop.view;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.actions.ShowAttributeStatisticsAction;
import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.desktop.util.ReflectionUtils;
import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.model.Attribute;
import com.pinguela.yourpc.model.Category;
import com.pinguela.yourpc.model.ProductCriteria;
import com.pinguela.yourpc.service.AttributeService;
import com.pinguela.yourpc.service.impl.AttributeServiceImpl;
import com.pinguela.yourpc.util.CategoryUtils;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class AttributeStatisticsView
extends YPCView {

	private static Logger logger = LogManager.getLogger(AttributeStatisticsView.class);

	private JPanel criteriaPanel;
	private JPanel chartPanel;

	private JDateChooser startDateChooser;
	private JDateChooser endDateChooser;
	private JComboBox<Attribute<?>> attributeComboBox;
	private JLabel categoryLabel;
	private JComboBox<Category> categoryComboBox;

	private JButton searchButton;
	private JLabel attributeLabel;

	private AttributeService attributeService;

	private final ItemListener categoryListener = (evt) -> {
		if (categoryComboBox.getSelectedIndex() == 0) {
			attributeComboBox.setSelectedIndex(0);
			attributeComboBox.setEnabled(false);
		} else {
			Short categoryId = ((Category) categoryComboBox.getSelectedItem()).getId();
			try {
				attributeComboBox.setModel(ReflectionUtils.createComboBoxModel(
						attributeService.findByCategory(categoryId, AttributeService.NO_UNASSIGNED_VALUES).values(),
						Attribute.class));
			} catch (YPCException e) {
				logger.error(e.getMessage(), e);
				SwingUtils.showDatabaseAccessErrorDialog(this);
			} 
			attributeComboBox.setEnabled(true);
		}
	};

	public AttributeStatisticsView() {
		initialize();
	}

	private void initialize() {
		criteriaPanel = new JPanel();
		add(criteriaPanel, BorderLayout.NORTH);
		GridBagLayout gbl_criteriaPanel = new GridBagLayout();
		gbl_criteriaPanel.columnWidths = new int[]{28, 120, 48, 14, 120, 48, 49, 154, 0};
		gbl_criteriaPanel.rowHeights = new int[]{23, 0, 40, 0, 0};
		gbl_criteriaPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_criteriaPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		startDateChooser.setPreferredSize(new Dimension(120, 22));
		GridBagConstraints gbc_startDateChooser = new GridBagConstraints();
		gbc_startDateChooser.anchor = GridBagConstraints.NORTHWEST;
		gbc_startDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_startDateChooser.gridx = 1;
		gbc_startDateChooser.gridy = 0;
		criteriaPanel.add(startDateChooser, gbc_startDateChooser);

		JLabel dateToLabel = new JLabel("to:");
		GridBagConstraints gbc_dateToLabel = new GridBagConstraints();
		gbc_dateToLabel.anchor = GridBagConstraints.WEST;
		gbc_dateToLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dateToLabel.gridx = 3;
		gbc_dateToLabel.gridy = 0;
		criteriaPanel.add(dateToLabel, gbc_dateToLabel);

		endDateChooser = ComponentFactory.getDateChooser(Calendar.getInstance());
		endDateChooser.setPreferredSize(new Dimension(120, 22));
		GridBagConstraints gbc_endDateChooser = new GridBagConstraints();
		gbc_endDateChooser.anchor = GridBagConstraints.NORTHWEST;
		gbc_endDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_endDateChooser.gridx = 4;
		gbc_endDateChooser.gridy = 0;
		criteriaPanel.add(endDateChooser, gbc_endDateChooser);

		categoryLabel = new JLabel("Category:");
		GridBagConstraints gbc_categoryLabel = new GridBagConstraints();
		gbc_categoryLabel.anchor = GridBagConstraints.WEST;
		gbc_categoryLabel.insets = new Insets(0, 0, 5, 5);
		gbc_categoryLabel.gridx = 6;
		gbc_categoryLabel.gridy = 0;
		criteriaPanel.add(categoryLabel, gbc_categoryLabel);

		categoryComboBox = ComponentFactory.createComboBox(CategoryUtils.CATEGORIES.values(), Category.class);
		categoryComboBox.setPreferredSize(new Dimension(240, 22));
		categoryComboBox.addItemListener(categoryListener);
		GridBagConstraints gbc_categoryComboBox = new GridBagConstraints();
		gbc_categoryComboBox.anchor = GridBagConstraints.NORTHWEST;
		gbc_categoryComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_categoryComboBox.gridx = 7;
		gbc_categoryComboBox.gridy = 0;
		criteriaPanel.add(categoryComboBox, gbc_categoryComboBox);

		attributeLabel = new JLabel("Attribute:");
		GridBagConstraints gbc_attributeLabel = new GridBagConstraints();
		gbc_attributeLabel.anchor = GridBagConstraints.WEST;
		gbc_attributeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_attributeLabel.gridx = 6;
		gbc_attributeLabel.gridy = 1;
		criteriaPanel.add(attributeLabel, gbc_attributeLabel);

		attributeComboBox = ComponentFactory.createComboBox(Arrays.asList(new Attribute[0]), Attribute.class);
		attributeComboBox.setEnabled(false);
		attributeComboBox.setPreferredSize(new Dimension(240, 22));
		GridBagConstraints gbc_attributeComboBox = new GridBagConstraints();
		gbc_attributeComboBox.anchor = GridBagConstraints.NORTHWEST;
		gbc_attributeComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_attributeComboBox.gridx = 7;
		gbc_attributeComboBox.gridy = 1;
		criteriaPanel.add(attributeComboBox, gbc_attributeComboBox);

		searchButton = new JButton(new ShowAttributeStatisticsAction(this));
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.insets = new Insets(0, 0, 5, 0);
		gbc_searchButton.anchor = GridBagConstraints.EAST;
		gbc_searchButton.gridx = 7;
		gbc_searchButton.gridy = 2;
		criteriaPanel.add(searchButton, gbc_searchButton);

		attributeService = new AttributeServiceImpl();
	}

	// TODO : Create StatisticsCriteria object
	public ProductCriteria getCriteria() {
		ProductCriteria criteria = new ProductCriteria();

		criteria.setCategoryId(((Category) categoryComboBox.getSelectedItem()).getId());
		criteria.setLaunchDateMin(startDateChooser.getDate());
		criteria.setLaunchDateMax(endDateChooser.getDate());

		Attribute<?> attribute = (Attribute<?>) attributeComboBox.getSelectedItem();
		criteria.getAttributes().put(attribute.getName(), attribute);

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
