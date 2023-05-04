package crawling;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import service.LeagueService;
import service.PlayerService;
import service.TeamService;
import service.TransferService;
import vo.LeagueVO;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TransferVO;

public class Crawling {
	private static int FIRST_YEAR = 2005; // 시작은 1992
	private static int CUR_YEAR = 1992; // test 목적으로 변경, 추후 2023으로 복귀

	private static char INTO_THE_TEAM = 'i';
	private static char OUT_OF_THE_TEAM = 'o';

	private Set<PlayerVO> players;
	private Queue<TransferVO> transfers;
	private Set<TeamVO> teams;
	private Set<LeagueVO> leagues;
	private Map<String, String> leagueNames = new HashMap<>();
	private Map<String, String> leagueCodes = new HashMap<>();
	private Set<String> tmpLeagueCodes = new HashSet<>();

	private int curYear;

	private PlayerService playerService = new PlayerService();
	private LeagueService leagueService = new LeagueService();
	private TeamService teamService = new TeamService();
	private TransferService transferService = new TransferService();
	
	public void crawlTeamImageSource() throws IOException {
		List<TeamVO> teams = teamService.selectTeamsByLeagueId(1);
		Map<String, TeamVO> teamMap = new HashMap<>();
		for (TeamVO team : teams) {
			teamMap.put(team.getTeam_name(), team);
		}
		List<TeamVO> updatedTeams = new ArrayList<>();
		String URL = "https://www.transfermarkt.com/laliga/startseite/wettbewerb/ES1";
		Document doc = Jsoup.connect(URL).maxBodySize(0).get();
		Element container = doc.selectFirst("#yw1");
		Elements rows = container.select("tbody > tr");
		for (Element row : rows) {
			Element a = row.selectFirst("td").selectFirst("a");
			String teamName = a.attr("title");
			String imgSrc = a.selectFirst("img").attr("src").replace("tiny", "head");
			TeamVO team = teamMap.get(teamName);
			team.setTeam_img_src(imgSrc);
			updatedTeams.add(team);
		}
		teamService.updateTeams(updatedTeams);
	}
	
	public void crawlPlayerImageSource() throws IOException {
		List<PlayerVO> allPlayers = playerService.selectAllPlayers();
		List<PlayerVO> targetPlayers = new ArrayList<>();
		for (int i = 23000; i < allPlayers.size(); i++) {
			if (i % 100 == 0) {
				playerService.updatePlayers(targetPlayers);
				targetPlayers.clear();
				System.out.println("*****"+i+"*****");
			}
			PlayerVO player = allPlayers.get(i);
			String userName = player.getPlayer_name().toLowerCase().replace(" ", "-");
			userName = userName.replace(".", "-");
			userName = userName.replace("'", "");
			if (!Charset.forName("US-ASCII").newEncoder().canEncode(userName)) {
				continue;
			}
			String URL = "https://www.transfermarkt.com/" + userName + "/profil/spieler/" + player.getPlayer_id();
			Document doc = Jsoup.connect(URL).maxBodySize(0).get();
			Element profile = doc.selectFirst("div.data-header__profile-container > div > img");
			if (profile == null) {
				continue;
			}
			player.setImg_src(profile.attr("src"));
			targetPlayers.add(player);
			System.out.println(profile.attr("src"));
		}
		playerService.updatePlayers(targetPlayers);
	}

	/**
	 * 크롤링 시작 메서드 연도 -> 시즌(여름, 겨울) -> 팀 -> in -> out 순으로 순회 : 먼저 발생한 순서대로 데이터를 저장하기
	 * 위해서 시즌 코드 s, w 순으로 crawlBySeason 호출
	 * 
	 * @throws IOException 작성자 : 손준범
	 */
	public void crawl() throws IOException {
		initializeDefaultLeagueNames();
		initializeContryCodes(leagueCodes);
		for (int year = FIRST_YEAR; year >= CUR_YEAR; --year) {
			curYear = year;
			System.out.println(year + "*************");
			crawlBySeason('s');
			crawlBySeason('w');
		}
	}

	/**
	 * 국가명 → 해당 국가 리그명으로 변환 map 리스트에 키값이 없을경우 국가명+league로 변환 작성자 : 김창겸
	 */
	private void initializeDefaultLeagueNames() {
		leagueNames.put("Brazil", "Breasileirao");
		leagueNames.put("Argentina", "Superliga_Argentina");
		leagueNames.put("Netherlands", "Eredivisie");
		leagueNames.put("Portugal", "Primeira_Liga");
		leagueNames.put("United States", "MLS");
		leagueNames.put("Republic of Korea", "K리그");
		leagueNames.put("Scotland", "Scotland Premiership");
	}

	/**
	 * 시즌별 크롤링 하기 위해 url을 만드는 메서드 각 시즌별로 국가명의 url코드를 사용하며 반복해서 url 생성후, 크롤링 진행 크롤링한
	 * 데이터를 팀별로 수집하기 위해 transferByTheTeam호출
	 * 
	 * @param season
	 * @throws IOException 작성자 : 손준범
	 */
	public void crawlBySeason(char season) throws IOException {

		for (String leagueName : leagueCodes.keySet()) {
			String leagueCode = leagueCodes.get(leagueName);
			String URL = "https://www.transfermarkt.com/" + leagueName + "/transfers/wettbewerb/" + leagueCode
					+ "/plus/?saison_id=" + curYear + "&s_w=" + season + "&leihe=1&intern=0&intern=1";
			System.out.println(URL);
			Document doc = Jsoup.connect(URL).maxBodySize(0).get();
			Elements teamElements = doc.select(".show-for-small ~ .box");

			players = new HashSet<>();
			transfers = new LinkedList<>();
			teams = new HashSet<>();
			leagues = new HashSet<>();
			for (int i = 0; i < teamElements.size(); i++) {
				transferByTheTeam(teamElements.get(i));
			}
//			시즌별로 저장한 정보 service에 저장하기 위한 호출 코드 입력 하기

			playerService.addPlayerNationality(players);
//			playerService.insertPlayers(players);
//			leagueService.insertLeagues(leagues);
//			teamService.insertTeams(teams);
//			transferService.insertTransfers(transfers);
		}
	}

	/**
	 * 국가별 코드를 매핑하기 위한 Map 초기화 국가별 코드는 크롤링을 위한 url에서 사용
	 * 
	 * @param countryCodes 작성자 : 손준범
	 */
	public void initializeContryCodes(Map<String, String> countryCodes) {
		countryCodes.put("premier-league", "GB1");
		countryCodes.put("laliga", "ES1");
		countryCodes.put("bundesliga", "L1");
		countryCodes.put("serie-a", "IT1");
		countryCodes.put("ligue-1", "FR1");
	}

	/**
	 * 팀별로 이적정보를 저장하기 위한 메소드 해당 팀에 들어오는 정보와, 팀에서 나가는 이적을 구분해서 메서드 구분 해당 팀에 들어오는 정보는
	 * transferToTheTeam()메서드 호출 해당 팀에서 나가는 정보는 transferFromTheTeam()메서드 호출
	 * 
	 * @param teamElement 작성자 : 손준범
	 */
	public void transferByTheTeam(Element teamElement) { // in, out에 해당 팀을 넣어주기
		String teamName = teamElement.select("h2 > a").get(1).text();
		transfer(teamElement.select(".responsive-table").get(0).select("table > tbody > tr"), INTO_THE_TEAM, teamName);
		if (teamElement.select(".responsive-table").size() > 1) {
			transfer(teamElement.select(".responsive-table").get(1).select("table > tbody > tr"), OUT_OF_THE_TEAM,
					teamName);
		}
	}

	/**
	 * inOrOut 값에 따라 해당팀에 들어오는 이적 정보인지, 해당팀에서 다른팀으로 이적을 나가는 정보인지 구분하여 이적 정보를 하나의
	 * 메소드에서 처리
	 * 
	 * @param playerElements
	 * @param inOrOut
	 * @param teamName
	 */
	public void transfer(Elements playerElements, char inOrOut, String teamName) { // i : in, o : out
		for (int i = 0; i < playerElements.size(); ++i) {
			Element playerElement = playerElements.get(i);
			Elements idElements = playerElement.select("td > .di > .show-for-small > a");
			if (idElements.size() == 0) {
				continue;
			}
			String idStr = idElements.get(0).attr("href");
			int player_id = Integer.parseInt(idStr.substring(idStr.lastIndexOf("/") + 1, idStr.length()));
//			String player_name = playerElement.select("td > .di > .hide-for-small > a").get(0).text();
//			String ageStr = playerElement.select("td").get(1).text().replaceAll("[^0-9]", "");
//			int age = 0;
//			if (!ageStr.equals("")) {
//				age = Integer.parseInt(ageStr);
//			}
			String nation = null;
			if (playerElement.select("td > img").size() > 0) {
				nation = playerElement.select("td > img").get(0).attr("title");
			}

//			if (playerElement.select("td").size() < 3) {
//				System.out.println(player_name + "doesn't have a position******");
//			}

//			String position = playerElement.select("td").get(3).text();
//
//			String previousTeamName = null;
//			String previousLeagueName = null;
//			String newTeamName = null;
//			String newLeagueName = null;
//			LeagueVO league = null;
//
//			if (inOrOut == 'i') {
//				newTeamName = teamName;
//				if (playerElement.select("td.zentriert > a > img").size() > 0) {
//					previousTeamName = playerElement.select("td.zentriert > a > img").get(0).attr("alt");
//				} else {
//					previousTeamName = playerElement.select("td.zentriert > img").get(playerElement.select("td.zentriert > img").size() - 1).attr("alt");
//				}
//				previousLeagueName = playerElement.select("td.verein-flagge-transfer-cell > img").size() == 0 ? "-"
//						: playerElement.select("td.verein-flagge-transfer-cell > img").get(0).attr("title");
//				if (leagueNames.get(previousLeagueName) == null) {
//					leagueNames.put(previousLeagueName, previousLeagueName + "_league");
//				}
//				league = makeLeagueVO(leagueNames.get(previousLeagueName), previousLeagueName);
//			} else {
//				previousTeamName = teamName;
//				if (playerElement.select("td.zentriert > a > img").size() > 0) {
//					newTeamName = playerElement.select("td.zentriert > a > img").get(0).attr("alt");
//				} else {
//					newTeamName = playerElement.select("td.zentriert > img").get(playerElement.select("td.zentriert > img").size() - 1).attr("alt");
//				}
//				newLeagueName = playerElement.select("td.verein-flagge-transfer-cell > img").size() == 0 ? "-"
//						: playerElement.select("td.verein-flagge-transfer-cell > img").get(0).attr("title");
//
//				if (leagueNames.get(newLeagueName) == null) {
//					leagueNames.put(newLeagueName, newLeagueName + "_league");
//				}
//				league = makeLeagueVO(leagueNames.get(newLeagueName), newLeagueName);
//			}
//
//			String fee = playerElement.select("td.rechts > a").get(0).text();
//			if (fee.contains("loan")) {
//				continue;
//			}

//			PlayerVO player = makePlayer(player_id, player_name);
//			players.add(player);
//			leagues.add(league);
			PlayerVO player = new PlayerVO();
			player.setPlayer_id(player_id);
			player.setPlayer_nationality(nation);
			players.add(player);
			
//			TeamVO previousTeam = makeTeamVO(previousTeamName, league);
//			TeamVO newTeam = makeTeamVO(newTeamName, league);
//			teams.add(previousTeam);
//			teams.add(newTeam);
//			TransferVO transfer = makeTransferVO(age, fee, player, position, previousTeam, newTeam);
//			transfers.add(transfer);
		}
	}
	
	public void crawlTeams() throws IOException {
		
		initializeTempTeamCodes();
		for (String tmpLeagueCode : tmpLeagueCodes) {
			teams = new HashSet<>();
			String URL = "https://www.transfermarkt.com/premier-league/transfers/wettbewerb/" + tmpLeagueCode;
			Document doc = Jsoup.connect(URL).get();
			String leagueName = doc.select(".data-header__headline-wrapper--oswald").get(0).text();
			System.out.println(leagueName);
			String nation = doc.select(".data-header__club > a").text();
			LeagueVO leagueVO = makeLeagueVO(leagueName, nation);
			leagueService.insertLeague(leagueVO);
			Elements teamElements = doc.select(".box").get(1).select(".wappenleiste-box").get(0).select("span > a > img");
			for (Element team : teamElements) {
				String teamName = team.attr("alt");
				TeamVO teamVO = makeTeamVO(teamName, leagueVO);
				teams.add(teamVO);
			}
			teamService.insertTeams(teams);
		}
		
	}
	
	private void initializeTempTeamCodes(){
		initializeTempTeamCode("FR", 3);
		initializeTempTeamCode("GB", 4);
		initializeTempTeamCode("IT", 2);
		initializeTempTeamCode("ES", 2);
		initializeTempTeamCode("L", 3);
	}
	
	private void initializeTempTeamCode(String code, int size) {
		for (int i = 1; i <= size; ++i) {
			tmpLeagueCodes.add(code + i);
		}
	}

	/**
	 * PlayerVO 오브젝트 생성 메서드
	 * 
	 * @param player_id
	 * @param player_name
	 * @return PlayerVO 작성자 : 손준범
	 */
	private PlayerVO makePlayer(int player_id, String player_name) {
		PlayerVO player = new PlayerVO();
		player.setPlayer_id(player_id);
		player.setPlayer_name(player_name);
		return player;
	}

	/**
	 * LeagueVO 오브젝트 생성 메서드
	 * 
	 * @param leagueName
	 * @param nation
	 * @return LeagueVO 작성자 : 손준범
	 */
	private LeagueVO makeLeagueVO(String leagueName, String nation) {
		LeagueVO league = new LeagueVO();
		league.setLeague_name(leagueName);
		league.setLeague_country(nation);
		return league;
	}

	/**
	 * TeamVO 오브젝트 생성 메서드
	 * 
	 * @param teamName
	 * @param league
	 * @return TeamVO 작성자 : 손준범
	 */
	private TeamVO makeTeamVO(String teamName, LeagueVO league) {
		TeamVO team = new TeamVO();
		team.setTeam_name(teamName);
		team.setLeague(league);
		return team;
	}

	/**
	 * Transfer 오브젝트 생성 메서드
	 * 
	 * @param age
	 * @param fee
	 * @param player
	 * @param position
	 * @param previousTeam
	 * @param newTeam
	 * @return TransferVO 작성자 : 손준범
	 */
	private TransferVO makeTransferVO(int age, String fee, PlayerVO player, String position, TeamVO previousTeam,
			TeamVO newTeam) {
		TransferVO transfer = new TransferVO();
		transfer.setAge(age);
		transfer.setFee(fee);
		transfer.setPrevious_team(previousTeam);
		transfer.setNew_team(newTeam);
		transfer.setPlayer(player);
		transfer.setPlayer_position(position);
		transfer.setTransfer_year(this.curYear);
		return transfer;
	}
}