package com.ctbri.utils.dataimport.event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

import com.ctbri.utils.dataimport.window.MainWindow;

/**
 * 选择文件事件
 * 
 * @author Hogan
 *
 */
public class SelectFileEvent extends MouseAdapter {

	private MainWindow mainWindow;

	public SelectFileEvent(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir")); // 从当前目录下选文件
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // 只选择文件
		int returnVal = fileChooser.showDialog(new JLabel(), "选择数据文件");
		if (returnVal == JFileChooser.APPROVE_OPTION) { // 如果符合文件类型
			mainWindow.setFile(fileChooser.getSelectedFile());
			mainWindow.getFilePath().setText(mainWindow.getFile().getAbsolutePath());
		}
	}

}
