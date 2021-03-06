<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<header>
	<c:import url="/WEB-INF/jsp/common/header.jsp" />

</header>
<head>

 <style>
body {
  background-image: url("img/backgroundImage.png");
    z-index: 1;  
  background-repeat: repeat-n;
    background-size: cover; 
}
</style>
</head>
<body >

<div class="sidenav">
	<ul>
		<c:url var="formAction" value="/StudentPortal" />
		<form method="POST" action="${formAction}">
			<c:forEach var="category" items="${categories}">
				<div class="categoryName">
					<li><c:out value="${category.name}" />
				</div>
				<ul>
					<c:forEach var="course" items="${category.courseListByCategory }">
						<c:url var="courseDetailsURL" value="/courseDetails">
							<c:param name="id">${course.courseId}</c:param>
						</c:url>
						<div>
							<li class="courseName"><p class="courseList"></p>
								<a href="${courseDetailsURL}">${course.courseName }</a>
							</li>
						</div>
					</c:forEach>
				</ul>
				</li>
			</c:forEach>
		</form>
</div>


</div>





<div class="center">

	<h2>Welcome To Your Student Portal</h2>







	<h3>After clicking on your class link, you can:</h3>
	<p>Find List of Registered Courses</p>
	<p>View Curriculum</p>
	<p>Submit Homework</p>
	<p>Check Your Grades
	<p>
</div>
</body>


