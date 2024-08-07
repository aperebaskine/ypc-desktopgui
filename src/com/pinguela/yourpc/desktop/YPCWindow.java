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
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.pinguela.yourpc.desktop.actions.OpenAttributeStatisticsTabAction;
import com.pinguela.yourpc.desktop.actions.OpenCustomerOrderSearchTabAction;
import com.pinguela.yourpc.desktop.actions.OpenCustomerSearchTabAction;
import com.pinguela.yourpc.desktop.actions.OpenCustomerViewDialogAction;
import com.pinguela.yourpc.desktop.actions.OpenEmployeeSearchTabAction;
import com.pinguela.yourpc.desktop.actions.OpenEmployeeViewDialogAction;
import com.pinguela.yourpc.desktop.actions.OpenProductSearchTabAction;
import com.pinguela.yourpc.desktop.actions.OpenProductTimelineTabAction;
import com.pinguela.yourpc.desktop.actions.OpenProductViewDialogAction;
import com.pinguela.yourpc.desktop.actions.OpenRMASearchTabAction;
import com.pinguela.yourpc.desktop.actions.OpenStatisticsTabAction;
import com.pinguela.yourpc.desktop.actions.OpenTicketSearchTabAction;
import com.pinguela.yourpc.desktop.actions.OpenTicketViewDialogAction;
import com.pinguela.yourpc.desktop.actions.OpenUserPopupMenuAction;
import com.pinguela.yourpc.desktop.components.CloseableTabComponent;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.dialog.LoginDialog;
import com.pinguela.yourpc.desktop.dialog.YPCDialog;
import com.pinguela.yourpc.desktop.util.FormattingUtils;
import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.model.Employee;

public class YPCWindow {

	private static final String DARK_LAF_CLASS_NAME = "com.formdev.flatlaf.FlatDarkLaf";

	public static final String AUTHENTICATED_USER_PROPERTY = "authenticatedUser";

	private static Logger logger = LogManager.getLogger();
	
	// TODO: Rewrite implementation
	private static final Map<String, List<String>> PERMISSION_MAP;
	
	static {
		Map<String, List<String>> permissionMap = new HashMap<String, List<String>>();
		permissionMap.put("product", Arrays.asList("EXC", "FIN", "MKT", "OPS", "SAL", "SUP"));
		permissionMap.put("customer", Arrays.asList("EXC", "SAL", "SUP"));
		permissionMap.put("employee", Arrays.asList("EXC", "HRS"));
		permissionMap.put("customerOrder", Arrays.asList("EXC", "SUP", "OPS"));
		permissionMap.put("ticket", Arrays.asList("EXC", "SUP"));
		permissionMap.put("rma", Arrays.asList("EXC", "SUP"));
		permissionMap.put("statistics", Arrays.asList("EXC", "FIN", "MKT", "OPS"));
		PERMISSION_MAP = Collections.unmodifiableMap(permissionMap);
	}

	// Singleton
	private static YPCWindow instance = null; 

	private Employee authenticatedUser;
	private final PropertyChangeListener authenticationListener = (evt) -> {
		authenticatedUser = (Employee) evt.getNewValue();
		((Window) SwingUtilities.getWindowAncestor((Component) evt.getSource())).dispose();
		onSuccessfulAuthentication();
	};

	private JFrame frame;

	private JButton userMenuButton;
	private JTabbedPane tabbedPane;

	private JMenu productMenu;
	private JMenu customerMenu;
	private JMenu employeeMenu;
	private JMenu customerOrderMenu;
	private JMenu ticketMenu;
	private JMenu rmaMenu;

	private JButton productTabButton;
	private JButton customerTabButton;
	private JButton employeeTabButton;
	private JButton customerOrderTabButton;
	private JButton ticketTabButton;
	private JButton rmaTabButton;
	private JMenu statisticsMenu;
	private JButton statisticsTabButton;

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

		productMenu = new JMenu("Products");
		menuBar.add(productMenu);
		
		JMenuItem createProductMenuItem = new JMenuItem(new OpenProductViewDialogAction());
		productMenu.add(createProductMenuItem);

		JMenuItem productSearchMenuItem = new JMenuItem("Search...");
		productMenu.add(productSearchMenuItem);
		
		customerMenu = new JMenu("Customers");
		menuBar.add(customerMenu);
		
		JMenuItem createCustomerMenuItem = new JMenuItem(new OpenCustomerViewDialogAction());
		customerMenu.add(createCustomerMenuItem);
		
		JMenuItem customerSearchMenuItem = new JMenuItem("Search...");
		customerMenu.add(customerSearchMenuItem);
		
		employeeMenu = new JMenu("Employees");
		menuBar.add(employeeMenu);
		
		JMenuItem createEmployeeMenuItem = new JMenuItem(new OpenEmployeeViewDialogAction());
		employeeMenu.add(createEmployeeMenuItem);
		
		JMenuItem employeeSearchMenuItem = new JMenuItem("Search...");
		employeeMenu.add(employeeSearchMenuItem);
		
		customerOrderMenu = new JMenu("Orders");
		menuBar.add(customerOrderMenu);
		
		JMenuItem orderSearchMenuItem = new JMenuItem("Search...");
		customerOrderMenu.add(orderSearchMenuItem);
		
		ticketMenu = new JMenu("Tickets");
		menuBar.add(ticketMenu);
		
		JMenuItem createTicketMenuItem = new JMenuItem(new OpenTicketViewDialogAction());
		ticketMenu.add(createTicketMenuItem);
		
		JMenuItem ticketSearchMenuItem = new JMenuItem("Search...");
		ticketMenu.add(ticketSearchMenuItem);
		
		rmaMenu = new JMenu("RMAs");
		menuBar.add(rmaMenu);
		
		JMenuItem rmaSearchMenuItem = new JMenuItem("Search...");
		rmaMenu.add(rmaSearchMenuItem);
		
		statisticsMenu = new JMenu("Statistics");
		menuBar.add(statisticsMenu);
		
		JMenuItem statisticsMenuItem = new JMenuItem("Statistics");
		statisticsMenu.add(statisticsMenuItem);
		
		JMenuItem productStatisticsMenuItem = new JMenuItem("Product statistics");
		statisticsMenu.add(productStatisticsMenuItem);
		
		JMenuItem attributeStatisticsMenuItem = new JMenuItem("Attribute statistics");
		statisticsMenu.add(attributeStatisticsMenuItem);

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

		productTabButton = new JButton(Icons.PRODUCT_ICON);
		toolBar.add(productTabButton);
		
		customerTabButton = new JButton(Icons.USER_ICON);
		toolBar.add(customerTabButton);
		
		employeeTabButton = new JButton(Icons.USER_ICON);
		toolBar.add(employeeTabButton);
		
		customerOrderTabButton = new JButton(Icons.DOCUMENT_ICON);
		toolBar.add(customerOrderTabButton);
		
		ticketTabButton = new JButton(Icons.TICKET_ICON);
		toolBar.add(ticketTabButton);
		
		rmaTabButton = new JButton(Icons.RMA_ICON);
		toolBar.add(rmaTabButton);
		
		statisticsTabButton = new JButton(Icons.CHART_ICON);
		toolBar.add(statisticsTabButton);

		JToolBar userMenuToolBar = new JToolBar();
		GridBagConstraints gbc_userMenuToolBar = new GridBagConstraints();
		gbc_userMenuToolBar.gridx = 1;
		gbc_userMenuToolBar.gridy = 0;
		panel.add(userMenuToolBar, gbc_userMenuToolBar);

		userMenuButton = new JButton(new OpenUserPopupMenuAction());
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
		
		ActionListener productSearchActionListener = new OpenProductSearchTabAction();
		ActionListener customerSearchActionListener = new OpenCustomerSearchTabAction();
		ActionListener employeeSearchActionListener = new OpenEmployeeSearchTabAction();
		ActionListener customerOrderSearchActionListener = new OpenCustomerOrderSearchTabAction();
		ActionListener ticketSearchActionListener = new OpenTicketSearchTabAction();
		ActionListener rmaSearchActionListener = new OpenRMASearchTabAction();
		ActionListener statisticsActionListener = new OpenStatisticsTabAction();

		productTabButton.addActionListener(productSearchActionListener);
		customerTabButton.addActionListener(customerSearchActionListener);
		employeeTabButton.addActionListener(employeeSearchActionListener);
		customerOrderTabButton.addActionListener(customerOrderSearchActionListener);
		ticketTabButton.addActionListener(ticketSearchActionListener);
		rmaTabButton.addActionListener(rmaSearchActionListener);
		statisticsTabButton.addActionListener(statisticsActionListener);

		productSearchMenuItem.addActionListener(productSearchActionListener);
		customerSearchMenuItem.addActionListener(customerSearchActionListener);
		employeeSearchMenuItem.addActionListener(employeeSearchActionListener);
		orderSearchMenuItem.addActionListener(customerOrderSearchActionListener);
		ticketSearchMenuItem.addActionListener(ticketSearchActionListener);
		rmaSearchMenuItem.addActionListener(rmaSearchActionListener);
		statisticsMenuItem.addActionListener(statisticsActionListener);
		productStatisticsMenuItem.addActionListener(new OpenProductTimelineTabAction());
		attributeStatisticsMenuItem.addActionListener(new OpenAttributeStatisticsTabAction());
	}

	public Employee getAuthenticatedUser() {
		return authenticatedUser;
	}

	public void setPermissions() {
		String department = authenticatedUser.getDepartmentHistory().get(0).getDepartmentId();

	    for (Map.Entry<String, List<String>> entry : PERMISSION_MAP.entrySet()) {
	        String entity = entry.getKey();
	        List<String> allowedDepartments = entry.getValue();

	        boolean hasPermission = allowedDepartments.contains(department);

	        switch (entity) {
	            case "product":
	                productMenu.setVisible(hasPermission);
	                productTabButton.setVisible(hasPermission);
	                break;
	            case "customer":
	                customerMenu.setVisible(hasPermission);
	                customerTabButton.setVisible(hasPermission);
	                break;
	            case "employee":
	                employeeMenu.setVisible(hasPermission);
	                employeeTabButton.setVisible(hasPermission);
	                break;
	            case "customerOrder":
	                customerOrderMenu.setVisible(hasPermission);
	                customerOrderTabButton.setVisible(hasPermission);
	                break;
	            case "ticket":
	                ticketMenu.setVisible(hasPermission);
	                ticketTabButton.setVisible(hasPermission);
	                break;
	            case "rma":
	                rmaMenu.setVisible(hasPermission);
	                rmaTabButton.setVisible(hasPermission);
	                break;
	            case "statistics":
	            	statisticsMenu.setVisible(hasPermission);
	            	statisticsTabButton.setVisible(hasPermission);
	            	break;
	            default:
	                throw new IllegalArgumentException("Unrecognized role.");
	        }
	    }
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
		userMenuButton.setText(FormattingUtils.formatFullName(authenticatedUser));
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
				UIManager.setLookAndFeel(DARK_LAF_CLASS_NAME);
				YPCWindow.getInstance();
			} catch (Exception e) {
				logger.fatal(e.getMessage(), e);
			}
		});
	}
}
