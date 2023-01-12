<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="uploadFormAction" method="post" enctype="multipart/form-data">
	<input type='file' name='uploadFile' multiple> <!-- multiple옵션을 붙이면 여러개를  선택할 수 있다(but. 파일명은 안 보임) -->

	<button>Submit</button> <!-- http://localhost:8081/uploadFormAction로 이동-->
</form>

</body>
</html>