package net.greatsoft.core.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;

import net.greatsoft.core.domain.model.task.Period;
/**
 * @author Litian
 * @date 2017年2月14日 下午1:33:28
 * @Description: 报表期Repository(根据任务类型创建对应报表期数据，如：月报创建12条报表期数据，季报创建4条报表期数据)
 * 
 */
public interface PeriodRepository extends JpaRepository<Period, Long>{
	/**
	 * 通过表期将表期的对象拿到
	 * @param periodId
	 * @return
	 */
	public Period getPeriodByPeriodId(Long periodId);
	
	
	
	public Period findPeriodByPeriodId(Long periodId);

}
