package gui;
/**
 * 扩展预设金额按钮
 * @author Kyle
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import dao.*;
import bean.*;
import gui.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class SaveOtherWindow extends JFrame {

	private JPanel contentPane;
	private	Account user ;
	private SaveMoneyWindow sWin;
	private JButton button100;
	private JButton button;
	
	 public SaveOtherWindow(SaveMoneyWindow sWin,Account user){
	    	this();
	    	this.user =user;
	    	this.sWin = sWin;
	    }
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OutOhterWindow frame = new OutOhterWindow();
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
	public SaveOtherWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SaveOtherWindow.class.getResource("/images/Bank1.png")));
		setTitle("\u5EFA\u8BBE\u94F6\u884C\u2014\u9884\u8BBE\u91D1\u989D");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel tips = new JLabel("\u8BF7\u9009\u62E9\u9884\u8BBE\u5B58\u6B3E\u91D1\u989D");
		tips.setBounds(10, 20, 182, 38);
		tips.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		contentPane.add(tips);
		
		button100 = new JButton("100");
		button100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int money = 100;
				int result = saveMoney(money);
				if(result == 1){
					JOptionPane.showMessageDialog(SaveOtherWindow.this,"存款100成功");
					HistoryDao historyDao = new HistoryDao();
					History history = new History();
					history.setType("用户存款");
					history.setMoney(money);
					history.setAccountID(user.getId());
					history.setTime( new Timestamp(System.currentTimeMillis()));
					boolean b = historyDao.addHistoryRecord(history);
				} 
			}
		});
		button100.setBounds(10, 85, 95, 50);
		button100.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPane.add(button100);
		
		JButton button200 = new JButton("200");
		button200.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int money = 200;
				int result = saveMoney(money);
				if(result == 1){
					JOptionPane.showMessageDialog(SaveOtherWindow.this,"存款200成功");
					HistoryDao historyDao = new HistoryDao();
					History jy = new History();
					jy.setType("用户存款");
					jy.setMoney(money);
					jy.setAccountID(user.getId());
					jy.setTime( new Timestamp(System.currentTimeMillis()));
					boolean b = historyDao.addHistoryRecord(jy);
				} 
			}
		});
		button200.setBounds(135, 85, 95, 50);
		button200.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPane.add(button200);
		
		JButton button500 = new JButton("500");
		button500.setBounds(259, 85, 95, 50);
		button500.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button500.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int money = 500;
				int result = saveMoney(money);
				if(result == 1){
					JOptionPane.showMessageDialog(SaveOtherWindow.this,"存款500成功");
					HistoryDao historyDao = new HistoryDao();
					History history = new History();
					history.setType("用户存款");
					history.setMoney(money);
					history.setAccountID(user.getId());
					history.setTime( new Timestamp(System.currentTimeMillis()));
					boolean b = historyDao.addHistoryRecord(history);
				} 
			}
		});
		contentPane.add(button500);
		
		JButton button1000 = new JButton("1000");
		button1000.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int money = 1000;
				int result = saveMoney(money);
				if(result == 1){
					JOptionPane.showMessageDialog(SaveOtherWindow.this,"存款1000成功");
					HistoryDao historyDao = new HistoryDao();
					History history = new History();
					history.setType("用户存款");
					history.setMoney(money);
					history.setAccountID(user.getId());
					history.setTime( new Timestamp(System.currentTimeMillis()));
					boolean b = historyDao.addHistoryRecord(history);
				} 
			}
		});
		button1000.setBounds(10, 175, 95, 50);
		button1000.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPane.add(button1000);
		
		JButton button5000 = new JButton("5000");
		button5000.setBounds(135, 175, 95, 50);
		button5000.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int money = 5000;
				int result = saveMoney(money);
				if(result == 1){
					JOptionPane.showMessageDialog(SaveOtherWindow.this,"存款5000成功");
					HistoryDao historyDao = new HistoryDao();
					History history = new History();
					history.setType("用户存款");
					history.setMoney(money);
					history.setAccountID(user.getId());
					history.setTime( new Timestamp(System.currentTimeMillis()));
					boolean b = historyDao.addHistoryRecord(history);
				} 
			}
		});
		button5000.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPane.add(button5000);
		
		JButton button10000 = new JButton("10000");
		button10000.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int money = 10000;
				int result = saveMoney(money);
				if(result == 1){
					JOptionPane.showMessageDialog(SaveOtherWindow.this,"存款10000成功");
					HistoryDao historyDao = new HistoryDao();
					History history = new History();
					history.setType("用户存款");
					history.setMoney(money);
					history.setAccountID(user.getId());
					history.setTime( new Timestamp(System.currentTimeMillis()));
					boolean b = historyDao.addHistoryRecord(history);
				} 
			}
		});
		button10000.setBounds(259, 175, 95, 50);
		button10000.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPane.add(button10000);
		
		button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sWin.setVisible(true);
				SaveOtherWindow.this.setVisible(false);
				
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		button.setBounds(260, 15, 85, 30);
		contentPane.add(button);
		}
		
	int saveMoney(int money) {
			int res = -1;
			AccountDao  userDao = new AccountDao();
			boolean b = userDao.saveMoney(user.getName(), money);
			 if(b){
			    	res = 1;
			 }
			  return res;  
	}

}
