package com.pinguela.yourpc.desktop.dialog;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.desktop.view.LoginView;

public class LoginDialog extends YPCDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3889635820810281777L;
	
	public LoginDialog() {
		this(null, new LoginView());
	}

	public LoginDialog(JFrame owner, JPanel contentPane) {
		super(owner, contentPane);
		setTitle("YourPC - Login");
		setPreferredSize(new Dimension(480, 344));
		setModal(true);
		pack();
		EventQueue.invokeLater(() -> {
			SwingUtils.centerOnScreen(this);
			setVisible(true);
		});
	}

}
