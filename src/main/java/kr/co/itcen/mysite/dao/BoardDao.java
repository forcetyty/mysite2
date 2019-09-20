package kr.co.itcen.mysite.dao;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.mysite.vo.BoardUserListVo;
import kr.co.itcen.mysite.vo.BoardViewVo;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.GuestbookVo;
import kr.co.itcen.mysite.vo.UserVo;

public class BoardDao {
	
	// View에 선택한 게시글을 표시해주는 Dao Method
	public  BoardViewVo selectView(Long no) {
		BoardViewVo result = new BoardViewVo();
		
		//List<BoardUserListVo> result = new ArrayList<BoardUserListVo>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			
			/// 제목, 글쓴이, 메일, 등록일, 조회수, 내용
			String sql = "select b.no, b.title, u.name , u.email, date_format(b.reg_date, '%Y-%m-%d %h:%i:%s'), b.hit, b.contents from user as u, board as b where b.user_no = u.no and b.no = ?";
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				Long num = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				String email = rs.getString(4);
				String date_format = rs.getString(5);
				Long hit = rs.getLong(6);
				String contents = rs.getString(7);
				

				BoardViewVo vo = new BoardViewVo();

				vo.setNo(num);
				vo.setTitle(title);
				vo.setName(name);
				vo.setEmail(email);
				vo.setReg_date(date_format);
				vo.setHit(hit);
				vo.setContents(contents);
				

				result = vo;
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
		
	}
	

	// 방명록에 대한 정보를 가져오는 Dao Method
	public List<BoardUserListVo> getList() {
		List<BoardUserListVo> result = new ArrayList<BoardUserListVo>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			// 번호
			// 제목
			// 글쓴이
			// 조회수
			// 작성일
			//

			String sql = "select b.no, b.title, u.name, b.hit, date_format(b.reg_date, '%Y-%m-%d %h:%i:%s') from user as u, board as b where u.no = b.user_no";
			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				Long hit = rs.getLong(4);
				String date_format = rs.getString(5);

				BoardUserListVo vo = new BoardUserListVo();

				vo.setNo(no);
				vo.setTitle(title);
				vo.setName(name);
				vo.setHit(hit);
				vo.setReg_date(date_format);

				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 원 게시글 등록하는 기능!!!
	public Boolean insertBoardDao(BoardVo bvo) {
		Boolean result = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 삭제된 글
			connection = getConnection();

//			`no`       INT UNSIGNED NOT NULL COMMENT '번호', 1
//			`title`    VARCHAR(200) NOT NULL COMMENT '제목', 2
//			`contents` TEXT         NOT NULL COMMENT '내용', 3
//			`hit`      INT          NOT NULL COMMENT '조회수', 4

//			`reg_date` DATETIME     NOT NULL COMMENT '등록일', 5

//			`g_no`     INT          NOT NULL COMMENT '그룹번호', 6
//			`o_no`     INT          NOT NULL COMMENT '그룹내순서', 7
//			`depth`    INT          NOT NULL COMMENT '깊이', 8

//			`user_no`  INT UNSIGNED NOT NULL COMMENT '회원번호', 9
//			`status`   BOOLEAN      NULL     COMMENT '상태' 10

			/*
			 * Long num = bvo.getNo(); // 테이블의 첫 값을 넣기 pstmt.setString(1, bvo.getTitle());
			 * // 제목 pstmt.setString(2, bvo.getContents()); // 내용
			 * 
			 * 
			 * if (bvo.getO_no() == 0) { pstmt.setLong(4, num); // 그룹번호 } else {
			 * pstmt.setLong(5, bvo.getO_no()); // 그룹내순서 }
			 * 
			 * pstmt.setLong(6, bvo.getDepth()); // 깊이 pstmt.setLong(7, bvo.getUser_no());
			 * // 회원아이디 pstmt.setBoolean(8, true); // 상태
			 */

			String sql = "insert into board values(null, ?, ?, 0, now(), (select ifnull(max(bo.g_no)+1, 1) from board as bo), 0, 0, ?, ?)";

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, bvo.getTitle());
			pstmt.setString(2, bvo.getContents());
			pstmt.setLong(3, bvo.getUser_no());
			pstmt.setBoolean(4, true);

			int count = pstmt.executeUpdate();
			result = (count == 1);

			stmt = connection.createStatement();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
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
