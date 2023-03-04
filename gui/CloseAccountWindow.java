package gui;
/**
 * 注销账户窗口
 * @author Kyle
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.Account;
import dao.AccountDao;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CloseAccountWindow extends JFrame {

	private JPanel contentPane;
	private Account  user ;
	private MainWindow mWin ;
	private JTextField confirmText;
	private JTextField passwordText;
	private JTextField accountText;
	private JButton confirm;
	private JButton back;
	private LoginWindow lwin;
	
	public  CloseAccountWindow(MainWindow mWin,Account user) {
		this();
		this.mWin =mWin;
		this.user = user;

	}
	//添加注销成功返回登录界面
	public  CloseAccountWindow(LoginWindow lwin) {
		this();
		this.lwin = lwin;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CloseAccountWindow frame = new CloseAccountWindow();
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
	public CloseAccountWindow() {
		setTitle("\u611F\u8C22\u966A\u4F34\uFF0C\u6709\u7F18\u518D\u4F1A\u3002");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CloseAccountWindow.class.getResource("/images/Bank1.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u60A8\u7684\u8D26\u6237\uFF1A");
		label.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		label.setBounds(10, 45, 150, 30);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u8BF7\u8F93\u5165\u60A8\u7684\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		label_1.setBounds(10, 100, 150, 30);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u8BF7\u518D\u6B21\u8F93\u5165\u5BC6\u7801\uFF1A");
		label_2.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		label_2.setBounds(10, 160, 150, 30);
		contentPane.add(label_2);
		
		accountText = new JTextField();
		accountText.setBounds(170, 45, 200, 35);
		contentPane.add(accountText);
		accountText.setColumns(10);
		
		passwordText = new JTextField();
		passwordText.setColumns(10);
		passwordText.setBounds(170, 100, 200, 35);
		contentPane.add(passwordText);
		
		confirmText = new JTextField();
		confirmText.setColumns(10);
		confirmText.setBounds(170, 160, 200, 35);
		contentPane.add(confirmText);
		
		back = new JButton("\u8FD4\u56DE");
		back.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mWin.setVisible(true);
				CloseAccountWindow.this.setVisible(false);
			}
		});
		back.setBounds(51, 220, 100, 40);
		contentPane.add(back);
		
		confirm = new JButton("\u786E\u8BA4");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = accountText.getText();
				String p = passwordText.getText();
				String rp = confirmText.getText();
				//执行条件 ,并增加重复确认窗口。
				int res = JOptionPane.showConfirmDialog(null,"(⋟﹏⋞)您确定要注销您的建设银行账户吗","系统提示",JOptionPane.YES_NO_OPTION);
					if(res == JOptionPane.YES_OPTION){
						if (a.equals("") || p.equals("")) {
							JOptionPane.showMessageDialog(null, "账户或密码不可为空！", "系统提示", JOptionPane.ERROR_MESSAGE);
							return;
						}if (rp.equals("")) {
							JOptionPane.showMessageDialog(null, "请输入确认密码！", "系统提示", JOptionPane.ERROR_MESSAGE);
							return;
						}if (!p.equals(rp)) {
							JOptionPane.showMessageDialog(null, "确认密码不同！", "系统提示", JOptionPane.ERROR_MESSAGE);
							return;
						}
						// 注销账户
						AccountDao accountDao = new AccountDao();
						boolean  Close = accountDao.Close(a);
						if (Close) {
							JOptionPane.showMessageDialog(null, "(T＿T)感谢您一直以来对建设银行的支持,有缘再会！", "系统提示", JOptionPane.PLAIN_MESSAGE);
							//若成功，返回登录界面
							CloseAccountWindow.this.setVisible(false);
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										LoginWindow frame = new LoginWindow();
										frame.setVisible(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
						} 
						}
					}
				});
		confirm.setForeground(Color.RED);
		confirm.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		confirm.setBounds(256, 220, 100, 40);
		contentPane.add(confirm);
		
		JButton clear = new JButton("清空");
		clear.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountText.setText(null);
				passwordText.setText(null);
				confirmText.setText(null);
			}
		});
		clear.setBounds(344, 10, 80, 25);
		contentPane.add(clear);
	}
					
}
