package ws.prager.models;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Uptime {

	@Autowired
	@Transient
	private UptimeRepository repository;

	final static Logger logger = LoggerFactory.getLogger(Uptime.class);
	@Autowired
	@Transient
	private Application application;
	@Transient
	private String trivia;

	@Id
	private Long id = 1L;
	@Transient
	private long nodeStartTime;

	private long applicationStartTime;

	@Transient
	private long appUptime;
	@Transient
	private long nodeUptime;

	public Uptime() {
		logger.debug("creating uptime bean");
		nodeStartTime = java.lang.System.currentTimeMillis();
	}

	@PostConstruct
	public void init() {
		logger.debug("is up");
		if (application == null)
			logger.debug("application still not instantiated yet -- we have a problem!!");
		if (application != null && application.getNumbers() < 2) {
			logger.debug("I am the first and only.");
			applicationStartTime = nodeStartTime;
			logger.debug("persist uptime");
			repository.save(this);
			if (application.isOnCF()) {
				logger.debug("persist uptime");
				repository.save(this);
			}
		} else {
			logger.debug("retrieve app starttime");
			Uptime tmpUptime = new Uptime();
			tmpUptime = repository.findOne(1L);
			this.applicationStartTime = tmpUptime.applicationStartTime;
			logger.debug("app starttime set to {}.", applicationStartTime);
		}
	}

	public long getNodeUptime() {
		long nodeUptime = java.lang.System.currentTimeMillis() - nodeStartTime;
		logger.debug("nodeUptime is now: {}.", nodeUptime);
		return nodeUptime;
	}

	public String getTrivia() throws ClientProtocolException, IOException {
		Trivia client = new Trivia();
		this.trivia = client.get((java.lang.System.currentTimeMillis() - nodeStartTime) / 1000);
		return trivia;
	}

	public long getApplicationUpTime() {
		return java.lang.System.currentTimeMillis() - applicationStartTime;
	}

	public long getAppUptime() {
		long appUptime = java.lang.System.currentTimeMillis() - applicationStartTime;
		logger.debug("nodeUptime is now: {}.", nodeUptime);
		return appUptime;
	}

}