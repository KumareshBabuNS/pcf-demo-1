package ws.prager.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	final static Logger logger = LoggerFactory.getLogger(IndexController.class);
	// @Autowired Uptime uptime;
	
	@RequestMapping("/")
	String index() {
		return "index";
	}
}
