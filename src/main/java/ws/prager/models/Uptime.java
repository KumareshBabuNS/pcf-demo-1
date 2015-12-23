package ws.prager.models;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Uptime {

	final static Logger logger = LoggerFactory.getLogger(Uptime.class);

	private long uptime = 0;

	public Uptime() {
		logger.debug("creating uptime bean");
	}

	@Bean
	public long getUptime() {
		RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();
		logger.debug("current uptime is {}.", mxBean.getUptime());
		return uptime;
	}

}
