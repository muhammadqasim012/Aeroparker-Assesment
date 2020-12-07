<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Registration</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
</head>
<body>

	<%
		String err = "";
		if (request.getAttribute("errMsg") != null) {
			err = request.getAttribute("errMsg").toString();
		}
		String url = "Avalon City";
		if (request.getParameter("url") != null) {
			if (!request.getParameter("url").equals("")) {

				String urls = request.getParameter("url");
				if (urls.equals("Avalon City") || urls.equals("AceParks")) {
					url = urls;
				}
			}

		}
	%>

	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<form action="/AeroParkerRegistration/CustomerServlet" method="post"
					id="fileForm" role="form">
					<h2 style="margin-top: 20px;">Customer Registration Form</h2>
					<br>
					<p style='color: red;'>
						<%
							out.print(err);
						%>
					</p>
					<div class="form-group">
						<label for="email"><b>* Email Address: </b> </label> <input
							class="form-control" type="text" name="email" id="email"
							onchange="email_validate(this.value);" required />
						<div class="status" id="status"></div>
					</div>
					<input type="hidden" name="url" value="<%out.print(url);%>">
					<div class="form-group">
						<label for="title"><b>* Title: </b> </label> <input type="text"
							name="title" id="title" class="form-control title" maxlength="5"
							required />
					</div>

					<div class="form-group">
						<label for="firstname"><b>* First name: </b> </label> <input
							class="form-control" type="text" name="firstname" id="txt"
							maxlength="50" required />
						<div id="errFirst"></div>
					</div>

					<div class="form-group">
						<label for="lastname"><b>* Last name: </b> </label> <input
							class="form-control" type="text" name="lastname" id="txt"
							maxlength="50" required />
						<div id="errLast"></div>
					</div>

					<div class="form-group">
						<label for="address1"><b>* Address Line 1: </b> </label> <input
							class="form-control" type="text" name="address1" id="address1"
							maxlength="255" required />
						<div id="errAddress1"></div>
					</div>

					<div class="form-group">
						<label for="address2"><b>Address Line 2: </b> </label> <input
							class="form-control" type="text" name="address2" id="address2"
							maxlength="255" />
						<div id="errAddress2"></div>
					</div>

					<div class="form-group">
						<label for="city"><b>City: </b> </label> <input name="city"
							type="text" class="form-control city" maxlength="255" id="city" />
					</div>

					<div class="form-group">
						<label for="postcode"><b>* Post Code: </b> </label> <input
							name="postcode" type="text" class="form-control postcode"
							id="postcode" maxlength="10" required />
					</div>

					<div class="form-group">
						<label for="telephone"><b>Telephone Number: </b></label> <input
							name="telephone" type="text" class="form-control telephone"
							id="telephone" maxlength="20" />
					</div>

					<div class="form-group">
						<input class="btn btn-success" type="submit" name="submit_reg"
							value="Register">
					</div>

				</form>
				<!-- ends register form -->
			</div>
			<!-- ends col-6 -->



		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/validation.js"></script>
</body>
</html>