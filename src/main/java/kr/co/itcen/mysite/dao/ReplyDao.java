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
			
			// -- no / title / contents / hit / reg_date / g_no / o_no / depth / user_no / status
			// insert into board values(null,'chickin','치킨 짱', hit, now(), 2, (select max(bo.o_no) + 1 from board as bo where bo.g_no = 2 ),( select max(boo.depth) + 1 from board as boo where boo.o_no = 11), 1, 1);
			String sql = "insert into board values(null, ?, ? , 0, now(), ?, o_no + 1, depth + 1, ?, ?);";
			
			//1 . title
			//2. contents
			//3. g_no - 그룹번호 - 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//답글에 답글을 달떄
	public void reply2Insert() {
		Connection connection = null; //연결객체
		PreparedStatement pstmt = null; //운반객체 - Query를 묶어서 보내는 기능
		
		
		try {
			//데이터베이스와 연결 할 떄 오류가 발생 할 수 있기 때문에
			//예외처리가 필요
			connection = getConnection();
			
			String sql = "insert into board values(null,'chickin','치킨 짱', hit, now(), 2, (select max(bo.o_no) + 1 from board as bo where bo.g_no = 2 ),( select max(boo.depth) + 1 from board as boo where boo.o_no = 11), 1, 1)";
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

				String url = "jdbc:mariadb://192.168.0.2:3306/webdb?characterEncoding=utf8";
				connection = DriverManager.getConnection(url, "webdb", "webdb");

			} catch (ClassNotFoundException e) {
				System.out.println("Fail to Loading Driver:" + e);
			}

			return connection;
		}

}
