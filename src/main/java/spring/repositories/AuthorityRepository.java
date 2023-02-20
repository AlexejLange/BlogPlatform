package spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.domains.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {}
