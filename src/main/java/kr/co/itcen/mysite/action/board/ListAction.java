package kr.co.itcen.mysite.action.board;

import java.awt.print.Book;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardCountVo;
import kr.co.itcen.mysite.vo.BoardUserListVo;

import kr.co.itcen.mysite.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int firstBoardlist = 1;
		int maxBoardlist = 6;
		int pageNum = firstBoardlist/6*6;
		
		
		if(request.getParameter("page")!=null) {
			firstBoardlist = Integer.parseInt(request.getParameter("page"));
		}
		
		List<BoardUserListVo> list = new BoardDao().getList((firstBoardlist-1)*maxBoardlist, maxBoardlist);
		request.setAttribute("firstBoardlist", firstBoardlist);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("list", list);
		
		//System.out.println(blockStartNum + blockLastNum + lastPageNum);

		WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}

}
