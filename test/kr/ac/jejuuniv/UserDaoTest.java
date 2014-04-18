package kr.ac.jejuuniv;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserDaoTest {

	private UserDao dao;
	private User defaultUser;

	@Before
	public void setup() {
		defaultUser = new User("1", "성의현", "1111");
		dao = getUserDao();
	}

	@Test
	public void get() throws ClassNotFoundException, SQLException {
		User user = dao.get("1");

		assertSameUser(defaultUser, user);
	}

	@Test
	public void add() throws ClassNotFoundException, SQLException {
		User user = createRandomUser();

		dao.add(user);
		User addedUser = dao.get(user.getId());

		assertSameUser(user, addedUser);
	}

	@Test
	public void delete() throws ClassNotFoundException, SQLException {
		User user = createRandomUser();
		dao.add(user);
		dao.delete(user.getId());

		User deletedUser = dao.get(user.getId());

		assertNull(deletedUser);
	}

	private void assertSameUser(User user, User addedUser) {
		assertEquals(user.getId(), addedUser.getId());
		assertEquals(user.getName(), addedUser.getName());
		assertEquals(user.getPassword(), addedUser.getPassword());
	}

	private User createRandomUser() {
		User user = new User();
		String id = String.valueOf(new Random().nextInt() / 100);
		user.setId(id);
		user.setName("asdasd");
		user.setPassword("asdasdasd");
		return user;
	}

	private UserDao getUserDao() {
		ApplicationContext context = new GenericXmlApplicationContext("daoFactory.xml");
		return context.getBean("userDao", UserDao.class);
	}
}
