package com.jobApp.SpringJobApp.DB;

import com.jobApp.SpringJobApp.Model.JobPost;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class DbConfig {
    private final SessionFactory sessionFactory;

    DbConfig() {
        Configuration configuration = new Configuration();
        
        // Set additional Hibernate properties
        Properties properties = new Properties();
        properties.put(Environment.PHYSICAL_NAMING_STRATEGY, "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        properties.put(Environment.IMPLICIT_NAMING_STRATEGY, "org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl");
        
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(JobPost.class);
        configuration.configure();
        
        sessionFactory = configuration.buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
