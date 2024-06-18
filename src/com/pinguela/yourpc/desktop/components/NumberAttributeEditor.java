package com.pinguela.yourpc.desktop.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.factory.ComponentFactory;
import com.pinguela.yourpc.desktop.util.AttributeUtils;
import com.pinguela.yourpc.model.Attribute;
import com.pinguela.yourpc.service.AttributeService;

@SuppressWarnings("serial")
public class NumberAttributeEditor<T extends Number & Comparable<T>>
extends AttributeEditor<T> {

	private AttributeEditor<T> delegate;

	/**
	 * Unused constructor required for rendering within a WindowBuilder designer.
	 */
	@SuppressWarnings({"unused", "unchecked"})
	private NumberAttributeEditor() {
		this((Attribute<T>) Attribute.getInstance(Long.class), null, AttributeService.RETURN_UNASSIGNED_VALUES);
	}

	public NumberAttributeEditor(Attribute<T> attribute, Integer handlingMode, boolean showUnassignedValues) {
		super(attribute, handlingMode, showUnassignedValues);
		initialize(handlingMode, showUnassignedValues);
	}

	private void initialize(Integer handlingMode, boolean showUnassignedValues) {
		setLayout(new BorderLayout(0, 0));

		Attribute<T> attribute = getEditingAttribute();

		if (handlingMode == null) {
			handlingMode = attribute.getValueHandlingMode();
		}

		switch (handlingMode) {
		case RANGE:
			delegate = new NumberEditorRangeDelegate(attribute, showUnassignedValues);
			break;
		case SET:
			delegate = new NumberEditorSetDelegate(attribute, showUnassignedValues);
			break;
		}

		add(delegate, BorderLayout.CENTER);
	}

	@Override
	protected Collection<T> getEditorValues() {
		return delegate.getEditorValues();
	}

	private class NumberEditorRangeDelegate extends AttributeEditor<T> {

		private JPanel minValuePanel;
		private JPanel maxValuePanel;
		private JSpinner minValueSpinner;
		private JSpinner maxValueSpinner;

		protected NumberEditorRangeDelegate(Attribute<T> attribute, boolean showUnassignedValues) {
			super(attribute, null, showUnassignedValues);
			initialize();
			setInitialValues();
		}

		private void initialize() {

			setLayout(new BorderLayout(0, 0));

			minValuePanel = new JPanel();
			add(minValuePanel, BorderLayout.NORTH);

			maxValuePanel = new JPanel();
			add(maxValuePanel, BorderLayout.SOUTH);

			JLabel minValueLabel = new JLabel("Min:");
			minValueLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			minValueLabel.setPreferredSize(new Dimension(28, 14));
			minValueLabel.setMinimumSize(new Dimension(28, 14));
			minValueLabel.setMaximumSize(new Dimension(28, 14));
			minValuePanel.add(minValueLabel);

			JLabel maxValueLabel = new JLabel("Max:");
			maxValueLabel.setMinimumSize(new Dimension(28, 14));
			maxValueLabel.setMaximumSize(new Dimension(28, 14));
			maxValueLabel.setPreferredSize(new Dimension(28, 14));
			maxValueLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			maxValuePanel.add(maxValueLabel);
		}

		@SuppressWarnings("unchecked")
		protected void setInitialValues() {

			Attribute<T> attribute = getSavedAttribute();

			if (attribute.getValues().size() < 1) {
				return;
			}

			minValueSpinner = AttributeUtils.getAttributeNumberSpinner(attribute, 0);
			minValuePanel.add(minValueSpinner);

			maxValueSpinner = AttributeUtils.getAttributeNumberSpinner(attribute, attribute.getValues().size()-1);
			maxValuePanel.add(maxValueSpinner);

			minValueSpinner.addChangeListener((e) ->{
				if (((T) minValueSpinner.getValue()).compareTo((T) maxValueSpinner.getValue()) > 0) {
					maxValueSpinner.setValue(minValueSpinner.getValue());
				}
			});

			maxValueSpinner.addChangeListener((e) -> {
				if (((T) maxValueSpinner.getValue()).compareTo((T) minValueSpinner.getValue()) < 0) {
					minValueSpinner.setValue(maxValueSpinner.getValue());
				}
			});
		}

		@Override
		@SuppressWarnings("unchecked")
		protected Collection<T> getEditorValues() {

			T min = (T) minValueSpinner.getValue();
			T max = (T) maxValueSpinner.getValue();

			if (min.equals(max)) {
				return Arrays.asList(min);
			} else {
				return Arrays.asList(min, max);
			}
		}		
	}

	private class NumberEditorSetDelegate extends AttributeEditor<T> {

		private JScrollPane selectedValuesScrollPane;
		private JList<T> selectedValuesList;
		private JFormattedTextField valueTextField;

		private JPanel valueSelectionPanel;

		protected NumberEditorSetDelegate(Attribute<T> attribute, boolean showUnassignedValues) {
			super(attribute, null, showUnassignedValues);
			initialize();
		}

		@SuppressWarnings("unchecked")
		private void initialize() {

			setLayout(new BorderLayout(0, 0));

			selectedValuesScrollPane = new JScrollPane();
			selectedValuesScrollPane.setPreferredSize(new Dimension(200, 100));
			add(selectedValuesScrollPane);

			valueSelectionPanel = new JPanel();
			add(valueSelectionPanel, BorderLayout.NORTH);

			valueTextField = ComponentFactory.createNullableNumberTextField(getSavedAttribute().getTypeParameterClass());
			valueSelectionPanel.add(valueTextField);

			JButton addValueButton = new JButton("Add", Icons.ADD_ICON);
			addValueButton.addActionListener((evt) -> {
				((DefaultListModel<T>) selectedValuesList.getModel()).addElement((T) valueTextField.getValue());
			});
			valueSelectionPanel.add(addValueButton);

			selectedValuesList = 
					new JList<T>(new DefaultListModel<>());
			selectedValuesScrollPane.setViewportView(selectedValuesList);

			JLabel selectedValuesLabel = new JLabel("Curently selected value(s):");
			selectedValuesLabel.setHorizontalAlignment(SwingConstants.CENTER);
			selectedValuesScrollPane.setColumnHeaderView(selectedValuesLabel);
		}

		@Override
		protected Collection<T> getEditorValues() {
			return Collections.list(((DefaultListModel<T>) selectedValuesList.getModel()).elements());
		}

	}

}
