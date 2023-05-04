package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbUtil.util;
import vo.LeagueVO;
import vo.PlayerVO;

public class LeagueDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst; // ?지원
	private ResultSet rs;
	private int resultCount; 
	
	CallableStatement cst;
	
	/**
	 * league_name, country를 insert 하는것
	 * @param league
	 * @return 저장 성공 시 1, 실패시 0
	 * 작성자 : 한진
	 */
	public int insertLeague(LeagueVO league) {
		String sql = """
				insert into league (league_name, country_name) values(?, ?)
				""";
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, league.getLeague_name());
			pst.setString(2, league.getLeague_country());
			resultCount = pst.executeUpdate(); //DML문장 실행한다. 
		} catch (SQLException e) {
			resultCount = -1;
			e.printStackTrace();
		} finally {
			util.dbDisconnect(null, pst, conn);
		}
		
		return resultCount;
	}
	
	/**
	*****Null return 가능*****
    * league_name를 받아 아이디에 해당하는 LeagueVO 리턴 
    * @param league_name
    * @return 해당하는 리그 이름이 있으면 LeagueVO, 없으면 null return
    * 작성자 : 한진
    */
	public LeagueVO selectLeagueByLeagueName(String league_name) {
		LeagueVO league = null;
		String sql = """
				select * from league
				where league_name = ?
				""";
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, league_name);
			rs = pst.executeQuery();
			while(rs.next()) {
				league = new LeagueVO();
				league.setLeague_id(rs.getInt("league_id"));
				league.setLeague_name(rs.getString("league_name"));
				league.setLeague_country(rs.getString("country_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(null, pst, conn);
		}
		return league;
	}
	
	/**
    * 모든 country_name의 데이터를 추출
    * @return 모든 country_name return
    * 작성자 : 한진
    */
	public List<String> selectAllCountry() {
		List<String> country = new ArrayList<>();
		String sql = """
				select distinct(country_name) from league where country_name !='-'
				""";
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				country.add(rs.getString("country_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(null, pst, conn);
		}
		return country;
	}
	
	/**
	 * country_name를 받아 모든 league_name 데이터를 리턴
	 * @param country_name
	 * @return league_name
	 * 작성자 : 한진
	 */
	public List<String> selectAllLeagueByCountry(String country_name) {
		List<String> leagues = new ArrayList<>();
		String sql = """
				select distinct(league_name) from league where country_name = ?
				""";
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, country_name);
			rs = pst.executeQuery();
			while(rs.next()) {
				leagues.add(rs.getString("league_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(null, pst, conn);
		}
		return leagues;
	}
}
