package controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import service.TeamService;

public class AllTransferTeamsInLeagueController implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		TeamService teamService = new TeamService();
		List<String> teams = teamService.selectTeamsByleague(request.getParameter("league"));
	    ObjectMapper objectMapper = new ObjectMapper();
		return "responseBody:" + objectMapper.writeValueAsString(teams);
	}

}
