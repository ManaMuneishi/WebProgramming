<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.0/css/bootstrap.min.css" integrity="sha384-PDle/QlgIONtM1aqA2Qemk5gPOE7wFq8+Em+G/hmo5Iq0CCmYZLv3fVRDJ4MMwEA" crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
<title>ユーザ情報更新</title>
</head>
<body>
	<ul class="nav justify-content-end">
 		<li class="nav-item">
  		<li class="nav-bar-text">${userInfo.name} さん</li>
 		<li class="nav-item">
   			 <a class="nav-link" href="Logout_Servlet">ログアウト</a>
 		</li>
	</ul>


	<h1 class="userUpdate">ユーザ情報更新</h1>
		<div class="container">
			<div class="row">
				<div class="col-sm-4">
				</div>
    			<div class="col-sm-4">
					<form action="UpdateU_Servlet">

  							<div class="form-group">
   								<label for="exampleDropdownFormEmail2">ログインID</label>
   								${detail.loginId}
 							 </div>

 							<div class="form-group">
   								<label for="exampleDropdownFormPassword2">パスワード</label>
   				 				<input name=pass1 class="form-control input-sm" type="text" class="form-control" >
 							</div>

 							<div class="form-group">
   								<label for="exampleDropdownFormPassword2">パスワード(確認)</label>
   				 				<input name=pass2 class="form-control input-sm" type="text" class="form-control" >
 							</div>

 							<div class="form-group">
   								<label for="exampleDropdownFormPassword2">ユーザー名</label>
   				 				<input name=name class="form-control input-sm" type="text" class="form-control">
 							</div>

 							<div class="form-group">
   								<label for="exampleDropdownFormPassword2">生年月日</label>
   				 				<input name=birth class="form-control input-sm" type="text" class="form-control">
 							</div>

							<div class="form-group">
 								<button type="submit" class="btn btn-primary" value="${detail.id}">更新</button>
 							</div>
 							<div class="form-group">
 					<button type="button" class="btn btn-secondary" onclick="history.back()">戻る</button>
 				</div>
						</form>
				</div>
				<div class="col-sm-4">
				</div>

			</div>
		</div>
</body>
</html>