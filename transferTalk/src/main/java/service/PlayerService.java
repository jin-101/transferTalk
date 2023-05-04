package service;

import java.util.List;
import java.util.Set;

import dao.PlayerDAO;
import vo.PlayerVO;

public class PlayerService {

	PlayerDAO playerDao = new PlayerDAO();

	/**
	 * PlayerVO를 받아 저장하는 기능
	 * 
	 * @param player
	 * @return 저장 성공 시 1, 실패시 0
	 */
	public int insertPlayer(PlayerVO player) {
		if (selectPlayerById(player.getPlayer_id()) == null) {
			return playerDao.insertPlayer(player);
		}
		return 0;
	}

	/**
	 * *****Null return 가능***** player_id를 받아 아이디에 해당하는 PlayerVO 리턴
	 * 
	 * @param player_id
	 * @return 해당하는 아이디가 있으면 PlayerVO, 없으면 null return
	 */
	public PlayerVO selectPlayerById(int player_id) {
		return playerDao.selectPlayerById(player_id);
	}

	public void insertPlayers(Set<PlayerVO> players) {
		for (PlayerVO player : players) {
			insertPlayer(player);
		}
	}

	/**
	 * 검색하는 이름에 해당하는 모든 선수 조회 메서드 playerName을 받아 이름에 해당하는 List<PlayerVO> 리턴
	 * 
	 * @param playerName 작성자 : 서준호
	 */
	public List<PlayerVO> selectPlayersByName(String playerName) {

		return playerDao.selectPlayersByName(playerName);
	}

	/**
	 * 모든 플레이어 조회
	 * 
	 * @return 모든 플레이어
	 */
	public List<PlayerVO> selectAllPlayers() {

		return playerDao.selectAllPlayers();
	}

	/**
	 * 플레이어 업데이트 대량으로 받아서 한번에 처리 작성자 : 손준범
	 */
	public void updatePlayers(List<PlayerVO> players) {
		playerDao.updatePlayers(players);
	}

	/**
	 * 국적을 받아서 한번에 업데이트
	 * 
	 * @param players
	 */
	public void addPlayerNationality(Set<PlayerVO> players) {
		playerDao.addPlayerNationality(players);

	}

	/**
	 * userid로 저장된 playerid 조회
	 * 
	 */
	public List<PlayerVO> selectFavoritePlayersByUserId(String userId) {
		return playerDao.selectFavoritePlayersByUserId(userId);
	}

	public void addFavoritePlayer(String user_id, int player_id) {
		playerDao.addFavoritePlayer(user_id, player_id);
	}

	public boolean isFavorite(String user_id, int player_id) {
		int result = playerDao.isFavorite(user_id, player_id);
		if (result == 1) {
			return true;
		}
		return false;
	}

	public void deleteFavoritePlayer(String user_id, int player_id) {
		playerDao.deleteFavoritePlayer(user_id, player_id);
	}
}
