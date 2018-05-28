package com.nursing.data.db;

import com.nursing.data.DatabaseConfig;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Jack on 2015/12/12.
 */
public class DBHelper {

	public static String DB_DRIVER = "";
	public static String DB_URL = "";
	public static String DB_USER = "";
	public static String DB_PASSWORD = "";
	public static String DB_TYPE = "";

	private static Connection sConnection;
	private static PreparedStatement sPs = null;
	private static ResultSet sRs = null;

	static {
		try {
			loadDBConfig();
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (sConnection == null) {
			try {
				System.out.println("数据库连接信息：url=" + DB_URL + ",user=" + DB_USER + ",password=" + DB_PASSWORD);
				sConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sConnection;
	}

	/**
	 * 重置数据库连接
	 */
	public static void resetConnection() {
		loadDBConfig();
		sConnection = null;
		getConnection();
	}

	private static void loadDBConfig() {
		Properties properties = DatabaseConfig.getProperties();
		DB_TYPE = properties.getProperty("db.type").toString().trim();
		if (DB_TYPE.equals("sql")) {
			DB_DRIVER = properties.getProperty("dbsql.driver");
			DB_URL = properties.getProperty("dbsql.url");
			DB_USER = properties.getProperty("dbsql.user");
			DB_PASSWORD = properties.getProperty("dbsql.password");
			
		} else if (DB_TYPE.equals("orcl")) {
			DB_DRIVER = properties.getProperty("dborcl.driver");
			DB_URL = properties.getProperty("dborcl.url");
			DB_USER = properties.getProperty("dborcl.user");
			DB_PASSWORD = properties.getProperty("dborcl.password");
		}

	}

	// 查询数据
	public ResultSet select(String sql) throws Exception {
		try {
			sPs = getConnection().prepareStatement(sql);
			sRs = sPs.executeQuery(sql);
			return sRs;
		} catch (SQLException sqle) {
			throw new SQLException("select data Exception: " + sqle.getMessage());
		} catch (Exception e) {
			throw new Exception("System error: " + e.getMessage());
		}
	}

	// 插入数据
	public static int insert(String sql) throws Exception {
		int num = 0;// 计数
		Connection conn = null;
		try {
			conn = getConnection();
			sPs = conn.prepareStatement(sql);
			num = sPs.executeUpdate();
		} catch (SQLException sqle) {
			throw new SQLException("insert data Exception: " + sqle.getMessage());
		} finally {
			try {
				if (sPs != null) {
					sPs.close();
				}
			} catch (Exception e) {
				throw new Exception("ps close exception: " + e.getMessage());
			}
		}
		return num;
	}

	// 删除数据
	public static int delete(String sql) throws Exception {
		int num = 0;// 计数
		Connection conn = null;
		try {
			conn = getConnection();
			sPs = conn.prepareStatement(sql);
			num = sPs.executeUpdate();
		} catch (SQLException sqle) {
			throw new SQLException("delete data Exception: " + sqle.getMessage());
		} finally {
			try {
				if (sPs != null) {
					sPs.close();
				}
			} catch (Exception e) {
				throw new Exception("ps close Exception: " + e.getMessage());
			}
		}
		return num;
	}

	// 修改数据
	public static int update(String sql) throws Exception {
		int num = 0;// 计数
		Connection conn = null;
		try {
			conn = getConnection();
			sPs = conn.prepareStatement(sql);
			num = sPs.executeUpdate();
		} catch (SQLException sqle) {
			throw new SQLException("update data Exception: " + sqle.getMessage());
		} finally {
			try {
				if (sPs != null) {
					sPs.close();
				}
			} catch (Exception e) {
				System.out.println("e=" + e.toString());
			}
		}
		return num;
	}

}
