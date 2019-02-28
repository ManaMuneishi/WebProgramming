<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.0/css/bootstrap.min.css" integrity="sha384-PDle/QlgIONtM1aqA2Qemk5gPOE7wFq8+Em+G/hmo5Iq0CCmYZLv3fVRDJ4MMwEA" crossorigin="anonymous">

<link rel="stylesheet" href="css/style.css">
<title>ユーザ削除確認</title>
</head>
<body>

  		<ul class="nav justify-content-end">

  		<li class="nav-bar-text">${userInfo.name} さん</li>

 		<li class="nav-item">
   			 <a class="nav-link" href="Logout_Servlet">ログアウト</a>
 		</li>
	</ul>

<h1 class="userDelete">ユーザ削除確認</h1>

	<div class="container">
		<div class="row">
   			 <div class="col-sm-4">
   			 </div>

   			 <div class="col-sm-4">
				<form action= "DeleteU_S" method="post">
					<input type="hidden" name="id" value="${detail.id}">
  						<div class="form-group">
   							<label for="exampleDropdownFormEmail2">ログインID:</label>
								${detail.loginId}
   							<div>を本当に削除してよろしいでしょうか。
 							</div>
 							 <div class="container">
 								<div class="row">

									<div class="col-sm-5">
										<button type="button" class="btn btn-primary btn-block" onclick="history.back()">キャンセル</button>
									</div>
									<div class="col-sm-2">
									</div>
									<div class="col-sm-5">
										<button type="submit" class="btn btn-danger btn-block">OK</button>
									</div>
								</div>
							</div>
						</div>
 					</form>
 			</div>

 			<div class="col-sm-4">
 			</div>
 		</div>
 	</div>
</body>
</html>