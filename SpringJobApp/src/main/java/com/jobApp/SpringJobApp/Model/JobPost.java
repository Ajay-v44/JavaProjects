package com.jobApp.SpringJobApp.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "job_post")
//@Access(AccessType.PROPERTY)
public class JobPost implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postid")
    private Integer postId;

    @Column(name = "postprofile")
    private String postProfile;
    
    @Column(name = "postdesc")
    private String postDesc;
    
    @Column(name = "reqexperience")
    private Integer reqExperience;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "job_tech_stack", 
        joinColumns = @JoinColumn(name = "job_id")
    )
    @Column(name = "tech_stack")
    private List<String> postTechStack = new ArrayList<>();
    
    // Default constructor required by JPA
    public JobPost() {
    }
    
    public JobPost(String postProfile, String postDesc, Integer reqExperience, List<String> postTechStack) {
        this.postProfile = postProfile;
        this.postDesc = postDesc;
        this.reqExperience = reqExperience;
        if (postTechStack != null) {
            this.postTechStack = postTechStack;
        }
    }
    
    // Explicit getters and setters to ensure proper access
    public Integer getPostId() {
        return postId;
    }
    
    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}
