package perfekttest.config;

/**
 *
 * @author dglunts
 */
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {
    
    @Override
    public void onStartup (ServletContext servletContext) throws ServletException {
    
        AnnotationConfigWebApplicationContext ctx
        = new AnnotationConfigWebApplicationContext();    
        ctx.setConfigLocation("perfekttest.config");        
        
        servletContext.addListener(new ContextLoaderListener(ctx));
        
        ServletRegistration.Dynamic dispatcher = 
            servletContext.addServlet("dispatcher-servlet", 
                new DispatcherServlet(ctx));
        
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
