package net.greatsoft.core.repository.system;

import java.util.List;

import net.greatsoft.core.domain.model.system.Dic;
import net.greatsoft.core.domain.model.system.Message;
import net.greatsoft.core.domain.model.system.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,String>{
	@Override
	Message save(Message msg);
	
	Message findMessageById(String id);
	
	@Query("from Message t where t.createOrgId = ?1 order by t.createTime")
	List<Message> findMessageList(String orgId);
	
	@Query("from Message t")
	List<Message> findAllMessageList();
}
