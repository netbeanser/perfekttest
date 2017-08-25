package perfekttest.security;

/**
 *
 * @author dglunts
 */
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.core.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest req,
            HttpServletResponse resp, AuthenticationException e) throws IOException{
        
        logger.info("AuthFailure: " + e.toString());
        
        logger.info("AuthFailure params: username="
                +req.getParameter("username")
                +" password="+req.getParameter("password"));
        
        redirectStrategy.sendRedirect(req, resp, "/login?error=true");
    }
}
