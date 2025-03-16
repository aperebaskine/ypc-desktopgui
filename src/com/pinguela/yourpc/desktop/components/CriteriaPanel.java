package com.pinguela.yourpc.desktop.components;

import javax.swing.JPanel;

import com.pinguela.yourpc.model.Criteria;
import java.awt.GridBagLayout;

@SuppressWarnings("serial")
public abstract class CriteriaPanel<T> extends JPanel {
	
	public CriteriaPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		setLayout(gridBagLayout);
	}
	
	public abstract Criteria<?, T> getCriteria();
	
	public abstract void setFieldsEnabled(boolean isEnabled);
	
	public abstract void resetFields(Object source);

}
