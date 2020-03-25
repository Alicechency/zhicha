package com.ctbri.utils.dataimport.window;

import com.ctbri.utils.dataimport.event.ImportEvent;
import com.ctbri.utils.dataimport.event.SelectFileEvent;
import com.ctbri.utils.dataimport.event.UpdateConfigEvent;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JRadioButton;

/**
 * 主窗口
 * 
 * @author Hogan
 *
 */
public class MainWindow {

	private JFrame frame;
	private JComboBox<String> boxType;
	private JProgressBar progressBar;
	private JTextArea logInfo;
	private JTextField filePath;
	private File file;

	private JRadioButton initTableYes;
	private JRadioButton isProduceYes;
	private JRadioButton isDumpYes;
	private JRadioButton isPushESYes;
	private JTextField textForUsername;
	private JTextField textForPassword;

	/**
	 * Launch the application.
	 */
	// <module>ctbri-utils-dimport</module>
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("数据导入工具");
		frame.setBounds(450, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		// 数据导入TAB
		initializelayerDImport(tabbedPane);

		// 配置信息TAB
		initializeconfigInfo(tabbedPane);

		// 设置皮肤
		try {
			UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initializelayerDImport(JTabbedPane tabbedPane) {

		progressBar = new JProgressBar();
		filePath = new JTextField(40);
		logInfo = new JTextArea();
		boxType = new JComboBox<String>();

		// 数据导入TAB
		JPanel layerDImport = new JPanel();
		layerDImport.setLayout(null);
		layerDImport.setBackground(new Color(113, 191, 234)); // 淡蓝
		layerDImport.setFont(new Font(Font.SERIF, Font.BOLD, 18));

		tabbedPane.addTab("数据导入", null, layerDImport, null);

		// 标题组件
		JLabel textTitle = new JLabel("数据导入工具 v1.0");
		textTitle.setFont(new Font(Font.DIALOG, Font.BOLD, 28));
		textTitle.setBounds(170, 0, 400, 60);
		layerDImport.add(textTitle);

		// 第一行组件
		JLabel textType = new JLabel("类型");
		textType.setFont(new Font(Font.SERIF, Font.BOLD, 18));
		textType.setBounds(100, 50, 120, 120);
		layerDImport.add(textType);

		boxType.setBounds(300, 95, 200, 30);
		boxType.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
		boxType.addItem("警情");
		boxType.addItem("常控");
		layerDImport.add(boxType);

		// 第二行组件
		JLabel textSourcePath = new JLabel("数据文件位置");
		textSourcePath.setFont(new Font(Font.SERIF, Font.BOLD, 18));
		textSourcePath.setBounds(100, 113, 140, 120);
		layerDImport.add(textSourcePath);

		filePath.setBounds(300, 170, 250, 25);
		filePath.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
		filePath.setEditable(false);
		filePath.setBackground(Color.gray);
		layerDImport.add(filePath);

		// 第三行组件
		JLabel textProcessing = new JLabel("处理进度");
		textProcessing.setFont(new Font(Font.SERIF, Font.BOLD, 18));
		textProcessing.setBounds(100, 175, 120, 120);

		progressBar.setForeground(Color.GREEN);
		progressBar.setBounds(300, 225, 250, 20);
		layerDImport.add(progressBar);

		// 第四行组件
		JButton fileButton = new JButton("选择文件");
		fileButton.setFont(new Font(Font.SERIF, Font.BOLD, 18));
		fileButton.setBounds(100, 270, 120, 30);
		layerDImport.add(fileButton);

		JButton importButton = new JButton("开始导入");
		importButton.setFont(new Font(Font.SERIF, Font.BOLD, 18));
		importButton.setBounds(350, 270, 120, 30);
		layerDImport.add(importButton);
		layerDImport.add(textProcessing);

		// 第五行组件
		logInfo.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
		JScrollPane textLog = new JScrollPane(logInfo);
		textLog.setBounds(0, 330, 588, 112);
		textLog.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textLog.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		layerDImport.add(textLog);

		// 事件处理
		// 添加获取文件事件
		fileButton.addMouseListener(new SelectFileEvent(this));
		importButton.addMouseListener(new ImportEvent(this));

	}

	private void initializeconfigInfo(JTabbedPane tabbedPane) {

		JLayeredPane configInfo = new JLayeredPane();
		configInfo.setLayout(null);
		configInfo.setBackground(new Color(113, 191, 234)); // 淡蓝
		configInfo.setFont(new Font(Font.SERIF, Font.BOLD, 18));
		tabbedPane.addTab("配置信息", null, configInfo, null);

		// 标题组件
		JLabel textTitle = new JLabel("配置设置工具 v1.0");
		textTitle.setFont(new Font(Font.DIALOG, Font.BOLD, 28));
		textTitle.setBounds(170, 0, 400, 60);
		configInfo.add(textTitle);

		// 第八行组件
		JSeparator sepButton = new JSeparator();
		sepButton.setBounds(0, 100, 589, 110);
		configInfo.add(sepButton);

		JLabel textIsCreate = new JLabel("初始化表:");
		textIsCreate.setBounds(60, 120, 100, 30);
		textIsCreate.setFont(new Font(Font.SERIF, Font.BOLD, 18));
		configInfo.add(textIsCreate);

		ButtonGroup initTableGroup = new ButtonGroup();

		initTableYes = new JRadioButton("是");
		initTableYes.setBounds(170, 120, 50, 27);
		configInfo.add(initTableYes);

		JRadioButton initTableNo = new JRadioButton("否");
		initTableNo.setSelected(true);
		initTableNo.setBounds(219, 120, 50, 27);
		configInfo.add(initTableNo);

		initTableGroup.add(initTableYes);
		initTableGroup.add(initTableNo);

		JLabel textIsProduce = new JLabel("衍生数据:");
		textIsProduce.setBounds(301, 120, 100, 30);
		textIsProduce.setFont(new Font(Font.SERIF, Font.BOLD, 18));
		configInfo.add(textIsProduce);

		ButtonGroup isProduceGroup = new ButtonGroup();

		isProduceYes = new JRadioButton("是");
		isProduceYes.setBounds(411, 120, 50, 27);
		configInfo.add(isProduceYes);

		JRadioButton isProduceNo = new JRadioButton("否");
		isProduceNo.setSelected(true);
		isProduceNo.setBounds(459, 120, 50, 27);
		configInfo.add(isProduceNo);

		isProduceGroup.add(isProduceYes);
		isProduceGroup.add(isProduceNo);

		JLabel textIsDump = new JLabel("去重数据:");
		textIsDump.setBounds(60, 175, 100, 30);
		textIsDump.setFont(new Font(Font.SERIF, Font.BOLD, 18));
		configInfo.add(textIsDump);

		ButtonGroup isDumpGroup = new ButtonGroup();

		isDumpYes = new JRadioButton("是");
		isDumpYes.setBounds(170, 175, 50, 27);
		configInfo.add(isDumpYes);

		JRadioButton isDumpNo = new JRadioButton("否");
		isDumpNo.setSelected(true);
		isDumpNo.setBounds(219, 175, 50, 27);
		configInfo.add(isDumpNo);

		isDumpGroup.add(isDumpYes);
		isDumpGroup.add(isDumpNo);

		JLabel isPushES = new JLabel("推送到ES：");
		isPushES.setFont(new Font("Serif", Font.BOLD, 18));
		isPushES.setBounds(301, 175, 100, 30);
		configInfo.add(isPushES);

		ButtonGroup isPushESGroup = new ButtonGroup();

		isPushESYes = new JRadioButton("是");
		isPushESYes.setBounds(411, 175, 50, 27);
		configInfo.add(isPushESYes);

		JRadioButton isPushESNo = new JRadioButton("否");
		isPushESNo.setSelected(true);
		isPushESNo.setBounds(459, 175, 50, 27);
		configInfo.add(isPushESNo);

		isPushESGroup.add(isPushESYes);
		isPushESGroup.add(isPushESNo);

		// 第九行组件
		JButton commitButton = new JButton("更改配置");
		commitButton.setFont(new Font(Font.SERIF, Font.BOLD, 18));
		commitButton.setBounds(225, 374, 120, 30);
		configInfo.add(commitButton);

		JSeparator separator = new JSeparator();
		separator.setToolTipText("");
		separator.setBounds(0, 245, 589, 12);
		configInfo.add(separator);

		JLabel labelForUsername = new JLabel("用户名:");
		labelForUsername.setFont(new Font("Serif", Font.BOLD, 18));
		labelForUsername.setBounds(60, 260, 100, 30);
		configInfo.add(labelForUsername);

		textForUsername = new JTextField();
		textForUsername.setFont(new Font("宋体", Font.BOLD, 18));
		textForUsername.setText("root");
		textForUsername.setBounds(170, 260, 100, 30);
		configInfo.add(textForUsername);
		textForUsername.setColumns(10);

		JLabel labelForPassword = new JLabel("密码:");
		labelForPassword.setFont(new Font("Serif", Font.BOLD, 18));
		labelForPassword.setBounds(301, 260, 100, 30);
		configInfo.add(labelForPassword);

		textForPassword = new JTextField();
		textForPassword.setText("root");
		textForPassword.setFont(new Font("宋体", Font.BOLD, 18));
		textForPassword.setColumns(10);
		textForPassword.setBounds(411, 260, 100, 30);
		configInfo.add(textForPassword);

		JLabel label_1 = new JLabel("一般配置");
		label_1.setFont(new Font("宋体", Font.BOLD, 18));
		label_1.setBounds(14, 75, 100, 27);
		configInfo.add(label_1);

		JLabel label_2 = new JLabel("数据库配置");
		label_2.setFont(new Font("宋体", Font.BOLD, 18));
		label_2.setBounds(14, 223, 100, 18);
		configInfo.add(label_2);

		// 添加提交事件
		commitButton.addMouseListener(new UpdateConfigEvent(this));
	}

	public JComboBox<String> getBoxType() {
		return boxType;
	}

	public File getFile() {
		return file;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public JTextArea getLogInfo() {
		return logInfo;
	}

	public JTextField getFilePath() {
		return filePath;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public JTextField getTextForUsername() {
		return textForUsername;
	}

	public JTextField getTextForPassword() {
		return textForPassword;
	}

	public JRadioButton getInitTableYes() {
		return initTableYes;
	}

	public JRadioButton getIsProduceYes() {
		return isProduceYes;
	}

	public JRadioButton getIsDumpYes() {
		return isDumpYes;
	}

	public JRadioButton getIsPushESYes() {
		return isPushESYes;
	}

}
