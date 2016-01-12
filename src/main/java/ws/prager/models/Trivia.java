package ws.prager.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Trivia {
	final static Logger logger = LoggerFactory.getLogger(Trivia.class);
	
	String urlBase = "http://numbersapi.com/";
	String urlString = "/trivia?notfound=floor&fragment";
	public Trivia() {
		// TODO Auto-generated constructor stub
	}
	
	String get(long number) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		logger.debug("requesting {}.", urlBase + number + urlString);
		HttpGet request = new HttpGet(urlBase + number + urlString);
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		String trivia = rd.readLine();
		logger.debug("received '{}'	.", trivia);
		return trivia;

	}

}
