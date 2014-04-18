package kr.ac.jejuuniv;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserDao {

	private JdbcTemplate jdbcTemplate;
	private RowMapper<User> userMapper = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet result, int rownum) throws SQLException {
			User user = new User();
			user.setId(result.getString("id"));
			user.setName(result.getString("name"));
			user.setPassword(result.getString("password"));
			return user;
		}
	};

	public UserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public User get(final String id) throws SQLException {
		final String sql = "select id,name,password from userinfo where id=?";
		final String[] params = new String[] { id };
		User queryForObject = null;

		try {
			queryForObject = jdbcTemplate.queryForObject(sql, params, userMapper);
		} catch (EmptyResultDataAccessException e) {
		}

		return queryForObject;
	}

	public void add(final User user) throws SQLException {
		final String sql = "insert into userinfo(id,name,password) values(?,?,?)";
		final String[] params = new String[] { user.getId(), user.getName(), user.getPassword() };
		jdbcTemplate.update(sql, params);
	}

	public void delete(final String id) throws SQLException {
		final String sql = "delete from userinfo where id=?";
		final String[] params = new String[] { id };
		jdbcTemplate.update(sql, params);
	}
}
