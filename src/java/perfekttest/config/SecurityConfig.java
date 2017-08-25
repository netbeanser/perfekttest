package perfekttest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import perfekttest.security.UserDetailsServiceImpl;
import perfekttest.security.AuthSuccessHandler;
import perfekttest.security.AuthFailureHandler;

@Configuration
@EnableWebSecurity
@ComponentScan("perfekttest.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
     
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @Autowired
    private AuthSuccessHandler authSuccessHandler;
    
    @Autowired
    private AuthFailureHandler authFailureHandler;
    
    @Bean(name="md5PasswordEncoder")
    public Md5PasswordEncoder md5PasswordEncoder(){
        return new Md5PasswordEncoder();
    }
    
    @Bean(name="daoAuthenticationProvider")
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuth = new DaoAuthenticationProvider();
        daoAuth.setForcePrincipalAsString(true);
        daoAuth.setUserDetailsService(userDetailsService);
        daoAuth.setPasswordEncoder(md5PasswordEncoder());
        daoAuth.setSaltSource(null);
        return daoAuth;
    }
    
    /*Excludes resources from security filter chain */
    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/resources/css/**");
        web.ignoring().antMatchers("/resources/js/**");
        web.ignoring().antMatchers("/resources/img/**");
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    } 
 
    /*
    * Adds various filters to SpringSecurityFilterChain o http-level
      Methods are overriden according to their signature  
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {     
     
        http.authorizeRequests()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/books/**,/favchange").hasRole("USER")
        .and()
            .authorizeRequests()
            .antMatchers("/login","/","/registration")
            .permitAll()                
        .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login")                
            .usernameParameter("username")
            .passwordParameter("password")
            .successHandler(authSuccessHandler)
            .failureHandler(authFailureHandler)
        .and()
            .logout()
            .logoutUrl("/home")
            .logoutSuccessUrl("/home")
            .invalidateHttpSession(true)
            .deleteCookies("localhost")
        .and()
            .exceptionHandling()
            .accessDeniedPage("/403")
        .and()
            .headers()
            .frameOptions()
            .sameOrigin()
        .and()
            .csrf();

 }
}
