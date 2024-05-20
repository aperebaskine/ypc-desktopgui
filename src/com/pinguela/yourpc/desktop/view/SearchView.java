package com.pinguela.yourpc.desktop.view;

import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.pinguela.yourpc.model.Criteria;

public interface SearchView<T> {

	String TABLE_MODEL_PROPERTY = "tableModel";
	String CRITERIA_PROPERTY = "criteria";

	void setModel(TableModel model);
	
	void addListenerToSearchButton(ActionListener listener);
	
	Criteria<?, T> getCriteria();
	
	void resetCriteriaFields(Object source);
	
	void setFieldsEnabled(boolean isEnabled);
	
	void doSearch();
	
	JTable getTable();

}