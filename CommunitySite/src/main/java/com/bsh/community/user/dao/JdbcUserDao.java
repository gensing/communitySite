package com.bsh.community.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bsh.community.user.dto.UserDto;

public class JdbcUserDao {
	Connection connection;
	PreparedStatement statement;
	ResultSet resultSet;

	String driverName = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:tensing";
	String user = "community";
	String password = "1234";

	public JdbcUserDao() {
		try {
			// ① 로드
			Class.forName(driverName);

		} catch (ClassNotFoundException e) {
			System.out.println("[로드 오류]\n" + e.getStackTrace());
		}

	}

	public void closeDatabase() {
		try {
			if (connection != null) {
				// connection 닫기
				connection.close();
			}

			if (statement != null) {
				// statement 닫기
				statement.close();
			}

			if (resultSet != null) {
				// resultSet 닫기
				resultSet.close();
			}
		} catch (SQLException e) {
			System.out.println("[닫기 오류]\n" + e.getStackTrace());
		}
	}

	public UserDto getUser(String username)  {
		UserDto userDto = new UserDto();

		try {
			String queryString = "SELECT email as id, passwd as password, authority as roles FROM user_info WHERE email = ?";

			// ② 연결 [Connection]
			connection = DriverManager.getConnection(url, user, password);

			// ② 연결 [Statement]
			statement = connection.prepareStatement(queryString);

			statement.setString(1, username);
			resultSet = statement.executeQuery();

			if (!resultSet.next()) {
				return null;
			}

			userDto.setId(resultSet.getString("id"));
			userDto.setPassword(resultSet.getString("password"));
			userDto.setRoles(resultSet.getString("roles"));

		} catch (SQLException e) {
			System.out.println("[쿼리 오류]\n" + e.getStackTrace());
		} finally {
			closeDatabase();
		}
		return userDto;

	}

}
