package com.pinguela.yourpc.desktop.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Objects;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.pinguela.yourpc.desktop.actions.ResetCriteriaAction;
import com.pinguela.yourpc.desktop.actions.SearchAction;
import com.pinguela.yourpc.desktop.actions.SearchActionBuilder;
import com.pinguela.yourpc.desktop.actions.YPCAction;
import com.pinguela.yourpc.desktop.components.ActionPane;
import com.pinguela.yourpc.desktop.components.CriteriaPanel;
import com.pinguela.yourpc.model.Criteria;

@SuppressWarnings("serial")
public abstract class AbstractSearchView<T> 
extends YPCView {
	
	public static final String TABLE_MODEL_PROPERTY = "tableModel";
	public static final String CRITERIA_PROPERTY = "criteria";
	
	private CriteriaPanel<T> criteriaPanel;
	private ActionPane actionPane;
	private JScrollPane resultScrollPane;
	private JTable resultTable;
	private JButton searchButton;
	
	private final ComponentListener defaultButtonSetter = new ComponentAdapter() {
		@Override
		public void componentShown(ComponentEvent e) {
			if (getRootPane() != null) {
				getRootPane().setDefaultButton(searchButton);
			}
		}
		@Override
		public void componentHidden(ComponentEvent e) {
			if (getRootPane() != null && Objects.equals(getRootPane().getDefaultButton(), searchButton)) {
				getRootPane().setDefaultButton(null);
			}
		}
	};

	public AbstractSearchView(SearchActionBuilder<T, ? extends SearchAction<T>> builder) {
		
		JPanel searchPanel = new JPanel();
		add(searchPanel, BorderLayout.NORTH);
		searchPanel.setLayout(new BorderLayout(0, 0));

		criteriaPanel = initializeCriteriaPanel();
		searchPanel.add(criteriaPanel);

		actionPane = new ActionPane();
		FlowLayout flowLayout = (FlowLayout) actionPane.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		searchPanel.add(actionPane, BorderLayout.SOUTH);

		searchButton = new JButton(builder.setView(this).build());
		actionPane.add(searchButton);
		addActionButton(new ResetCriteriaAction(criteriaPanel));

		Component verticalStrut = Box.createVerticalStrut(40);
		verticalStrut.setPreferredSize(new Dimension(0, 50));
		actionPane.add(verticalStrut);

		JPanel resultPanel = new JPanel();
		resultPanel.setVisible(true);
		add(resultPanel, BorderLayout.CENTER);
		resultPanel.setLayout(new BorderLayout(0, 0));

		resultScrollPane = new JScrollPane();
		resultPanel.add(resultScrollPane);
		
		resultTable = new JTable();
		resultScrollPane.setViewportView(resultTable);
		
		addComponentListener(defaultButtonSetter);
	}
	
	public final void addSearchActionListener(ActionListener listener) {
		searchButton.addActionListener(listener);
	}
	
	public final void addActionButton(Action action) {
		actionPane.addAction(action);
	}
	
	public void setTableActions(boolean firstActionAsMouseListener, YPCAction... actions) {
		for (int i = actions.length; i <= 0; i--) {
			
		}
	}

	public final void setTableModel(TableModel model) {
		TableModel old = resultTable.getModel();
		resultTable.setModel(model);
		firePropertyChange(TABLE_MODEL_PROPERTY, old, model);
	}
	
	public void setTableCellRenderer(Class<?> columnClass, TableCellRenderer renderer) {
		resultTable.setDefaultRenderer(columnClass, renderer);
	}
	
	public void setTableCellEditor(Class<?> columnClass, TableCellEditor editor) {
		resultTable.setDefaultEditor(columnClass, editor);
	}
	
	public final void resetCriteriaFields(Object source) {
		criteriaPanel.resetFields(source);
		firePropertyChange(CRITERIA_PROPERTY, null, null);
	}
	
	public final void setCriteriaFieldsEnabled(boolean isEnabled) {
		criteriaPanel.setFieldsEnabled(isEnabled);
	}
	
	public Criteria<?, ? super T> getCriteria() {
		return criteriaPanel.getCriteria();
	}
	
	public Object[][] getTableSelection() {
		int[] selectedRows = resultTable.getSelectedRows();
		int[] selectedColumns = resultTable.getSelectedColumns();
		
		Object[][] selection = new Object[selectedRows.length][selectedColumns.length];
		
		for (int i = 0; i < selectedRows.length; i++) {
			for (int j = 0; j < selectedColumns.length; j++) {
				selection[i][j] = resultTable.getValueAt(selectedRows[i], selectedColumns[j]);
			}
		}
		
		return selection;
	}
	
	public Object getTableCellEditorValue() {
		TableCellEditor editor = resultTable.getCellEditor();
		return editor == null ? null : editor.getCellEditorValue();
	}
	
	public void doSearch() {
		searchButton.getAction().actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
	}
		
	protected abstract CriteriaPanel<T> initializeCriteriaPanel();
	
}
