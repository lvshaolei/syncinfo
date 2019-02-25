package syncinfo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateStatement {
	
	public static Statement getStmt() {
		Connection conn = JdbcUtil.getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			return stmt;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	public static PreparedStatement getPstmt(String sql) {
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			return pstmt;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}
}
