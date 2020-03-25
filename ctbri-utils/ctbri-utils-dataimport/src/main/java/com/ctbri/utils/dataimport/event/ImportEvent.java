package com.ctbri.utils.dataimport.event;

import com.ctbri.utils.dataimport.util.Consts;
import com.ctbri.utils.dataimport.util.ScriptUtil;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import com.ctbri.utils.dataimport.core.DataImport;
import com.ctbri.utils.dataimport.core.GlobalProperty;
import com.ctbri.utils.dataimport.window.MainWindow;
import com.ctbri.utils.dataimport.window.MessageDialog;

/**
 * 数据导入事件
 * 
 * @author Hogan
 *
 */
public class ImportEvent extends MouseAdapter {

	private MainWindow mainWindow;
	private long lastCount;
	private long lastSumRows;
	private int lastPercentage;
	private boolean done;
	private GlobalProperty globalProperty = GlobalProperty.newInstance();

	public ImportEvent(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (globalProperty.isConfig()) {
			DataImport dataImport = new DataImport(mainWindow.getFile().getAbsolutePath(),
					mainWindow.getBoxType().getSelectedIndex());
			new SwingWorker<List<Long>, Long>() {
				JTextArea logInfo = mainWindow.getLogInfo();
				
				// 处理耗时任务
				@Override
				protected List<Long> doInBackground() throws Exception {
					List<Long> list = new ArrayList<>();
					new Thread() {
						public void run() {
							logInfo.removeAll();
							if (globalProperty.isInit()) {
								logInfo.setText("开始初始化表信息");
								ScriptUtil.exceuteScript(Consts.SCRIPT_CREATE_NAME);
								logInfo.setText(logInfo.getText() + "\r\n" + "初始化表信息完成!");
							}
							logInfo.setText("开始导入数据");
							dataImport.dimport();
							if (globalProperty.isProduce()) {
								logInfo.setText("开始衍生数据");
								ScriptUtil.exceuteScript(Consts.SCRIPT_PRODUCE_NAME);
							}
							done = true;
						}
					}.start();
					long count = dataImport.getAlreadyHandleCount();
					long sumRow = dataImport.getSumRows();
					while (count <= sumRow && !done) {
						publish(count);
						count = dataImport.getAlreadyHandleCount();
						sumRow = dataImport.getSumRows();
					}
					return list;
				}

				// 处理中间数据
				@Override
				protected void process(List<Long> chunks) {
					long currentCount = chunks.get(chunks.size() - 1);
					if (lastPercentage >= 50) {
						dataImport.setSumRows(lastSumRows + (currentCount - lastCount));
					}
					long sumRow = dataImport.getSumRows();
					int percentage = (int) ((currentCount * 100) / sumRow);
					if (done) {
						mainWindow.getProgressBar().setValue(100);
					} else {
						mainWindow.getProgressBar().setValue(percentage);
					}
					if (currentCount > 0 && lastCount != currentCount) {
						logInfo.setText(logInfo.getText() + "\r\n" + "已导入" + currentCount + "条数据");
						lastCount = currentCount;
						lastSumRows = sumRow;
						lastPercentage = percentage;
					}
					super.process(chunks);
				}

				// 处理结束操作
				@Override
				protected void done() {
					done = false;
					logInfo.setText(logInfo.getText() + "\r\n" + "数据导入完毕！");
					super.done();
				}
			}.execute();
		} else {
			new MessageDialog(mainWindow).setVisible(true);
		}
	}

}