package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;
import java.lang.reflect.Type;

import javax.swing.JPanel;
import javax.swing.table.TableCellEditor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.yourpc.desktop.components.EntitySelector;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.util.ReflectionUtils;
import com.pinguela.yourpc.desktop.util.TableUtils;
import com.pinguela.yourpc.desktop.view.SearchView;

@SuppressWarnings("serial")
public class SelectEntityAction<T> 
extends OpenDialogAction<T> {
	
	private static final String SEARCH_VIEW_PLACEHOLDER;
	
	private static Logger logger = LogManager.getLogger(SelectEntityAction.class);
	
	static {
		Class<?> searchView = SearchView.class;
		SEARCH_VIEW_PLACEHOLDER = new StringBuilder(searchView.getPackage().getName())
				.append(".%s")
				.append(searchView.getSimpleName())
				.toString();
	}
	
	private EntitySelector<T> selector;
	private SearchView<T> view;
	
	public SelectEntityAction(EntitySelector<T> selector) {
		super("Select...");
		this.selector = selector;
	}
	
	@Override
	protected final YPCDialog createDialog(ActionEvent e) {
		view = initializeSearchView();
		TableUtils.initializeActionPanes(view.getTable(), new CloseDialogAction(Icons.OK_ICON, (JPanel) view));
		return new YPCDialog(null, (JPanel) view);
	}

	@Override
	protected boolean shouldSetRelativeLocation() {
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected final void onClose() {
		TableCellEditor editor = view.getTable().getCellEditor();
		
		if (editor == null) {
			return;
		}
		
		T c = (T) view.getTable().getCellEditor().getCellEditorValue();
		
		if (c == null) {
			return;
		}
		
		selector.setEntity(c);
	}
	
	@SuppressWarnings("unchecked")
	protected SearchView<T> initializeSearchView() {
		
		Type typeParameter = ReflectionUtils.getTypeParameterBounds(selector.getClass())[0];
		Class<T> typeParameterClass = (Class<T>) ReflectionUtils.getClass(typeParameter);
		
		String fullyQualifiedClassName = String.format(SEARCH_VIEW_PLACEHOLDER, typeParameterClass.getSimpleName());
		
		try {
			return (SearchView<T>) Class.forName(fullyQualifiedClassName)
					.getDeclaredConstructor()
					.newInstance();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new IllegalStateException(String.format(
					"Exception thrown while instantiating %s.", fullyQualifiedClassName, fullyQualifiedClassName));
		}
	}
	
	

}
