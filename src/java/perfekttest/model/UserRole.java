package perfekttest.model;

/**
 *
 * @author dglunts
 * @Description auxiliary class for composed user+role non-entity
 * request
 */
public class UserRole {
    private Integer user_id;
    private String username;
    private String password;
    private String rolename;
    
    UserRole(){
        
    }

    public UserRole(Integer user_id,String username, String password, String rolename) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.rolename = rolename;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "UserRole{" + "username=" + username + ", password=" + password + ", rolename=" + rolename + '}';
    }
    
}
