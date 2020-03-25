package com.ctbri.utils.dataimport.window;

import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class MessageDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public MessageDialog(MainWindow mainWindow) {
		super();
		Container conn = getContentPane();
		conn.add(new JLabel("test lable"));
		setBounds(100, 100, 100, 100);
	}
}
