package com.pinguela.yourpc.desktop;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.formdev.flatlaf.FlatDarkLaf;
import com.pinguela.yourpc.desktop.actions.OpenTabAction;
import com.pinguela.yourpc.desktop.actions.UserPopupMenuAction;
import com.pinguela.yourpc.desktop.components.CloseableTabComponent;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.dialog.LoginDialog;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.desktop.view.CustomerSearchView;
import com.pinguela.yourpc.desktop.view.EmployeeSearchView;
import com.pinguela.yourpc.desktop.view.ProductSearchView;
import com.pinguela.yourpc.model.Employee;

public class YPCWindow {

	public static final String AUTHENTICATED_USER_PROPERTY = "authenticatedUser";

	private static Logger logger = LogManager.getLogger();

	// Singleton
	private static YPCWindow instance = null; 

	private Employee authenticatedUser;
	private final PropertyChangeListener authenticationListener = (evt) -> {
		authenticatedUser = (Employee) evt.getNewValue();
		((Window) SwingUtilities.getWindowAncestor((Component) evt.getSource())).dispose();
		onSuccessfulAuthentication();
	};

	private JFrame frame;

	private JMenuItem productSearchMenuItem;
	private JButton userMenuButton;
	private JTabbedPane tabbedPane;

	static {
		try {
			Class.forName("com.pinguela.yourpc.desktop.constants.Icons");
		} catch (ClassNotFoundException e) {
			logger.fatal(String.format("Could not initialize required resources: %s", e.getMessage()), e);
		}
	}

	/**
	 * Singleton design pattern
	 * @return the instance
	 */
	public static YPCWindow getInstance() {
		if (instance == null) {
			instance = new YPCWindow();					
		}
		return instance;
	}

	/**
	 * Create the application.
	 */
	private YPCWindow() {
		showLoginDialog();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeFrame() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(960, 720));
		frame.setTitle("YourPC");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(YPCWindow.class.getResource("/nuvola/16x16/1301_chip_ram_processor_microchip_chip_processor_ram_microchip.png")));
		frame.setBounds(100, 100, 218, 149);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));

		JPanel northPanel = new JPanel();
		mainPanel.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout(0, 0));

		JMenuBar menuBar = new JMenuBar();
		northPanel.add(menuBar, BorderLayout.NORTH);

		JMenu productMenu = new JMenu("Products");
		menuBar.add(productMenu);

		productSearchMenuItem = new JMenuItem("Search...");
		productMenu.add(productSearchMenuItem);

		JPanel panel = new JPanel();
		northPanel.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JToolBar toolBar = new JToolBar();
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.anchor = GridBagConstraints.WEST;
		gbc_toolBar.insets = new Insets(0, 0, 0, 5);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		panel.add(toolBar, gbc_toolBar);

		JButton productTabButton = new JButton(Icons.PRODUCT_ICON);
		toolBar.add(productTabButton);
		
		JButton customerTabButton = new JButton(Icons.USER_ICON);
		toolBar.add(customerTabButton);
		
		JButton employeeTabButton = new JButton(Icons.USER_ICON);
		toolBar.add(employeeTabButton);
		
		JButton customerOrderTabButton = new JButton("New button");
		toolBar.add(customerOrderTabButton);
		
		JButton ticketTabButton = new JButton("");
		ticketTabButton.setIcon(new ImageIcon(YPCWindow.class.getResource("/nuvola/32x32/1798_mail_to_post_to_post_mail.png")));
		toolBar.add(ticketTabButton);
		
		JButton rmaTabButton = new JButton("");
		rmaTabButton.setIcon(new ImageIcon(YPCWindow.class.getResource("/nuvola/32x32/1761_screwdriver_screwdriver_tool_tool.png")));
		toolBar.add(rmaTabButton);

		JToolBar userMenuToolBar = new JToolBar();
		GridBagConstraints gbc_userMenuToolBar = new GridBagConstraints();
		gbc_userMenuToolBar.gridx = 1;
		gbc_userMenuToolBar.gridy = 0;
		panel.add(userMenuToolBar, gbc_userMenuToolBar);

		userMenuButton = new JButton(new UserPopupMenuAction());
		userMenuButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		userMenuToolBar.add(userMenuButton);

		JPanel southPanel = new JPanel();
		FlowLayout fl_southPanel = (FlowLayout) southPanel.getLayout();
		fl_southPanel.setAlignment(FlowLayout.TRAILING);
		mainPanel.add(southPanel, BorderLayout.SOUTH);

		JPanel westPanel = new JPanel();
		FlowLayout fl_westPanel = (FlowLayout) westPanel.getLayout();
		fl_westPanel.setAlignment(FlowLayout.LEFT);
		mainPanel.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		FlowLayout fl_eastPanel = (FlowLayout) eastPanel.getLayout();
		fl_eastPanel.setAlignment(FlowLayout.RIGHT);
		mainPanel.add(eastPanel, BorderLayout.EAST);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		mainPanel.add(tabbedPane, BorderLayout.CENTER);
		
		productTabButton.addActionListener(new OpenTabAction<>(ProductSearchView.class, "Product search"));
		customerTabButton.addActionListener(new OpenTabAction<>(CustomerSearchView.class, "Customer search"));
		employeeTabButton.addActionListener(new OpenTabAction<>(EmployeeSearchView.class, "Employee search"));
	}

	public Employee getAuthenticatedUser() {
		return authenticatedUser;
	}

	public void setPermissions() {
		// TODO: Make menus visible according to user's department
	}

	public void addCloseableTab(final String title, final Component panel) {
		tabbedPane.addTab(null, panel);
		int lastIndex = tabbedPane.getTabCount() -1;
		tabbedPane.setTabComponentAt(lastIndex, new CloseableTabComponent(tabbedPane, panel, title));
		tabbedPane.setSelectedIndex(lastIndex);
	}

	private void showLoginDialog() {
		YPCDialog loginDialog = new LoginDialog();
		loginDialog.getContentPane().addPropertyChangeListener(AUTHENTICATED_USER_PROPERTY, authenticationListener);
	}

	private void onSuccessfulAuthentication() {
		initializeFrame();
		setPermissions();
		userMenuButton.setText(authenticatedUser.getFullName());
		SwingUtils.centerOnScreen(frame);
		frame.setVisible(true);
		logger.info(String.format("Logged in as user %s.", authenticatedUser.getUsername()));
	}

	public void logout() {
		this.authenticatedUser = null;
		frame.dispose();
		showLoginDialog();
	}

	/**
	 * Unused entry point for WindowBuilder designer
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings("unused")
	private void windowBuilderEntryPoint() {
		main(null);
		onSuccessfulAuthentication();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UIManager.setLookAndFeel(new FlatDarkLaf());
				YPCWindow.getInstance();
			} catch (Exception e) {
				logger.fatal(e.getMessage(), e);
			}
		});
	}
}
