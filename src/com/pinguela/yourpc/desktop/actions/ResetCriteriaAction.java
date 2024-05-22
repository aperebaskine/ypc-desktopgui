package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.view.SearchView;

public class ResetCriteriaAction 
extends YPCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 71428148390135239L;

	private SearchView<?> view;
	
	public ResetCriteriaAction(SearchView<?> view) {
		super("Reset", Icons.UNDO_ICON);
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction(e);
	}
	
	@Override
	protected void doAction() {}
	
	protected void doAction(ActionEvent e) {
		view.resetCriteriaFields((JComponent) e.getSource());
		view.doSearch();
	}

}
