package perfekttest.security;

/**
 *
 * @author dglunts
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import perfekttest.model.Role;
//import perfekttest.model.User;
import perfekttest.repo.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    private final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);        
    
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("ModelUser to search: "+username);
        perfekttest.model.User modelUser = userRepository.findByUsername(username);
        if (modelUser == null){
            logger.error("ModelUserDetails " + username +" not found");
            throw new UsernameNotFoundException("ModelUser " + username +" not found");
        }
        

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : modelUser.getRoleList()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        org.springframework.security.core.userdetails.User authUser = 
        new org.springframework.security.core.userdetails.User(modelUser.getUsername(), modelUser.getPassword(), grantedAuthorities);        
        
        logger.info("authUser: " + authUser.toString());
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        return authUser;
    }
}
