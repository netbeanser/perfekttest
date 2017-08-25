package perfekttest.repo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import perfekttest.model.User;
import perfekttest.model.Role;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author dglunts
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    public User findByUsername(String username); 
    
    public List<User> findAll();
    
    @Query(value="DELETE FROM favorites WHERE book_id=:book_id "
    +" AND user_id=(SELECT id FROM users "
    +" WHERE username=:username)", nativeQuery=true)
    @Modifying(clearAutomatically=false)
    @Transactional    
    public void deleteFromFavorites(@Param("book_id") Integer book_id,
            @Param("username") String username);           

    @Query(value="INSERT INTO favorites (book_id,user_id) "
    +" VALUES (:book_id,"
    +" (SELECT id FROM users "
    +" WHERE username=:username))", nativeQuery=true)
    @Modifying(clearAutomatically=false)
    @Transactional 
    public void addToFavorites(@Param("book_id") Integer book_id,
            @Param("username") String username);
    
    @Query(value="SELECT u.id,u.username,u.password,r.name FROM users u "
    +" JOIN user_role ur ON u.id=ur.user_id "
    +"JOIN role r ON r.id=ur.role_id",nativeQuery=true)
    public List<Object[]> getUsersWithRoles();
    
}
