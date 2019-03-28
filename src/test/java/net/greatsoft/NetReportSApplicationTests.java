package net.greatsoft;

import net.greatsoft.core.StartApplication;
import net.greatsoft.core.domain.mapper.TaskScopeMapper;
import net.greatsoft.core.domain.model.system.Org;
import net.greatsoft.core.domain.model.system.Role;
import net.greatsoft.core.domain.model.system.User;
import net.greatsoft.core.domain.model.system.UserRole;
import net.greatsoft.core.domain.model.task.TaskUser;
import net.greatsoft.core.service.system.OrgService;
import net.greatsoft.core.service.system.RoleService;
import net.greatsoft.core.service.system.SynchronizationService;
import net.greatsoft.core.service.system.UserService;
import net.greatsoft.core.service.task.AnalysisService;
import net.greatsoft.core.service.task.TaskService;
import net.greatsoft.core.service.timer.TransferTimerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartApplication.class)
public class NetReportSApplicationTests {
	@Resource
	private AnalysisService analysisService;
	@Resource
	private RoleService roleService;

	@Resource
	private UserService userService;

	@Resource
	private OrgService orgService;

	@Resource
	private TransferTimerService transferTimerService;

	@Resource
	private TaskScopeMapper taskScopeMapper;

	@Resource
	private TaskService taskService;
	@Resource
	private SynchronizationService synchronizationService;

	@Test
	public void contextLoads() {
		//this.transferTimerService.transferTimerTask();

	}

	@Test
	public void insertUser() {

		/*List<TaskUser> taskUsers = new ArrayList<TaskUser>();
		Role roleById = this.roleService.findRoleById("3");
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleById);
		for (int i = 0; i < 500; i++) {
			User u = new User();
			u.setName("gdce" + i);
			u.setPassword("9711d33bdf119a4e85b2b64d5df9abdc");
			u.setRealName("广东测试用户" + i);
			u.setAdminOrganizationId("0069401324400001");
			u.setIsCancel(0);
			u.setIsValid(1);
			u.setIsAgent(0);
			u.setRoles(roles);
			User save = this.userService.save(u);
			TaskUser tu = new TaskUser();
			tu.setTaskId("333");
			tu.setUserId(save.getId());
			taskUsers.add(tu);

		}
		this.taskService.insertTaskUserBatch(taskUsers);*/
		
		/*测试机构同步
		/*List<Map<String,Object>> orgList = new ArrayList();
		System.out.println(">>>>>>>>>>>>>>>");
		Map org = new HashMap();
		org.put("orgId","22222");
		org.put("orgName", "portmantest");
		org.put("reportTypeCode", "05");
		org.put("isDeleted", "1");
		org.put("orgStatus", "0");
		orgList.add(org);
		synchronizationService.sysOrg("2", orgList);
		System.out.println("<<<<<<<<<<<<<<<<<");*/
		
		analysisService.getQueryParamById("144d23a5-1a69-42f0-9916-81fbcb457960");
	}

}
