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
	private Node node;

	@Id
	private Long id = 1L;
	private long applicationStartTime;

	@Autowired
	@Transient
	private UptimeRepository repository;

	final static Logger logger = LoggerFactory.getLogger(Uptime.class);
	@Autowired
	@Transient
	private Application application;
	@Transient
	private String trivia;

	@Transient
	private long appUptime;
	@Transient
	private long nodeUptime;

	public Uptime() {
		logger.debug("creating uptime bean");
	}

	@PostConstruct
	public void init() {
		logger.debug("is up");
		if (application == null) {
			logger.debug("application still not instantiated yet -- we have a problem!!");
		} else {
			logger.debug("Instances already running: {}.", application.getRunning());
			logger.debug("Index : {}.", application.getIndex());
		}
		if (application.getRunning() < 1) {
			logger.debug("I am the first and only.");
			applicationStartTime = java.lang.System.currentTimeMillis();
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
		}
		logger.debug("app starttime is now {}.", applicationStartTime);
	}

	public String getTrivia() throws ClientProtocolException, IOException {
		Trivia client = new Trivia();
		this.trivia = client.get((java.lang.System.currentTimeMillis() - applicationStartTime) / 1000);
		return trivia;
	}

	public long getAppUptime() {
		this.appUptime = java.lang.System.currentTimeMillis() - applicationStartTime;
		logger.debug("appUptime is now: {}.", this.appUptime);
		return this.appUptime;
	}

	public long getNodeUptime() {
		this.nodeUptime = java.lang.System.currentTimeMillis() - node.getStartTime();
		logger.debug("nodeUptime is now: {}.", nodeUptime);
		return nodeUptime;
	}

}