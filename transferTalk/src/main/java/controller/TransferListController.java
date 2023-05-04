package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class TransferListController implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		String leagueName = request.getParameter("league");
		String teamName = request.getParameter("team");
		if (teamName != null) {
			teamName = teamName.replace("%20", " ");
			request.setAttribute("title", teamName);
		}
		else if (leagueName != null) {
			leagueName = leagueName.replace("%20", " ");
			request.setAttribute("title", leagueName);
		}
		
		return "/layout/transferList.jsp";
	}

}
