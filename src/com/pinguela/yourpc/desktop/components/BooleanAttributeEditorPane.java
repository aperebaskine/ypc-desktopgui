package com.pinguela.yourpc.desktop.components;

import java.awt.Component;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import com.pinguela.yourpc.model.dto.AttributeDTO;
import com.pinguela.yourpc.service.AttributeService;

@SuppressWarnings("serial")
public class BooleanAttributeEditorPane 
extends AttributeEditorPane<Boolean> {

	private ButtonGroup buttonGroup;
	private JRadioButton trueRadioButton;
	private JRadioButton falseRadioButton;

	/**
	 * Unused constructor required for rendering within a WindowBuilder designer.
	 */
	@SuppressWarnings("unused")
	private BooleanAttributeEditorPane() {
		this(AttributeDTO.getInstance(Boolean.class), null, AttributeService.RETURN_UNASSIGNED_VALUES);
	}

	public BooleanAttributeEditorPane(AttributeDTO<Boolean> attribute, Integer handlingMode, boolean showUnassignedValues) {
		super(attribute, handlingMode, true);
		initialize();
		setInitialValues();
	}

	private void initialize() {

		trueRadioButton = new JRadioButton("Yes");
		add(trueRadioButton);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut);

		falseRadioButton = new JRadioButton("No");
		add(falseRadioButton);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(trueRadioButton);
		buttonGroup.add(falseRadioButton);
		
	}
	
	private void setInitialValues() {
		AttributeDTO<Boolean> attribute = getEditingAttribute();
		if (attribute.getValues().size() == 1 && Boolean.FALSE.equals(attribute.getValueAt(0))) {
			falseRadioButton.setSelected(true);
		} else {
			trueRadioButton.setSelected(true);
		}
	}
	
	@Override
	protected Collection<Boolean> getEditorValues() {
		return Arrays.asList(trueRadioButton.isSelected());
	}

}
