package com.pinguela.yourpc.desktop.components;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;

import com.pinguela.yourpc.desktop.util.AttributeUtils;
import com.pinguela.yourpc.model.Attribute;

public class NumberAttributeInputPane<T extends Number & Comparable<T>>
extends AttributeInputPane<T> {

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
	private NumberAttributeInputPane() {
		this((Attribute<T>) Attribute.getInstance(Long.class));
	}

	public NumberAttributeInputPane(Attribute<T> attribute) {
		super(attribute);
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
		
		Attribute<T> attribute = getAttribute();

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

}
