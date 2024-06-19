package com.pinguela.yourpc.desktop.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Collection;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.model.Attribute;
import com.pinguela.yourpc.model.AttributeValue;
import com.pinguela.yourpc.service.AttributeService;

@SuppressWarnings("serial")
public class StringAttributeEditorPane 
extends AttributeEditorPane<String> {
	
	private JScrollPane selectedValuesScrollPane;
	private JList<String> selectedValuesList;
	private JComboBox<AttributeValue<String>> valueComboBox;

	private JPanel valueSelectionPanel;

	/**
	 * Unused constructor required for rendering within a WindowBuilder designer.
	 */
	@SuppressWarnings("unused")
	private StringAttributeEditorPane() {
		this(Attribute.getInstance(String.class), null, AttributeService.RETURN_UNASSIGNED_VALUES);
	}

	public StringAttributeEditorPane(Attribute<String> attribute, Integer handlingMode, boolean showUnassignedValues) {
		super(attribute, handlingMode, showUnassignedValues);
		initialize();
		setInitialValues();
	}

	@SuppressWarnings("unchecked")
	private void initialize() {

		setLayout(new BorderLayout(0, 0));

		selectedValuesScrollPane = new JScrollPane();
		selectedValuesScrollPane.setPreferredSize(new Dimension(200, 100));
		add(selectedValuesScrollPane);
		
		valueSelectionPanel = new JPanel();
		add(valueSelectionPanel, BorderLayout.NORTH);
		
		valueComboBox = ComponentFactory.createComboBox(getEditingAttribute().getValues(), AttributeValue.class);
		valueSelectionPanel.add(valueComboBox);
		
		JButton addValueButton = new JButton("Add", Icons.ADD_ICON);
		addValueButton.addActionListener((evt) -> {
			((DefaultListModel<String>) selectedValuesList.getModel())
			.addElement(((AttributeValue<String>) valueComboBox.getSelectedItem()).getValue());
		});
		valueSelectionPanel.add(addValueButton);
	}
	
	private void setInitialValues() {
		selectedValuesList = 
				new JList<String>(new DefaultListModel<>());
		selectedValuesScrollPane.setViewportView(selectedValuesList);
		
		JLabel selectedValuesLabel = new JLabel("Curently selected value(s):");
		selectedValuesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectedValuesScrollPane.setColumnHeaderView(selectedValuesLabel);
	}
	
	@Override
	protected Collection<String> getEditorValues() {
		return Collections.list(((DefaultListModel<String>) selectedValuesList.getModel()).elements());
	}

}
