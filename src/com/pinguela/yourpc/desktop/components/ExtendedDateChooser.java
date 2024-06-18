package com.pinguela.yourpc.desktop.components;

import java.util.Date;

import javax.swing.JTextField;

import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class ExtendedDateChooser 
extends JDateChooser 
implements YPCComponent {

	public ExtendedDateChooser() {
		super();
	}

	public ExtendedDateChooser(Date arg0, String arg1, IDateEditor arg2) {
		super(arg0, arg1, arg2);
	}

	public ExtendedDateChooser(Date arg0, String arg1) {
		super(arg0, arg1);
	}

	public ExtendedDateChooser(Date arg0) {
		super(arg0);
	}

	public ExtendedDateChooser(IDateEditor arg0) {
		super(arg0);
	}

	public ExtendedDateChooser(JCalendar arg0, Date arg1, String arg2, IDateEditor arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ExtendedDateChooser(String arg0, String arg1, char arg2) {
		super(arg0, arg1, arg2);
	}

	public void setEditable(boolean isEditable) {
		
		JTextField dateEditor = (JTextField) getDateEditor();
		
		if (dateEditor != null) {
			dateEditor.setEditable(isEditable);
		}
		getCalendarButton().setEnabled(isEditable);
	}
	
}
