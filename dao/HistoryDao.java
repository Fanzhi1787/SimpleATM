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
	    
			ps = conn.prepareStatement(sqlstr);   //构建sql语句
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
	
	
	
	
	
	//根据 账号id，查询history表，返回交易记录，如果无，则返回null。记录按操作时间由近到远排序
	 public ArrayList<History> getHistouy(int accountID){
	    	ArrayList<History> ls = null;
			PreparedStatement  ps = null;
			ResultSet   rs = null;	
		    Connection conn = null;
			try {
		
			  conn = DB.getConnection();
		
		      String sqlstr = "select * from history where accountID=? order by time desc ";
		    
				 ps = conn.prepareStatement(sqlstr);   //构建sql语句
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
	
	 
	//清空历史数据
	 public boolean ClearHistory(int accountID) {
			boolean res = false;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection conn = null;
			try {
				// 1 获取连接
				conn = DB.getConnection();
				// 构建sql ，更新数据
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
//主方法测试	
	 public static void main(String[] args) {
		HistoryDao jyd = new HistoryDao();
		History jy = new History();
		jy.setType("存款");//类型
		jy.setMoney(1000);
		jy.setAccountID(1);//账号id
		jy.setTime( new Timestamp(System.currentTimeMillis()));//通过系统调用获取当前的系统毫秒数
		
		boolean b = jyd.addHistoryRecord(jy);
		if(b) {
			System.out.println("插入成功");
		}else {
			System.out.println("插入失败");
		}
			//增加查询
		
	         ArrayList<History>  ls = jyd.getHistouy(1);
	         for(History tmp:ls){
	        	 System.out.println(tmp.getType()+","+tmp.getMoney()+","+tmp.getTime());
	         }
    
		}
	}