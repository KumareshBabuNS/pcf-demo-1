package ws.prager.controllers;

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
	
	 @RequestMapping(value = "/upTime", method = RequestMethod.GET)
	 public Uptime getUptime() {
		 return uptime;
	 }
	
	 @RequestMapping(value = "/appInfo", method = RequestMethod.GET)
	 public Application getAppInfo() {
		return application;
	 }

}
