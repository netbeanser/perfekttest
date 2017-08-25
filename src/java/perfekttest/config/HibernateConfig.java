package perfekttest.config;

import javax.sql.DataSource;
import java.util.Properties;

import org.hibernate.SessionFactory;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

import org.springframework.orm.jpa.JpaTransactionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author dglunts
 */
@Configuration
@EnableJpaRepositories(basePackages = {"perfekttest.repo"})
@ComponentScan({ "perfekttest.model" })
@PropertySource(value= {"classpath:/application.properties"})
public class HibernateConfig {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private Environment env;
        
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource  dataSource = new DriverManagerDataSource();
                
        dataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
        dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));        
        dataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));
        dataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));
        
        return dataSource;
    } 
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan(new String[]{ "perfekttest.model" });
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);
        emf.setJpaProperties(hibernateProperties());
        return emf;
        
   }    
    
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();   
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "perfekttest.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }  
       
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect",env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql",env.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql",env.getRequiredProperty("hibernate.format_sql"));
        return properties;        
    }
/*     
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    } 
*/

    @Bean
    @Autowired
    public JpaTransactionManager transactionManager(SessionFactory s) {
       JpaTransactionManager txManager = new JpaTransactionManager();
//       txManager.setSessionFactory(s);
//        txManager.setDataSource(dataSource());
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());        
       return txManager;
       
    } 

    
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
      return new PersistenceExceptionTranslationPostProcessor();
   }    
    
}
