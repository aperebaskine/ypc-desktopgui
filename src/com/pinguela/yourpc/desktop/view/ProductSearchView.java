package com.pinguela.yourpc.desktop.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.pinguela.yourpc.desktop.actions.AddAttributeCriteriaAction;
import com.pinguela.yourpc.desktop.actions.DeleteAttributeAction;
import com.pinguela.yourpc.desktop.actions.EditAttributeAction;
import com.pinguela.yourpc.desktop.actions.ProductSearchAction;
import com.pinguela.yourpc.desktop.actions.ResetCriteriaAction;
import com.pinguela.yourpc.desktop.actions.SearchAction;
import com.pinguela.yourpc.desktop.actions.SearchActionBuilder;
import com.pinguela.yourpc.desktop.actions.SetProductRangesAction;
import com.pinguela.yourpc.desktop.actions.YPCAction;
import com.pinguela.yourpc.desktop.components.ExtendedDateChooser;
import com.pinguela.yourpc.desktop.constants.AttributeTableConstants;
import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.desktop.model.ActionPaneMapTableModel;
import com.pinguela.yourpc.desktop.renderer.AttributeTableCellRenderer;
import com.pinguela.yourpc.desktop.renderer.ProductTableCellRenderer;
import com.pinguela.yourpc.desktop.util.TableUtils;
import com.pinguela.yourpc.model.Attribute;
import com.pinguela.yourpc.model.Category;
import com.pinguela.yourpc.model.Product;
import com.pinguela.yourpc.model.ProductCriteria;
import com.pinguela.yourpc.model.ProductRanges;
import com.pinguela.yourpc.service.AttributeService;
import com.pinguela.yourpc.util.CategoryUtils;

import slider.RangeSlider;

@SuppressWarnings("serial")
public class ProductSearchView 
extends AbstractPaginatedSearchView<Product> {

	private JFormattedTextField productIdField;
	private JTextField productNameField;
	private JComboBox<Category> categoryComboBox;
	private ExtendedDateChooser minLaunchDateChooser;
	private ExtendedDateChooser maxLaunchDateChooser;
	private RangeSlider priceRangeSlider;
	private JFormattedTextField stockMinField;
	private JFormattedTextField stockMaxField;
	private JLabel minPriceLabel;
	private JLabel maxPriceLabel;
	private JTable attributeTable;
	private JScrollPane attributePane;
	private JButton addAttributeButton;

	public ProductSearchView() {
		this(new SearchActionBuilder<>(ProductSearchAction.class));
	}

	private ProductSearchView(SearchActionBuilder<Product, ? extends SearchAction<Product>> builder) {
		super(builder);
		initialize();
		postInitialize((ProductSearchAction) builder.getAfterBuild());
	}

	private void initialize() {
		
		setPreferredSize(new Dimension(960, 720));

		GridBagLayout gbl_searchCriteriaPanel = new GridBagLayout();
		gbl_searchCriteriaPanel.columnWidths = new int[]{0, 0, 100, 0, 100, 40, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0};
		gbl_searchCriteriaPanel.rowHeights = new int[]{0, 0, 0, 26, 0, 0};
		gbl_searchCriteriaPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_searchCriteriaPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		getCriteriaPanel().setLayout(gbl_searchCriteriaPanel);

		JLabel productIdLabel = new JLabel("Product ID:");
		GridBagConstraints gbc_productIdLabel = new GridBagConstraints();
		gbc_productIdLabel.anchor = GridBagConstraints.EAST;
		gbc_productIdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_productIdLabel.gridx = 0;
		gbc_productIdLabel.gridy = 0;
		getCriteriaPanel().add(productIdLabel, gbc_productIdLabel);

		JLabel attributeLabel = new JLabel("Attributes:");
		GridBagConstraints gbc_attributeLabel = new GridBagConstraints();
		gbc_attributeLabel.anchor = GridBagConstraints.EAST;
		gbc_attributeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_attributeLabel.gridx = 6;
		gbc_attributeLabel.gridy = 0;
		getCriteriaPanel().add(attributeLabel, gbc_attributeLabel);

		JLabel productNameLabel = new JLabel("Product name:");
		GridBagConstraints gbc_productNameLabel = new GridBagConstraints();
		gbc_productNameLabel.anchor = GridBagConstraints.EAST;
		gbc_productNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_productNameLabel.gridx = 0;
		gbc_productNameLabel.gridy = 1;
		getCriteriaPanel().add(productNameLabel, gbc_productNameLabel);

		productNameField = new JTextField();
		productNameLabel.setLabelFor(productNameField);
		GridBagConstraints gbc_productNameField = new GridBagConstraints();
		gbc_productNameField.gridwidth = 4;
		gbc_productNameField.insets = new Insets(0, 0, 5, 5);
		gbc_productNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_productNameField.gridx = 1;
		gbc_productNameField.gridy = 1;
		getCriteriaPanel().add(productNameField, gbc_productNameField);
		productNameField.setColumns(10);
		
		addAttributeButton = new JButton();
		GridBagConstraints gbc_addAttributeButton = new GridBagConstraints();
		gbc_addAttributeButton.anchor = GridBagConstraints.EAST;
		gbc_addAttributeButton.insets = new Insets(0, 0, 5, 5);
		gbc_addAttributeButton.gridx = 6;
		gbc_addAttributeButton.gridy = 1;
		getCriteriaPanel().add(addAttributeButton, gbc_addAttributeButton);

		JLabel categoryLabel = new JLabel("Category:");
		GridBagConstraints gbc_categoryLabel = new GridBagConstraints();
		gbc_categoryLabel.anchor = GridBagConstraints.EAST;
		gbc_categoryLabel.insets = new Insets(0, 0, 5, 5);
		gbc_categoryLabel.gridx = 0;
		gbc_categoryLabel.gridy = 2;
		getCriteriaPanel().add(categoryLabel, gbc_categoryLabel);

		JLabel launchDateLabel = new JLabel("Launch date:");
		GridBagConstraints gbc_launchDateLabel = new GridBagConstraints();
		gbc_launchDateLabel.anchor = GridBagConstraints.EAST;
		gbc_launchDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_launchDateLabel.gridx = 0;
		gbc_launchDateLabel.gridy = 3;
		getCriteriaPanel().add(launchDateLabel, gbc_launchDateLabel);

		JLabel launchDateFromLabel = new JLabel("from");
		GridBagConstraints gbc_launchDateFromLabel = new GridBagConstraints();
		gbc_launchDateFromLabel.anchor = GridBagConstraints.EAST;
		gbc_launchDateFromLabel.insets = new Insets(0, 0, 5, 5);
		gbc_launchDateFromLabel.gridx = 1;
		gbc_launchDateFromLabel.gridy = 3;
		getCriteriaPanel().add(launchDateFromLabel, gbc_launchDateFromLabel);

		JPanel launchDateFromPanel = new JPanel();
		GridBagConstraints gbc_launchDateFromPanel = new GridBagConstraints();
		gbc_launchDateFromPanel.insets = new Insets(0, 0, 5, 5);
		gbc_launchDateFromPanel.fill = GridBagConstraints.BOTH;
		gbc_launchDateFromPanel.gridx = 2;
		gbc_launchDateFromPanel.gridy = 3;
		getCriteriaPanel().add(launchDateFromPanel, gbc_launchDateFromPanel);
		launchDateFromPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		minLaunchDateChooser = ComponentFactory.getDateChooser();
		launchDateFromPanel.add(minLaunchDateChooser);

		JLabel launchDateToLabel = new JLabel("to");
		GridBagConstraints gbc_launchDateToLabel = new GridBagConstraints();
		gbc_launchDateToLabel.anchor = GridBagConstraints.EAST;
		gbc_launchDateToLabel.insets = new Insets(0, 0, 5, 5);
		gbc_launchDateToLabel.gridx = 3;
		gbc_launchDateToLabel.gridy = 3;
		getCriteriaPanel().add(launchDateToLabel, gbc_launchDateToLabel);

		JPanel launchDateToPanel = new JPanel();
		GridBagConstraints gbc_launchDateToPanel = new GridBagConstraints();
		gbc_launchDateToPanel.insets = new Insets(0, 0, 5, 5);
		gbc_launchDateToPanel.fill = GridBagConstraints.BOTH;
		gbc_launchDateToPanel.gridx = 4;
		gbc_launchDateToPanel.gridy = 3;
		getCriteriaPanel().add(launchDateToPanel, gbc_launchDateToPanel);
		launchDateToPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		maxLaunchDateChooser = ComponentFactory.getDateChooser();
		launchDateToPanel.add(maxLaunchDateChooser);

		JLabel priceLabel = new JLabel("Price:");
		GridBagConstraints gbc_priceLabel = new GridBagConstraints();
		gbc_priceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_priceLabel.anchor = GridBagConstraints.EAST;
		gbc_priceLabel.gridx = 0;
		gbc_priceLabel.gridy = 4;
		getCriteriaPanel().add(priceLabel, gbc_priceLabel);

		JPanel priceRangeSliderPanel = new JPanel();
		priceRangeSliderPanel.setBorder(null);
		GridBagConstraints gbc_priceRangeSliderPanel = new GridBagConstraints();
		gbc_priceRangeSliderPanel.gridwidth = 4;
		gbc_priceRangeSliderPanel.insets = new Insets(0, 0, 5, 5);
		gbc_priceRangeSliderPanel.fill = GridBagConstraints.BOTH;
		gbc_priceRangeSliderPanel.gridx = 1;
		gbc_priceRangeSliderPanel.gridy = 4;
		getCriteriaPanel().add(priceRangeSliderPanel, gbc_priceRangeSliderPanel);

		minPriceLabel = new JLabel("0");
		minPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		minPriceLabel.setPreferredSize(new Dimension(28, 14));
		priceRangeSliderPanel.add(minPriceLabel);

		priceRangeSlider = new RangeSlider(0, Integer.MAX_VALUE);
		priceRangeSlider.setValue(0);
		priceRangeSlider.setUpperValue(Integer.MAX_VALUE);
		priceRangeSlider.setPreferredSize(new Dimension(160, 15));
		priceRangeSliderPanel.add(priceRangeSlider);

		maxPriceLabel = new JLabel("100");
		maxPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		maxPriceLabel.setPreferredSize(new Dimension(28, 14));
		priceRangeSliderPanel.add(maxPriceLabel);

		JLabel stockLabel = new JLabel("Stock:");
		GridBagConstraints gbc_stockLabel = new GridBagConstraints();
		gbc_stockLabel.anchor = GridBagConstraints.EAST;
		gbc_stockLabel.insets = new Insets(0, 0, 0, 5);
		gbc_stockLabel.gridx = 0;
		gbc_stockLabel.gridy = 5;
		getCriteriaPanel().add(stockLabel, gbc_stockLabel);

		JLabel stockFromLabel = new JLabel("from");
		GridBagConstraints gbc_stockFromLabel = new GridBagConstraints();
		gbc_stockFromLabel.anchor = GridBagConstraints.EAST;
		gbc_stockFromLabel.insets = new Insets(0, 0, 0, 5);
		gbc_stockFromLabel.gridx = 1;
		gbc_stockFromLabel.gridy = 5;
		getCriteriaPanel().add(stockFromLabel, gbc_stockFromLabel);

		stockMinField = new JFormattedTextField();
		stockFromLabel.setLabelFor(stockMinField);
		GridBagConstraints gbc_stockFromField = new GridBagConstraints();
		gbc_stockFromField.insets = new Insets(0, 0, 0, 5);
		gbc_stockFromField.fill = GridBagConstraints.HORIZONTAL;
		gbc_stockFromField.gridx = 2;
		gbc_stockFromField.gridy = 5;
		getCriteriaPanel().add(stockMinField, gbc_stockFromField);

		JLabel stockToLabel = new JLabel("to");
		GridBagConstraints gbc_stockToLabel = new GridBagConstraints();
		gbc_stockToLabel.anchor = GridBagConstraints.EAST;
		gbc_stockToLabel.insets = new Insets(0, 0, 0, 5);
		gbc_stockToLabel.gridx = 3;
		gbc_stockToLabel.gridy = 5;
		getCriteriaPanel().add(stockToLabel, gbc_stockToLabel);

		attributePane = new JScrollPane();
		attributePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		attributePane.setPreferredSize(new Dimension(240, 148));
		attributePane.setSize(new Dimension(200, 100));
		GridBagConstraints gbc_attributePane = new GridBagConstraints();
		gbc_attributePane.gridheight = 6;
		gbc_attributePane.gridwidth = 9;
		gbc_attributePane.fill = GridBagConstraints.BOTH;
		gbc_attributePane.gridx = 7;
		gbc_attributePane.gridy = 0;
		getCriteriaPanel().add(attributePane, gbc_attributePane);

		stockMaxField = new JFormattedTextField();
		stockToLabel.setLabelFor(stockMaxField);
		GridBagConstraints gbc_stockToField = new GridBagConstraints();
		gbc_stockToField.insets = new Insets(0, 0, 0, 5);
		gbc_stockToField.fill = GridBagConstraints.HORIZONTAL;
		gbc_stockToField.gridx = 4;
		gbc_stockToField.gridy = 5;
		getCriteriaPanel().add(stockMaxField, gbc_stockToField);

		attributeTable = new JTable();
		attributePane.setViewportView(attributeTable);
	}

	private void postInitialize(ProductSearchAction action) {

		productIdField = ComponentFactory.createNullableNumberTextField(Long.class);
		GridBagConstraints gbc_productIdField = new GridBagConstraints();
		gbc_productIdField.gridwidth = 4;
		gbc_productIdField.insets = new Insets(0, 0, 5, 5);
		gbc_productIdField.fill = GridBagConstraints.HORIZONTAL;
		gbc_productIdField.gridx = 1;
		gbc_productIdField.gridy = 0;
		getCriteriaPanel().add(productIdField, gbc_productIdField);
		productIdField.setColumns(10);

		attributeTable.setModel(new ActionPaneMapTableModel<String, Attribute<?>>(AttributeTableConstants.COLUMN_NAMES));
		TableUtils.initializeActionPanes(attributeTable, new DeleteAttributeAction(attributeTable),
				new EditAttributeAction(attributeTable, AttributeService.NO_UNASSIGNED_VALUES));
		attributeTable.setDefaultRenderer(Object.class, new AttributeTableCellRenderer());
		attributeTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		categoryComboBox = ComponentFactory.createComboBox(CategoryUtils.CATEGORIES.values(), Category.class);
		GridBagConstraints gbc_categoryComboBox = new GridBagConstraints();
		gbc_categoryComboBox.gridwidth = 4;
		gbc_categoryComboBox.anchor = GridBagConstraints.SOUTH;
		gbc_categoryComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_categoryComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_categoryComboBox.gridx = 1;
		gbc_categoryComboBox.gridy = 2;
		getCriteriaPanel().add(categoryComboBox, gbc_categoryComboBox);

		priceRangeSlider.addChangeListener((e) -> {
			minPriceLabel.setText(Integer.valueOf(priceRangeSlider.getValue()).toString());
			maxPriceLabel.setText(Integer.valueOf(priceRangeSlider.getUpperValue()).toString());
		});
		getTable().setDefaultRenderer(Object.class, new ProductTableCellRenderer());

		// TODO: Add listener for ID field

		productNameField.getDocument().addDocumentListener(action);
		categoryComboBox.addItemListener(action);
		minLaunchDateChooser.getDateEditor().addPropertyChangeListener("date", action);
		minLaunchDateChooser.getDateEditor().addPropertyChangeListener("date", action);
		priceRangeSlider.addChangeListener(action);
		stockMinField.addPropertyChangeListener("value", action);
		stockMaxField.addPropertyChangeListener("value", action);
		attributeTable.getModel().addTableModelListener(action);

		categoryComboBox.addActionListener(new SetProductRangesAction(this));
		categoryComboBox.addActionListener(new ResetCriteriaAction(this));
		
		YPCAction addAttributeCriteriaAction = new AddAttributeCriteriaAction(this);
		addAttributeButton.setAction(addAttributeCriteriaAction);
		categoryComboBox.addItemListener(addAttributeCriteriaAction);
	}

	@SuppressWarnings("unchecked")
	public ProductCriteria getCriteria() {
		ProductCriteria criteria = new ProductCriteria();

		if (productIdField.getText().isEmpty()) {
			criteria.setId(null);
		} else {
			criteria.setId((Long) productIdField.getValue());
		}

		if (productNameField.getText().isEmpty()) {
			criteria.setName(null);
		} else {
			criteria.setName(productNameField.getText());
		}

		Category c = (Category) categoryComboBox.getSelectedItem();
		if (c == null) {
			criteria.setCategoryId(null);
		} else {
			criteria.setCategoryId(c.getId());
		}

		if (minLaunchDateChooser.getDate() == null) {
			criteria.setLaunchDateMin(null);
		} else {
			criteria.setLaunchDateMin(minLaunchDateChooser.getDate());
		}

		if (maxLaunchDateChooser.getDate() == null) {
			criteria.setLaunchDateMax(null);
		} else {
			criteria.setLaunchDateMax(maxLaunchDateChooser.getDate());
		}

		criteria.setPriceMin(Double.valueOf(priceRangeSlider.getValue()));
		criteria.setPriceMax(Double.valueOf(priceRangeSlider.getUpperValue()));

		criteria.setStockMin((Integer) stockMinField.getValue());
		criteria.setStockMax((Integer) stockMaxField.getValue());
		
		criteria.setAttributes(((ActionPaneMapTableModel<String, Attribute<?>>) attributeTable.getModel()).getData());

		return criteria;
	}

	public void setRanges(ProductRanges ranges) {
		priceRangeSlider.setMinimum(Integer.valueOf(ranges.getPriceMin().intValue()) -1);
		priceRangeSlider.setValue(priceRangeSlider.getMinimum());
		priceRangeSlider.setMaximum(Integer.valueOf(ranges.getPriceMax().intValue()) +1);
		priceRangeSlider.setUpperValue(priceRangeSlider.getMaximum());
		stockMinField.setValue(ranges.getStockMin());
		stockMaxField.setValue(ranges.getStockMax());
		minLaunchDateChooser.setDate(ranges.getLaunchDateMin());
		maxLaunchDateChooser.setDate(ranges.getLaunchDateMax());
	}

	@Override
	protected void doResetCriteriaFields(Object source) {

		if (!productNameField.equals(source)) {
			productNameField.setText(null);
		}

		if (!minLaunchDateChooser.equals(source)) {
			minLaunchDateChooser.setDate(null);
		}

		if (!maxLaunchDateChooser.equals(source)) {
			maxLaunchDateChooser.setDate(null);
		}

		if (!stockMinField.equals(source)) {
			stockMinField.setValue(null);
		}

		if (!stockMaxField.equals(source)) {
			stockMaxField.setValue(null);
		}

		if (!priceRangeSlider.equals(source)) {
			priceRangeSlider.setMinimum(0);
			priceRangeSlider.setValue(priceRangeSlider.getMinimum());
			priceRangeSlider.setMaximum(Integer.MAX_VALUE);
			priceRangeSlider.setUpperValue(priceRangeSlider.getMaximum());
		}

		if (!categoryComboBox.equals(source)) {
			categoryComboBox.setSelectedItem(categoryComboBox.getItemAt(0));
		}

		((ActionPaneMapTableModel<?, ?>) attributeTable.getModel()).clear();
	}

	@Override
	public void setCriteriaFieldsEnabled(boolean isEnabled) {
		productNameField.setEnabled(isEnabled);
		categoryComboBox.setEnabled(isEnabled);
		minLaunchDateChooser.setEnabled(isEnabled);
		maxLaunchDateChooser.setEnabled(isEnabled);
		stockMinField.setEnabled(isEnabled);
		stockMaxField.setEnabled(isEnabled);
		priceRangeSlider.setEnabled(isEnabled);
	}

	@SuppressWarnings("unchecked")
	public void addAttribute(Attribute<?> attribute) {
		((ActionPaneMapTableModel<String, Attribute<?>>) attributeTable.getModel()).addRow(attribute.getName(), attribute);
	}

}
