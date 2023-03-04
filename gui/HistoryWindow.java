package gui;
/**
 * 交易记录窗口
* @author Kyle
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.History;
import bean.Account;
import dao.HistoryDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.Color;


public class HistoryWindow extends JFrame {

	private JPanel contentPane;
	
	Account user;
	MainWindow mWin;
	private JTextArea Record;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoryWindow frame = new HistoryWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HistoryWindow(MainWindow mWin,Account user) {
		this();
		this.mWin = mWin;
		this.user = user;
	}
	
	/**
	 * Create the frame.
	 */
	public HistoryWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(HistoryWindow.class.getResource("/images/Bank1.png")));
		setTitle("\u4EA4\u6613\u8BB0\u5F55");
		addWindowListener(new WindowAdapter() {
			@Override
			//显示交易记录
			public void windowActivated(WindowEvent e) { 
			setTitle("欢迎" +user.getName()+"使用建设银行");
			HistoryDao jyd = new HistoryDao();
			ArrayList<History> ls = jyd.getHistouy (user.getId());
			for(History tmp:ls){
			//对齐
			String str = " " + tmp.getType()+"            "+tmp. getMoney()+"                 "+tmp.getTime() + "\n";
			System.out.println(str);	
			Record.append(str);
			}
			
			}});
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u64CD\u4F5C\u7C7B\u578B\uFF1A");
		label.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		label.setBounds(25, 30, 84, 29);
		contentPane.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("\u91D1\u989D\uFF1A");
		lblNewLabel_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(150, 30, 62, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("\u65F6\u95F4\uFF1A");
		label_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		label_1.setBounds(245, 30, 84, 30);
		contentPane.add(label_1);
		
		JButton back = new JButton("\u8FD4\u56DE");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HistoryWindow.this.setVisible(false);
				mWin.setVisible(true);
			}
		});
		back.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		back.setBounds(70, 245, 115, 45);
		contentPane.add(back);
		
		JButton exit = new JButton("\u9000\u51FA");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(HistoryWindow.this, "确定退出系统");
				if(res == JOptionPane.YES_OPTION){
				   System.exit(0);
			}}
		});
		exit.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		exit.setBounds(317, 245, 115, 45);
		contentPane.add(exit);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(89, 65, 370, 170);
		contentPane.add(scrollPane);
		
		Record = new JTextArea();
		Record.setFont(new Font("微软雅黑 Light", Font.PLAIN, 12));
		scrollPane.setViewportView(Record);
		
		JButton button = new JButton("\u6E05\u7A7A\u8BB0\u5F55");
		button.setForeground(Color.RED);
		button.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HistoryDao JYD = new HistoryDao();
				boolean  Clear = JYD.ClearHistory(user.getId());
				if (Clear) {
					JOptionPane.showMessageDialog(null, "历史记录已清空", "系统提示", JOptionPane.PLAIN_MESSAGE);

			Record.paintImmediately(Record.getBounds());
				}
				HistoryDao jyd = new HistoryDao();
				History jy = new History();
				jy.setType("清空记录");
				jy.setMoney(user.getMoney());
				jy.setAccountID(user.getId());
				jy.setTime( new Timestamp(System.currentTimeMillis()));
				boolean b = jyd.addHistoryRecord(jy);
				//返回主窗口
		
			}
		});
		button.setBounds(370, 15, 90, 30);
		contentPane.add(button);
	}
}
