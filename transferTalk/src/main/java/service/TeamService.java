package service;

import java.util.List;
import java.util.Set;

import dao.TeamDAO;
import vo.TeamVO;

public class TeamService {
	TeamDAO teamDao = new TeamDAO();
	LeagueService leagueService = new LeagueService();
	
	/**
	 * TeamVO에 맞게 insert 하는것
	 * @param team
	 * @return 저장 성공 시 1, 실패시 0
	 * 작성자 : 한진
	 */
	public int insertTeam(TeamVO team) {
		//팀이 db에 없을 경우
		TeamVO selectTeam = selectTeamByTeamName(team.getTeam_name());
		if(selectTeam == null) {
			int leagueId = leagueService.selectLeagueByLeagueName(team.getLeague().getLeague_name()).getLeague_id();
			team.getLeague().setLeague_id(leagueId);
			return teamDao.insertTeam(team);
		}
		return 0;
	}
	
	/**
	 * *****Null return 가능*****
	 * team_name를 받아 TeamVO를 리턴
	 * @param team_name
	 * @return 해당하는 team_name이 있으면 TeamVO, 없으면 null return
	 * 작성자 : 한진
	 */
	public TeamVO selectTeamByTeamName(String team_name) {
		return teamDao.selectTeamByTeamName(team_name);
	}
	
	public int insertTeams(Set<TeamVO> teams) {
		int result = 0;
		for (TeamVO team : teams) {
			result += insertTeam(team);
		}
		return result;
	}
	
	/**
	 * league_name를 받아 모든 team_name 데이터를 리턴
	 * @param league_name
	 * @return team_name
	 * 작성자 : 한진
	 */
	public List<String> selectTeamsByleague(String league_name) {
		return teamDao.selectTeamsByleague(league_name);
	}
	
	/**
	 * leagueId로 team 데이터 리턴
	 * 작성자 손준범
	 */
	public List<TeamVO> selectTeamsByLeagueId(int leagueId) {
		return teamDao.selectTeamsByLeagueId(leagueId);
	}

	public void updateTeams(List<TeamVO> updatedTeams) {
		teamDao.updateTeams(updatedTeams);
	}
}
