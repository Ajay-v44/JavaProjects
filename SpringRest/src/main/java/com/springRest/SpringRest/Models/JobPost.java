package com.springRest.SpringRest.Models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "jobPost")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPost implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    private String postProfile;

    private String postDesc;

    private Integer reqExperience;

    private List<String> postTechStack = new ArrayList<>();


}

