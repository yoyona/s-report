package net.greatsoft.core.runner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.greatsoft.core.domain.mapper.OrgMapper;
import net.greatsoft.core.domain.model.system.DicArea;
import net.greatsoft.core.domain.model.system.Org;
import net.greatsoft.core.repository.redis.CacheRedisDao;
import net.greatsoft.core.repository.system.DicAreaRepository;
import net.greatsoft.core.repository.system.OrgRepository;
import net.greatsoft.core.service.system.SystemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 项目启动加载初始数据
 * @author fxy
 *
 */
/*@Component*/
@Order(value=1)
public class StartupRunner implements CommandLineRunner{
	@Autowired
	private CacheRedisDao cacheRedisDao;
	@Autowired
	private DicAreaRepository areaRepository;
	@Autowired
	private OrgRepository orgRepository;
	@Autowired
	private OrgMapper orgMapper;
	@Autowired
	private SystemService systemService;
	
	@Override
	public void run(String... arg0) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>>>>  数据初始化    <<<<<<<<<<<<<<<<<<<<<");
		System.out.println(">>>>>>>>>>>>>>>>>>  地区列表    <<<<<<<<<<<<<<<<<<<<<");
		//System.out.println(System.currentTimeMillis());
		List<DicArea> areaList = areaRepository.queryAreas();
		cacheRedisDao.set("areaList", areaList);
		System.out.println(">>>>>>>>>>>>>>>>>>  缓存地区机构列表   <<<<<<<<<<<<<<<<<<<<<");
		List<DicArea> areaLists = queryAreas(areaList,"0");
		for (int i = 0; i < areaLists.size(); i++) {
			DicArea dicArea = areaLists.get(i);
			String id = dicArea.getId();
			if (id.endsWith("0000")) {
				List<Org> orgsByProvinces = this.orgMapper.getOrgsByProvinces(id.substring(0, 2));
				cacheRedisDao.set(id.substring(0, 2)+"%", orgsByProvinces);
				// 获取机构下面的县或者其他什么的
				List<DicArea> queryAreas = systemService.queryAreas(id);
				for (int j = 0; j < queryAreas.size(); j++) {
					DicArea area = queryAreas.get(j);
					// 查询下级的的机构放入缓存
					List<Org> orgsByProvinces2 = orgMapper.getOrgsByProvinces(area.getId().substring(0, 4));
					cacheRedisDao.set(area.getId().substring(0, 4)+"_", orgsByProvinces2);
				}
			}
		}
		System.out.println(">>>>>>>>>>>>>>>>>>  机构列表    <<<<<<<<<<<<<<<<<<<<<");
		List<DicArea> areas = areaRepository.queryAreasById();
		for (int i = 0; i < areas.size(); i++) {
			DicArea area = areas.get(i);
			List<Org> orgList = orgRepository.getOrgsByAdministrativeCode(area.getId());
			cacheRedisDao.set(area.getId(), orgList);
		}
		//System.out.println(System.currentTimeMillis());
		System.out.println(">>>>>>>>>>>>>>>>>>  初始化完毕    <<<<<<<<<<<<<<<<<<<<<");
	}
	
	private List<DicArea> queryAreas(List<DicArea> areas,String areaId){
		List<DicArea> returnAreas = new ArrayList<DicArea>();
		for (int i = 0; i < areas.size(); i++) {
			DicArea area = areas.get(i);
			if(areaId == null || "0".equals(areaId)){
				if(area.getpId() == null || "".equals(area.getpId())){
					returnAreas.add(area);
				}
			}else{
				if(areaId.equals(area.getpId())){
					returnAreas.add(area);
				}
			}
		}
		return returnAreas;
	}
}
