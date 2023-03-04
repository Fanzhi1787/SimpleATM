package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.*;
import dao.*;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;


public class QueryWindow extends JFrame {
	

	private JPanel contentPane;
	private MainWindow mWin;
	private Account user;
	private JLabel Yuer;

	QueryWindow(MainWindow mWin,Account user){
		this();
    	this.user = user;
    	this.mWin = mWin ;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QueryWindow frame = new QueryWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QueryWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(QueryWindow.class.getResource("/images/Bank1.png")));
		setTitle("\u6B22\u8FCE\u4F7F\u7528\u5EFA\u8BBE\u94F6\u884CATM");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				setTitle("欢迎" + user.getName() + "使用建设银行");
				AccountDao userDao = new AccountDao();
				user = userDao.getByName(user.getName());
				Yuer.setText(String.valueOf(user.getMoney()));
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u60A8\u7684\u4F59\u989D\u4E3A\uFF1A");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 23));
		label.setBounds(28, 40, 150, 35);
		contentPane.add(label);
		
		Yuer = new JLabel("0");
		Yuer.setForeground(new Color(50, 205, 50));
		Yuer.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		Yuer.setBounds(188, 42, 150, 35);
		contentPane.add(Yuer);
		
		JButton button = new JButton("\u8FD4\u56DE");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QueryWindow.this.setVisible(false);
				mWin.setVisible(true);
			}
		});
		button.setBounds(28, 127, 120, 45);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u67E5\u8BE2\u4F1A\u5458\u7B49\u7EA7");
		button_1.setBackground(Color.ORANGE);
		button_1.setForeground(Color.RED);
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int money =	user.getMoney();
				int result = actualMoney(money);
				if(result == -2){
					JOptionPane.showMessageDialog(QueryWindow.this,"您的存款超过1000000！，您是黑金至尊Vip！");
				}else if(result == -3){
					JOptionPane.showMessageDialog(QueryWindow.this,"鉴于您的存款额度，您的身份为钻石vip！");
				}else if(result == -4){
					JOptionPane.showMessageDialog(QueryWindow.this,"鉴于您的存款额度，您的身份为黄金vip！");
				}else if(result == -5){
					JOptionPane.showMessageDialog(QueryWindow.this,"鉴于您的存款额度，您的身份为白银vip！");
				}else if(result == -6){
					JOptionPane.showMessageDialog(QueryWindow.this,"您的存款在1000元以下，您的身份为塑料用户");
				}
			}
		});
		button_1.setBounds(217, 118, 150, 60);
		contentPane.add(button_1);
	}
	//判断会员等级
	int actualMoney(int money) {
		AccountDao userDao = new AccountDao();
		user = userDao.getByName(user.getName());
		 int actual =user.getMoney();
			int res = -1;
			if(actual > 10000000){
				return res = -2;
			}else if(actual >= 1000000 && actual <= 10000000) {
				return res = -3;
			}else if(actual >= 100000 && actual <= 1000000) {
				return res = -4;
			}else if(actual >= 10000 && actual <= 100000){
				return res = -5;
			}else if(actual <= 1000) {
				return res = -6;
			}
			return res;
	}
}
