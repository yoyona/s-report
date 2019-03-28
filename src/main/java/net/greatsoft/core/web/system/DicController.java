package net.greatsoft.core.web.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.greatsoft.core.domain.model.system.Dic;
import net.greatsoft.core.service.system.DicService;
import net.greatsoft.core.web.dto.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/dic")
public class DicController {
	@Autowired
	private DicService dicService;
	/**
	 * 查询字典表
	 * @return
	 */
	@GetMapping(value="/query")
	public ResultDto queryDicsByType(){
		Map<String,Object> result = new HashMap<String,Object>();
		List<Dic> dics = dicService.findDics();
		result.put("dics", dics);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	@PostMapping(value="/update")
	public ResultDto updateDic(@RequestBody Dic dic){
		Map<String,Object> result = new HashMap<String,Object>();
		Dic d = dicService.updateDic(dic);
		result.put("dic", d);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", result);
	}
	
	@PostMapping(value="/delete")
	public ResultDto deleteDic(@RequestBody Dic dic){
		dicService.deleteDic(dic);
		return new ResultDto(ResultDto.CODE_SUCCESS, "", null);
	}
}
