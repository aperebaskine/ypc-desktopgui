package com.pinguela.yourpc.desktop.components;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.pinguela.yourpc.model.Attribute;

public class BooleanAttributeInputPane 
extends AttributeInputPane<Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1803990479858952340L;

	/**
	 * Unused constructor required for rendering within a WindowBuilder designer.
	 */
	@SuppressWarnings("unused")
	private BooleanAttributeInputPane() {
		this(Attribute.getInstance(Boolean.class));
	}

	public BooleanAttributeInputPane(Attribute<Boolean> attribute) {
		super(attribute);
	}
	
	@Override
	protected JPanel initializeContentPane() {
		
		JPanel contentPane = new JPanel();
		
		JLabel trueLabel = new JLabel("Yes");
		contentPane.add(trueLabel);

		JRadioButton trueRadioButton = new JRadioButton("");
		contentPane.add(trueRadioButton);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		contentPane.add(horizontalStrut);

		JLabel falseLabel = new JLabel("No");
		contentPane.add(falseLabel);

		JRadioButton falseRadioButton = new JRadioButton("");
		contentPane.add(falseRadioButton);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(trueRadioButton);
		buttonGroup.add(falseRadioButton);
		
		return contentPane;
	}
	
	@Override
	protected void setInitialValues() {
	}
	
}
