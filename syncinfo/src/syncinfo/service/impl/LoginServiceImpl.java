package syncinfo.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import syncinfo.jdbc.CreateStatement;
import syncinfo.model.User;
import syncinfo.service.LoginService;
import syncinfo.utils.UserCache;

public class LoginServiceImpl implements LoginService {
	
	/*
	 * (non-Javadoc)
	 * @see syncinfo.service.LoginService#login(syncinfo.model.User)
	 * 判断数据库里有没有该用户，以及该用户是否已在别地登录
	 */
	
	@Override
	public boolean login(User user) {
		User u = null;
		boolean flag = false;
		String sql = "select * from user where role=? and username=? and password=?";
		PreparedStatement pstmt = CreateStatement.getPstmt(sql);
		try {
			pstmt.setString(1, user.getRole());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getPassword());
			ResultSet resultSet = pstmt.executeQuery();
			if(resultSet.next()) {
				flag = true;
				u.setId(resultSet.getString("id"));
				u.setUsername(resultSet.getString("username"));
				u.setRole(resultSet.getString("role"));
				u.setPassword(resultSet.getString("password"));
				if(isLogin(u)) {
					return false;
				}
			}else {
				return flag;
			}
			UserCache.cache.put(u.getRole() + u.getUsername(), u);
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * @see syncinfo.service.LoginService#isLogin(syncinfo.model.User)
	 * 判断是否登录
	 */
	@Override
	public boolean isLogin(User user) {
		// 判断登录缓存里有没有这个用户
		return UserCache.cache.containsKey(user.getRole() + user.getUsername());
	}
	
}
