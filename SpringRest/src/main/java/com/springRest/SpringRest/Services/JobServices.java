package com.springRest.SpringRest.Services;

import com.springRest.SpringRest.Models.JobPost;
import com.springRest.SpringRest.Repository.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServices {

    @Autowired
    private JobRepo repo;

    public String addJob(JobPost jobPost) {
        repo.save(jobPost);
        return "Saved";
    }

    public List<JobPost> getAllJobs() {
        return repo.findAll();
    }

    public JobPost getJob(int jobId) {
        return repo.findById(jobId).orElse(null);
    }

    public JobPost updateJob(JobPost jobPost) {
        return repo.save(jobPost);

    }

    public String deleteJob(int jobId) {
        repo.deleteById(jobId);
        return "{'message':'Job Removed'}";
    }

    public List<JobPost> search(String keyword) {
        return repo.findByPostProfileContainingOrPostDescContaining(keyword,keyword);
    }
}

