package dao;

import java.sql.*;
import java.util.ArrayList;

import bean.History;


public class HistoryDao {
	
	
	public boolean addHistoryRecord(History history) {
    	boolean bres = false;
		PreparedStatement  ps = null;
		Connection conn = null;
		try {

		  conn = DB.getConnection();
		 
		  String sqlstr = "insert into history(type,money,time,accountID) values (?,?,?,?)";
	    
			ps = conn.prepareStatement(sqlstr);   //����sql���
			ps.setString(1, history.getType()); 
			ps.setInt(2,history.getMoney());
			ps.setTimestamp(3, history.getTime()); 
			ps.setInt(4, history.getAccountID());
			
			int r = ps.executeUpdate();
			 
			if(r > 0){ 
				bres = true;
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DB.closeConnection(conn);
		}		

    	return bres;
    }
	
	
	
	
	
	//���� �˺�id����ѯhistory�����ؽ��׼�¼������ޣ��򷵻�null����¼������ʱ���ɽ���Զ����
	 public ArrayList<History> getHistouy(int accountID){
	    	ArrayList<History> ls = null;
			PreparedStatement  ps = null;
			ResultSet   rs = null;	
		    Connection conn = null;
			try {
		
			  conn = DB.getConnection();
		
		      String sqlstr = "select * from history where accountID=? order by time desc ";
		    
				 ps = conn.prepareStatement(sqlstr);   //����sql���
				 ps.setInt(1, accountID);  
				
				 rs = ps.executeQuery();  
				 while(rs.next()){  
					if(ls == null){
						ls = new ArrayList<History>();
					}
						History jy = new History();
						jy.setId(rs.getInt("id"));
						jy.setType(rs.getString("type"));
						jy.setMoney(rs.getInt("money"));
						jy.setTime(rs.getTimestamp("time"));
						jy.setAccountID(rs.getInt("accountID"));
						ls.add(jy);
					
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DB.closeConnection(conn);
			}	  	
	    	
	    	return ls;   	
	    	
	    }
	
	 
	//�����ʷ����
	 public boolean ClearHistory(int accountID) {
			boolean res = false;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection conn = null;
			try {
				// 1 ��ȡ����
				conn = DB.getConnection();
				// ����sql ����������
				String sqlstr = "delete from history  where accountID=? ";
				ps = conn.prepareStatement(sqlstr);
				ps.setInt(1, accountID);
			
				int r = ps.executeUpdate();
				if (r > 0) {
					res = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection(conn);
			}
				return res;
		}
	  
	 /*
		 * @param args
		 */
//����������	
	 public static void main(String[] args) {
		HistoryDao jyd = new HistoryDao();
		History jy = new History();
		jy.setType("���");//����
		jy.setMoney(1000);
		jy.setAccountID(1);//�˺�id
		jy.setTime( new Timestamp(System.currentTimeMillis()));//ͨ��ϵͳ���û�ȡ��ǰ��ϵͳ������
		
		boolean b = jyd.addHistoryRecord(jy);
		if(b) {
			System.out.println("����ɹ�");
		}else {
			System.out.println("����ʧ��");
		}
			//���Ӳ�ѯ
		
	         ArrayList<History>  ls = jyd.getHistouy(1);
	         for(History tmp:ls){
	        	 System.out.println(tmp.getType()+","+tmp.getMoney()+","+tmp.getTime());
	         }
    
		}
	}