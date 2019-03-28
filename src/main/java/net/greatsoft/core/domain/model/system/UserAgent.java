package net.greatsoft.core.domain.model.system;
/**
 * 用户代理机构
 * @author litian
 *
 */
public class UserAgent {
	private String userId;
	private String orgId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	@Override
	public String toString() {
		return "UserAgent [userId=" + userId + ", orgId=" + orgId + "]";
	}
	
}
