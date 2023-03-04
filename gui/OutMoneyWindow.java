package gui;
/**
 * 取款窗口
 * * @author Kyle
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.Timestamp;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;

import bean.History;
import bean.Account;

import dao.HistoryDao;
import dao.AccountDao;

public class OutMoneyWindow extends JFrame {

	private JPanel contentPane;
	private Account user ;
    private MainWindow mWin ;
    private JTextField Amount;
    private JLabel lblYuer;
    private JButton confirm;
    private JLabel label_2;
    private JButton defaultButton;
    
    public OutMoneyWindow(MainWindow mWin,Account user){
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
					OutMoneyWindow frame = new OutMoneyWindow();
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
	public OutMoneyWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(OutMoneyWindow.class.getResource("/images/Bank1.png")));
		setTitle("\u6B22\u8FCE\u4F7F\u7528\u5EFA\u8BBE\u94F6\u884CATM");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				AccountDao userDao = new AccountDao();
				user = userDao.getByName(user.getName());
				lblYuer.setText(String.valueOf(user.getMoney()));
			}
		});
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u53D6\u6B3E\u6570\u989D\uFF1A");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(15, 66, 150, 46);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u60A8\u5F53\u524D\u4F59\u989D\u4E3A\uFF1A");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_1.setBounds(35, 25, 140, 30);
		contentPane.add(label_1);
		
		Amount = new JTextField();
		Amount.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		Amount.setColumns(10);
		Amount.setBounds(187, 71, 200, 35);
		contentPane.add(Amount);
		
		JButton button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OutMoneyWindow.this.setVisible(false);
				mWin.setVisible(true);
			}
		});
		button.setFont(new Font("微软雅黑 Light", Font.PLAIN, 21));
		button.setBounds(45, 191, 110, 50);
		contentPane.add(button);
		
		confirm = new JButton("\u786E\u8BA4");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String input= Amount.getText();
				  if(validInput(input)){
					int money =  Integer.valueOf(input);
					
					//返回：1 成功，-1表示不是100的倍数，返回-2，表示超过10000，-4 存钱失败
			        int result = OutMoney(money);
					if(result == 1){
						JOptionPane.showMessageDialog(OutMoneyWindow.this,"取款" + input +"元成功");
						//添加记录
						HistoryDao historyDao = new HistoryDao();
						History history = new History();
						history.setType("用户取款");
						history.setMoney(money);
						history.setAccountID(user.getId());
						history.setTime( new Timestamp(System.currentTimeMillis()));
					
					}else if(result == -2){
						JOptionPane.showMessageDialog(OutMoneyWindow.this,"本系统不支持浮点数，请输入整数！");
					}else if(result == -3){
						JOptionPane.showMessageDialog(OutMoneyWindow.this,"超过一次最大取款数十万，请移至柜台办理！");
					}else if(result == -4){
						JOptionPane.showMessageDialog(OutMoneyWindow.this,"账户内余额已不足0元，无法取款!");
					}else if(result == -5){
						JOptionPane.showMessageDialog(OutMoneyWindow.this,"余额不足，取款失败，请查看您账户内存款是否足够！");
					}  				  
					}
				  else{
					JOptionPane.showMessageDialog(OutMoneyWindow.this,"不要调戏我哦，请输入数额");
				  }
				 
			}			
			});
		confirm.setFont(new Font("微软雅黑 Light", Font.PLAIN, 21));
		confirm.setBounds(260, 191, 110, 50);
		contentPane.add(confirm);
		
		lblYuer = new JLabel("0");
		lblYuer.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		lblYuer.setBounds(185, 24, 176, 30);
		contentPane.add(lblYuer);
		
		label_2 = new JLabel("         \u6269\u5C55\u9009\u9879\uFF1A");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		label_2.setBounds(12, 125, 150, 35);
		contentPane.add(label_2);
		
		defaultButton = new JButton("\u9884\u8BBE\u91D1\u989D");
		defaultButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					OutOhterWindow domw = new OutOhterWindow(OutMoneyWindow.this,user);
					domw.setVisible(true);
					OutMoneyWindow.this.setVisible(false);
			}
		});
		defaultButton.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		defaultButton.setBounds(187, 130, 200, 35);
		contentPane.add(defaultButton);
	}

	//验证输入是否为空
	boolean validInput(String input){
		boolean res=false;
		if(input!=null && !input.trim().equals("")){
			return true;
		}
	    return res;				
	}
	//验证取款条件
	int OutMoney(int money){
		 AccountDao  acd = new AccountDao();
		user = acd.getByName(user.getName());
		int actual =user.getMoney();
		int input = Integer.valueOf(Amount.getText()).intValue();
		int res = -1;   
	 	if(money % 1 != 0){//输入金额必须非整数。
			return res= -2;
		}
	    if(money>100000){//一次性取款数额大于十万，取款失败。
			return res= -3;
		}if(actual < 1) {//实际余额小于1,则取款失败。
			return res= -4;
		}
	    if(actual < input) {//对比输入余额是否大于实际余额。
	    	return res= -5;
	    }
	    boolean b = acd.OutMoney(user.getName(), money);
	    if(b){
	    	res = 1;
	    }
		return res;	
	}
}
 	