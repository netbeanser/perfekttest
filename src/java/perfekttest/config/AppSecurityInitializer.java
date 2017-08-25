package perfekttest.config;

/**
 *
 * @author dglunts
 * @Description: incorporates Spring securityFilterChain into filterChain.
 * That's why POST method need not be implemented for login
 * in @Controller classes
 */
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class AppSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}
