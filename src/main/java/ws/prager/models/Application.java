package ws.prager.models;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.cloudfoundry.client.lib.CloudCredentials;
import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource(value={"classpath:credentials.properties"})
@Component
public class Application {

	@Autowired
	Environment env;
	@Value("${cfuser.name}")
	String username;
	@Value("${cfuser.pwd}")
	String pwd;
	@Value("${cfapp.name}")
	String cfAppName;

	final static Logger logger = LoggerFactory.getLogger(Application.class);

	private int numbers;
	private int running;
	private int index;
	private boolean onCF;
	URL url = null;
	
	CloudCredentials credentials = null;
	CloudFoundryClient cloudFoundryClient = null;

	public boolean isOnCF() {
		return onCF;
	}

	public void setOnCF(boolean onCF) {
		this.onCF = onCF;
	}

	public Application() {
		logger.debug("constructed");
	}
	
	@PostConstruct
	public void init() {
		logger.debug("init");
		try {
			url = new URL("https://api.run.pivotal.io");
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
		}
		credentials = new CloudCredentials(username, pwd);
		cloudFoundryClient = new CloudFoundryClient(credentials , url);
		CloudApplication cloudApplication = cloudFoundryClient.getApplication(cfAppName);
		this.running = cloudApplication.getRunningInstances();
		this.numbers = cloudApplication.getInstances();
		logger.debug("app numbers are now: {}.", numbers);
		String cfIndex = System.getenv("CF_INSTANCE_INDEX");
		if (cfIndex != null) {
			logger.debug("Index: {}.", cfIndex);
			this.index = Integer.parseInt(cfIndex);
			this.onCF = true;
		} else {
			this.index = 0;
			this.onCF = false;
		}
	}

	public int getNumbers() {
		CloudApplication cloudApplication = cloudFoundryClient.getApplication(cfAppName);
		this.numbers = cloudApplication.getInstances();
		logger.debug("Instances are now: {}.", this.numbers);
		return numbers;
	}

	public int getIndex() {
		String cfIndex = System.getenv("CF_INSTANCE_INDEX");
		if (cfIndex != null) {
			logger.debug("CF_INSTANCE_INDEX: {}.", cfIndex);
			this.index = Integer.parseInt(cfIndex);
		} else
			this.index = 0;
		return index;
	}
	
	public int getRunning() {
		CloudApplication cloudApplication = cloudFoundryClient.getApplication(cfAppName);
		this.running = cloudApplication.getRunningInstances();
		logger.debug("running instances are now: {}.", running);
		return running;
	}

}
