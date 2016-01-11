package ws.prager.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "name", "label", "tags", "plan", "credentials" })
public class ElephantSql {

	@JsonProperty("name")
	private String name;
	@JsonProperty("label")
	private String label;
	@JsonProperty("tags")
	private List<String> tags = new ArrayList<String>();
	@JsonProperty("plan")
	private String plan;
	@JsonProperty("credentials")
	private Credentials credentials;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return The label
	 */
	@JsonProperty("label")
	public String getLabel() {
		return label;
	}

	/**
	 * 
	 * @param label
	 *            The label
	 */
	@JsonProperty("label")
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * 
	 * @return The tags
	 */
	@JsonProperty("tags")
	public List<String> getTags() {
		return tags;
	}

	/**
	 * 
	 * @param tags
	 *            The tags
	 */
	@JsonProperty("tags")
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	/**
	 * 
	 * @return The plan
	 */
	@JsonProperty("plan")
	public String getPlan() {
		return plan;
	}

	/**
	 * 
	 * @param plan
	 *            The plan
	 */
	@JsonProperty("plan")
	public void setPlan(String plan) {
		this.plan = plan;
	}

	/**
	 * 
	 * @return The credentials
	 */
	@JsonProperty("credentials")
	public Credentials getCredentials() {
		return credentials;
	}

	/**
	 * 
	 * @param credentials
	 *            The credentials
	 */
	@JsonProperty("credentials")
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}