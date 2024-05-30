package com.pinguela.yourpc.desktop.view;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.pinguela.yourpc.model.Criteria;

public interface SearchView<T> {

	String TABLE_MODEL_PROPERTY = "tableModel";
	String CRITERIA_PROPERTY = "criteria";
	
	Criteria<?, T> getCriteria();
	
	JTable getTable();

	void setModel(TableModel model);
	
	void resetCriteriaFields(Object source);
	
	void setCriteriaFieldsEnabled(boolean isEnabled);
	
	void doSearch();

}