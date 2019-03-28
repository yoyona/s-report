package net.greatsoft.core.service.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greatsoft.core.domain.model.task.Period;
import net.greatsoft.core.repository.task.PeriodRepository;

/**
 * 表期的服务层
 * @author litian
 *
 */
@Service
public class PeriodService {

	@Autowired
	private PeriodRepository  periodRepository;

	/**
	 * 通过表期的id来获取表期的对象
	 * @param periodId
	 * @return
	 */
	public Period getPeriodById(Long periodId){
		return this.periodRepository.getPeriodByPeriodId(periodId);
	}

}
