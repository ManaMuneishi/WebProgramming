<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.0/css/bootstrap.min.css" integrity="sha384-PDle/QlgIONtM1aqA2Qemk5gPOE7wFq8+Em+G/hmo5Iq0CCmYZLv3fVRDJ4MMwEA" crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">

<title>ログイン画面</title>
</head>
<body>
	<nav class="nav">
  		<ul class="nav justify-content-end">
 			 <li class="nav-item">
   			 	<a class="nav-link active" href="#"></a>
 			 </li>

  			<li class="nav-item">
  				  <a class="nav-link" href="#"></a>

		</ul>
	</nav>

	<h1 class="loginPage">ログイン画面</h1>

		<div class="container">

		 ${errMsg}

			<div class="row">
   				 <div class="col-sm-4">
   				 </div>

   				 <div class="col-sm-4">
						<form  class="form-signin" action="Login_Servlet" method="post">
  							<div class="form-group">
   								 <label>ログインID</label>
   								 <input type="text" name="loginId" placeholder="id" required> <!--required 必須項目だよの意味 -->
 							 </div>

 							 <div class="form-group">
   								<label>パスワード</label>
   				 				<input type="password" name="password" placeholder="●●●●●" required>
 							 </div>
							<div class="text-center">
 								<button type ="submit" class="btn btn-primary">ログイン</button>
 							</div>
						</form>
				</div>
   				<div class="col-sm-4">
				</div>
			</div>
		</div>
</body>
</html>