package ws.prager.models;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import ws.prager.controllers.AppController;

@Configuration
public class SimpleDbConfig {

	@Bean
	public DataSource dataSource() {

		final Logger logger = LoggerFactory.getLogger(DataSource.class);
		URI dbUri;
		SimpleDriverDataSource basicDataSource = null;
		logger.debug("configure datasource");
		try {
			String username = "ysxpnwka";
			String password = "a1ivItDtKK3Lf97LOIMBP22eeod3it2q";
			String url = "jdbc:postgresql://pellefant.db.elephantsql.com:5432/ysxpnwka";
//			String username = "bernd";
//			String password = "muemmi";
//			String url = "jdbc:postgresql://192.168.1.5:5432/test";
			String dbProperty = System.getProperty("database.url");
			if (dbProperty != null) {
				dbUri = new URI(dbProperty);

				username = dbUri.getUserInfo().split(":")[0];
				password = dbUri.getUserInfo().split(":")[1];
				url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
			}

			basicDataSource = new SimpleDriverDataSource();
			basicDataSource.setUrl(url);
			basicDataSource.setUsername(username);
			basicDataSource.setPassword(password);
			basicDataSource.setDriverClass(org.postgresql.Driver.class);

		} catch (URISyntaxException e) {
			// Deal with errors here.
		}
		return basicDataSource;
	}
}
