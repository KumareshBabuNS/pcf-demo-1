package ws.prager.controllers;

import java.net.MalformedURLException;
import java.net.URL;

import org.cloudfoundry.client.lib.CloudCredentials;
import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ws.prager.models.Application;
import ws.prager.models.Uptime;

@RestController
public class AppController {
	final static Logger logger = LoggerFactory.getLogger(AppController.class);
	@Autowired
	private Application application;
	@Autowired
	private Uptime uptime;
	URL url = null;

	public AppController() throws MalformedURLException {
		url = new URL("https://api.run.pivotal.io");
	}
	
	 @RequestMapping(value = "/upTime", method = RequestMethod.GET)
	 public Uptime getUptime() {
		 return uptime;
	 }
	
	
	 @RequestMapping(value = "/appInfo", method = RequestMethod.GET)
	 public Application getAppInfo() {
		Integer numberOfInstances = 0;
		CloudCredentials credentials = new CloudCredentials("bernd@prager.ws", "muemmi");
		CloudFoundryClient cloudFoundryClient = new CloudFoundryClient(credentials , url);
		CloudApplication cloudApplication = cloudFoundryClient.getApplication("bprager-pivotal-demo");
		numberOfInstances = cloudApplication.getRunningInstances();
		String index = System.getenv("CF_INSTANCE_INDEX");
		if (index == null) {
			index = "0";
			application.setOnCF(false);
		} else {
			application.setOnCF(true);
		}
		logger.info("number of instances: {}.", numberOfInstances);
		logger.info("index: {}.", index);
		application.setIndex(Integer.parseInt(index));
		application.setNumbers(numberOfInstances);
		return application;
	 }

}
