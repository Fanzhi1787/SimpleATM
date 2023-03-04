package gui;
/**
 * 存款窗口
 *  @author Kyle
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.History;
import bean.Account;
import dao.HistoryDao;
import dao.AccountDao;

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

public class SaveMoneyWindow extends JFrame {

	private JPanel contentPane;
	private JTextField moneyAmount;
	private Account user ;
    private MainWindow mWin ;
    private JLabel Jlabel;
    private JLabel Yuer;
    private JLabel label_1;
    private JButton button;
    
    public SaveMoneyWindow(MainWindow mWin,Account user){
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
					SaveMoneyWindow frame = new SaveMoneyWindow();
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
	public SaveMoneyWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SaveMoneyWindow.class.getResource("/images/Bank1.png")));
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u5B58\u6B3E\u91D1\u989D\uFF1A");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(10, 85, 167, 25);
		contentPane.add(label);
		
		moneyAmount = new JTextField();
		moneyAmount.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		moneyAmount.setBounds(187, 79, 220, 35);
		contentPane.add(moneyAmount);
		moneyAmount.setColumns(10);
		
		JButton confirm = new JButton("\u786E\u5B9A");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  String input= moneyAmount.getText();
			  if(validInput(input)){
				int money =  Integer.valueOf(input);
				
				//返回：1成功，-1表示非整数，-2表示超过十万，-4存钱失败
		        int result = saveMoney(money);
				if(result == 1){
					JOptionPane.showMessageDialog(SaveMoneyWindow.this,"存款"+ input + "成功");
					HistoryDao historyDao = new HistoryDao();
					History history = new History();
					history.setType("用户存款");
					history.setMoney(money);
					history.setAccountID(user.getId());
					history.setTime( new Timestamp(System.currentTimeMillis()));//通过系统调用获取当前的系统毫秒数
					boolean b1 = historyDao.addHistoryRecord(history);
				
				}else if(result == -2){
					JOptionPane.showMessageDialog(SaveMoneyWindow.this,"请输入整数");
				}else if(result == -3){
					JOptionPane.showMessageDialog(SaveMoneyWindow.this,"超过一次最大存款数100000，请您移步柜台办理");
				}else if(result == -4){
					JOptionPane.showMessageDialog(SaveMoneyWindow.this,"如果您没有钱，就不要存款，谢谢.");
				}
				}
			  else{
				JOptionPane.showMessageDialog(SaveMoneyWindow.this,"输入不能为空");
			  }
			}			
		});
		confirm.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		confirm.setBounds(260, 195, 120, 50);
		contentPane.add(confirm);
		
		JButton back = new JButton("\u8FD4\u56DE");
		back.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  //返回
				SaveMoneyWindow.this.setVisible(false);
				mWin.setVisible(true);
			}
		});
		back.setBounds(56, 195, 120, 50);
		contentPane.add(back);
		
		Jlabel = new JLabel("\u60A8\u5F53\u524D\u4F59\u989D\u4E3A\uFF1A");
		Jlabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Jlabel.setBounds(28, 30, 150, 25);
		contentPane.add(Jlabel);
		
		Yuer = new JLabel("0");
		Yuer.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		Yuer.setBounds(187, 24, 191, 34);
		contentPane.add(Yuer);
		
		label_1 = new JLabel("           \u6269\u5C55\u529F\u80FD\uFF1A");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_1.setBounds(10, 140, 167, 25);
		contentPane.add(label_1);
		
		button = new JButton("\u9884\u8BBE\u91D1\u989D");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveOtherWindow sow = new SaveOtherWindow(SaveMoneyWindow.this,user);
				sow.setVisible(true);
				SaveMoneyWindow.this.setVisible(false);

			}
		});
		button.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		button.setBounds(187, 135, 220, 35);
		contentPane.add(button);
	}
	
	//验证输入是否为空
	boolean validInput(String input){
		boolean res=false;
		if(input!=null && !input.trim().equals("")){
			return true;
		}
	    return res;				
	}
	
	
	int saveMoney(int money){
		int res = -1;    //默认存钱失败
	 	if(money % 1 !=0){//存款金额必须是整数
			return res = -2;
		}
	    if(money>100000){//存款大于十万，需至柜台处理
			return res = -3;
		}if(money == 0) {
			return res = -4;
		}
	    AccountDao  userDao = new AccountDao();
	    boolean b = userDao.saveMoney(user.getName(), money);
	    if(b){
	    	res = 1;
	    }
		return res ;
	}

}

