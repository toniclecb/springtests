package com.toniclecb.springtests;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DatastoreSystemsHealthTest {

	@Autowired
	DataSource dataSource;
	
	/**
	 * Here we are testing the DataSource bean and his connection.
	 * The metadata of connection is not null and connection's catalog name is 'testdb'?
	 */
	@Test
	public void dbPrimaryIsOk() {
		try {
			DatabaseMetaData metadata = dataSource.getConnection().getMetaData();
			String catalogName = dataSource.getConnection().getCatalog();

			assertThat(metadata, is(notNullValue()));
			assertThat(catalogName.toLowerCase(), is(equalTo("testdb")));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
