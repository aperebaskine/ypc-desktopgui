package com.pinguela.yourpc.desktop.components;

import javax.swing.JPanel;

import com.pinguela.yourpc.model.Criteria;

public abstract class CriteriaPanel<PK, T> extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3013094086018080361L;
	
	public abstract Criteria<PK, T> getCriteria();
	
	public abstract void setFieldsEnabled(boolean isEnabled);
	
	public abstract void resetFields(Object source);

}
