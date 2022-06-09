package com.evg3108.randompicker;

import com.evg3108.randompicker.repository.ConnectionManager;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication
public class RandompickerApplication {

	public static void main(String[] args) throws LiquibaseException {
		//manually running liquibase until I get to run it automatically
		Connection connection = ConnectionManager.getConnection();
		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
		Liquibase liquibase = new liquibase.Liquibase("classpath:db/changelog/db.changelog-master.yaml", new ClassLoaderResourceAccessor(), database);
		liquibase.update(new Contexts(), new LabelExpression());

		SpringApplication.run(RandompickerApplication.class, args);
	}

}
