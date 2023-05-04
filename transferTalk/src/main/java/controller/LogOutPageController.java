package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class LogOutPageController implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");

		request.getSession().invalidate();
		return "redirect:" +  request.getContextPath() + "/index.jsp";
	}
}
