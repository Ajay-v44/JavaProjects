<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Browse Jobs - CareerConnect</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" type="text/css" href="style1.css">
</head>
<body>

<!-- Modern Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-white sticky-top">
    <div class="container">
        <a class="navbar-brand" href="#">
            <i class="fas fa-briefcase me-2"></i>CareerConnect
        </a>
        <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="home"><i class="fas fa-home me-1"></i> Home</a></li>
                <li class="nav-item"><a class="nav-link active" href="viewalljobs"><i class="fas fa-list me-1"></i> All Jobs</a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="fas fa-envelope me-1"></i> Contact</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Page Header -->
<div class="container-fluid py-4 mb-4" style="background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));">
    <div class="container">
        <div class="row">
            <div class="col-12 text-center text-white">
                <h1 class="display-5 fw-bold">Available Job Opportunities</h1>
                <p class="lead">Discover your next career move with our curated job listings</p>
            </div>
        </div>
    </div>
</div>

<!-- Search and Filter Section -->
<div class="container mb-4">
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="search-box p-3 bg-white rounded shadow-sm">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search jobs by title, skills or keywords..." id="job-search">
                    <button class="btn btn-primary" type="button">
                        <i class="fas fa-search"></i> Search
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container mb-5">
    <div class="row">
        <!-- Filter Sidebar -->
        <div class="col-lg-3 mb-4">
            <div class="filter-section p-3 rounded">
                <h5 class="mb-3"><i class="fas fa-filter me-2"></i>Filters</h5>
                
                <div class="mb-3">
                    <label class="form-label fw-bold">Experience Level</label>
                    <div class="form-check">
                        <input class="form-check-input filter-check" type="checkbox" value="0-2" id="exp1">
                        <label class="form-check-label" for="exp1">0-2 years</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input filter-check" type="checkbox" value="3-5" id="exp2">
                        <label class="form-check-label" for="exp2">3-5 years</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input filter-check" type="checkbox" value="5+" id="exp3">
                        <label class="form-check-label" for="exp3">5+ years</label>
                    </div>
                </div>
                
                <div class="mb-3">
                    <label class="form-label fw-bold">Technologies</label>
                    <div class="form-check">
                        <input class="form-check-input filter-check" type="checkbox" value="Java" id="tech1">
                        <label class="form-check-label" for="tech1">Java</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input filter-check" type="checkbox" value="JavaScript" id="tech2">
                        <label class="form-check-label" for="tech2">JavaScript</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input filter-check" type="checkbox" value="Python" id="tech3">
                        <label class="form-check-label" for="tech3">Python</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input filter-check" type="checkbox" value="React" id="tech4">
                        <label class="form-check-label" for="tech4">React</label>
                    </div>
                </div>
                
                <button class="btn btn-outline-primary btn-sm w-100">
                    <i class="fas fa-sync-alt me-1"></i> Reset Filters
                </button>
            </div>
        </div>
        
        <!-- Job Listings -->
        <div class="col-lg-9">
            <div class="row" id="job-list">
                <c:forEach var="jobPost" items="${jobPosts}">
                    <div class="col-md-6 mb-4 job-card-container" 
                         data-experience="${jobPost.reqExperience}" 
                         data-tech="${jobPost.postTechStack}">
                        <div class="job-card h-100 p-4">
                            <div class="job-card-header">
                                <h5 class="job-title">${jobPost.postProfile}</h5>
                            </div>
                            <div class="job-card-body">
                                <p class="job-description">${jobPost.postDesc}</p>
                                <div class="job-meta">
                                    <span class="job-experience">
                                        <i class="fas fa-user-clock me-1"></i> ${jobPost.reqExperience} years
                                    </span>
                                </div>
                                <div class="job-tech-stack">
                                    <c:forEach var="tech" items="${jobPost.postTechStack}">
                                        <span class="tech-badge">${tech}</span>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="job-card-footer mt-3">
                                <button class="btn btn-sm btn-outline-primary">
                                    <i class="fas fa-bookmark me-1"></i> Save
                                </button>
                                <button class="btn btn-sm btn-primary">
                                    <i class="fas fa-paper-plane me-1"></i> Apply
                                </button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            
            <!-- Empty State (shown when no jobs match filters) -->
            <div id="empty-state" class="text-center py-5 d-none">
                <i class="fas fa-search fa-3x text-muted mb-3"></i>
                <h4>No jobs match your criteria</h4>
                <p class="text-muted">Try adjusting your filters or search terms</p>
                <button class="btn btn-outline-primary">
                    <i class="fas fa-sync-alt me-1"></i> Reset Filters
                </button>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<footer class="bg-dark text-white py-4 mt-5">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h5><i class="fas fa-briefcase me-2"></i>CareerConnect</h5>
                <p class="small">Connecting talent with opportunity</p>
            </div>
            <div class="col-md-6 text-md-end">
                <p class="small">© 2023 CareerConnect. All rights reserved.</p>
                <div>
                    <a href="#" class="text-white me-2"><i class="fab fa-facebook-f"></i></a>
                    <a href="#" class="text-white me-2"><i class="fab fa-twitter"></i></a>
                    <a href="#" class="text-white me-2"><i class="fab fa-linkedin-in"></i></a>
                    <a href="#" class="text-white"><i class="fas fa-globe"></i></a>
                </div>
            </div>
        </div>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        // Search functionality
        $("#job-search").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $(".job-card-container").filter(function() {
                var matches = $(this).text().toLowerCase().indexOf(value) > -1;
                $(this).toggle(matches);
            });
            
            updateEmptyState();
        });
        
        // Filter functionality
        $(".filter-check").on("change", function() {
            filterJobs();
        });
        
        function filterJobs() {
            // Get selected experience filters
            var selectedExp = [];
            $("input[id^='exp']:checked").each(function() {
                selectedExp.push($(this).val());
            });
            
            // Get selected tech filters
            var selectedTech = [];
            $("input[id^='tech']:checked").each(function() {
                selectedTech.push($(this).val());
            });
            
            // Show all jobs if no filters selected
            if (selectedExp.length === 0 && selectedTech.length === 0) {
                $(".job-card-container").show();
            } else {
                // Filter jobs based on selections
                $(".job-card-container").each(function() {
                    var jobExp = $(this).data("experience");
                    var jobTech = $(this).data("tech");
                    
                    var expMatch = selectedExp.length === 0;
                    var techMatch = selectedTech.length === 0;
                    
                    // Check experience match
                    if (selectedExp.length > 0) {
                        for (var i = 0; i < selectedExp.length; i++) {
                            var range = selectedExp[i].split("-");
                            if (range.length === 2) {
                                if (jobExp >= parseInt(range[0]) && jobExp <= parseInt(range[1])) {
                                    expMatch = true;
                                    break;
                                }
                            } else if (range[0] === "5+") {
                                if (jobExp >= 5) {
                                    expMatch = true;
                                    break;
                                }
                            }
                        }
                    }
                    
                    // Check tech match
                    if (selectedTech.length > 0) {
                        for (var i = 0; i < selectedTech.length; i++) {
                            if (jobTech && jobTech.indexOf(selectedTech[i]) > -1) {
                                techMatch = true;
                                break;
                            }
                        }
                    }
                    
                    $(this).toggle(expMatch && techMatch);
                });
            }
            
            updateEmptyState();
        }
        
        // Show empty state if no jobs visible
        function updateEmptyState() {
            if ($(".job-card-container:visible").length === 0) {
                $("#empty-state").removeClass("d-none");
            } else {
                $("#empty-state").addClass("d-none");
            }
        }
        
        // Reset filters
        $(".btn-outline-primary").click(function() {
            $(".filter-check").prop("checked", false);
            $("#job-search").val("");
            $(".job-card-container").show();
            $("#empty-state").addClass("d-none");
        });
    });
</script>
</body>
</html>