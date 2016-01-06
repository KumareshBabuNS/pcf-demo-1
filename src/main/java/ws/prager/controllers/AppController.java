package ws.prager.controllers;

import java.net.MalformedURLException;
import java.net.URL;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
	final static Logger logger = LoggerFactory.getLogger(AppController.class);
	URL url = null;

	public AppController() throws MalformedURLException {
		url = new URL("http://bprager-pivotal-host-01.cfapps.io/");
	}
	
	 @RequestMapping(value = "/allInstances", method = RequestMethod.GET)
	 public Integer allInstances() {
		Integer numberOfInstances = 0;
		CloudFoundryClient cloudFoundryClient = new CloudFoundryClient(url);
		CloudApplication cloudApplication = cloudFoundryClient.getApplication("bprager-pivotal-demo");
		numberOfInstances = cloudApplication.getRunningInstances();
		logger.info("number of instances: {}.", numberOfInstances);
		return numberOfInstances;
	 }

}
