package dao;


import java.sql.*;



import bean.Account;


public class AccountDao {

	// �����û�����ȡ�˺���Ϣ�������������򷵻�null
	public Account getByName(String name) {
		Account user = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			// 1 ��ȡ����
			conn = DB.getConnection();
			// ����sql ����ѯ����
			String sqlstr = "select * from account where name= ? ";
			ps = conn.prepareStatement(sqlstr); // ����sql���
			ps.setString(1, name); // �������ֲ���,1 ��Ӧ��һ����
			rs = ps.executeQuery(); // ��ʼ�α��ڵ�һ����¼������
			if (rs.next()) { // ���α�����һ����¼������¼���ڷ���true
				user = new Account();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setMoney(rs.getInt("money"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.closeConnection(conn);
		}
		return user;
	}

	//�����˻�
	public boolean Regist(String name,String password) {
		boolean res = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			
			conn = DB.getConnection();
			//�����ԣ�Mysql���ݿ����������IDӦ�����Զ�������money��Ҳ���޲�����ֵ�������ɳ��򴴽����û����޷���ȡ��
			//String sql = "insert into account(id,name,password,) values (null,?,?) ";
			
			String sql = "insert into account(id,name,password,money) values (null,?,?,0) ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
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
	
	// ���
	public boolean saveMoney(String name, int money) {
		boolean res = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			// 1 ��ȡ����
			conn = DB.getConnection();
			// ����sql ����������
			String sqlstr = "update account set money=money+? where name=? ";
			ps = conn.prepareStatement(sqlstr); // ����sql���
			ps.setInt(1, money);
			ps.setString(2, name);

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

	// ȡ��
	public boolean OutMoney(String name, int money) {
		boolean res = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DB.getConnection();
			String sqlstr = "update account set money=money-? where name=? ";
			ps = conn.prepareStatement(sqlstr);
			ps.setInt(1, money);
			ps.setString(2, name);

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


	// ת��



	// �޸�����
	public boolean PwdChange(String name, String password) {

		boolean res = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DB.getConnection();
			String sql = "update account set password=? where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, name);

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
	
	//ע���˻�
	public boolean Close(String name) {
		boolean res = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			
			conn = DB.getConnection();
			String sql = "delete from account where name =? ";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, name);
		
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
}
