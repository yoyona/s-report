package net.greatsoft.core.dto.system;

public class UserDto {
	private String id;
	private String name;
	private String adminOrganizationId;
	private String token;
	private Integer isAgent;

	public Integer getIsAgent() {
		return isAgent;
	}

	public void setIsAgent(Integer isAgent) {
		this.isAgent = isAgent;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdminOrganizationId() {
		return adminOrganizationId;
	}
	public void setAdminOrganizationId(String adminOrganizationId) {
		this.adminOrganizationId = adminOrganizationId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
