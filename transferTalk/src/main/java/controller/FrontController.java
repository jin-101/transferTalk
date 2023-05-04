package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({"/transfer/*", "/player/*", "/login/*", "/user/*"}) //기본주소 : /transferTalk
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		Controller controller = null;
		Map<String, Object> data = new HashMap<>();
		data.put("method", request.getMethod());
		data.put("request", request);
		data.put("response", response);
		System.out.println(path);
		switch (path) {
		case "/login/duplicateIdCheck":
			controller = new DuplicateIdCheckController();
			break;
		case "/login/loginPage":
			controller = new LoginPageController();
			break;
		case "/login/logoutPage":
			controller = new LogOutPageController();
			break;
		case "/login/MainPage":
			controller = new LoginCheckController();
			break;
//		case "/login/signin":
//			controller = new LoginSignInController();
//			break;
		case "/login/signup":
			controller = new SignUpController();
			break;
		case "/login/signupPage":
			controller = new SignUpPageController();
			break;
		case "/transfer/country":
			controller = new AllTransferCountryController();
			break;
		case "/transfer/country/searchLeague": 
			controller = new SearchLeagueInCountryController(); 
			break;
		case "/transfer/country/leagues": 
			controller = new AllTransferLeaguesInCountryController(); 
			break; 
		case "/transfer/country/league/teams": 
			controller = new AllTransferTeamsInLeagueController(); 
			break;
		case "/transfer/transferList":
			controller = new TransferListController();
			break;
		case "/transfer/league":
			controller = new TransferInfoController();
			break;
		case "/player/searchPage":
			controller = new AllplayerSearchPageController();
			break;
		case "/player/searchContents":
			controller = new AllplayerInfoController();
			break;
		case "/player/detail":
			controller = new PlayerInfoController();
			break;
		case "/player/img":
			controller = new PlayerImgController();
			break;
		case "/transfer/summary":
			controller = new RankerController();
			break;
		case "/transfer/summaryDetail":
			controller = new TransferSummaryDetail();
			break;	
		case "/user/favoritePlayer":
			controller = new FavoritePlayerCotroller();
			break;
		case "/user/myFavoritePlayers":
			controller = new UserFavoritePlayers();
			break;
//		case "/site-result/changePhoto.do":
//			controller = new ChangePhotoController();
//			break;
		}
		
		String page = null;
		try {
			page = controller.execute(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (page.indexOf("redirect:") >= 0) {
			response.sendRedirect(page.substring(9));
		} else if(page.indexOf("download") >= 0) {
			response.getWriter().append("download OK");
		} else if (page.indexOf("responseBody:")>=0) {
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().append(page.substring(13));
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}

	

}
