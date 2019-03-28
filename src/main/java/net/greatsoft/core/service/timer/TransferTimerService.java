package net.greatsoft.core.service.timer;

import net.greatsoft.core.domain.mapper.OrgMapper;
import net.greatsoft.core.domain.mapper.RoleMapper;
import net.greatsoft.core.domain.mapper.UserMapper;
import net.greatsoft.core.domain.model.system.Org;
import net.greatsoft.core.domain.model.system.User;
import net.greatsoft.core.repository.system.OrgRepository;
import net.greatsoft.core.repository.system.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransferTimerService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private OrgMapper orgMapper;

    @Resource
    private UserRepository userRepository;

    @Resource
    private OrgRepository orgRepository;

    @Transactional
    public void transferTimerTask() {
    	System.out.println("------------定时器任务开始---------------------");
    	System.out.println("------------清空数据---------------------");
//    	this.userMapper.deleteAllUsersInfo();
//    	this.roleMapper.deleteAllUsersRole();
    	this.orgMapper.deleteAllOrg();
        System.out.println("------------导入机构数据---------------------");
        this.orgMapper.insertOpenRegistryOrgs();
        System.out.println("------------导入机构数据结束---------------------");
        System.out.println("------------导入虚拟机构数据---------------------");
        List<Org> openRegistryOrgsProvince = this.orgMapper.findOpenRegistryOrgsProvince();
//        List<User> userList = new ArrayList<User>();
        for (int i = 0; i < openRegistryOrgsProvince.size(); i++) {
            Map<String, Object> param = new HashMap<String, Object>();
            Org orgInfo = openRegistryOrgsProvince.get(i);
            String id = orgInfo.getId();
            String name = orgInfo.getName();
            orgInfo.setUniqueId(id + "2");
            orgInfo.setSubjectionPid(orgInfo.getTopSubjectPid());
            orgInfo.setRegionPid(orgInfo.getTopSubjectPid());
            orgInfo.setName(name + "直属汇总");
            orgInfo.setSummaryType("3");
            orgInfo.setId(id + "2");
            orgInfo.setOrgLevel("0");
            param.put("pid", orgInfo.getTopSubjectPid());
            this.orgRepository.save(orgInfo);
            param.put("aid", id + "2");
            orgInfo.setSubjectionPid(id + "2");
            orgInfo.setRegionPid(id + "2");
            orgInfo.setName(name + "本级");
            orgInfo.setId(id + "3");
            orgInfo.setSummaryType("4");
            orgInfo.setOrgLevel("1");
            orgInfo.setUniqueId(id + "3");
            this.orgRepository.save(orgInfo);
            param.put("id", id + "2");
            this.orgMapper.updateOpenFollowOrg(param);
            // 新增用户并且给予权限
            /*User user = new User();
            user.setName(orgInfo1.getName());
            user.setRealName(orgInfo1.getName());
            user.setPassword("9711d33bdf119a4e85b2b64d5df9abdc");
            user.setPasswordSalt("123456");
            user.setCreateTime(new Date());
            user.setType(9);
            user.setIsValid(1);
            user.setIsCancel(0);
            user.setRoleId("5");
            // userList.add(user);
            User saveAndFlush = userRepository.saveAndFlush(user);
            userList.add(saveAndFlush);*/
        }
        /*if (!userList.isEmpty()) {
        	this.roleMapper.insertBatchUsersRole(userList);
        }*/
        System.out.println("------导入用户数据-----");
//        this.userMapper.insertOpenUsersInfo();
//        this.roleMapper.insertUsersRoleFromOpen();
        System.out.println("------定时器完毕-----");

    }

}
