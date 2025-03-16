package com.pinguela.yourpc.desktop.util;

import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.pinguela.yourpc.desktop.actions.ActionCommands;
import com.pinguela.yourpc.desktop.components.ActionPane;
import com.pinguela.yourpc.desktop.constants.TableConstants;
import com.pinguela.yourpc.desktop.editor.ActionPaneCellEditor;
import com.pinguela.yourpc.desktop.renderer.ActionPaneCellRenderer;

public class TableUtils 
implements TableConstants {
	
	private static final String SELECTION_BACKGROUND_PROPERTY = "selectionBackground";
	private static final PropertyChangeListener ACTION_PANE_SELECTION_BACKGROUND_LISTENER = (evt) -> {
		JTable table = (JTable) evt.getSource();
		ActionPaneCellEditor editor = (ActionPaneCellEditor) table.getDefaultEditor(ActionPane.class);
		editor.setBackground(table.getSelectionBackground());
	};
	
	public static void addTableAction(JTable table, Action action, boolean asMouseListener) {
		
		
		TableCellRenderer renderer = table.getDefaultRenderer(ActionPane.class);
		TableCellEditor editor = table.getDefaultEditor(ActionPane.class);
		
	}
	
	public static void initializeActionPanes(JTable table, Action... actions) {
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setRowHeight(30);
		
		// Reflect changes made to the table's cell selection background by the look-and-feel
		table.addPropertyChangeListener(SELECTION_BACKGROUND_PROPERTY, ACTION_PANE_SELECTION_BACKGROUND_LISTENER);
		
		ActionPaneCellRenderer renderer = new ActionPaneCellRenderer();
		ActionPaneCellEditor editor = new ActionPaneCellEditor();
		
		for (Action action : actions) {
			renderer.addAction(action);
			editor.addAction(action, ActionCommands.TABLE_BUTTON);
		}
		
		table.setDefaultRenderer(ActionPane.class, renderer);
		table.setDefaultEditor(ActionPane.class, editor);
	}

}
