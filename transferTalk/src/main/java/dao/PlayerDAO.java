package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dbUtil.util;
import vo.PlayerVO;

public class PlayerDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst; // ?지원
	private ResultSet rs;
	private int resultCount;

	/**
	 * 1. player_id, player_name insert 하는것
	 * @param player
	 * @return 저장 성공 시 1, 실패시 0
	 */
	public int insertPlayer(PlayerVO player) {
		String sql = """
				insert into player(player_id, player_name) values (?, ?)
				""";
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, player.getPlayer_id());
			pst.setString(2, player.getPlayer_name());
			resultCount = pst.executeUpdate();
		} catch (SQLException e) {
			resultCount = -1;
			e.printStackTrace();
		} finally {
			util.dbDisconnect(null, pst, conn);
		}

		return resultCount;
	}
	

	/**
	 ***** Null return 가능***** player_id를 받아 아이디에 해당하는 PlayerVO 리턴
	 * 
	 * @param player_id
	 * @return 해당하는 아이디가 있으면 PlayerVO, 없으면 null return
	 */
	public PlayerVO selectPlayerById(int player_id) {
		String sql = """
				select player_id, player_name, player_img_src
				from player
				where player_id = """ + player_id;

		PlayerVO player = null;
		conn = util.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				player = new PlayerVO();
				player.setPlayer_id(player_id);
				player.setPlayer_name(rs.getString("player_name"));
				player.setImg_src(rs.getString("player_img_src"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(null, st, conn);
		}
		return player;
	}

	/**
	 * 검색하는 이름에 해당하는 모든선수 조회 메서드 
	 * playerName을 받아 %이름%에 해당하는 모든선수 조회
	 * @param playerName
	 * @return List<PlayerVO> players 
	 * 작성자 : 서준호
	 */
	public List<PlayerVO> selectPlayersByName(String playerName) {
		String sql = """
				select *
				from player
				where player_name like ?
				""";

		List<PlayerVO> players = new ArrayList<>();
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + playerName + "%");
			rs = pst.executeQuery();
			while (rs.next()) {
				PlayerVO player = new PlayerVO();
				player.setPlayer_id(rs.getInt("player_id"));
				player.setPlayer_name(rs.getString("player_name"));
				player.setImg_src(rs.getString("player_img_src"));
				player.setPlayer_nationality(rs.getString("player_nationality"));
				players.add(player);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(null, pst, conn);
		}
		return players;
	}

	/**
	 * 모든 플레이어 조회
	 * @return 모든 플레이어 
	 * 작성자 : 손준범
	 */
	public List<PlayerVO> selectAllPlayers() {
		String sql = "select * from player";
		List<PlayerVO> players = new ArrayList<>();
		conn = util.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				PlayerVO player = new PlayerVO();
				player.setPlayer_id(rs.getInt("player_id"));
				player.setPlayer_name(rs.getString("player_name"));
				players.add(player);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(rs, st, conn);
		}
		return players;
	}

	/**
	 * 플레이어 업데이트 
	 * 대량으로 받아서 한번에 처리 
	 * 작성자 : 손준범
	 */
	public void updatePlayers(List<PlayerVO> players) {
		String sql = "update player set player_name = ?, player_img_src = ? where player_id = ?";
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			for (PlayerVO player : players) {
				pst.setString(1, player.getPlayer_name());
				pst.setString(2, player.getImg_src());
				pst.setInt(3, player.getPlayer_id());

				pst.addBatch();
				pst.clearParameters();
			}
			pst.executeBatch();
			pst.clearBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(rs, pst, conn);
		}
	}

	
	public void addPlayerNationality(Set<PlayerVO> players) {
		String sql = "update player set player_nationality = ? where player_id = ?";
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			for (PlayerVO player : players) {
				pst.setString(1, player.getPlayer_nationality());
				pst.setInt(2, player.getPlayer_id());

				pst.addBatch();
				pst.clearParameters();
			}
			pst.executeBatch();
			pst.clearBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(rs, pst, conn);
		}

	}
	
	
	public List<PlayerVO> selectFavoritePlayersByUserId(String userId) {
		List<PlayerVO> favoritePlayers = new ArrayList<>();
		String sql = """
				select user_id, player.player_id, player_name, player_img_src, player_nationality
				from my_favorite_player
				join player on (my_favorite_player.player_id = player.player_id)
				where user_id = ?;
				""";
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, userId);
			rs = pst.executeQuery();
			while(rs.next()) {
				PlayerVO player = new PlayerVO();
				player.setPlayer_id(rs.getInt("player_id"));
				player.setPlayer_name(rs.getString("player_name"));
				player.setImg_src(rs.getString("player_img_src"));
				player.setPlayer_nationality(rs.getString("player_nationality"));
				favoritePlayers.add(player);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(rs, pst, conn);
		}
		return favoritePlayers;
	}

	public void addFavoritePlayer(String user_id, int player_id) {
		String sql = """
				insert into my_favorite_player(user_id, player_id) values (?, ?)
				""";
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,user_id);
			pst.setInt(2, player_id);
			pst.executeUpdate();
		} catch (SQLException e) {
			resultCount = -1;
			e.printStackTrace();
		} finally {
			util.dbDisconnect(null, pst, conn);
		}
	}


	public int isFavorite(String user_id, int player_id) {
		String sql = """
				select * from my_favorite_player where user_id = ? and player_id = ?
				""";
		resultCount = 0;
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pst.setString(1, user_id);
			pst.setInt(2, player_id);
			rs = pst.executeQuery();
			rs.last();
			resultCount = rs.getRow();
		} catch (SQLException e) {
			resultCount = -1;
			e.printStackTrace();
		} finally {
			util.dbDisconnect(null, pst, conn);
		}
		return resultCount;
	}


	public void deleteFavoritePlayer(String user_id, int player_id) {
		String sql = "delete from my_favorite_player where user_id = ? and player_id = ?";
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_id);
			pst.setInt(2, player_id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(null, pst, conn);
		}
	}
}
