package kr.ac.jejuuniv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStatementStrategy implements StatementStrategy {

	private User user;

	public AddStatementStrategy(User user) {
		super();
		this.user = user;
	}

	@Override
	public PreparedStatement makeStatement(Connection connection) throws SQLException {
		PreparedStatement statement = connection
				.prepareStatement("insert into userinfo(id,name,password) values(?,?,?)");
		statement.setString(1, user.getId());
		statement.setString(2, user.getName());
		statement.setString(3, user.getPassword());
		return statement;
	}

}
