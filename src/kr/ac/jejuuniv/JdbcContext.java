package kr.ac.jejuuniv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcContext {
	public DataSource dataSource;

	public JdbcContext(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public User queryForObject(final String sql, final String[] params) throws SQLException {
		return jdbcConetxtWithStatementStrategyForSelect(new StatementStrategy() {
			@Override
			public PreparedStatement makeStatement(Connection connection) throws SQLException {
				PreparedStatement statement = connection.prepareStatement(sql);
				for (int i = 0; i < params.length; i++)
					statement.setString(i + 1, params[i]);
				return statement;
			}
		});
	}

	public void update(final String sql, final String[] params) throws SQLException {
		jdbcContextWithStatementStrategyForUpdate(new StatementStrategy() {
			@Override
			public PreparedStatement makeStatement(Connection connection) throws SQLException {
				PreparedStatement statement = connection.prepareStatement(sql);
				for (int i = 0; i < params.length; i++)
					statement.setString(i + 1, params[i]);
				return statement;
			}
		});
	}

	public User jdbcConetxtWithStatementStrategyForSelect(StatementStrategy statementStrategty) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		User user = null;
		try {
			connection = dataSource.getConnection();

			statement = statementStrategty.makeStatement(connection);

			result = statement.executeQuery();

			user = null;
			if (result.next()) {
				user = new User();
				user.setId(result.getString("id"));
				user.setName(result.getString("name"));
				user.setPassword(result.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (result != null)
				result.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

		return user;
	}

	public void jdbcContextWithStatementStrategyForUpdate(StatementStrategy statementStrategty) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();

			statement = statementStrategty.makeStatement(connection);

			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
	}

}