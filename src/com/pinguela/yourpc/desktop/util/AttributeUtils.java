package com.pinguela.yourpc.desktop.util;

import static com.pinguela.yourpc.model.constants.AttributeValueHandlingModes.RANGE;
import static com.pinguela.yourpc.model.constants.AttributeValueHandlingModes.SET;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.pinguela.yourpc.desktop.editor.ClampingSpinnerNumberEditor;
import com.pinguela.yourpc.desktop.model.AttributeSpinnerNumberModel;
import com.pinguela.yourpc.model.dto.AttributeDTO;
import com.pinguela.yourpc.model.dto.AttributeValueDTO;

public class AttributeUtils {
	
	public static <T> String formatValueList(AttributeDTO<T> attribute) {
		return formatValueList(attribute.getValues(), attribute.getValueHandlingMode());
	}
	
	public static <T> String formatValueList(List<AttributeValueDTO<T>> values, int valueHandlingMode) {

		String formattedValues = null;
		if (values.size() == 0) {
			formattedValues = "No values";
		} else if (values.size() == 1) {
			return values.get(0).getValue().toString();
		} else {
			switch (valueHandlingMode) {
			case RANGE:
				formattedValues = String.format("%s - %s", values.get(0).getValue(), values.get(values.size()-1).getValue());
				break;
			case SET:
				StringBuilder builder = new StringBuilder();
				for (AttributeValueDTO<T> value : values) {
					builder.append(value.getValue()).append(", ");
				}
				formattedValues = builder.delete(builder.length()-2, builder.length()).toString();
				break;
			}
		}
		return formattedValues;
	}

	public static <T extends Number & Comparable<T>> JSpinner getAttributeNumberSpinner(AttributeDTO<T> attribute, int initialValueIndex) {
	
		SpinnerNumberModel model = new AttributeSpinnerNumberModel<T>(attribute, initialValueIndex);
		JSpinner spinner = new JSpinner(model);
		spinner.setEditor(new ClampingSpinnerNumberEditor(spinner));
	
		spinner.setPreferredSize(new Dimension(100, 20));
		return spinner;
	}

}
