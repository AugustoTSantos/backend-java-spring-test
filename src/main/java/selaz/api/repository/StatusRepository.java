package selaz.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import selaz.api.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{
    
}
