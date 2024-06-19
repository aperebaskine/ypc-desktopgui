package com.pinguela.yourpc.desktop.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemListener;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.model.Attribute;

@SuppressWarnings("serial")
public class AttributeInputPane 
extends InputPane<Attribute<?>> {
	
	private boolean showUnassignedValues;
	
	private JPanel contentPane;
	private JComboBox<Attribute<?>> attributeComboBox;
	private AttributeEditor<?> editorPane;
	
	private Integer forcedHandlingMode;
	
	private ItemListener selectionListener = (evt) -> {
		if (editorPane != null) {
			contentPane.remove(editorPane);
			editorPane = null;
		}
		Attribute<?> attribute = (Attribute<?>) evt.getItem();
		if (attribute.getName() != null) {
			initializeValueInputPane();
		}
		revalidate();
		repaint();
	};
	
	public AttributeInputPane(Map<String, Attribute<?>> attributes, boolean showUnassignedValues) {
		this(attributes, null, showUnassignedValues);
	}

	public AttributeInputPane(Map<String, Attribute<?>> attributes, Integer forcedHandlingMode, boolean showUnassignedValues) {
		super("Select attribute:");
		this.showUnassignedValues = showUnassignedValues;
		this.forcedHandlingMode = forcedHandlingMode;
		
		postInitialize(attributes);
	}

	@Override
	protected JPanel initializeContentPane() {
		contentPane = new JPanel();
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{450, 0};
		gbl_contentPane.rowHeights = new int[]{15, 114, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
	
		return contentPane;
	}
	
	private void postInitialize(Map<String, Attribute<?>> attributes) {
		attributeComboBox = ComponentFactory.createComboBox(attributes.values(), Attribute.class);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		contentPane.add(attributeComboBox, gbc_comboBox);
		attributeComboBox.addItemListener(selectionListener);
	}
	
	@SuppressWarnings("unchecked")
	private <T> void initializeValueInputPane() {
		editorPane = AttributeEditor.getInstance((Attribute<T>) attributeComboBox.getSelectedItem(), forcedHandlingMode, showUnassignedValues);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(editorPane, gbc_panel);
		
		JDialog dialog = (JDialog) SwingUtilities.getAncestorOfClass(JDialog.class, this);
		
		if (dialog != null) {
			dialog.pack();
		}
	}

	@Override
	public Attribute<?> getInput() {
		Attribute<?> attribute = ((Attribute<?>) attributeComboBox.getSelectedItem()).clone();
		attribute.removeAllValues();
		
		for (Object value : editorPane.getEditorValues()) {
			attribute.addValue(null, value);
		}
		
		return attribute;
	}

}
