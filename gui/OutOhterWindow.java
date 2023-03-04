package gui;


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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
/**
 * ��չԤ���ť����δʵ��
 * @author Kyle
 *
 */
public class OutOhterWindow extends JFrame {

	private JPanel contentPane;
	private Account user ;
    private OutMoneyWindow oWin ;
	
    
    public OutOhterWindow(OutMoneyWindow oWin,Account user){
    	this();
    	this.user = user;
    	this.oWin = oWin ;
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
	public OutOhterWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(OutOhterWindow.class.getResource("/images/Bank1.png")));
		setTitle("\u5EFA\u8BBE\u94F6\u884C\u2014\u9884\u8BBE\u91D1\u989D");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel tips = new JLabel("\u8BF7\u9009\u62E9\u9884\u8BBE\u53D6\u6B3E\u91D1\u989D");
		tips.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		tips.setBounds(21, 10, 192, 38);
		contentPane.add(tips);
		
		JButton button100 = new JButton("100");
		button100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int money = 100;
				int result = OutMoney(money);
				if(result == 1){
					JOptionPane.showMessageDialog(OutOhterWindow.this,"ȡ��100Ԫ�ɹ���");
					HistoryDao historyDao = new HistoryDao();
					History history = new History();
					history.setType("�û�ȡ��");
					history.setMoney(money);
					history.setAccountID(user.getId());
					history.setTime( new Timestamp(System.currentTimeMillis()));
					boolean b = historyDao.addHistoryRecord(history);
				}else if(result == -3){
					JOptionPane.showMessageDialog(OutOhterWindow.this,"���㣬ȡ��ʧ�ܣ���鿴���˻��ڴ���Ƿ��㹻��","ϵͳ��ʾ",JOptionPane.WARNING_MESSAGE);
				}   
			}
		});
		button100.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button100.setBounds(10, 85, 95, 45);
		contentPane.add(button100);
		
		JButton button200 = new JButton("200");
		button200.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int money = 200;
				int result = OutMoney(money);
				if(result == 1){
					JOptionPane.showMessageDialog(OutOhterWindow.this,"ȡ��200Ԫ�ɹ���");
					HistoryDao historyDao = new HistoryDao();
					History history = new History();
					history.setType("�û�ȡ��");
					history.setMoney(money);
					history.setAccountID(user.getId());
					history.setTime( new Timestamp(System.currentTimeMillis()));
					boolean b = historyDao.addHistoryRecord(history);
				} else if(result == -3){
					JOptionPane.showMessageDialog(OutOhterWindow.this,"���㣬ȡ��ʧ�ܣ���鿴���˻��ڴ���Ƿ��㹻��","ϵͳ��ʾ",JOptionPane.WARNING_MESSAGE);
				}  
			}
		});
		button200.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button200.setBounds(135, 85, 95, 45);
		contentPane.add(button200);
		
		JButton button500 = new JButton("500");
		button500.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button500.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int money = 500;
				int result = OutMoney(money);
				if(result == 1){
					JOptionPane.showMessageDialog(OutOhterWindow.this,"ȡ��500Ԫ�ɹ���");
					HistoryDao historyDao = new HistoryDao();
					History history = new History();
					history.setType("�û�ȡ��");
					history.setMoney(money);
					history.setAccountID(user.getId());
					history.setTime( new Timestamp(System.currentTimeMillis()));
					boolean b = historyDao.addHistoryRecord(history);
				}else if(result == -3){
					JOptionPane.showMessageDialog(OutOhterWindow.this,"���㣬ȡ��ʧ�ܣ���鿴���˻��ڴ���Ƿ��㹻��","ϵͳ��ʾ",JOptionPane.WARNING_MESSAGE);
				}    
			} 
		});
		button500.setBounds(259, 85, 95, 45);
		contentPane.add(button500);
		
		JButton button1000 = new JButton("1000");
		button1000.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int money = 1000;
				int result = OutMoney(money);
				if(result == 1){
					JOptionPane.showMessageDialog(OutOhterWindow.this,"ȡ��1000Ԫ�ɹ���");
					HistoryDao historyDao = new HistoryDao();
					History history = new History();
					history.setType("�û�ȡ��");
					history.setMoney(money);
					history.setAccountID(user.getId());
					history.setTime( new Timestamp(System.currentTimeMillis()));
					boolean b = historyDao.addHistoryRecord(history);
				} else if(result == -3){
					JOptionPane.showMessageDialog(OutOhterWindow.this,"���㣬ȡ��ʧ�ܣ���鿴���˻��ڴ���Ƿ��㹻��","ϵͳ��ʾ",JOptionPane.WARNING_MESSAGE);
				}  		  
			}
		});
		button1000.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button1000.setBounds(10, 175, 95, 45);
		contentPane.add(button1000);
		
		JButton button5000 = new JButton("5000");
		button5000.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int money = 5000;
				int result = OutMoney(money);
				if(result == 1){
					JOptionPane.showMessageDialog(OutOhterWindow.this,"ȡ��5000Ԫ�ɹ���");
					HistoryDao historyDao = new HistoryDao();
					History history = new History();
					history.setType("�û�ȡ��");
					history.setMoney(money);
					history.setAccountID(user.getId());
					history.setTime( new Timestamp(System.currentTimeMillis()));
					boolean b = historyDao.addHistoryRecord(history);
				}else if(result == -3){
					JOptionPane.showMessageDialog(OutOhterWindow.this,"���㣬ȡ��ʧ�ܣ���鿴���˻��ڴ���Ƿ��㹻��","ϵͳ��ʾ",JOptionPane.WARNING_MESSAGE);
				}  
			}
		});
		button5000.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button5000.setBounds(135, 172, 95, 45);
		contentPane.add(button5000);
		
		JButton button10000 = new JButton("10000");
		button10000.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int money = 10000;
				int result = OutMoney(money);
				if(result == 1){
					JOptionPane.showMessageDialog(OutOhterWindow.this,"ȡ��10000Ԫ�ɹ���");
					HistoryDao historyDao = new HistoryDao();
					History history = new History();
					history.setType("�û�ȡ��");
					history.setMoney(money);
					history.setAccountID(user.getId());
					history.setTime( new Timestamp(System.currentTimeMillis()));
					boolean b = historyDao.addHistoryRecord(history);
				}else if(result == -3){
					JOptionPane.showMessageDialog(OutOhterWindow.this,"���㣬ȡ��ʧ�ܣ���鿴���˻��ڴ���Ƿ��㹻��","ϵͳ��ʾ",JOptionPane.WARNING_MESSAGE);
				}  
			}
		});
		button10000.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button10000.setBounds(259, 175, 95, 45);
		contentPane.add(button10000);
		
		JButton button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oWin.setVisible(true);
				OutOhterWindow.this.setVisible(false);
			}
		});
		button.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		button.setBounds(274, 17, 80, 30);
		contentPane.add(button);
	}
	
	int OutMoney(int money) {
		
		int res = -1;
		AccountDao  userDao = new AccountDao();
		int actual =user.getMoney();
		boolean b = userDao.OutMoney(user.getName(), money);
		
	    if(actual < money) {//�Ա���������Ƿ����ʵ����
	    	return res= -3;
	    }
		if(b){
		    	res = 1;
		 }
		  return res;  
}
}
