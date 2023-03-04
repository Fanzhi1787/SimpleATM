package gui;
	/**
	 * ��½����
	 * @author Kyle
	 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;

import bean.Account;

import dao.AccountDao;

public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	Account  user = null;
	int cnt = 0;
	private JButton CreateAccount;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	//�����ݿ��ж�ȡ�û������벢��֤��
	boolean validUser(String name,String password){
		   boolean bres = false;
		   AccountDao  userDao = new AccountDao();
		   user = userDao.getByName(name);
	       if(user!=null && user.getPassword().equals(password)
	    		   && user.getName().equals(name)){  //�Ƚ��û���(���ִ�Сд)
		      System.out.println(user.getName()+ " "+user.getPassword());
		      bres = true;
	       }else{
	    	   System.out.println("�û��������벻��ȷ");
	       }	
			
		   return bres ;
	}
	
	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginWindow.class.getResource("/images/Bank1.png")));
		setTitle("\u6B22\u8FCE\u5149\u4E34\u5EFA\u8BBE\u94F6\u884C\u5728\u7EBFATM\uFF0C\u4EBA\u5DE5\u667A\u969C\u4E3A\u60A8\u670D\u52A1\u3002");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u60A8\u7684\u8D26\u6237\u5361\u53F7\uFF1A");
		label.setFont(new Font("΢���ź�", Font.PLAIN, 19));
		label.setBounds(22, 27, 190, 40);
		contentPane.add(label);
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		txtUser.setBounds(230, 28, 220, 39);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8BF7\u8F93\u5165\u60A8\u7684\u8D26\u6237\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 19));
		label_1.setBounds(22, 107, 190, 37);
		contentPane.add(label_1);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		txtPassword.setBounds(230, 107, 220, 39);
		contentPane.add(txtPassword);
		
		JButton confirm = new JButton("\u786E\u8BA4");
		confirm.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//1 ��ȡ����ֵ
				String name = txtUser.getText();  //��ȡ�û���
				char[]  password = txtPassword.getPassword();  //��ȡ����
				String  strPassword = new String(password);  //�����ַ����飬���ɶ�Ӧ���ַ���
				//2 ��֤���벻Ϊ��
				if("".equals(name.trim()) || "".equals(strPassword.trim())){
					//JOptionPane.showConfirmDialog(Login.this, "���벻��Ϊ��","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
				    JOptionPane.showMessageDialog(LoginWindow.this, "���벻��Ϊ��","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				
				if(validUser(name,strPassword)){
			            System.out.println("longin ok");
			            MainWindow main = new MainWindow(LoginWindow.this,user); //���ɴ���ʵ��,�����˺���Ϣ
			            main.setVisible(true);  
			            LoginWindow.this.setVisible(false) ;//�رյ�¼����
			      
			            
		        }else{ 
		        	//��ʾ�û������������,��������,����������
		        	 System.out.println("longin failed");
		        	 if(cnt>=3){
		        		 JOptionPane.showMessageDialog(LoginWindow.this, "���3�Σ�ǿ���˳�","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);	 
		        		 System.exit(0);
		        	 }
		        	 JOptionPane.showMessageDialog(LoginWindow.this, "�û������������","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);	 
		        	//�����������򣬱����û���
		        	 txtPassword.setText(""); 
		        	 cnt = cnt + 1;
		        	 
		        };
			}
		});
		confirm.setBounds(350, 180, 110, 49);
		contentPane.add(confirm);
		
		JButton exit = new JButton("\u9000\u51FA");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//�Ż�����ѡ��
				int res = JOptionPane.showConfirmDialog(LoginWindow.this, "ȷ���˳�ϵͳ?","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
				if(res == JOptionPane.YES_OPTION){
				   System.exit(0);
				}
			}
		});
		exit.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		exit.setBounds(182, 180, 110, 49);
		contentPane.add(exit);
		
		CreateAccount = new JButton("\u521B\u5EFA\u8D26\u6237");
		CreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAccountWindow caw = new CreateAccountWindow(LoginWindow.this,user);
				caw.setVisible(true);
				LoginWindow.this.setVisible(false);
				
			}
		});
		CreateAccount.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		CreateAccount.setBounds(20, 180, 110, 49);
		contentPane.add(CreateAccount);
	}
}
