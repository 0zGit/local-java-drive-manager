<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>sisdocs | Drive Manager</title>
</head>
<body>

	<h1>Drive Manager</h1>
	<h2>Index of <strong style="color:lime;">${messages.location}</strong></h2>

	<div class="form">
		<form method="GET" action="ServletCreateDrive">
			<input type="submit" value="+" class="btn-hover btn">
		</form>
		<form method="GET" action="ServletListDrive">
			<input type="submit" value="List" class="btn-hover btn">
		</form>
		<form method="POST" action="ServletFile" enctype="multipart/form-data">
			<input type="file" name="file">
			<input type="submit" value="Upload" class="btn-hover btn">
		</form>
		<form method="GET" action="ServletDeleteFile">
			<select name="content">
				<option value="" disabled selected>Select a file</option>
				<c:forEach items="${contents}" var="content">
					<option><c:out value="${content.name}"></c:out></option>
				</c:forEach>
			</select>
			<input type="submit" value="Delete" class="btn-hover btn">
		</form>
		
	</div>

	<hr>

	<div style="margin:0 0 15px 0;">
		<span style="font-size:13px;font-style:italic;color:lime;">
			# ${messages.message}
		</span>
	</div>

	<table>
		<thead>
			<tr class="header" id="theader">
				<th>Name</th>
				<th>Size</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${files}" var="file">
				<tr>
					<td><c:out value="${file.getKey()}"></c:out></td>
					<td><c:out value="${file.getValue()}"></c:out></td>
				</tr>
			</c:forEach>		
		</tbody>
	</table>

</body>
</html>