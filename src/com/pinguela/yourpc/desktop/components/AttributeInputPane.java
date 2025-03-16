package com.pinguela.yourpc.desktop.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.model.dto.AttributeDTO;

@SuppressWarnings("serial")
public class AttributeInputPane 
extends InputPane<AttributeDTO<?>> {
	
	private boolean showUnassignedValues;
	
	private JPanel contentPane;
	private JComboBox<AttributeDTO<?>> attributeComboBox;
	private AttributeEditorPane<?> editorPane;
	
	private Integer handlingMode;
	
	private ItemListener selectionListener = (evt) -> {
		if (editorPane != null) {
			contentPane.remove(editorPane);
			editorPane = null;
		}
		AttributeDTO<?> attribute = (AttributeDTO<?>) evt.getItem();
		if (attribute.getName() != null && attributeComboBox.getSelectedIndex() > 0) {
			initializeValueInputPane();
		}
		revalidate();
		repaint();
	};
	
	public AttributeInputPane(Map<String, AttributeDTO<?>> attributes, boolean showUnassignedValues) {
		this(attributes, null, showUnassignedValues);
	}

	public AttributeInputPane(Map<String, AttributeDTO<?>> attributes, Integer handlingMode, boolean showUnassignedValues) {
		super("Select attribute:");
		this.showUnassignedValues = showUnassignedValues;
		this.handlingMode = handlingMode;
		
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
	
	private void postInitialize(Map<String, AttributeDTO<?>> attributes) {
		attributeComboBox = ComponentFactory.createComboBox(attributes.values(), AttributeDTO.class);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		contentPane.add(attributeComboBox, gbc_comboBox);
		attributeComboBox.addItemListener(selectionListener);
	}
	
	@SuppressWarnings("unchecked")
	private <T> void initializeValueInputPane() {
		editorPane = AttributeEditorPane.getInstance((AttributeDTO<T>) attributeComboBox.getSelectedItem(), handlingMode, showUnassignedValues);
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
	public AttributeDTO<?> getInput() {
		
		AttributeDTO<?> attribute = (AttributeDTO<?>) attributeComboBox.getSelectedItem();
		Collection<?> editorPaneValues = editorPane.getEditorValues();
		
		Iterator<?> iterator = editorPaneValues.iterator();
		Object[] array = (Object[]) Array.newInstance(attribute.getTypeParameterClass(), editorPaneValues.size());
		for (int i = 0; i < array.length; i++) {
			array[i] = iterator.next();
		}
		return AttributeDTO.getInstance(attribute.getId(), attribute.getName(), array);
	}

}
