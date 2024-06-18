package com.pinguela.yourpc.desktop.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

@SuppressWarnings("serial")
public abstract class YPCAction
extends AbstractAction 
implements PropertyChangeListener, MouseListener, KeyListener, ItemListener, 
ChangeListener, DocumentListener, TableModelListener {

	public YPCAction() {
		super();
	}

	public YPCAction(String name) {
		super(name);
	}

	public YPCAction(Icon icon) {
		super(null, icon);
	}

	public YPCAction(String name, Icon icon) {
		super(name, icon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();	
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		doAction();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		doAction();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		doAction();
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {}

	@Override
	public void removeUpdate(DocumentEvent e) {}

	@Override
	public void changedUpdate(DocumentEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void tableChanged(TableModelEvent e) {}
	
	protected abstract void doAction();

}
