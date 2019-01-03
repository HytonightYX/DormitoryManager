package me.hsy.test;
 
import java.io.IOException;
 
/**
 * 
 * @ClassName testCMD2
 * @Description:java实现mysql数据库的备份和还原
 * @author Administrator
 * @date 2013-8-15
 */
public class testCMD2 {
	/**
	 * 数据库用户名
	 */
	public static String username = "root";
	/**
	 * 数据库密码
	 */
	public static String password = "admin" ;
	/**
	 * mysql bin路径
	 * mysql安装过程中  在安装路径中不能有空格
	 */
	public static String mysqlpaths = "C://";
	/**
	 * 数据库名称
	 */
	public static String databaseName = "local";
	/**
	 * 数据库url
	 */
	public static String address = "dormitory";
	/**
	 * 备份还原路径
	 */
	public static String sqlpath = "C://";
	
	
	public static void main(String[] args) {
			back("dormitory.sql");
//			load("fengxin.sql");
	}

	/**
	 * 
	 * @Description:备份Mysql数据库
	 * @param fileName 数据备份sql文件名
	 * @throws 
	 * @author fengxin
	 */
	public static void back(String fileName){
		StringBuilder sb=new StringBuilder();
		sb.append("mysqldump").append(" --opt").append(" -h").append(address);
		sb.append(" --user=").append(username).append(" --password=").append(password).append(" --lock-all-tables=true");
		sb.append(" --result-file=").append(sqlpath).append(fileName).append(" --default-character-set=utf8 ").append(databaseName);
		Runtime cmd = Runtime.getRuntime();
		try {
			Process p = cmd.exec("cmd /c "+sb.toString());
			p.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Description:还原mysql数据库
	 * @param fileName 还原mysql数据sql文件名称
	 * @throws 
	 * @author Administrator
	 */
	public static void load(String fileName){
		StringBuilder cmd=new StringBuilder();
		cmd.append("mysql").append(" -h ").append("localhost");
		cmd.append(" -u").append("root").append(" -p").append("root");
		cmd.append(" ").append("podms");
		cmd.append(" <").append(sqlpath).append(fileName);
		Runtime runtime=Runtime.getRuntime();
		Process process=null;
		try {
			process=runtime.exec("cmd /c "+cmd);
			process.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
 
}
