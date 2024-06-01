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

public class StringAttributeValueInputPane 
extends AttributeValueInputPane<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3479487153780500916L;
	
	private JScrollPane selectedValuesScrollPane;
	private JList<String> selectedValuesList;
	private JComboBox<AttributeValue<String>> valueComboBox;

	private JPanel valueSelectionPanel;

	/**
	 * Unused constructor required for rendering within a WindowBuilder designer.
	 */
	@SuppressWarnings("unused")
	private StringAttributeValueInputPane() {
		this(Attribute.getInstance(String.class), AttributeService.RETURN_UNASSIGNED_VALUES, true);
	}

	

	public StringAttributeValueInputPane(Attribute<String> attribute, boolean showUnassignedValues,
			boolean showActions) {
		super(attribute, showUnassignedValues, showActions);
		postInitialize();
	}

	@Override
	protected JPanel initializeContentPane() {

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));

		selectedValuesScrollPane = new JScrollPane();
		selectedValuesScrollPane.setPreferredSize(new Dimension(200, 100));
		contentPane.add(selectedValuesScrollPane);
		
		valueSelectionPanel = new JPanel();
		contentPane.add(valueSelectionPanel, BorderLayout.NORTH);

		return contentPane;
	}
	
	@SuppressWarnings("unchecked")
	private void postInitialize() {
		valueComboBox = ComponentFactory.createComboBox(getSavedAttribute().getValues(), AttributeValue.class);
		valueSelectionPanel.add(valueComboBox);
		
		JButton addValueButton = new JButton("Add", Icons.ADD_ICON);
		addValueButton.addActionListener((evt) -> {
			((DefaultListModel<String>) selectedValuesList.getModel()).addElement(((AttributeValue<String>) valueComboBox.getSelectedItem()).getValue());
		});
		valueSelectionPanel.add(addValueButton);
	}

	@Override
	protected void setInitialValues() {
		selectedValuesList = 
				new JList<String>(new DefaultListModel<>());
		selectedValuesScrollPane.setViewportView(selectedValuesList);
		
		JLabel selectedValuesLabel = new JLabel("Curently selected value(s):");
		selectedValuesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectedValuesScrollPane.setColumnHeaderView(selectedValuesLabel);

	}
	
	@Override
	protected Collection<String> getInputValues() {
		return Collections.list(((DefaultListModel<String>) selectedValuesList.getModel()).elements());
	}

}
