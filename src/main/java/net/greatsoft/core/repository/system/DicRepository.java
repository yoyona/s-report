package net.greatsoft.core.repository.system;

import java.util.List;

import net.greatsoft.core.domain.model.system.Dic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DicRepository extends JpaRepository<Dic,String>{
	
	@Query("from Dic t where t.type = ?1 order by t.order")
	List<Dic> queryDicsByType(String type);
	
	
	@Query("from Dic t where t.id = ?1 and t.type = ?2")
	List<Dic> queryDicsByIdAndType(String id,String type);

	@Query("from Dic t where t.type = ?1 and (select count(1) from Dic t1 where t1.type = ?1 and t1.pId = t.id) = 0  order by t.order")
	List<Dic> queryDicsWithouthl(String type);
}
