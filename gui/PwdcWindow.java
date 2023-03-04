package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.Account;
import dao.AccountDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
/**
 * 密码修改窗口
 * @author Kyle
 *
 */
public class PwdcWindow extends JFrame {

	private JPanel contentPane;
	private JPasswordField oldpassword;
	private JPasswordField newpassword;
	private JPasswordField passwordRepeat;
	private Account user;
	private MainWindow mWin;

	public PwdcWindow(MainWindow mWin, Account user) {
		this();
		this.user = user;
		this.mWin = mWin;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PwdcWindow frame = new PwdcWindow();
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
	public PwdcWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PwdcWindow.class.getResource("/images/Bank1.png")));
		setTitle("\u5EFA\u8BBE\u94F6\u884C\u2014\u5BC6\u7801\u4FEE\u6539");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u539F\u5BC6\u7801\uFF1A");
		label.setFont(new Font("微软雅黑 Light", Font.PLAIN, 17));
		label.setBounds(23, 40, 140, 40);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u8BF7\u8F93\u5165\u65B0\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 17));
		label_1.setBounds(23, 90, 140, 40);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u518D\u6B21\u786E\u8BA4\u65B0\u5BC6\u7801\uFF1A");
		label_2.setFont(new Font("微软雅黑 Light", Font.PLAIN, 17));
		label_2.setBounds(10, 140, 140, 40);
		contentPane.add(label_2);

		oldpassword = new JPasswordField();
		oldpassword.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		oldpassword.setBounds(161, 40, 250, 36);
		contentPane.add(oldpassword);

		newpassword = new JPasswordField();
		newpassword.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		newpassword.setBounds(161, 90, 250, 36);
		contentPane.add(newpassword);

		passwordRepeat = new JPasswordField();
		passwordRepeat.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		passwordRepeat.setBounds(161, 140, 250, 36);
		contentPane.add(passwordRepeat);

		JButton back = new JButton("\u8FD4\u56DE");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PwdcWindow.this.setVisible(false);
				mWin.setVisible(true);
			}
		});
		back.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		back.setBounds(47, 210, 122, 47);
		contentPane.add(back);

		JButton confirm = new JButton("\u786E\u8BA4");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oldPassword = oldpassword.getText();
				String newPassword1 = newpassword.getText();
				String newPassword2 = passwordRepeat.getText();

				if (oldPassword.equals("") || newPassword1.equals("") || newPassword2.equals("")) {
					JOptionPane.showMessageDialog(null, "旧密码与新密码不可为空！", "系统提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// 判断两个新密码是否相同
				if (!newPassword1.equals(newPassword2)) {
					JOptionPane.showMessageDialog(null, "确认密码不同！", "系统提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// 判断旧密码是否正确
				if (!oldPassword.equals(user.getPassword())) {
					JOptionPane.showMessageDialog(null, "原始密码错误！", "系统提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// 修改密码
				AccountDao accountDao = new AccountDao();
				boolean pwdChange = accountDao.PwdChange(user.getName(), newPassword1);
				if (pwdChange) {
					JOptionPane.showMessageDialog(null, "密码修改成功，请重新登录！", "系统提示", JOptionPane.PLAIN_MESSAGE);
					PwdcWindow.this.setVisible(false);
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
					return;
				} else {
					JOptionPane.showMessageDialog(null, "密码修改失败，请稍后再试！", "系统提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		confirm.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		confirm.setBounds(250, 210, 122, 47);
		contentPane.add(confirm);
		
		JButton button = new JButton("清空");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oldpassword.setText(null);
				newpassword.setText(null);
				passwordRepeat.setText(null);
			}
		});
		button.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		button.setBounds(344, 10, 80, 25);
		contentPane.add(button);
	}
}
