package log;

public class Project_Log {
/**
 * 4月29日 跟随老师完成原有需求，理解功能实现步骤和项目需求
 * 
 * 5月1日  已完成需求项目 添加取款、查询、修改密码，转账等功能 并优化原有功能显示。
 * 
 * 5月2日  添加额外需求 创建账户，注销账户 ，用户等级查询，并添加预设取款、存款按钮。
 * 
 * 5月3日  测试功能 添加存款和取款窗口内显示实时余额功能
 * 							发现问题：
 * 								1.创建账户功能无法正常创建
 * 										解决：需要在数据库表中设置主键ID自动递增
 * 							    2.使用创建账户功能创建的账户无法正常进行存款取款转账等功能...
 * 										解决：没有给创建的账户的余额插入初始值
 * 								3.清空历史记录按钮使用的truncate jiaoyi方式重置清空id，并让id从1开始自动递增，但此语句会删除所有的用户的记录
 * 										解决：改用delete from zhanghao where accountID =? 语句
 * 
 * 5月4日  继续测试功能 优化细节 添加图标 添加清空输入按钮
 * 							发现问题：
 * 								1.查询记录功能如果数据库内表为空值，会出现空指针异常					
 * 									    解决：在主窗口的查询记录按钮和清空历史记录功能也添加进记录列表里。
 * 								2.清空历史记录后使用Record.paintImmediately(Record.getBounds());进行实时刷新,发现无法删除之前的记录
 * 									暂未解决
 * 						项目基本完成
 * 							
 * 
 * @author Kyle
 */
	public static void main(String[] args) {
		System.out.println("This is my first java project!" + "\n" + "@author by Kyle");
	}
}
