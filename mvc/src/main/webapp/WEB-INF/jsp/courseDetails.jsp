<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<c:import url="/WEB-INF/jsp/common/header.jsp" />

</header>

<body>
	<div class="center">
		<h3>Curriculum Page</h3>

		<div class="container">
			<div class="Image">
				<a class="image"> <img id="myImage"
					src="<c:url value= "/img/${course.courseId }.jpg" />" />
				</a>
			</div>




				<h2>${course.courseName}</h2>
				<p>${category.name }</p>

				<p>Duration: ${course.courseDuration} week(s)</p>
				<p>${course.courseDescription}</p>

				<p>
				<h2>Curriculum</h2>
				<c:forEach var="curriculum" items="${course.curriculumListByCourse}">
					<c:out value="${curriculum.curriculumName }"></c:out>
					<br>
					<c:forEach var="homework" items="${curriculum.homeworkList }">


						<form method="POST" action="${courseDetailsURL}">
							<c:set var="buttonStatus" value="Complete" />
							<c:if test="${homework.complete == true}">
								<c:set var="buttonStatus" value="Incomplete" />
							</c:if>
							<button id="hwComplete" type="submit">${buttonStatus}</button>
							<c:url var="courseDetailsURL" value="/courseDetails"></c:url>
							<input type="hidden" name="id" value="${course.courseId}" /> <input
								type="hidden" name="complete" value="${homework.complete}" />
						</form>

						<c:out value="${homework.homeworkName }"></c:out>

						<%-- <form method="POST" action="${courseDetailsURL}">
			<button id="hwReopen" type="submit">Re-open</button><br>
			<c:url var="courseDetailsURL" value="/courseDetails">
			<c:param name="id">${course.courseId}</c:param></c:url>
			</form> --%>


					</c:forEach>
				</c:forEach>


				<%-- <li><c:out
						value="${homework.homeworkName} Due Date: ${homework.dueDate }" /></li>
				<input type="checkbox" id="hwComplete"
					name="${homework.homeworkName }" value="Complete?">

			</c:forEach> --%>
				</ul>

			</div>
</body>
