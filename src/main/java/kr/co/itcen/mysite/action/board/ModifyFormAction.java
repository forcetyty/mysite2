package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.dao.UserDao;
import kr.co.itcen.mysite.vo.BoardViewVo;
import kr.co.itcen.mysite.vo.UserVo;
import kr.co.itcen.mysite.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Session을 통해서 수정을 할수 있는 인원을 제한!!!
		// 접근제어(ACL)
		HttpSession session = request.getSession();

		if (session == null) {
			WebUtils.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			return;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			WebUtils.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			return;
		}

		String no = request.getParameter("no");
		System.out.println(no);
		
		System.out.println(authUser);

		BoardViewVo vo = new BoardDao().selectView(Long.parseLong(no));

		request.setAttribute("vo", vo);

		if(authUser.getEmail() == vo.getEmail()) {
			WebUtils.forward(request, response, "/WEB-INF/views/board/modify.jsp");
		}else {
			WebUtils.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
		}

	}

}
