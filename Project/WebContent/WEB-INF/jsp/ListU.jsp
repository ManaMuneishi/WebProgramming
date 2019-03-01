<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.0/css/bootstrap.min.css" integrity="sha384-PDle/QlgIONtM1aqA2Qemk5gPOE7wFq8+Em+G/hmo5Iq0CCmYZLv3fVRDJ4MMwEA" crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
<title>ユーザ一覧</title>
</head>
<body>

	<ul class="nav justify-content-end">

  			<li class="nav-bar-text">${userInfo.name} さん</li>
 		<li class="nav-item">
   			 <a class="nav-link" href="Logout_Servlet">ログアウト</a>
 		</li>
	</ul>


	<h1 class="userList">ユーザ一覧</h1>

	<div class="container">
		<div class="text-right">

			<a href="NewU_Servlet">新規登録</a>

		</div>
		<form action="ListU_Servlet" method="post">

				<div class="row">
    				<div class="col-sm-4"></div>
  					<div class="col-sm-4">
  						<div class="form-group">
   							 <label for="exampleDropdownFormEmail2">ログインID</label>
   							<input name="login_id" class="form-control input-sm" type="text" class="form-control" id="exampleDropdownFormEmail2">
 						</div>

 						<div class="form-group">
   							<label for="exampleDropdownFormPassword2">ユーザ名</label>
   				 			<input name ="name" class="form-control input-sm" type="text" class="form-control" id="exampleDropdownFormPassword2">
 						</div>
 						<div class="form-group">
   							<label for="exampleDropdownFormEmail2">生年月日</label>
   							<input name= "birth1" class="form-control input-sm" type="date" class="form-control" id="exampleDropdownFormEmail2" placeholder="年/月/日">
 						</div>
 						 <div class="form-group">
   							 <label for="exampleDropdownFormPassword2">〜</label>
   				 			<input name= "birth2" class="form-control input-sm" type="date" class="form-control" id="exampleDropdownFormPassword2" placeholder="年/月/日">
 				 		</div>
 					</div>
 				</div>
 			</div>
    		<div class="col-sm-4">
    		</div>
 			<div class="container">
 				<div class="row">
 					<div class="col-sm-4">
 					</div>
 					<div class="col-sm-4">
 					</div>
 					<div class="col-sm-4">
 						<button type="submit" class="btn btn-primary btn-lg btn-block" onclick="location.href='ListU_Servlet?${userInfo}'">検索</button>
 					</div>
 				</div>
 			</div>
		</form>

		<div class="dropdown-divider">
		</div>
		<div class="container">
			<table class="table">
 				 <thead class="thead-dark">
   					 <tr>
				      <th scope="col">ログインID</th>
				      <th scope="col">ユーザ名</th>
				      <th scope="col">生年月日</th>
				      <th scope="col"></th>
				      <th scope="row"></th>
   				 </tr>
 			 </thead>
 			 <tbody>

   				<c:forEach var="U_Beans" items="${userList}">
   				 <tr>
     				 <td>${U_Beans.loginId}</td>
                     <td>${U_Beans.name}</td>
                     <td>${U_Beans.birthDate}</td>

                     <!-- TODO 未実装；ログインボタンの表示制御を行う -->
                     <c:if test="${U_Beans.id = 1}">
				      <td>
				     		 <a class="btn btn-primary" href="DetailU_S?id=${U_Beans.id}">詳細</a>
				     		 <a class="btn btn-success" href="UpdateU_Servlet?id=${U_Beans.id}">更新</a>
				     		 <a class="btn btn-danger" href="DeleteU_S?id=${U_Beans.id}">削除</a>
				      </td>
				      </c:if>
				      <td>
				     		 <a class="btn btn-primary" href="DetailU_S?id=${U_Beans.id}">詳細</a>
				     		 <a class="btn btn-success" href="UpdateU_Servlet?id=${U_Beans.id}">更新</a>
				     		 <a class="btn btn-danger" href="DeleteU_S?id=${U_Beans.id}">削除</a>
				      </td>

			    </tr>
			    </c:forEach>
  			</tbody>
		</table>
		</div>
</body>
</html>