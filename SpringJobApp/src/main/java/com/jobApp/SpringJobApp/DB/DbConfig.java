package com.jobApp.SpringJobApp.DB;

import com.jobApp.SpringJobApp.Model.JobPost;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class DbConfig {
    private final SessionFactory sessionFactory;

    DbConfig() {
        sessionFactory = new Configuration().addAnnotatedClass(JobPost.class).configure().buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
