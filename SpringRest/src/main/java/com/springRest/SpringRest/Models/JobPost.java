package com.springRest.SpringRest.Models;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class JobPost implements Serializable {

    private Integer postId;

    private String postProfile;

    private String postDesc;

    private Integer reqExperience;

    private List<String> postTechStack = new ArrayList<>();

    public JobPost() {
    }

    public JobPost(Integer postId,String postProfile, String postDesc, Integer reqExperience, List<String> postTechStack) {
        this.postId=postId;
        this.postProfile = postProfile;
        this.postDesc = postDesc;
        this.reqExperience = reqExperience;
        if (postTechStack != null) {
            this.postTechStack = postTechStack;
        }
    }

}

