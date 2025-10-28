<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>CareerConnect Job Portal</title>

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
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
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ms-auto">
				<li class="nav-item">
					<a class="nav-link active" href="home"><i class="fas fa-home me-1"></i> Home</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="viewalljobs"><i class="fas fa-list me-1"></i> All Jobs</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#"><i class="fas fa-envelope me-1"></i> Contact</a>
				</li>
			</ul>
		</div>
	</div>
</nav>

<!-- Hero Section -->
<div class="container-fluid py-5 mb-4" style="background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));">
	<div class="container">
		<div class="row align-items-center">
			<div class="col-lg-6 text-white">
				<h1 class="display-4 fw-bold mb-4 slide-up">Find Your Dream Job</h1>
				<p class="lead mb-4 slide-up">Explore opportunities and connect with top employers in the tech industry.</p>
				<div class="d-flex gap-3 slide-up">
					<a href="/viewalljobs" class="btn btn-light btn-lg">Browse Jobs</a>
					<a href="/addjob" class="btn btn-outline-light btn-lg">Post a Job</a>
				</div>
			</div>
			<div class="col-lg-6 d-none d-lg-block">
				<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg" class="img-fluid" alt="Job Portal" style="max-height: 300px;">
			</div>
		</div>
	</div>
</div>

<!-- Feature Cards -->
<div class="container mt-4 mb-5">
	<h2 class="text-center mb-4">What would you like to do?</h2>
	<div class="feature-container">
		<!-- Feature Card 1 -->
		<div class="feature-card fade-in">
			<div class="icon">
				<i class="fas fa-search"></i>
			</div>
			<h3>Find Jobs</h3>
			<p>Browse through our extensive collection of job listings from top companies.</p>
			<a href="/viewalljobs" class="btn btn-primary">View All Jobs</a>
		</div>

		<!-- Feature Card 2 -->
		<div class="feature-card fade-in">
			<div class="icon">
				<i class="fas fa-plus-circle"></i>
			</div>
			<h3>Post a Job</h3>
			<p>Create a new job listing and reach thousands of qualified candidates.</p>
			<a href="/addjob" class="btn btn-primary">Add Job</a>
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
</body>
</html>