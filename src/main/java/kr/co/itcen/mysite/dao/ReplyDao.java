package kr.co.itcen.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReplyDao {
	
	//원글에 대한 답글을 달때
	public void replyInsert() {
		
		Connection connection = null;	// 연결객체
		PreparedStatement pstmt = null;	// 운반객체
		
		try {
			connection = getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	// DataBase와 연결시키는 객체
		private Connection getConnection() throws SQLException {
			Connection connection = null;

			try {
				Class.forName("org.mariadb.jdbc.Driver");

				String url = "jdbc:mariadb://192.168.1.81:3306/webdb?characterEncoding=utf8";
				connection = DriverManager.getConnection(url, "webdb", "webdb");

			} catch (ClassNotFoundException e) {
				System.out.println("Fail to Loading Driver:" + e);
			}

			return connection;
		}

}
