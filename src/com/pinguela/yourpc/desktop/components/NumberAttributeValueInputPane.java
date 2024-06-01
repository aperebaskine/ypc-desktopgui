package com.pinguela.yourpc.desktop.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;

import com.pinguela.yourpc.desktop.util.AttributeUtils;
import com.pinguela.yourpc.model.Attribute;
import com.pinguela.yourpc.service.AttributeService;

public class NumberAttributeValueInputPane<T extends Number & Comparable<T>>
extends AttributeValueInputPane<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5159156731571599552L;

	private JPanel minValuePanel;
	private JPanel maxValuePanel;
	private JSpinner minValueSpinner;
	private JSpinner maxValueSpinner;

	/**
	 * Unused constructor required for rendering within a WindowBuilder designer.
	 */
	@SuppressWarnings({"unused", "unchecked"})
	private NumberAttributeValueInputPane() {
		this((Attribute<T>) Attribute.getInstance(Long.class), AttributeService.RETURN_UNASSIGNED_VALUES, true);
	}

	public NumberAttributeValueInputPane(Attribute<T> attribute, boolean showUnassignedValues, boolean showActions) {
		super(attribute, showUnassignedValues, showActions);
	}

	@Override
	protected JPanel initializeContentPane() {

		JPanel contentPane = new JPanel();

		contentPane.setLayout(new BorderLayout(0, 0));

		minValuePanel = new JPanel();
		contentPane.add(minValuePanel, BorderLayout.NORTH);

		maxValuePanel = new JPanel();
		contentPane.add(maxValuePanel, BorderLayout.SOUTH);

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
		return contentPane;
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
	protected Collection<T> getInputValues() {

		T min = (T) minValueSpinner.getValue();
		T max = (T) maxValueSpinner.getValue();

		if (min.equals(max)) {
			return Arrays.asList(min);
		} else {
			return Arrays.asList(min, max);
		}
	}

}
