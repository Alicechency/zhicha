package com.ctbri.utils.dataimport.event;

import com.ctbri.utils.dataimport.core.GlobalProperty;
import com.ctbri.utils.dataimport.window.MainWindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 更改配置文件事件
 * 
 * @author Hogan
 *
 */
public class UpdateConfigEvent extends MouseAdapter {

	private MainWindow mainWindow;
	private GlobalProperty globalProperty = GlobalProperty.newInstance();

	public UpdateConfigEvent(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		globalProperty.setInit(mainWindow.getInitTableYes().isSelected());
		globalProperty.setProduce(mainWindow.getIsProduceYes().isSelected());
		globalProperty.setIsDump(mainWindow.getIsDumpYes().isSelected());
		globalProperty.setPushES(mainWindow.getIsPushESYes().isSelected());
		String username = mainWindow.getTextForUsername().getText();
		String password = mainWindow.getTextForPassword().getText();
		globalProperty.setConfig(true);
		GlobalProperty.refreshConfig(username, password);
	}

}