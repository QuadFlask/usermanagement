package kr.ac.jejuuniv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetStatementStrategy implements StatementStrategy {

	private String id;

	public GetStatementStrategy(String id) {
		this.id = id;
	}

	@Override
	public PreparedStatement makeStatement(Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select id,name,password from userinfo where id=?");
		statement.setString(1, id);
		return statement;
	}

}
