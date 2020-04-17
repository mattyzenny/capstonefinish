<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<script type="text/javascript">
	$(document).ready(function() {

		$("form").validate({

			rules : {
				userName : {
					required : true
				},
				password : {
					required : true
				}
			},
			messages : {
				confirmPassword : {
					equalTo : "Passwords do not match"
				}
			},
			errorClass : "error"
		});
	});
</script>
<style>
body {
	background-image: url("img/login1.jpg");
	z-index: 1;
	background-size: cover;
	background-repeat: repeat-n;
}
</style>

<body>
	<div class="row">
		<div class="col-sm-4">
			<c:url var="formAction" value="/login" />
			<form method="POST" action="${formAction}">


				<input type="hidden" name="destination" value="/StudentPortal" /> <input
					type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
				<div class="form-group">
					<label for="userName">User Name: </label> <input type="text"
						id="userName" name="userName" placeHolder="User Name"
						class="form-control" />
					<form:errors path="username" cssClass="error" />
				</div>
				<div class="form-group">
					<label for="password">Password: </label> <input type="password"
						id="password" name="password" placeHolder="Password"
						class="form-control" />
					<form:errors path="password" cssClass="error" />
				</div>
				<button type="submit" class="btn-primary">Login</button>
			</form>



		</div>
	</div>
</body>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />