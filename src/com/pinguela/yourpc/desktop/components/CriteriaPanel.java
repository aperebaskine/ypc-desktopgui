package com.pinguela.yourpc.desktop.components;

import javax.swing.JPanel;

import com.pinguela.yourpc.model.Criteria;

@SuppressWarnings("serial")
public abstract class CriteriaPanel<PK, T> extends JPanel {
	
	public abstract Criteria<PK, T> getCriteria();
	
	public abstract void setFieldsEnabled(boolean isEnabled);
	
	public abstract void resetFields(Object source);

}
