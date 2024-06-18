package com.pinguela.yourpc.desktop.dialog;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.desktop.view.LoginView;

@SuppressWarnings("serial")
public class LoginDialog extends YPCDialog {
	
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
