package syncinfo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import syncinfo.service.LogService;
import syncinfo.controller.LoginController;

/**
 * 登录controller
 * @author lvsl
 */
@Controller
public class LoginController {
	
	@Autowired
	private LogService logService;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	/**
	 * 用户登录
	 * 
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		logger.debug("request url = " + request.getRequestURL());
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String dologin(HttpServletRequest request, 
			@RequestParam(value="username") String username,
			@RequestParam(value="password") String password,
			@RequestParam(value="role") String role) {
		logger.debug("login username = " + username);
		Subject subject = SecurityUtils.getSubject();
		try{  
			// 身份验证
			subject.login(new UsernamePasswordToken(username, password));
			
		} catch (AuthenticationException e) {
			
			logger.error("shiro authentication error.", e);
			
//		    throw new LoginPageValidationException(certsn, "", RAErrNumRes.RA_LoginOperator_ERROR);
		}
		return "admin";
	}
}
