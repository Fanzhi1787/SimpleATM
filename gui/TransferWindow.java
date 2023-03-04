package gui;
/**
 * 转账窗口
 *  @author Kyle
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.History;
import bean.Account;
import dao.HistoryDao;
import dao.AccountDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class TransferWindow extends JFrame {

	private JPanel contentPane;
	private JTextField oppositeAccount;
	private JTextField moneyAmount;
	private Account user;
	private MainWindow mWin;

	public TransferWindow(MainWindow mWin, Account user) {
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
					TransferWindow frame = new TransferWindow();
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
	public TransferWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TransferWindow.class.getResource("/images/Bank1.png")));
		setTitle("\u5EFA\u8BBE\u94F6\u884C\u2014\u8F6C\u8D26");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u5BF9\u65B9\u8D26\u6237\uFF1A\r\n");
		lblNewLabel.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 21, 164, 45);
		contentPane.add(lblNewLabel);

		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u8F6C\u8D26\u91D1\u989D\uFF1A");
		label.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		label.setBounds(10, 81, 164, 45);
		contentPane.add(label);

		oppositeAccount = new JTextField();
		oppositeAccount.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		oppositeAccount.setBounds(184, 25, 225, 35);
		contentPane.add(oppositeAccount);
		oppositeAccount.setColumns(10);

		moneyAmount = new JTextField();
		moneyAmount.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		moneyAmount.setBounds(184, 88, 225, 35);
		contentPane.add(moneyAmount);
		moneyAmount.setColumns(10);

		JButton back = new JButton("\u8FD4\u56DE");
		back.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransferWindow.this.setVisible(false);
				mWin.setVisible(true);
			}
		});
		back.setBounds(45, 150, 129, 50);
		contentPane.add(back);

		JButton confirm = new JButton("\u786E\u8BA4");
		confirm.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String account = oppositeAccount.getText();
				String textMoney = moneyAmount.getText();
				// 账户不能为空
				if (null == account || "".equals(account)) {
					JOptionPane.showMessageDialog(null, "账户不能为空！", "输入错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// 交易额不能为空
				if (null == textMoney || "".equals(textMoney)) {
					JOptionPane.showMessageDialog(null, "金额不能为空！", "输入错误", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// 判断对方账号是否存在
				AccountDao userDao = new AccountDao();
				Account moneySender = userDao.getByName(user.getName());// 发款者
				Account moneyReceiver = userDao.getByName(account);// 收款者
			
				if (moneyReceiver == null) {
					JOptionPane.showMessageDialog(null, "您输入的账号不存在,请确认后再输入！", "系统提示", JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					int money = Integer.parseInt(textMoney);
					// 交易额不允许为负数
					if (money <= 0) {
						JOptionPane.showMessageDialog(null, "转账金额不能为负数！", "系统提示", JOptionPane.ERROR_MESSAGE);
						return;
					}
					// 判断交易额是否大于自身账号存款
					if (money > moneySender.getMoney()) {
						JOptionPane.showMessageDialog(null, "您的余额不足,转账失败！", "系统提示", JOptionPane.ERROR_MESSAGE);
						return;
					}
					// 进行转账
					char a=textMoney.charAt(0);
					if(((a>=65 && a<=90) ||(a>=97 && a<=122)||(a>=48 && a<=57))){
					userDao.OutMoney(moneySender.getName(), money);
					userDao.saveMoney(moneyReceiver.getName(), money);
					JOptionPane.showMessageDialog(null, "转账成功,账户" + moneyReceiver.getName() + "会收到一笔来自您的转账！" , "转账成功！", JOptionPane.PLAIN_MESSAGE);
					HistoryDao jyd = new HistoryDao();
					History jy = new History();
					jy.setType("用户转账");
					jy.setMoney(money);
					jy.setAccountID(user.getId());
					jy.setTime( new Timestamp(System.currentTimeMillis()));
					boolean b = jyd.addHistoryRecord(jy);
					}
				} catch (Exception ee) {
					// 判断交易额是否为数字
					JOptionPane.showMessageDialog(null, "转账金额必须是正整数！，请不要输入字母或汉字！", "系统提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		confirm.setBounds(263, 150, 129, 50);
		contentPane.add(confirm);
	}
}
