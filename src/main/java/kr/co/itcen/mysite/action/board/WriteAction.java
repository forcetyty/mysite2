package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.dao.GuestbookDao;
import kr.co.itcen.mysite.dao.UserDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.GuestbookVo;
import kr.co.itcen.mysite.vo.UserVo;
import kr.co.itcen.mysite.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String name = request.getParameter("name");
//		String password = request.getParameter("password");
//		String contents = request.getParameter("contents");
//
//		GuestbookVo vo = new GuestbookVo();
//		
//		vo.setName(name);
//		vo.setPassword(password);
//		vo.setContents(contents);
//
//		new GuestbookDao().insert(vo);
		
		// 접근제어(ACL)
		HttpSession session = request.getSession();

		if (session == null) {
			WebUtils.redirect(request, response, request.getContextPath());
			return;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			WebUtils.redirect(request, response, request.getContextPath());
			return;
		}
		
		UserVo vo = new UserDao().get(authUser.getNo());
				
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardVo bvo = new BoardVo();
//		`no`       INT UNSIGNED NOT NULL COMMENT '번호', 1
//		`title`    VARCHAR(200) NOT NULL COMMENT '제목', 2
//		`contents` TEXT         NOT NULL COMMENT '내용', 3
//		`hit`      INT          NOT NULL COMMENT '조회수', 4

//		`reg_date` DATETIME     NOT NULL COMMENT '등록일', 5

//		`g_no`     INT          NOT NULL COMMENT '그룹번호', 6
//		`o_no`     INT          NOT NULL COMMENT '그룹내순서', 7
//		`depth`    INT          NOT NULL COMMENT '깊이', 8

//		`user_no`  INT UNSIGNED NOT NULL COMMENT '회원번호', 9
//		`status`   BOOLEAN      NULL     COMMENT '상태' 10

		
//		bvo.setTitle(title);
//		bvo.setContents(content);
//		bvo.setUser_no(vo.getNo());
//		
//		if(bvo.getG_no() == null) {
//			bvo.setG_no(1L);
//			bvo.setO_no(1L);
//			bvo.setDepth(0L);
//		}else {
//			bvo.setG_no(bvo.getG_no());
//		}
		
		bvo.setNo(vo.getNo());
		bvo.setTitle(title);
		bvo.setContents(content);
		bvo.setUser_no(vo.getNo());
		
		
		
		new BoardDao().insertBoardDao(bvo);
		
		response.sendRedirect(request.getContextPath() + "/board");

	}

}
