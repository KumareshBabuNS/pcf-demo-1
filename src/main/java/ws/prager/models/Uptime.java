package ws.prager.models;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Uptime {

	final static Logger logger = LoggerFactory.getLogger(Uptime.class);
	@Autowired
	@Transient
	private Application application;

	@Id
	private Long id = 1L;
	@Transient
	private long nodeStartTime;
	private long applicationStartTime;

	public Uptime() {
		logger.debug("creating uptime bean");
		nodeStartTime = java.lang.System.currentTimeMillis();
		if (application == null) logger.debug("application not instantiated yet");
		if (application != null && application.getNumbers() < 2) {
			logger.debug("I am the first and only.");
			applicationStartTime = nodeStartTime;
			if (application.isOnCF()) {
				// TODO: persist
			}
		} else {
			// TODO: get time from database
		}

	}

	@PostConstruct
	public void up() {
		logger.debug("is up");
	}

	public long getUptime() {
		RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();
		this.nodeStartTime = mxBean.getUptime();
		logger.debug("current uptime is {}.", this.nodeStartTime);
		return nodeStartTime;
	}

	public long getNodeUptime() {
		return nodeStartTime;
	}

	public void setNodeUptime(long nodeUptime) {
		this.nodeStartTime = nodeUptime;
	}

	public long getApplicationUptime() {
		return applicationStartTime;
	}

	public void setApplicationUptime(long applicationUptime) {
		this.applicationStartTime = applicationUptime;
	}

}
