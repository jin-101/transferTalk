package service;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.LeagueDAO;
import vo.LeagueVO;

public class LeagueService {
	LeagueDAO leagueDao = new LeagueDAO();
	
	/**
	 * LeagueVO에 맞게 insert 하는것
	 * @param league
	 * @return 저장 성공 시 1, 실패시 0
	 * 작성자 : 한진
	 */
	public int insertLeague(LeagueVO league) {
		LeagueVO selectLeague = selectLeagueByLeagueName(league.getLeague_name());
		if(selectLeague == null) {
			return leagueDao.insertLeague(league);
		}
		return 0;
	}
	
	/**
	 * *****Null return 가능*****
	 * league_name를 받아 LeagueVO를 리턴
	 * @param league_name
	 * @return 해당하는 league_name이 있으면 LeagueVO, 없으면 null return
	 * 작성자 : 한진
	 */
	public LeagueVO selectLeagueByLeagueName(String league_name) {
		return leagueDao.selectLeagueByLeagueName(league_name);
	}
	
	public int insertLeagues(Set<LeagueVO> leagues) {
		int result = 0;
		for (LeagueVO league : leagues) {
			result += insertLeague(league);
		}
		return result;
	}
	
	/**
	 * 모든 country_name의 데이터를 리턴
	 * @return 모든 country_name return
	 * 작성자 : 한진
	 */
	public List<String> selectAllCountry() {
		return leagueDao.selectAllCountry();
	}
	
	/**
	 * country_name를 받아 모든 league_name 데이터를 리턴
	 * @param country_name
	 * @return league_name
	 * 작성자 : 한진
	 */
	public List<String> selectAllLeagueByCountry(String country_name) {
		return leagueDao.selectAllLeagueByCountry(country_name);
	}
}
