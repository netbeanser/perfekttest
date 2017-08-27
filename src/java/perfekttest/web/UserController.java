package perfekttest.web;
/**
 *
 * @author dglunts
 */

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import perfekttest.model.User;
import perfekttest.model.Role;
import perfekttest.model.UserRole;
import perfekttest.repo.UserRepository;
import perfekttest.repo.RoleRepository;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Md5PasswordEncoder md5PasswordEncoder;    
    
    @RequestMapping(value="/registration", method = RequestMethod.POST)
    public String addUser (@RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            Model model){
        
        User user = userRepository.findByUsername(username);
        if (user != null) {
            model.addAttribute("error", "User with name "
            +username+" already exists");

            return "/registration";
        }
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal().toString().equals(username)){
            model.addAttribute("error", "You are already logged in");
            return "/registration";            
        } 
        
        user = new User();
        user.setUsername(username);
        user.setPassword(md5PasswordEncoder.encodePassword(password, null));
        user.setEmail(email);

        Role role = roleRepository.findByName("ROLE_USER");       
        role.getUserList().add(user);
        roleRepository.saveAndFlush(role);
        
        User savedUser = userRepository.findByUsername(username);
        if (savedUser != null){
            logger.info("Saved User: "+savedUser.toString());
        }
        
        auth =  new UsernamePasswordAuthenticationToken(username,
                password,
                AuthorityUtils.createAuthorityList("ROLE_USER"));
        SecurityContextHolder.getContext().setAuthentication(auth);
        
        return "redirect:/books/all?offset=0";
    }    
    
    @RequestMapping(value="/registration", method = RequestMethod.GET)    
    public String registration(Model model){
        return "registration";
    }
            
    @RequestMapping(value="/userlist", method = RequestMethod.GET)
    public String   listUsers(Model model) {
        //See comment in BookController
        List<Object[]> objList = userRepository.getUsersWithRoles();
        List<UserRole> userList = objListToUserRoleLis(objList);
        
        model.addAttribute("userList", userList);
        return "userlist";
    }
    
    private List<UserRole> objListToUserRoleLis(List<Object[]> objList){
        
        List<UserRole> userList = new ArrayList<>(); 
        
        for (Object[] obj : objList){
            Integer user_id = Integer.valueOf(obj[0].toString());
            UserRole userRole = new UserRole(
                    user_id,
                    obj[1].toString(),
                    obj[2].toString(),
                    obj[3].toString());
            userList.add(userRole);
        }   
        return userList;        
        
    }
    
    
    
}
