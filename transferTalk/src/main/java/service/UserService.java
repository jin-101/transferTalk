package service;

import dao.UserDAO;
import vo.UserVO;

public class UserService {

	private UserDAO userDao = new UserDAO();
	
	public int registerCheck(String userId) {
		return userDao.registerCheck(userId);
	}

	/**
	 * 회원가입
	 * @param user_id
	 * @param user_pw
	 * @param user_name
	 * @param phone
	 * @param email
	 * @return 1 -> 회원가입 성공, 0 -> 회원가입 실패, -1 -> 데이터베이스 오류
	 * 작성자: 김창겸
	 */
	public int register(String user_id, String user_pw, String user_name, String phone, String email) {
		return userDao.register(user_id, user_pw, user_name, phone, email);
	}
	
	/**
	 * 로그인
	 * @param user_id
	 * @param user_pw
	 * @return  -> 로그인 성공,  -> 로그인 실패
	 * 작성자: 김창겸
	 */
	public UserVO login(String user_id, String user_pw) {
		return userDao.login(user_id, user_pw);
	}

}
