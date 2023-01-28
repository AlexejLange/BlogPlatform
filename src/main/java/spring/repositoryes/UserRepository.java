package spring.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.domians.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
