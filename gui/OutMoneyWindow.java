package gui;
/**
 * ȡ���
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
		label.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label.setBounds(15, 66, 150, 46);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u60A8\u5F53\u524D\u4F59\u989D\u4E3A\uFF1A");
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_1.setBounds(35, 25, 140, 30);
		contentPane.add(label_1);
		
		Amount = new JTextField();
		Amount.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
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
		button.setFont(new Font("΢���ź� Light", Font.PLAIN, 21));
		button.setBounds(45, 191, 110, 50);
		contentPane.add(button);
		
		confirm = new JButton("\u786E\u8BA4");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String input= Amount.getText();
				  if(validInput(input)){
					int money =  Integer.valueOf(input);
					
					//���أ�1 �ɹ���-1��ʾ����100�ı���������-2����ʾ����10000��-4 ��Ǯʧ��
			        int result = OutMoney(money);
					if(result == 1){
						JOptionPane.showMessageDialog(OutMoneyWindow.this,"ȡ��" + input +"Ԫ�ɹ�");
						//��Ӽ�¼
						HistoryDao historyDao = new HistoryDao();
						History history = new History();
						history.setType("�û�ȡ��");
						history.setMoney(money);
						history.setAccountID(user.getId());
						history.setTime( new Timestamp(System.currentTimeMillis()));
					
					}else if(result == -2){
						JOptionPane.showMessageDialog(OutMoneyWindow.this,"��ϵͳ��֧�ָ�������������������");
					}else if(result == -3){
						JOptionPane.showMessageDialog(OutMoneyWindow.this,"����һ�����ȡ����ʮ����������̨����");
					}else if(result == -4){
						JOptionPane.showMessageDialog(OutMoneyWindow.this,"�˻�������Ѳ���0Ԫ���޷�ȡ��!");
					}else if(result == -5){
						JOptionPane.showMessageDialog(OutMoneyWindow.this,"���㣬ȡ��ʧ�ܣ���鿴���˻��ڴ���Ƿ��㹻��");
					}  				  
					}
				  else{
					JOptionPane.showMessageDialog(OutMoneyWindow.this,"��Ҫ��Ϸ��Ŷ������������");
				  }
				 
			}			
			});
		confirm.setFont(new Font("΢���ź� Light", Font.PLAIN, 21));
		confirm.setBounds(260, 191, 110, 50);
		contentPane.add(confirm);
		
		lblYuer = new JLabel("0");
		lblYuer.setFont(new Font("΢���ź�", Font.PLAIN, 22));
		lblYuer.setBounds(185, 24, 176, 30);
		contentPane.add(lblYuer);
		
		label_2 = new JLabel("         \u6269\u5C55\u9009\u9879\uFF1A");
		label_2.setFont(new Font("΢���ź�", Font.PLAIN, 19));
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
		defaultButton.setFont(new Font("΢���ź� Light", Font.PLAIN, 18));
		defaultButton.setBounds(187, 130, 200, 35);
		contentPane.add(defaultButton);
	}

	//��֤�����Ƿ�Ϊ��
	boolean validInput(String input){
		boolean res=false;
		if(input!=null && !input.trim().equals("")){
			return true;
		}
	    return res;				
	}
	//��֤ȡ������
	int OutMoney(int money){
		 AccountDao  acd = new AccountDao();
		user = acd.getByName(user.getName());
		int actual =user.getMoney();
		int input = Integer.valueOf(Amount.getText()).intValue();
		int res = -1;   
	 	if(money % 1 != 0){//����������������
			return res= -2;
		}
	    if(money>100000){//һ����ȡ���������ʮ��ȡ��ʧ�ܡ�
			return res= -3;
		}if(actual < 1) {//ʵ�����С��1,��ȡ��ʧ�ܡ�
			return res= -4;
		}
	    if(actual < input) {//�Ա���������Ƿ����ʵ����
	    	return res= -5;
	    }
	    boolean b = acd.OutMoney(user.getName(), money);
	    if(b){
	    	res = 1;
	    }
		return res;	
	}
}
 	