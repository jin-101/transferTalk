package controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import service.PlayerService;
import vo.PlayerVO;

public class UserFavoritePlayers implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get(("request"));
		HttpSession session = request.getSession();
		String loginUserId = (String)session.getAttribute("loginUserId");
		PlayerService playerService = new PlayerService();
		List<PlayerVO> favoritePlayers = playerService.selectFavoritePlayersByUserId(loginUserId);
		request.setAttribute("favoritePlayers", favoritePlayers);
		return "/layout/myFavoritePlayers.jsp";
	}

}
