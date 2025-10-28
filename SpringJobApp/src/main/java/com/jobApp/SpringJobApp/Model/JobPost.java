package com.jobApp.SpringJobApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor // Keep this for Spring/JPA to create default instances
@Entity
@Table(name = "job_post")
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Let the DB generate the ID
    @Column(name ="postId" )
    private int postId;

    @Column(name ="postProfile" )
    private  String postProfile;
    @Column(name ="postDesc" )
    private String postDesc;
    @Column(name ="reqExperience" )
    private int reqExperience;
    @Column(name ="postTechStack" )
    private List<String> postTechStack;
    public JobPost(String postProfile, String postDesc, int reqExperience, List<String> postTechStack) {
        this.postProfile = postProfile;
        this.postDesc = postDesc;
        this.reqExperience = reqExperience;
        this.postTechStack = postTechStack;
    }

}
