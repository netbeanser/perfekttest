/**
 *
 * @author dglunts
 * @Description Various webconfigs. Spring searches for 
 * WebMvcConfigurerAdapter interface implementation AUTOMATICALLY
 * at startup
 */

package perfekttest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages="perfekttest.web")
public class WebConfig extends WebMvcConfigurerAdapter {
    
    
/*
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/books").setViewName("books");
        registry.addViewController("/403").setViewName("403");
    }
*/    
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry){
        registry.addResourceHandler("/resources/css/**")
        .addResourceLocations("/resources/css/");
        registry.addResourceHandler("/resources/js/**")
        .addResourceLocations("/resources/js/");   
        registry.addResourceHandler("/resources/img/**")
        .addResourceLocations("/resources/img/");                
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
          return resolver;
    }         
}
