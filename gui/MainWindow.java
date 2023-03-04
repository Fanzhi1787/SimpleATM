package gui;
/**
 * 主窗口
 * @author Kyle
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Toolkit;

import javax.swing.border.TitledBorder;

import bean.History;
import bean.Account;
import dao.HistoryDao;
import java.sql.Timestamp;
import javax.swing.ImageIcon;


public class MainWindow extends JFrame {
	private Account  user ;
	private LoginWindow lwin ;
	private JPanel contentPane;
	private JButton CloseAccount;
	
	
	//调用 MainWindow()构造方法
	public MainWindow(LoginWindow lwin,Account user){
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
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/images/Bank1.png")));
		setTitle("\u6B22\u8FCE\u5149\u4E34\u5EFA\u8BBE\u94F6\u884C \u4EBA\u5DE5\u667A\u969C\u4E3A\u60A8\u670D\u52A1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 370);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Save = new JButton("\u5B58\u6B3E");
		Save.addActionListener(new ActionListener() {
			//存款窗口
			public void actionPerformed(ActionEvent e) {
				SaveMoneyWindow sw = new SaveMoneyWindow(MainWindow.this,user);
				sw.setVisible(true);
				MainWindow.this.setVisible(false);
			}
		});
		Save.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Save.setBounds(10, 70, 140, 55);
		contentPane.add(Save);
		
		JButton Pwdc = new JButton("\u4FEE\u6539\u5BC6\u7801");
		Pwdc.addActionListener(new ActionListener() {
			//修改密码窗口
			public void actionPerformed(ActionEvent arg0) {
				PwdcWindow pwdc = new PwdcWindow(MainWindow.this,user);
				pwdc.setVisible(true);
				setVisible(false);
			}
		});
		Pwdc.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Pwdc.setBounds(10, 170, 140, 55);
		contentPane.add(Pwdc);
		
		JButton Query = new JButton("\u67E5\u8BE2\u4F59\u989D");
		Query.addActionListener(new ActionListener() {
			//查询窗口
			public void actionPerformed(ActionEvent arg0) {    
			
				HistoryDao historyDao = new HistoryDao();
				History history = new History();
				history.setType("查询余额");
				history.setMoney(user.getMoney());
				history.setAccountID(user.getId());
				history.setTime( new Timestamp(System.currentTimeMillis()));
				boolean b = historyDao.addHistoryRecord(history);
				
				QueryWindow  qw = new QueryWindow(MainWindow.this,user);
				qw.setVisible(true);
				MainWindow.this.setVisible(false);
			}
		});
		Query.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Query.setBounds(175, 70, 140, 55);
		contentPane.add(Query);
		
		JButton Transfer = new JButton("\u8F6C\u8D26");
		Transfer.addActionListener(new ActionListener() {
			//转账窗口
			public void actionPerformed(ActionEvent e) {
				TransferWindow tfw = new TransferWindow(MainWindow.this,user);
				tfw.setVisible(true);
				setVisible(false);
			}
		});
		Transfer.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Transfer.setBounds(175, 170, 140, 55);
		contentPane.add(Transfer);
		
		JButton Histouy = new JButton("\u4EA4\u6613\u8BB0\u5F55");
		Histouy.addActionListener(new ActionListener() {
			//交易记录窗口
			public void actionPerformed(ActionEvent e) {
				//插入查看记录的历史记录，否则若历史记录为空，会出现空指针异常
				HistoryDao historyDao = new HistoryDao();
				History history = new History();
				history.setType("查看记录");
				history.setMoney(user.getMoney());
				history.setAccountID(user.getId());
				history.setTime( new Timestamp(System.currentTimeMillis()));
				boolean b = historyDao.addHistoryRecord(history);
				
				HistoryWindow Hw = new HistoryWindow(MainWindow.this,user);
				Hw.setVisible(true);
				setVisible(false);
			}
		});
		Histouy.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Histouy.setBounds(340, 170, 140, 55);
		contentPane.add(Histouy);
		
		JButton outMoney = new JButton("\u53D6\u6B3E");
		outMoney.addActionListener(new ActionListener() {
			//取款窗口
			public void actionPerformed(ActionEvent e) {
			OutMoneyWindow sw = new OutMoneyWindow(MainWindow.this,user);
			sw.setVisible(true);
			MainWindow.this.setVisible(false);
		}
		});
		outMoney.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		outMoney.setBounds(340, 70, 140, 55);
		contentPane.add(outMoney);
		
		JButton back = new JButton("\u8FD4\u56DE");
		back.addActionListener(new ActionListener() {
			//返回登陆界面
			public void actionPerformed(ActionEvent e) {  //返回登录窗口
				lwin.setVisible(true);// 显示登录窗口
				MainWindow.this.setVisible(false);
			}
		});
		back.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		back.setBounds(30, 260, 140, 50);
		contentPane.add(back);
		
		JButton exit = new JButton("\u9000\u51FA");
		exit.addActionListener(new ActionListener() {
			//退出
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(null, "确定退出建设银行网上ATM？","系统提示",JOptionPane.YES_NO_OPTION);
				if(res == JOptionPane.YES_OPTION){
				   System.exit(0);
				}
			}
		});
		exit.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		exit.setBounds(328, 260, 140, 50);
		contentPane.add(exit);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainWindow.class.getResource("/images/Bank3.jpg")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label.setBounds(0, 0, 504, 314);
		contentPane.add(label);
		
		CloseAccount = new JButton("\u6CE8\u9500\u8D26\u6237");
		CloseAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			CloseAccountWindow caw = new CloseAccountWindow(MainWindow.this,user);
			caw.setVisible(true);
			MainWindow.this.setVisible(false);
			}
		});
		CloseAccount.setForeground(Color.RED);
		CloseAccount.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		CloseAccount.setBounds(200, 270, 90, 30);
		contentPane.add(CloseAccount);
	}
}
