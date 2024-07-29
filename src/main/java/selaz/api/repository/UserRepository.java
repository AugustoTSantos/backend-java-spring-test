package selaz.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import selaz.api.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
