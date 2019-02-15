package syncinfo.jdbc;

//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Enumeration;
//import java.util.Properties;

public class Test {
	public static void main(String[] args) throws SQLException {
//		ConnectionPool connectionPool = ConnectionPool.getInstance();
//		for(int i = 0; i < 100; i++) {
//			Connection connection = connectionPool.getConnection();
//			System.out.println(i);
//		}
//		Properties p = new Properties();
//		try {
//			p.load(new BufferedInputStream(new FileInputStream("src/db.properties")));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		Enumeration<Object> keys = p.keys();
//		while(keys.hasMoreElements()) {
//			String key = (String) keys.nextElement();
//			System.out.println(key + "=" + p.getProperty(key));
//		}
		try {
			System.out.println("test");
			Connection conn = JdbcUtil.getConnection();
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from user");
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("id"));
			}
			JdbcUtil.release(conn, statement, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
