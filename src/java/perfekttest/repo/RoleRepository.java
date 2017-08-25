package perfekttest.repo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import perfekttest.model.Role;

/**
 *
 * @author dglunts
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> { 
    
    public Role findByName(String name);
    
}
