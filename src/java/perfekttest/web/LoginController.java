package perfekttest.web;

/**
 *
 * @author dglunts
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import perfekttest.repo.UserRepository;

@Controller
@RequestMapping("/")
public class LoginController {     
       
    @Autowired
    private UserRepository userRepository;
            
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = {"/","/home"},method = RequestMethod.GET)
    public String home(){
        return "home";
    }
       
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model){
        return "login";
    }    
            
    /*LogoutHandler: invalidates HttpSession and clears 
    authentication data
    */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            SecurityContextLogoutHandler sclh = 
                    new SecurityContextLogoutHandler();
            sclh.setInvalidateHttpSession(true);
            sclh.setClearAuthentication(true);            
            sclh.logout(request, response, auth);
        }
        return "home";
    }  
        
    @RequestMapping(value="/admin",method=RequestMethod.GET)
    public String admin(){
        return "admin";
    }
    
    @RequestMapping(value="/UnderConstruction",method=RequestMethod.GET)
    public String underConstruction(){
        return "UnderConstruction";
    }
        
}
