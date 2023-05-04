package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import service.PlayerService;

public class FavoritePlayerCotroller implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get(("request"));
		String user_id = request.getParameter("user_id");
		int player_id = Integer.parseInt(request.getParameter("player_id"));
		boolean isAdd = Boolean.parseBoolean(request.getParameter("isAdd"));
		PlayerService playerService = new PlayerService();
		if (isAdd) {
			playerService.addFavoritePlayer(user_id, player_id);
		} else {
			playerService.deleteFavoritePlayer(user_id, player_id);
		}
		ObjectMapper objectMapper = new ObjectMapper();
		return "responseBody:" + objectMapper.writeValueAsString("OK");
	}

}
