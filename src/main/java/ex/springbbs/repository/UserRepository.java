package ex.springbbs.repository;

import ex.springbbs.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Member, Long>{
    Member findByEmail(String email);
}
