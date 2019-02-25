package syncinfo.service;

import syncinfo.model.User;

public interface LoginService {
	boolean login(User user);
	boolean isLogin(User user);
}
