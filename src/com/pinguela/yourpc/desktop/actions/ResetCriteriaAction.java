package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import com.pinguela.yourpc.desktop.components.CriteriaPanel;
import com.pinguela.yourpc.desktop.constants.Icons;

@SuppressWarnings("serial")
public class ResetCriteriaAction 
extends YPCAction {

	private CriteriaPanel<?> panel;
	
	public ResetCriteriaAction(CriteriaPanel<?> panel) {
		super("Reset", Icons.UNDO_ICON);
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction(e);
	}
	
	@Override
	protected void doAction() {}
	
	protected void doAction(ActionEvent e) {
		panel.resetFields((JComponent) e.getSource());
	}

}
