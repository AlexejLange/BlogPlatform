package spring.repositoryes;

import org.springframework.data.repository.CrudRepository;
import spring.domians.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}
