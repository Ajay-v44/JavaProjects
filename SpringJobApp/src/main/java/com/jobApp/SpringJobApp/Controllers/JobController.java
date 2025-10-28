package com.jobApp.SpringJobApp.Controllers;
import com.jobApp.SpringJobApp.Model.JobPost;
import com.jobApp.SpringJobApp.Service.JobServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class JobController {
    @Autowired
    private JobServices jobServices;
    @GetMapping({"/","/home"})
    public String home(){
        return "Home";
    }

    @GetMapping("addjob")
    public String addJob(){
        return "addjob";
    }

    @PostMapping("handleForm")
    public String handleForm(JobPost jobPost){
        jobServices.addJob(jobPost);
        return "success";
    }

    @GetMapping("viewalljobs")
    public String viewAllJobs(Model model){
        List<JobPost> jobs=jobServices.getAllJobs();
        model.addAttribute("jobPosts",jobs);
        return "viewalljobs";
    }
}
