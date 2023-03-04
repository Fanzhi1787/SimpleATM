package gui;
/**
 * 创建账户窗口
 * @author by Kyle
 */
import java.awt.EventQueue;


import dao.*;
import bean.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateAccountWindow extends JFrame {

	private Account  user ;
	private LoginWindow lwin ;
	private JPanel contentPane;
	private JTextField accountText;
	private JTextField passwordText;
	private JTextField confirmText;
	
	
	public CreateAccountWindow(LoginWindow lwin,Account user){
		this();   		
		this.user = user;
		this.lwin = lwin ;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccountWindow frame = new CreateAccountWindow();
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
	public CreateAccountWindow() {
		setTitle("\u521B\u5EFA\u4E00\u4E2A\u65B0\u7684\u5EFA\u8BBE\u94F6\u884C\u7F51\u94F6\u8D26\u6237\uFF01");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CreateAccountWindow.class.getResource("/images/Bank1.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u65B0\u5EFA\u8D26\u6237:");
		label.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		label.setBounds(50, 40, 90, 40);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u8D26\u6237\u5BC6\u7801:");
		label_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		label_1.setBounds(50, 95, 90, 40);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u518D\u6B21\u8F93\u5165\u5BC6\u7801\uFF1A");
		label_2.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		label_2.setBounds(20, 155, 140, 40);
		contentPane.add(label_2);
		
		accountText = new JTextField();
		accountText.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		accountText.setBounds(150, 45, 230, 35);
		contentPane.add(accountText);
		accountText.setColumns(10);
		
		passwordText = new JTextField();
		passwordText.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		passwordText.setColumns(10);
		passwordText.setBounds(150, 100, 230, 35);
		contentPane.add(passwordText);
		
		confirmText = new JTextField();
		confirmText.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		confirmText.setColumns(10);
		confirmText.setBounds(150, 160, 230, 35);
		contentPane.add(confirmText);
		
		JButton back = new JButton("\u8FD4\u56DE");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lwin.setVisible(true);
				CreateAccountWindow.this.setVisible(false);
			}
		});
		back.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		back.setBounds(70, 215, 90, 40);
		contentPane.add(back);
		//rp.equals("")
		JButton confirm = new JButton("\u786E\u8BA4");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = accountText.getText();
				String p = passwordText.getText();
				String rp = confirmText.getText();
				//执行条件
				if (a.equals("") || p.equals("")) {
					JOptionPane.showMessageDialog(null, "账户或密码不可为空！", "系统提示", JOptionPane.ERROR_MESSAGE);
					return;
				}if (rp.equals("")) {
					JOptionPane.showMessageDialog(null, "请输入确认密码！", "系统提示", JOptionPane.ERROR_MESSAGE);
					return;
				}if (!p.equals(rp)) {
					JOptionPane.showMessageDialog(null, "确认密码不同！", "系统提示", JOptionPane.ERROR_MESSAGE);
					return;
				}// 创建账户
				AccountDao accountDao = new AccountDao();
				boolean  Regist = accountDao.Regist(a, p);
				if (Regist) {
					JOptionPane.showMessageDialog(null, "账户创建成功！", "系统提示", JOptionPane.PLAIN_MESSAGE);
				
				}else {
					JOptionPane.showMessageDialog(null, "账户创建失败！", "系统提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			}
		});
		confirm.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		confirm.setBounds(263, 215, 90, 40);
		contentPane.add(confirm);
		
		JButton button = new JButton("\u6E05\u7A7A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountText.setText(null);
				passwordText.setText(null);
				confirmText.setText(null);
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button.setBounds(330, 10, 90, 25);
		contentPane.add(button);
	}
}
