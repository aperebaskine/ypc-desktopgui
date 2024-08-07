package com.pinguela.yourpc.desktop.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.ServiceException;
import com.pinguela.yourpc.desktop.actions.AddAttributeAction;
import com.pinguela.yourpc.desktop.actions.DeleteAttributeAction;
import com.pinguela.yourpc.desktop.actions.EditAttributeAction;
import com.pinguela.yourpc.desktop.components.ExtendedDateChooser;
import com.pinguela.yourpc.desktop.components.ImageGalleryPanel;
import com.pinguela.yourpc.desktop.constants.AttributeTableConstants;
import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.desktop.model.ActionPaneMapTableModel;
import com.pinguela.yourpc.desktop.renderer.AttributeTableCellRenderer;
import com.pinguela.yourpc.desktop.util.DialogUtils;
import com.pinguela.yourpc.desktop.util.TableUtils;
import com.pinguela.yourpc.model.Attribute;
import com.pinguela.yourpc.model.AttributeValueHandlingModes;
import com.pinguela.yourpc.model.Category;
import com.pinguela.yourpc.model.ImageEntry;
import com.pinguela.yourpc.model.Product;
import com.pinguela.yourpc.service.AttributeService;
import com.pinguela.yourpc.service.ImageFileService;
import com.pinguela.yourpc.service.impl.ImageFileServiceImpl;
import com.pinguela.yourpc.util.CategoryUtils;

@SuppressWarnings("serial")
public class ProductView 
extends AbstractEntityView<Product> {
	
	private static Logger logger = LogManager.getLogger(ProductView.class);
	
	private ImageFileService imageFileService;

	private JLabel idValueLabel;
	private JTextField nameTextField;
	private JComboBox<Category> categoryComboBox;
	private ExtendedDateChooser launchDateChooser;
	private JFormattedTextField stockTextField;
	private JFormattedTextField purchasePriceField;
	private JFormattedTextField salePriceField;
	private JTextArea descriptionTextArea;
	private JTable attributeTable;
	private ImageGalleryPanel imageGalleryPanel;
	
	private JButton addAttributeButton;
	private TableColumn actionColumn;
	
	private TableColumnModelListener columnListener = new TableColumnModelListener() {
		
		@Override
		public void columnSelectionChanged(ListSelectionEvent e) {}
		
		@Override
		public void columnRemoved(TableColumnModelEvent e) {}
		
		@Override
		public void columnMoved(TableColumnModelEvent e) {}
		
		@Override
		public void columnMarginChanged(ChangeEvent e) {}
		
		@Override
		public void columnAdded(TableColumnModelEvent e) {
			if (attributeTable.getColumnCount() > AttributeTableConstants.ACTION_PANE_COLUMN_INDEX) {
				actionColumn = attributeTable.getColumnModel().getColumn(AttributeTableConstants.ACTION_PANE_COLUMN_INDEX);
				if (!isEditable()) {
					attributeTable.removeColumn(actionColumn);
				}
			}
		}
	};
	private PropertyChangeListener editableListener = (evt) -> {
		imageGalleryPanel.setEditable(isEditable());

		if (isEditable() && attributeTable.getColumnCount() < 3) {
			attributeTable.getColumnModel().addColumn(actionColumn);
		} else if (attributeTable.getColumnCount() > 2) {
			attributeTable.getColumnModel().removeColumn(actionColumn);
		}
	};

	public ProductView() {
		initialize();
		postInitialize();
	}

	private void initialize() {
		
		JPanel viewPanel = getViewPanel();

		viewPanel.setPreferredSize(new Dimension(540, 0));

		GridBagLayout gridBagLayout = (GridBagLayout) viewPanel.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0};
		gridBagLayout.columnWidths = new int[]{80, 160, 20, 80, 160};
		gridBagLayout.rowHeights = new int[]{30, 30, 30, 30, 30, 30, 30, 0, 0, 30, 30, 0, 0};

		JLabel idLabel = new JLabel("ID:");
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.anchor = GridBagConstraints.EAST;
		gbc_idLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idLabel.gridx = 0;
		gbc_idLabel.gridy = 0;
		viewPanel.add(idLabel, gbc_idLabel);

		idValueLabel = new JLabel("");
		GridBagConstraints gbc_idValueLabel = new GridBagConstraints();
		gbc_idValueLabel.anchor = GridBagConstraints.WEST;
		gbc_idValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idValueLabel.gridx = 1;
		gbc_idValueLabel.gridy = 0;
		viewPanel.add(idValueLabel, gbc_idValueLabel);

		JLabel nameLabel = new JLabel("Name:");
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 1;
		viewPanel.add(nameLabel, gbc_nameLabel);

		nameTextField = new JTextField();
		nameTextField.setEditable(false);
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.gridwidth = 4;
		gbc_nameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTextField.gridx = 1;
		gbc_nameTextField.gridy = 1;
		viewPanel.add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);

		JLabel categoryLabel = new JLabel("Category:");
		GridBagConstraints gbc_categoryLabel = new GridBagConstraints();
		gbc_categoryLabel.anchor = GridBagConstraints.EAST;
		gbc_categoryLabel.insets = new Insets(0, 0, 5, 5);
		gbc_categoryLabel.gridx = 0;
		gbc_categoryLabel.gridy = 2;
		viewPanel.add(categoryLabel, gbc_categoryLabel);

		JLabel purchasePriceLabel = new JLabel("Purchase price:");
		GridBagConstraints gbc_purchasePriceLabel = new GridBagConstraints();
		gbc_purchasePriceLabel.anchor = GridBagConstraints.EAST;
		gbc_purchasePriceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_purchasePriceLabel.gridx = 3;
		gbc_purchasePriceLabel.gridy = 2;
		viewPanel.add(purchasePriceLabel, gbc_purchasePriceLabel);

		purchasePriceField = new JFormattedTextField();
		purchasePriceField.setEditable(false);
		GridBagConstraints gbc_purchasePriceField = new GridBagConstraints();
		gbc_purchasePriceField.insets = new Insets(0, 0, 5, 0);
		gbc_purchasePriceField.fill = GridBagConstraints.HORIZONTAL;
		gbc_purchasePriceField.gridx = 4;
		gbc_purchasePriceField.gridy = 2;
		viewPanel.add(purchasePriceField, gbc_purchasePriceField);
		purchasePriceField.setColumns(10);

		JLabel launchDateLabel = new JLabel("Launch date:");
		GridBagConstraints gbc_launchDateLabel = new GridBagConstraints();
		gbc_launchDateLabel.anchor = GridBagConstraints.EAST;
		gbc_launchDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_launchDateLabel.gridx = 0;
		gbc_launchDateLabel.gridy = 3;
		viewPanel.add(launchDateLabel, gbc_launchDateLabel);

		JLabel salePriceLabel = new JLabel("Sale price:");
		GridBagConstraints gbc_salePriceLabel = new GridBagConstraints();
		gbc_salePriceLabel.anchor = GridBagConstraints.EAST;
		gbc_salePriceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_salePriceLabel.gridx = 3;
		gbc_salePriceLabel.gridy = 3;
		viewPanel.add(salePriceLabel, gbc_salePriceLabel);

		salePriceField = new JFormattedTextField();
		salePriceField.setEditable(false);
		GridBagConstraints gbc_salePriceField = new GridBagConstraints();
		gbc_salePriceField.insets = new Insets(0, 0, 5, 0);
		gbc_salePriceField.fill = GridBagConstraints.HORIZONTAL;
		gbc_salePriceField.gridx = 4;
		gbc_salePriceField.gridy = 3;
		viewPanel.add(salePriceField, gbc_salePriceField);
		salePriceField.setColumns(10);

		JLabel stockLabel = new JLabel("Stock:");
		GridBagConstraints gbc_stockLabel = new GridBagConstraints();
		gbc_stockLabel.anchor = GridBagConstraints.EAST;
		gbc_stockLabel.insets = new Insets(0, 0, 5, 5);
		gbc_stockLabel.gridx = 0;
		gbc_stockLabel.gridy = 4;
		viewPanel.add(stockLabel, gbc_stockLabel);

		stockTextField = new JFormattedTextField();
		stockTextField.setEditable(false);
		GridBagConstraints gbc_stockTextField = new GridBagConstraints();
		gbc_stockTextField.insets = new Insets(0, 0, 5, 5);
		gbc_stockTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_stockTextField.gridx = 1;
		gbc_stockTextField.gridy = 4;
		viewPanel.add(stockTextField, gbc_stockTextField);
		stockTextField.setColumns(10);

		JLabel descriptionLabel = new JLabel("Description:");
		GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
		gbc_descriptionLabel.anchor = GridBagConstraints.EAST;
		gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionLabel.gridx = 0;
		gbc_descriptionLabel.gridy = 6;
		viewPanel.add(descriptionLabel, gbc_descriptionLabel);

		JScrollPane descriptionScrollPane = new JScrollPane();
		GridBagConstraints gbc_descriptionScrollPane = new GridBagConstraints();
		gbc_descriptionScrollPane.gridwidth = 4;
		gbc_descriptionScrollPane.gridheight = 3;
		gbc_descriptionScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_descriptionScrollPane.fill = GridBagConstraints.BOTH;
		gbc_descriptionScrollPane.gridx = 1;
		gbc_descriptionScrollPane.gridy = 6;
		viewPanel.add(descriptionScrollPane, gbc_descriptionScrollPane);

		descriptionTextArea = new JTextArea();
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setWrapStyleWord(true);
		descriptionTextArea.setEditable(false);
		descriptionScrollPane.setViewportView(descriptionTextArea);

		JLabel attributeLabel = new JLabel("Attributes:");
		GridBagConstraints gbc_attributeLabel = new GridBagConstraints();
		gbc_attributeLabel.anchor = GridBagConstraints.EAST;
		gbc_attributeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_attributeLabel.gridx = 0;
		gbc_attributeLabel.gridy = 10;
		viewPanel.add(attributeLabel, gbc_attributeLabel);

		JScrollPane attributeScrollPane = new JScrollPane();
		attributeScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_attributeScrollPane = new GridBagConstraints();
		gbc_attributeScrollPane.gridwidth = 4;
		gbc_attributeScrollPane.gridheight = 3;
		gbc_attributeScrollPane.fill = GridBagConstraints.BOTH;
		gbc_attributeScrollPane.gridx = 1;
		gbc_attributeScrollPane.gridy = 10;
		viewPanel.add(attributeScrollPane, gbc_attributeScrollPane);
		
		attributeTable = new JTable();
		attributeScrollPane.setViewportView(attributeTable);
	}

	private void postInitialize() {
		
		JPanel viewPanel = getViewPanel();
		add(viewPanel, BorderLayout.WEST);
		
		imageGalleryPanel = new ImageGalleryPanel();
		imageGalleryPanel.setBorder(new EmptyBorder(0, 16, 0, 0));
		add(imageGalleryPanel, BorderLayout.CENTER);

		categoryComboBox = ComponentFactory.createComboBox(CategoryUtils.CATEGORIES.values(), Category.class);
		GridBagConstraints gbc_categoryComboBox = new GridBagConstraints();
		gbc_categoryComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_categoryComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_categoryComboBox.gridx = 1;
		gbc_categoryComboBox.gridy = 2;
		viewPanel.add(categoryComboBox, gbc_categoryComboBox);
		categoryComboBox.setEnabled(false);
		
		categoryComboBox.addItemListener((evt) -> {
			addAttributeButton.setEnabled(isEditable() && categoryComboBox.getSelectedIndex() > 0);
		});

		launchDateChooser = ComponentFactory.getDateChooser();
		launchDateChooser.setEditable(false);
		launchDateChooser.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		GridBagConstraints gbc_launchDateChooser = new GridBagConstraints();
		gbc_launchDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_launchDateChooser.fill = GridBagConstraints.BOTH;
		gbc_launchDateChooser.gridx = 1;
		gbc_launchDateChooser.gridy = 3;
		viewPanel.add(launchDateChooser, gbc_launchDateChooser);
		
		attributeTable.getColumnModel().addColumnModelListener(columnListener);
		attributeTable.setModel(new ActionPaneMapTableModel<String, Attribute<?>>(
				AttributeTableConstants.COLUMN_NAMES, Collections.emptyMap()));
		TableUtils.initializeActionPanes(attributeTable, new DeleteAttributeAction(attributeTable), 
				new EditAttributeAction(attributeTable, AttributeValueHandlingModes.SET, AttributeService.RETURN_UNASSIGNED_VALUES));
		
		addAttributeButton = new JButton(new AddAttributeAction(this, AttributeValueHandlingModes.SET));
		GridBagConstraints gbc_addAttributeButton = new GridBagConstraints();
		gbc_addAttributeButton.anchor = GridBagConstraints.EAST;
		gbc_addAttributeButton.insets = new Insets(0, 0, 5, 5);
		gbc_addAttributeButton.gridx = 0;
		gbc_addAttributeButton.gridy = 11;
		getViewPanel().add(addAttributeButton, gbc_addAttributeButton);
		attributeTable.setDefaultRenderer(Object.class, new AttributeTableCellRenderer());
		attributeTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		imageFileService = new ImageFileServiceImpl();
		
		addPropertyChangeListener(IS_EDITABLE_PROPERTY, editableListener);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Product getEntityFromFields() {
		Product product = new Product();

		product.setId(idValueLabel.getText().length() == 0 ? null : Long.valueOf(idValueLabel.getText()));
		product.setName(nameTextField.getText());
		product.setCategoryId(((Category) categoryComboBox.getSelectedItem()).getId());
		product.setLaunchDate(launchDateChooser.getDate());
		product.setStock(Integer.valueOf(stockTextField.getText()));
		product.setPurchasePrice(Double.valueOf(purchasePriceField.getText()));
		product.setSalePrice(Double.valueOf(salePriceField.getText()));
		product.setDescription(descriptionTextArea.getText());
		
		product.setAttributes(((ActionPaneMapTableModel<String, Attribute<?>>) attributeTable.getModel()).getData());

		return product;
	}

	@Override
	public void resetFields() {
		nameTextField.setText(getCurrentEntity().getName());
		categoryComboBox.setSelectedIndex(getCurrentEntity().getCategoryId());
		launchDateChooser.setDate(getCurrentEntity().getLaunchDate());
		stockTextField.setValue(getCurrentEntity().getStock());
		purchasePriceField.setValue(getCurrentEntity().getPurchasePrice());
		salePriceField.setValue(getCurrentEntity().getSalePrice());
		descriptionTextArea.setText(getCurrentEntity().getDescription());
		
		attributeTable.setModel(
				new ActionPaneMapTableModel<String, Attribute<?>>(AttributeTableConstants.COLUMN_NAMES, getCurrentEntity().getAttributes()));
		
		findProductImages();
	}

	@Override
	protected void setFieldsEditable(boolean isEditable) {
		nameTextField.setEditable(isEditable);
		categoryComboBox.setEnabled(isEditable);
		launchDateChooser.setEditable(isEditable);
		stockTextField.setEditable(isEditable);
		purchasePriceField.setEditable(isEditable);
		salePriceField.setEditable(isEditable);
		descriptionTextArea.setEditable(isEditable);
		addAttributeButton.setEnabled(isEditable && categoryComboBox.getSelectedIndex() > 0);
	}

	@Override
	protected void loadItemData() {
		idValueLabel.setText(getCurrentEntity().getId().toString());
		nameTextField.setText(getCurrentEntity().getName());
		categoryComboBox.setSelectedIndex(getCurrentEntity().getCategoryId());
		launchDateChooser.setDate(getCurrentEntity().getLaunchDate());
		stockTextField.setValue(getCurrentEntity().getStock());
		purchasePriceField.setValue(getCurrentEntity().getPurchasePrice());
		salePriceField.setValue(getCurrentEntity().getSalePrice());
		descriptionTextArea.setText(getCurrentEntity().getDescription());

		attributeTable.setModel(new ActionPaneMapTableModel<String, Attribute<?>>(
				AttributeTableConstants.COLUMN_NAMES, getCurrentEntity().getAttributes()));
		
		findProductImages();
	}
	
	private void findProductImages() {
		clearImages();
		Product p = getCurrentEntity();
		
		if (p == null || p.getId() == null) {
			return;
		}
		
		try {
			addImages(imageFileService.getFiles("product", getCurrentEntity().getId()));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			DialogUtils.showDatabaseAccessErrorDialog(this);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void addAttribute(Attribute<?> attribute) {
		((ActionPaneMapTableModel<String, Attribute<?>>) attributeTable.getModel()).addRow(attribute.getName(), attribute);
	}
	
	public <T extends Action & ItemListener> void addAttributeAction(T action) {
		categoryComboBox.addItemListener(action);
		addAction(action, EDITOR_CARD);
	}
	
	public void addImage(ImageEntry entry) {
		imageGalleryPanel.addImage(entry);
	}

	public void addImages(List<ImageEntry> entries) {
		imageGalleryPanel.addImages(entries);
	}
	
	public void clearImages() {
		imageGalleryPanel.clearImages();
	}

	public List<ImageEntry> getModifiedImages() {
		return imageGalleryPanel.getImages();
	}

}
