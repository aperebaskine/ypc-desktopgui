package com.pinguela.yourpc.desktop.view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.pinguela.yourpc.desktop.components.ImagePanel;
import com.pinguela.yourpc.model.Employee;

public class EmployeeItemView extends AbstractItemView<Employee> {
	public EmployeeItemView() {
		getViewPanel().setLayout(new BorderLayout(0, 0));
		
		ImagePanel imagePanel = new ImagePanel();
		GridBagLayout gridBagLayout = (GridBagLayout) imagePanel.getLayout();
		gridBagLayout.columnWidths = new int[]{320};
		gridBagLayout.columnWeights = new double[]{0.0};
		getViewPanel().add(imagePanel, BorderLayout.WEST);
		
		JPanel centerPanel = new JPanel();
		getViewPanel().add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0};
		gbl_centerPanel.rowHeights = new int[]{0};
		gbl_centerPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
		JPanel eastPanel = new JPanel();
		getViewPanel().add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel labelPanel = new JPanel();
		eastPanel.add(labelPanel, BorderLayout.NORTH);
		
		JLabel addressLabel = new JLabel("Address:");
		labelPanel.add(addressLabel);
		
		AddressItemView addressView = new AddressItemView();
		eastPanel.add(addressView, BorderLayout.CENTER);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5614891033513128985L;

	@Override
	public Employee getModifiedItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetFields() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setFieldsEditable(boolean isEditable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onItemSet() {
		// TODO Auto-generated method stub
		
	}

}
