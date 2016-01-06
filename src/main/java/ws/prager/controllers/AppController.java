package ws.prager.controllers;

import java.net.MalformedURLException;
import java.net.URL;

import org.cloudfoundry.client.lib.CloudCredentials;
import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ws.prager.models.Application;

@RestController
public class AppController {
	final static Logger logger = LoggerFactory.getLogger(AppController.class);
	URL url = null;

	public AppController() throws MalformedURLException {
		url = new URL("https://api.run.pivotal.io");
	}
	
	 @RequestMapping(value = "/appInfo", method = RequestMethod.GET)
	 public Application allInstances() {
		Application app = new Application();
		Integer numberOfInstances = 0;
		CloudCredentials credentials = new CloudCredentials("bernd@prager.ws", "muemmi");
		CloudFoundryClient cloudFoundryClient = new CloudFoundryClient(credentials , url);
		CloudApplication cloudApplication = cloudFoundryClient.getApplication("bprager-pivotal-demo");
		numberOfInstances = cloudApplication.getRunningInstances();
		String index = System.getenv("CF_INSTANCE_INDEX");
		if (index == null) {
			index = "0";
			app.setOnCF(false);
		} else {
			app.setOnCF(true);
		}
		logger.info("number of instances: {}.", numberOfInstances);
		logger.info("index: {}.", index);
		app.setIndex(Integer.parseInt(index));
		app.setNumbers(numberOfInstances);
		return app;
	 }

}
