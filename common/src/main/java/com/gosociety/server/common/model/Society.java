package com.gosociety.server.common.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="societies")
public class Society {
	
	@Id
	private String socId;
	private String society;
	private String iconUrl;
	private String iconSmallUrl;
	
	//If the society has no parent it is a core society, otherwise it is a secret society.
	private String parent;
	
	//This is a system wide flag to determine if the society is active within the system.
	private String active;
	
	/*isLocked is used for both core and secret societies. For core, if a society hasn't been
	 * fully rolled out yet, we can set this value to false, and the user will see it, but won't 
	 * have access to it's functionality. If it is set to true for a secret society, then the user won't
	 * be able to gain entry into the secret society, until all the gates are satisfied.
	 */
	private boolean isLocked;
	
	private Map<String, String> hierarchyValue;

	private Map<String, List<GoSoFilter>> filters;
	
	public String getSociety() {
		return society;
	}

	public void setSociety(String society) {
		this.society = society;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Map<String, String> getHierarchyValue() {
		return hierarchyValue;
	}

	public void setHierarchyValue(Map<String, String> hierarchyValue) {
		this.hierarchyValue = hierarchyValue;
	}

	public String getSocId() {
		return socId;
	}

	public void setSocId(String socId) {
		this.socId = socId;
	}

	public String getIconSmallUrl() {
		return iconSmallUrl;
	}

	public void setIconSmallUrl(String iconSmallUrl) {
		this.iconSmallUrl = iconSmallUrl;
	}

	public Map<String, List<GoSoFilter>> getFilters() {
		return filters;
	}

	public void setFilters(Map<String, List<GoSoFilter>> filters) {
		this.filters = filters;
	}
}
