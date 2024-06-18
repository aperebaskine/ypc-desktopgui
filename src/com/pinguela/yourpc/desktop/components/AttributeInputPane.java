package com.pinguela.yourpc.desktop.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.pinguela.yourpc.desktop.renderer.AttributeListCellRenderer;
import com.pinguela.yourpc.model.Attribute;
import com.pinguela.yourpc.model.StringAttribute;

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
		
		Attribute<?> emptyAttribute = new StringAttribute();
		Attribute<?>[] attributeArray = new Attribute<?>[attributes.values().size()+1];
		attributeArray[0] = emptyAttribute;
		
		Iterator<Attribute<?>> iterator = attributes.values().iterator();
		for (int i = 1; i<attributeArray.length; i++) {
			attributeArray[i] = iterator.next();
		}
		
		attributeComboBox.setModel(new DefaultComboBoxModel<>(attributeArray));
		attributeComboBox.addItemListener(selectionListener);
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
		
		attributeComboBox = new JComboBox<Attribute<?>>();
		attributeComboBox.setRenderer(new AttributeListCellRenderer());
		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		contentPane.add(attributeComboBox, gbc_comboBox);
		
		return contentPane;
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
