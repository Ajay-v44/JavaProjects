package com.jobApp.SpringJobApp.Repository;

import com.jobApp.SpringJobApp.DB.DbConfig;
import com.jobApp.SpringJobApp.Model.JobPost;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JobRepo {

    List<JobPost> jobs=new ArrayList<>(List.of(
            new JobPost("Java Developer", "Must have good experience...", 2,
                    List.of("Core Java", "J2EE", "Spring Boot", "Hibernate"))));

    @Autowired
    DbConfig db;

    public List<JobPost> getAllJobs(){
        return jobs;
    }
    public void addJob(JobPost jobPost){
        try(Session session= db.getSession()){
            Transaction transaction=session.beginTransaction();
//            session.persist(jobPost);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        jobs.add(jobPost);
    }
}
