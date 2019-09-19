package kr.co.itcen.mysite.action.board;

import kr.co.itcen.web.mvc.Action;
import kr.co.itcen.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("writeform".equals(actionName)) {
			System.out.println("writeForm 호출");
			action = new WriteFormAction();
		} else if("write".equals(actionName)) {
			System.out.println("write 호출");
			action = new WriteAction();
		} else {
			/* deafult(list) */
			action = new ListAction();
			System.out.println(action);
			System.out.println(actionName);
		}
		
		return action;
	}
}