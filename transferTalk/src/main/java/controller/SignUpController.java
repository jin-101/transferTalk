package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;

/**
 * 회원가입 컨트롤러
 * @author 김창겸
 *
 */
public class SignUpController implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		HttpServletResponse response = (HttpServletResponse) data.get("response");
		UserService userService = new UserService();
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String user_id = request.getParameter("user_id");
        String user_pw = request.getParameter("user_pw");
        String user_name = request.getParameter("user_name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String page = "redirect:" + request.getContextPath() + "/login/loginPage";
        String messageType = "";
        String messageContent = "";
        HttpSession session = request.getSession();
        int result = userService.register(user_id, user_pw, user_name, phone, email);
        if (result == 1) {
        	messageType = "성공메시지"	;
        	messageContent = "회원가입에 성공했습니다.";
        } else if (result == 0) {
        	messageType = "오류메시지"	;
        	messageContent = "이미 존재하는 회원입니다.";
        } else {
        	messageType = "오류메시지"	;
        	messageContent = "데이터베이스 오류가 발생했습니다.";
        }
        session.setAttribute("messageType", messageType);
        session.setAttribute("messageContent", messageContent);
		return page;
	}

}
