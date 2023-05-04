package controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import service.LeagueService;

public class AllTransferLeaguesInCountryController implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		LeagueService leagueService = new LeagueService();
		List<String> leagues = leagueService.selectAllLeagueByCountry(request.getParameter("country"));
	    ObjectMapper objectMapper = new ObjectMapper();
		return "responseBody:" + objectMapper.writeValueAsString(leagues);
	}

}
