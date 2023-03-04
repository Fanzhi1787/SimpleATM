package dao;


import java.sql.*;



import bean.Account;


public class AccountDao {

	// 根据用户名获取账号信息，若果不存在则返回null
	public Account getByName(String name) {
		Account user = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			// 1 获取连接
			conn = DB.getConnection();
			// 构建sql ，查询数据
			String sqlstr = "select * from account where name= ? ";
			ps = conn.prepareStatement(sqlstr); // 构建sql语句
			ps.setString(1, name); // 设置名字参数,1 对应第一个？
			rs = ps.executeQuery(); // 初始游标在第一条记录的上面
			if (rs.next()) { // 将游标下移一条记录，若记录存在返回true
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

	//创建账户
	public boolean Regist(String name,String password) {
		boolean res = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			
			conn = DB.getConnection();
			//经测试，Mysql数据库表中中主键ID应设置自动递增，money栏也必修插入数值，否则由程序创建的用户将无法存取款
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
	
	// 存款
	public boolean saveMoney(String name, int money) {
		boolean res = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			// 1 获取连接
			conn = DB.getConnection();
			// 构建sql ，更新数据
			String sqlstr = "update account set money=money+? where name=? ";
			ps = conn.prepareStatement(sqlstr); // 构建sql语句
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

	// 取款
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


	// 转账



	// 修改密码
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
	
	//注销账户
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
