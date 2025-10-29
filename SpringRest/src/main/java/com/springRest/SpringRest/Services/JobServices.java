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

    public String addJob(JobPost jobPost){
        return repo.addJob(jobPost);
    }
    public List<JobPost> getAllJobs(){
        return repo.getAllJobs();
    }

    public JobPost getJob(int jobId) {
        return  repo.jetJob(jobId);
    }

    public JobPost updateJob(JobPost jobPost) {
        return  repo.updateJob(jobPost);

    }

    public String deleteJob(int jobId) {
        return repo.deleteJob(jobId);
    }
}

