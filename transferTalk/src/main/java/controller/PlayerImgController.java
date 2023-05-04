package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import service.PlayerService;
import vo.PlayerVO;

public class PlayerImgController implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		PlayerService playerservice = new PlayerService();
		int playerId = Integer.parseInt(request.getParameter("playerId"));
		PlayerVO playerImg = playerservice.selectPlayerById(playerId);
		//request.setAttribute("playerImg", playerImg);
		ObjectMapper objectMapper = new ObjectMapper();
		
		return "responseBody:" + objectMapper.writeValueAsString(playerImg.getImg_src());
	}


}
