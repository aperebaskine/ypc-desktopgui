package com.pinguela.yourpc.desktop.view;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.pinguela.yourpc.model.AbstractEntityCriteria;

public interface SearchView<T> {

	String TABLE_MODEL_PROPERTY = "tableModel";
	String CRITERIA_PROPERTY = "criteria";
	
	AbstractEntityCriteria<?, T> getCriteria();
	
	JTable getTable();

	void setModel(TableModel model);
	
	void resetCriteriaFields(Object source);
	
	void setCriteriaFieldsEnabled(boolean isEnabled);
	
	void doSearch();

}