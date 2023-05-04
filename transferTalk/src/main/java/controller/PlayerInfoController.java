package controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import service.PlayerService;
import service.TransferService;
import vo.PlayerVO;
import vo.TransferVO;

/**
 * 특정 플레이어 상세페이지 컨트롤러
 * @param player_id(선수아이디)
 * @return PlayerDetail.jsp로 forward
 * 작성자 : 서준호
 */
public class PlayerInfoController implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		TransferService  transferservice = new TransferService();
		PlayerService playerservice = new PlayerService();
		int playerId = Integer.parseInt(request.getParameter("playerId"));
		PlayerVO player = playerservice.selectPlayerById(playerId);
		TransferVO transfer = transferservice.selectTransfersByPlayerId(playerId);
		request.setAttribute("transfer", transfer);
		request.setAttribute("player", player);
		List<TransferVO> transferHistory = transferservice.selectHistoryByPlayerId(playerId);
		boolean isFavorite = playerservice.isFavorite((String)request.getSession().getAttribute("loginUserId"), playerId);
		request.setAttribute("transferHistory", transferHistory);
		request.setAttribute("isFavorite", isFavorite);
		return "/layout/playerInfo.jsp";
	}
}
