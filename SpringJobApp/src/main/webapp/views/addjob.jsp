<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Post a Job - CareerConnect</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css">
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
                <li class="nav-item"><a class="nav-link" href="viewalljobs"><i class="fas fa-list me-1"></i> All Jobs</a></li>
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
                <h1 class="display-5 fw-bold">Post a New Job</h1>
                <p class="lead">Create a job listing and find the perfect candidate for your position</p>
            </div>
        </div>
    </div>
</div>

<div class="container mb-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="form-section slide-up">
                <h2 class="text-center mb-4"><i class="fas fa-plus-circle me-2"></i>Job Details</h2>
                
                <form action="handleForm" method="post" class="needs-validation" novalidate>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="postId" class="form-label">Job ID</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="fas fa-id-card"></i></span>
                                <input type="text" class="form-control" id="postId" name="postId" required>
                                <div class="invalid-feedback">
                                    Please provide a job ID.
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="postProfile" class="form-label">Job Title</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="fas fa-briefcase"></i></span>
                                <input type="text" class="form-control" id="postProfile" name="postProfile" placeholder="e.g. Senior Java Developer" required>
                                <div class="invalid-feedback">
                                    Please provide a job title.
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="postDesc" class="form-label">Job Description</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="fas fa-align-left"></i></span>
                            <textarea class="form-control" id="postDesc" name="postDesc" rows="4" 
                                placeholder="Describe the responsibilities, qualifications, and benefits" required></textarea>
                            <div class="invalid-feedback">
                                Please provide a job description.
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="reqExperience" class="form-label">Required Experience (years)</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="fas fa-user-clock"></i></span>
                            <input type="number" class="form-control" id="reqExperience" name="reqExperience" min="0" max="20" required>
                            <div class="invalid-feedback">
                                Please specify required years of experience (0-20).
                            </div>
                        </div>
                    </div>

                    <div class="mb-4">
                        <label for="postTechStack" class="form-label">Tech Stack</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="fas fa-code"></i></span>
                            <select multiple class="form-select select2-tech" id="postTechStack" name="postTechStack" required>
                                <option value="Java">Java</option>
                                <option value="JavaScript">JavaScript</option>
                                <option value="Swift">Swift</option>
                                <option value="TypeScript">TypeScript</option>
                                <option value="Go">Go</option>
                                <option value="Kotlin">Kotlin</option>
                                <option value="Rust">Rust</option>
                                <option value="PHP">PHP</option>
                                <option value="HTML5">HTML5</option>
                                <option value="CSS3">CSS3</option>
                                <option value="GraphQL">GraphQL</option>
                                <option value="Raspberry Pi">Raspberry Pi</option>
                                <option value="Arduino">Arduino</option>
                                <option value="IoT (Internet of Things)">IoT (Internet of Things)</option>
                                <option value="Apache Kafka">Apache Kafka</option>
                                <option value="Elasticsearch">Elasticsearch</option>
                                <option value="Unity">Unity</option>
                                <option value="Game Development">Game Development</option>
                                <option value="Vue.js">Vue.js</option>
                                <option value="Angular">Angular</option>
                                <option value="React Native">React Native</option>
                                <option value="Flutter">Flutter</option>
                                <option value="Node.js">Node.js</option>
                                <option value="Express.js">Express.js</option>
                                <option value="Django">Django</option>
                                <option value="Flask">Flask</option>
                                <option value="Ruby on Rails">Ruby on Rails</option>
                                <option value="Laravel">Laravel</option>
                                <option value="TensorFlow">TensorFlow</option>
                                <option value="PyTorch">PyTorch</option>
                                <option value="Kubernetes">Kubernetes</option>
                                <option value="Docker">Docker</option>
                                <option value="Jenkins">Jenkins</option>
                                <option value="AWS (Amazon Web Services)">AWS (Amazon Web Services)</option>
                                <option value="Azure">Azure</option>
                                <option value="Google Cloud">Google Cloud</option>
                                <option value="DevOps">DevOps</option>
                                <option value="Blockchain">Blockchain</option>
                                <option value="Machine Learning">Machine Learning</option>
                                <option value="Artificial Intelligence">Artificial Intelligence</option>
                                <option value="Cybersecurity">Cybersecurity</option>
                                <option value="CISSP (Certified Information Systems Security Professional)">CISSP (Certified Information Systems Security Professional)</option>
                                <option value="CompTIA Security+">CompTIA Security+</option>
                                <option value="Certified Ethical Hacker (CEH)">Certified Ethical Hacker (CEH)</option>
                                <option value="Scrum">Scrum</option>
                                <option value="Agile">Agile</option>
                                <option value="Kanban">Kanban</option>
                            </select>
                            <div class="invalid-feedback">
                                Please select at least one technology.
                            </div>
                        </div>
                        <small class="text-muted">Hold Ctrl/Cmd to select multiple technologies</small>
                    </div>

                    <div class="d-grid gap-2">
                        <button type="submit" class="btn btn-primary btn-lg">
                            <i class="fas fa-paper-plane me-2"></i>Post Job
                        </button>
                    </div>
                </form>
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
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script>
    // Initialize Select2 for better multi-select experience
    $(document).ready(function() {
        $('.select2-tech').select2({
            placeholder: "Select technologies",
            allowClear: true
        });
    });
    
    // Form validation
    (function () {
        'use strict'
        var forms = document.querySelectorAll('.needs-validation')
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }
                    form.classList.add('was-validated')
                }, false)
            })
    })()
</script>
</body>
</html>