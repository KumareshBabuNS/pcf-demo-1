package ws.prager.models;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.cloudfoundry.client.lib.org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class SimpleDbConfig {
	
	String env = "{\"name\":\"rails-postgres\",\"label\":\"elephantsql\",\"tags\":[\"Data Stores\",\"Web-based\",\"User Provisioning\",\"PaaS\",\"Single Sign-On\",\"Windows\",\"New Product\",\"Mac\",\"Certified Applications\",\"Android\",\"Data Store\",\"postgresql\",\"Buyable\",\"relational\",\"Importable\",\"IT Management\"],\"plan\":\"turtle\",\"credentials\":{\"uri\":\"postgres://ysxpnwka:a1ivItDtKK3Lf97LOIMBP22eeod3it2q@pellefant-01.db.elephantsql.com:5432/ysxpnwka\"}}]}";

	@Autowired
	Application application;
	@Bean
	public DataSource dataSource() {

		final Logger logger = LoggerFactory.getLogger(DataSource.class);
		URI dbUri;
		SimpleDriverDataSource basicDataSource = null;
		ElephantSql elephantSql = null;
		
		logger.debug("configuring datasource");
		if (application.isOnCF()) {
			env = System.getenv("VCAP_SERVICES");
			logger.debug("found VCAP_SERVICES as: {}.", env);
		} else {
			logger.debug("no VCAP_SERVICES found. ☹");
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			elephantSql = mapper.readValue(env, ElephantSql.class);
		} catch (IOException e1) {
			logger.error("Oops: {}.", e1.getMessage());
		}
		logger.debug("retrieved db uri as: {}.", elephantSql.getCredentials().getUri());
		
		try {
			String username = "";
			String password = "";
			String url = "";
//			*** local test db config ***
//			String username = "bernd";
//			String password = "****";
//			String url = "jdbc:postgresql://192.168.1.5:5432/test";
			String dbProperty = elephantSql.getCredentials().getUri();
			if (dbProperty != null) {
				dbUri = new URI(dbProperty);
				username = dbUri.getUserInfo().split(":")[0];
				logger.debug("username: {}.", username);
				password = dbUri.getUserInfo().split(":")[1];
				logger.debug("password: {}.", password);
				url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
				logger.debug("url: {}.", url);
			}

			basicDataSource = new SimpleDriverDataSource();
			basicDataSource.setUrl(url);
			basicDataSource.setUsername(username);
			basicDataSource.setPassword(password);
			basicDataSource.setDriverClass(org.postgresql.Driver.class);

		} catch (URISyntaxException e) {
			logger.error(e.getMessage());
		}
		return basicDataSource;
	}
}
