package gui;
	/**
	 * 登陆窗口
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

	//从数据库中读取用户名密码并验证。
	boolean validUser(String name,String password){
		   boolean bres = false;
		   AccountDao  userDao = new AccountDao();
		   user = userDao.getByName(name);
	       if(user!=null && user.getPassword().equals(password)
	    		   && user.getName().equals(name)){  //比较用户名(区分大小写)
		      System.out.println(user.getName()+ " "+user.getPassword());
		      bres = true;
	       }else{
	    	   System.out.println("用户名或密码不正确");
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
		label.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		label.setBounds(22, 27, 190, 40);
		contentPane.add(label);
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		txtUser.setBounds(230, 28, 220, 39);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8BF7\u8F93\u5165\u60A8\u7684\u8D26\u6237\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 19));
		label_1.setBounds(22, 107, 190, 37);
		contentPane.add(label_1);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		txtPassword.setBounds(230, 107, 220, 39);
		contentPane.add(txtPassword);
		
		JButton confirm = new JButton("\u786E\u8BA4");
		confirm.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//1 获取输入值
				String name = txtUser.getText();  //获取用户名
				char[]  password = txtPassword.getPassword();  //获取密码
				String  strPassword = new String(password);  //根据字符数组，生成对应的字符串
				//2 验证输入不为空
				if("".equals(name.trim()) || "".equals(strPassword.trim())){
					//JOptionPane.showConfirmDialog(Login.this, "输入不能为空","系统提示",JOptionPane.ERROR_MESSAGE);
				    JOptionPane.showMessageDialog(LoginWindow.this, "输入不能为空","系统提示",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				
				if(validUser(name,strPassword)){
			            System.out.println("longin ok");
			            MainWindow main = new MainWindow(LoginWindow.this,user); //生成窗口实例,传入账号信息
			            main.setVisible(true);  
			            LoginWindow.this.setVisible(false) ;//关闭登录窗口
			      
			            
		        }else{ 
		        	//提示用户名或密码错误,输入框清空,可重新输入
		        	 System.out.println("longin failed");
		        	 if(cnt>=3){
		        		 JOptionPane.showMessageDialog(LoginWindow.this, "输错3次，强制退出","系统提示",JOptionPane.ERROR_MESSAGE);	 
		        		 System.exit(0);
		        	 }
		        	 JOptionPane.showMessageDialog(LoginWindow.this, "用户名或密码错误","系统提示",JOptionPane.ERROR_MESSAGE);	 
		        	//清空密码输入框，保留用户名
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
				//优化窗口选项
				int res = JOptionPane.showConfirmDialog(LoginWindow.this, "确定退出系统?","系统提示",JOptionPane.ERROR_MESSAGE);
				if(res == JOptionPane.YES_OPTION){
				   System.exit(0);
				}
			}
		});
		exit.setFont(new Font("微软雅黑", Font.PLAIN, 18));
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
		CreateAccount.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		CreateAccount.setBounds(20, 180, 110, 49);
		contentPane.add(CreateAccount);
	}
}
