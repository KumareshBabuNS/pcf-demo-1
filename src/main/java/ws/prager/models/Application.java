package ws.prager.models;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Application {

	final static Logger logger = LoggerFactory.getLogger(Application.class);

	private int numbers;
	private int index;
	private boolean onCF;

	public boolean isOnCF() {
		return onCF;
	}

	public void setOnCF(boolean onCF) {
		this.onCF = onCF;
	}

	public Application() {
		logger.debug("constructed");
	}
	
	@PostConstruct
	public void init() {
		logger.debug("init");
	}

	public int getNumbers() {
		return numbers;
	}

	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	

}
