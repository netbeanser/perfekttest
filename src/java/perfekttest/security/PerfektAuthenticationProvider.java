package perfekttest.security;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.ArrayList;

import perfekttest.repo.UserRepository;
import perfekttest.model.User;
/**
 *
 * @author dglunts
 */
@Component        
public class PerfektAuthenticationProvider implements AuthenticationProvider{
    
    @Autowired
    private UserRepository userRepository;
    
    private final Logger logger = LoggerFactory.getLogger(PerfektAuthenticationProvider.class);    
    
    
    
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
  
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();          
        
        User user = userRepository.findByUsername(name);
        
        Md5PasswordEncoder passEncoder = new Md5PasswordEncoder();
        if (passEncoder.encodePassword(password,null)
                .equalsIgnoreCase(user.getPassword())) {
            
            logger.info("AutenticayionProvider: username="
            +name+" password="+password
            +" dbEncPassword="+user.getPassword()
            +" Md5EncodedPassword="+passEncoder.encodePassword(password,null));
            UsernamePasswordAuthenticationToken token = 
                    new UsernamePasswordAuthenticationToken(name, password);
//, user.getRoleSet());
            token.setAuthenticated(true);
            
            return new UsernamePasswordAuthenticationToken(
              name, password, new ArrayList<>());                       
        } else {
            return null;
        }            
    }    
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
          UsernamePasswordAuthenticationToken.class);
    }        
    
}
