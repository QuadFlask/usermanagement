package kr.ac.jejuuniv;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.mysql.jdbc.Driver;

public class NConnectionMaker extends SimpleDriverDataSource {

	public NConnectionMaker() {
		super();
		setDriverClass(Driver.class);
		setUrl("jdbc:mysql://localhost/jeju");
		setUsername("jeju");
		setPassword("jejupw");
	}

}