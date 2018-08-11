<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bank Site</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/MyCss.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#"> <img
			src="${pageContext.request.contextPath}/pics/brand.png" width="30"
			height="30" alt="">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Dropdown </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="#">Action</a> <a
							class="dropdown-item" href="#">Another action</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Something else here</a>
					</div></li>
				<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a>
				</li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0 myhoverbutton"
					type="submit">Search</button>
			</form>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col">
				<button type="button" class="mybut btn btn-primary" id="newUser">newUser</button>
			</div>
			<div class="col-6">
				<button type="button" class="mybut btn btn-primary" id="newAccount">newAccount</button>
			</div>
			<div class="col">
				<button type="button" class="mybut btn btn-primary"
					id="newTransaction">newTransaction</button>
			</div>
		</div>
	</div>


	<div id="id01" class="modal"
		style="display: none; position: fixed; z-index: 1; left: 0; top: 0; width: 100%; height: 100%; overflow: auto; background-color: rgb(0, 0, 0); /* Fallback color */ background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */ padding-top: 60px;">
		<form id="id01form" class="modal-content animate" method="post"
			style="border: 3px solid #f1f1f1;">
			<div class="imgcontainer">
				<span onclick="document.getElementById('id01').style.display='none'"
					class="close" title="Close Modal">&times;</span> <img
					src="https://www.w3schools.com/howto/img_avatar2.png" alt="Avatar"
					class="avatar" style="width: 30%">
			</div>

			<div class="container">
				<label for="uname"><b>Username</b></label> <input type="text"
					placeholder="Enter Username" name="uname" required
					style="width: 100%; padding: 12px 20px; margin: 8px 0; display: inline-block; border: 1px solid #ccc; box-sizing: border-box;">
				<label for="psw"><b>Password</b></label> <input type="password"
					placeholder="Enter Password" name="psw" required
					style="width: 100%; padding: 12px 20px; margin: 8px 0; display: inline-block; border: 1px solid #ccc; box-sizing: border-box;">

				<button type="submit">Login</button>
			</div>
		</form>
	</div>
	<script>
		var modal = document.getElementById('id01');
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
		
		sessionStorage.setItem("hossein", "Yes");
		alert(sessionStorage.getItem("hossein"));
		 
		$(".mybut").click(function() {
			var $elemId = $(this).attr("id");
			if($elemId == "newUser")
			{
				document.getElementById('id01').style.display = 'block'
				document.getElementById('id01form').action = "/FanaMVC/" + $elemId
			}
			else if($elemId == "newAccount"){
				document.getElementById('id01form').action = "/FanaMVC/" + $elemId
			}
			else if($elemId == "newTransaction"){
			}
			});
	</script>
	<script type="text/javascript">
	var Msg = '<%=session.getAttribute("accountNumber")%>
		';
		if (Msg != "null") {
			function alertName() {
				alert("Form has been submitted" + msg);
			}
		}
	</script>

</body>
</html>