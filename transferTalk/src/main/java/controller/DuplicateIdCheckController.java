package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import service.UserService;

public class DuplicateIdCheckController implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		UserService userService = new UserService();
		request.setCharacterEncoding("UTF-8");
		int resultNum = userService.registerCheck(request.getParameter("user_id"));
		return "responseBody:" + resultNum;
	}

}
