package com.pinguela.yourpc.desktop.components;

import java.awt.Component;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.pinguela.yourpc.model.Attribute;
import com.pinguela.yourpc.service.AttributeService;

public class BooleanAttributeValueInputPane 
extends AttributeValueInputPane<Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1803990479858952340L;

	private ButtonGroup buttonGroup;
	private JRadioButton trueRadioButton;
	private JRadioButton falseRadioButton;

	/**
	 * Unused constructor required for rendering within a WindowBuilder designer.
	 */
	@SuppressWarnings("unused")
	private BooleanAttributeValueInputPane() {
		this(Attribute.getInstance(Boolean.class), AttributeService.RETURN_UNASSIGNED_VALUES, true);
	}

	public BooleanAttributeValueInputPane(Attribute<Boolean> attribute, boolean showUnassignedValues,
			boolean showActions) {
		super(attribute, true, showActions);
	}

	@Override
	protected JPanel initializeContentPane() {

		JPanel contentPane = new JPanel();

		trueRadioButton = new JRadioButton("Yes");
		contentPane.add(trueRadioButton);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		contentPane.add(horizontalStrut);

		falseRadioButton = new JRadioButton("No");
		contentPane.add(falseRadioButton);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(trueRadioButton);
		buttonGroup.add(falseRadioButton);

		return contentPane;
	}

	@Override
	protected void setInitialValues() {
		Attribute<Boolean> attribute = getAttribute();
		if (attribute.getValues().size() == 1 && Boolean.FALSE.equals(attribute.getValueAt(0))) {
			falseRadioButton.setSelected(true);
		} else {
			trueRadioButton.setSelected(true);
		}
	}
	
	@Override
	protected Collection<Boolean> getInputValues() {
		return Arrays.asList(trueRadioButton.isSelected());
	}

}
