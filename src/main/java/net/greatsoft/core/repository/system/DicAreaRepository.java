package net.greatsoft.core.repository.system;

import java.util.List;

import net.greatsoft.core.domain.model.system.DicArea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DicAreaRepository extends JpaRepository<DicArea,String>{
	
	@Query("from DicArea t where t.pId is null order by t.order")
	List<DicArea> queryTopAreas();
	
	@Query("from DicArea t where t.pId = ?1 order by t.order")
	List<DicArea> queryAreasByPid(String pId);
	
	@Query("from DicArea t order by t.order")
	List<DicArea> queryAreas();
	
	@Query("from DicArea t where t.id not like '%00%'")
	List<DicArea> queryAreasById();
}
