package controller;

import java.util.Map;

public class LoginPageController implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		return "/layout/login.jsp";
	}

}
