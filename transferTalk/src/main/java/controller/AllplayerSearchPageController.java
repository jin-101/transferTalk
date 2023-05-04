package controller;

import java.util.Map;

public class AllplayerSearchPageController implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		return "/layout/PlayerSearch.jsp";
	}

}
