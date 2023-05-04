package controller;

import java.util.Map;

public class SignUpPageController implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		return "/layout/register.jsp";
	}

}
