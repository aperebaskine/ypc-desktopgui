package com.pinguela.yourpc.desktop.actions;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JTable;

import com.pinguela.yourpc.desktop.dialog.YPCDialog;

public abstract class OpenDialogAction<T> 
extends YPCAction 
implements WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3786210364953347617L;

	public OpenDialogAction() {
		this(null, null);
	}

	public OpenDialogAction(Icon icon) {
		this(null, icon);
	}

	public OpenDialogAction(String name, Icon icon) {
		super(name, icon);
	}

	public OpenDialogAction(String name) {
		this(name, null);
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public final void windowClosing(WindowEvent e) {
		onClose();
		((Window) e.getSource()).dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() >= 2 && e.getSource() instanceof JTable) {
			doAction(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, ActionCommands.TABLE_CELL));
		}

	}

	@Override
	protected final void doAction() {}

	protected final void doAction(ActionEvent e) {
		YPCDialog dialog = createDialog(e);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.addWindowListener(this);
		dialog.pack();
		dialog.setResizable(true);
		if (shouldSetRelativeLocation()) {
			dialog.setLocationRelativeTo((Component) e.getSource());
		}
		dialog.setVisible(true);
	}

	protected abstract YPCDialog createDialog(ActionEvent e);

	protected abstract boolean shouldSetRelativeLocation();

	protected abstract void onClose();

}
