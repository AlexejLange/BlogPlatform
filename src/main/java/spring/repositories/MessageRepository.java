package spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.domains.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}

