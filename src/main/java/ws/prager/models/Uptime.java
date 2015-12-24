package ws.prager.models;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Uptime {

	final static Logger logger = LoggerFactory.getLogger(Uptime.class);

	private long uptime = 0;

	public Uptime() {
		logger.debug("creating uptime bean");
	}
	
	@PostConstruct
	public void up() {
		logger.debug("is up");
	}

	public long getUptime() {
		RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();
		this.uptime = mxBean.getUptime();
		logger.debug("current uptime is {}.", uptime );
		return uptime;
	}

}
