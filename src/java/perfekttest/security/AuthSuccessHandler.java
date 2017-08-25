package perfekttest.security;

/**
 *
 * @author dglunts
 * @Description Depending on logged in user role
 * redirects to admin page or book page
 * 
 */

import java.util.Collection;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest req,
            HttpServletResponse resp,
            Authentication auth) throws IOException {
        
        String targetUrl = getTargetUrl(auth);
        
        logger.info("AuthSuccess params: username="
                +req.getParameter("username")
                +" password="+req.getParameter("password")
                +"targetUrl="+targetUrl);        
        
        redirectStrategy.sendRedirect(req, resp, targetUrl);
        
    }
    
    private String getTargetUrl(Authentication auth){
        
        Boolean isUser = false;
        Boolean isAdmin = false;
        
        Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
        
        for (GrantedAuthority role : roles) {
            
            if (role.getAuthority().equalsIgnoreCase("ROLE_USER")){
                isUser = true;
                break;
            } else {
                if (role.getAuthority().equalsIgnoreCase("ROLE_ADMIN")) {
                    isAdmin = true;
                    break;
                }
            }
            
        }
        if (isUser) {
            return "/books/all?offset=0";
        } else if (isAdmin){
            return "/admin";
        } else {
            logger.error("Strange Auth:" + auth.toString());
            throw new IllegalStateException("Invalid Auth role. Auth: "
                    +auth.getPrincipal().toString());
        }
    }
    
}
