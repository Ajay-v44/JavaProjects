package com.springRest.SpringRest.Repository;

import com.springRest.SpringRest.Models.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public interface JobRepo extends JpaRepository<JobPost,Integer> {
    List<JobPost> findByPostProfileContainingOrPostDescContaining(String keyword,String keywordTwo);
}



//    List<JobPost> jobs = new ArrayList<>(List.of(
//            new JobPost(1, "Java Developer", "Must have good experience...", 2,
//                    List.of("Core Java", "J2EE", "Spring Boot", "Hibernate"))));
//
//    public List<JobPost> getAllJobs() {
//        return jobs;
//    }
//
//    public String addJob(JobPost jobPost) {
//        jobs.add(jobPost);
//        return "{'message':'Job Added'}";
//    }
//
//    public JobPost jetJob(int jobId) {
//        for (JobPost job : jobs) {
//            if (job.getPostId() == jobId)
//                return job;
//        }
//        return null;
//    }
//
//    public JobPost updateJob(JobPost jobPost) {
//        for (JobPost job : jobs) {
//            if (Objects.equals(job.getPostId(), jobPost.getPostId())) {
//                job.setPostProfile(jobPost.getPostProfile());
//                job.setPostDesc(jobPost.getPostDesc());
//            }
//        }
//        return jetJob(jobPost.getPostId());
//    }
//
//    public String deleteJob(int jobId) {
//        for (JobPost job : jobs) {
//            if (Objects.equals(job.getPostId(), jobId)) {
//                jobs.remove(job);
//
//            }
//        }
//        return "{'message':'Job Removed'}";
//    }

