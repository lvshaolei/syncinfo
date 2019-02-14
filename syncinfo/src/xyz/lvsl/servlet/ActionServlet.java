package xyz.lvsl.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.lvsl.jdbc.JdbcUtil;
import xyz.lvsl.pojo.User;

@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ActionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("action");
		HttpSession se = request.getSession(false);
		User user = (User)se.getAttribute("UserSession");
		
		if(user == null || "login".equals(action)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String role = request.getParameter("role");
			System.out.println(username + "\t" + password + "\t" + role);
			if(this.login(new User(username, password, role))) {
				
				switch(role) {
					case "0":	
						response.sendRedirect("/syncinfo/html/student.jsp");
						break;
					case "1":	
						response.sendRedirect("/syncinfo/html/teacher.jsp");
						break;
					case "2": 	
						response.sendRedirect("/syncinfo/html/admin.jsp");
						break;
				}
			}else {
				response.getOutputStream().println("error");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private boolean login(User user) {
		Connection conn = JdbcUtil.getConnection();
		String sql = "select * from user where username = ? and password = ? and role = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getRole());
			ResultSet resultSet = pstmt.executeQuery();
			if(resultSet.next()) {
				User nuser = new User(resultSet.getString("id"), user.getUsername(), user.getPassword(), user.getRole());
				System.out.println(nuser.getId());
				JdbcUtil.release(conn, pstmt, resultSet);
				return true;
			}
			JdbcUtil.release(conn, pstmt, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
