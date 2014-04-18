package kr.ac.jejuuniv;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.mysql.jdbc.Driver;

public class DConnectionMaker extends SimpleDriverDataSource {

	public DConnectionMaker() {
		super();
		setDriverClass(Driver.class);
		setUrl("jdbc:mysql://localhost/jeju");
		setUsername("jeju");
		setPassword("jejupw");
	}

}
