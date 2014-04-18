package kr.ac.jejuuniv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStatementStrategy implements StatementStrategy {

	private String id;

	public DeleteStatementStrategy(String id) {
		super();
		this.id = id;
	}

	@Override
	public PreparedStatement makeStatement(Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("delete from userinfo where id=?");
		statement.setString(1, (String) id);
		return statement;
	}

}
