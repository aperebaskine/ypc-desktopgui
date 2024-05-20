package com.pinguela.yourpc.desktop.model;

import javax.swing.SpinnerNumberModel;

import com.pinguela.yourpc.model.Attribute;

public class AttributeSpinnerNumberModel<T extends Number & Comparable<T>> 
extends SpinnerNumberModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1716878951061321396L;

	private static final int PREVIOUS = -1;
	private static final int NEXT = 1;

	// Methods using step size are overridden
	private static final int STEP_SIZE = 1;

	private Attribute<T> attribute;
	private int currentIndex;
	private int maxIndex;

	private boolean isCustomValueSet = false;

	public AttributeSpinnerNumberModel(Attribute<T> attribute, int initialValueIndex) {
		super(attribute.getValueAt(initialValueIndex),
				attribute.getValueAt(0), 
				attribute.getValueAt(attribute.getValues().size()-1),
				STEP_SIZE
				);

		this.attribute = attribute;
		this.currentIndex = initialValueIndex;
		this.maxIndex = attribute.getValues().size() - 1;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void setValue(Object value) {

		T number = (T) value;

		if (number.compareTo(attribute.getValueAt(currentIndex)) != 0) {
			if (number.compareTo((T) getMinimum()) == 0) {
				currentIndex = 0;
			} else if (number.compareTo((T) getMaximum()) == 0) {
				currentIndex = maxIndex;
			} else {
				isCustomValueSet = true;	
			}
		}
		super.setValue(value);
	}

	@Override
	public Object getNextValue() {
		return getAdjacentValue(NEXT);
	}

	@Override
	public Object getPreviousValue() {
		return getAdjacentValue(PREVIOUS);
	}

	private T getAdjacentValue(int offset) {

		if (isCustomValueSet) {
			isCustomValueSet = false;
			return getClosestValue(offset);
		}

		if (offset == NEXT) {
			if (currentIndex < maxIndex) {
				currentIndex += NEXT;
			}
		} else {
			if (currentIndex > 0) { 
				currentIndex += PREVIOUS;
			}
		}

		return attribute.getValueAt(currentIndex);
	}

	/**
	 * Finds the value stored in the attribute list that's closest to the current value,
	 * according to the specified offset.
	 * @param value Current selected value.
	 * @param offset Indicates whether the value to retrieve is higher or lower.
	 * @return The value stored within the attribute value list that has the closest value
	 * to the one received as parameter.
	 */
	@SuppressWarnings("unchecked")
	private T getClosestValue(int offset) {

		T currentValue = (T) getValue();

		int floor = 0;
		int ceiling = maxIndex;

		while (ceiling - floor > 1) {
			int index = (floor+ceiling)/2;
			T value = attribute.getValueAt(index);
			int compare = currentValue.compareTo(value);

			if (compare == 0) {
				currentIndex = index;
				return getAdjacentValue(offset);
			} else if (compare < 0) {
				ceiling = index;
			} else {
				floor = index;
			}
		}

		if (currentValue.compareTo(attribute.getValueAt(floor)) == 0) {
			currentIndex = floor;
			return getAdjacentValue(offset);
		} else if (currentValue.compareTo(attribute.getValueAt(ceiling)) == 0) {
			currentIndex = ceiling;
			return getAdjacentValue(offset);
		} 

		currentIndex = offset == NEXT ? ceiling : floor;
		return attribute.getValueAt(currentIndex);
	}

}
