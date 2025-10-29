package com.springRest.SpringRest.Controllers;

import com.springRest.SpringRest.Models.JobPost;
import com.springRest.SpringRest.Services.JobServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class JobControllers {

    @Autowired
    JobServices jobServices;

    @GetMapping("get_all_jobs")
    public List<JobPost> getAllJobs() {
        return jobServices.getAllJobs();
    }

    @GetMapping("get_job/{jobId}")
    public JobPost getJob(@PathVariable int jobId){
        return jobServices.getJob(jobId);
    }

    @PostMapping("addJob")
    public String addJob(@RequestBody JobPost jobPost){
        return jobServices.addJob(jobPost);
    }

    @PutMapping("updateJob")
    public JobPost updateJob(@RequestBody JobPost jobPost){
        return jobServices.updateJob(jobPost);
    }
    @DeleteMapping("deleteJob/{jobId}")
    public String deleteJob(@PathVariable int jobId){
        return jobServices.deleteJob(jobId);
    }

    @GetMapping("jobPosts/keyword/{keyword}")
    public List<JobPost> searchByKeyWord(@PathVariable String keyword){
       return jobServices.search(keyword);
    }
}
