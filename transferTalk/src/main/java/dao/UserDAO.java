package dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import dbUtil.util;
import vo.UserVO;

public class UserDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst; // ?지원
	private ResultSet rs;
	private int resultCount; 

	/**
	 * 아이디 중복 체크
	 * @param userId
	 * @return 1 -> 아이디 이미 존재, 0 -> 아이디 없음
	 */
	public int registerCheck(String userId) {
		String sql = "select * from users where user_id = ?";
		resultCount = 0;
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pst.setString(1, userId);
			rs = pst.executeQuery();
			rs.last();
			resultCount = rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(rs, pst, conn);
		}
		return resultCount;
	}

	/**
	 * 회원가입 (insert)
	 * @param user_id
	 * @param user_pw
	 * @param user_name
	 * @param phone
	 * @param email
	 * @return 1 -> 회원가입 성공, 0 -> 회원가입 실패, -1 -> 데이터베이스 오류
	 * 작성자: 김창겸
	 */
	public int register(String user_id, String user_pw, String user_name, String phone, String email) {
		String sql = """
				insert into users(user_id, user_pw, user_name, phone, email)
				values(?, ?, ?, ?, ?);
				""";
		conn = util.getConnection();
		resultCount = 0;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_id);
			pst.setString(2, passwordHash(user_pw));
			pst.setString(3, user_name);
			pst.setString(4, phone);
			pst.setString(5, email);
			resultCount = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			resultCount = -1;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			resultCount = -1;
		} finally {
			util.dbDisconnect(rs, pst, conn);
		}
		return resultCount;
	}
	
	/**
	 * String 비밀번호를 SHA-256을 적용해 해쉬코드로 만드는 작업
	 * @param user_pw
	 * @return 암호화 된 비밀번호
	 * @throws NoSuchAlgorithmException
	 */
	private String passwordHash(String user_pw) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedHash = digest.digest(user_pw.getBytes(StandardCharsets.UTF_8));
		StringBuilder hexString = new StringBuilder();
		for (byte b : encodedHash) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
	
	/**
	 * 로그인 
	 * @param user_id
	 * @param user_pw
	 * @return 입력한 정보가 있으면 1, 없으면 0
	 * 작성자: 김창겸
	 */
	public UserVO login(String user_id, String user_pw) {
		UserVO user = null;
		String sql = "select * from users where user_id=? and user_pw = ?";
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pst.setString(1, user_id);
			pst.setString(2, passwordHash(user_pw));
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new UserVO();
				user.setUser_id(user_id);
				user.setUser_pw(user_pw);
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setUser_name(rs.getString("user_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(rs, pst, conn);
		}
		return user;
	}
}
