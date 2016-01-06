package ws.prager.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ws.prager.models.Uptime;

@Controller
public class IndexController {
	final static Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired Uptime uptime;
	
	@RequestMapping("/")
	String index() {
		return "index";
	}
}
