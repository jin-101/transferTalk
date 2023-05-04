package controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import service.LeagueService;

public class AllTransferCountryController implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		LeagueService leagueService = new LeagueService();
		List<String> country = leagueService.selectAllCountry();
	    ObjectMapper objectMapper = new ObjectMapper();
		return "responseBody:" + objectMapper.writeValueAsString(country);
	}

}
