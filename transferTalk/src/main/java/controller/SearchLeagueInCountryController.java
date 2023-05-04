package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class SearchLeagueInCountryController implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		String country = request.getParameter("country");
		request.setAttribute("country", country);
		return "/layout/leagueSearch.jsp";
	}

}
