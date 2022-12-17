<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Artistilista</title>
</head>
<body>
	<h1>Artistilista</h1>
	
	<form method="post">
		<input name="namel" required type="text" placeholder=" kirjoita artisti"
		autofocus /> <input type="submit" value="Lisää" />
	</form>
	
	<ol>
		<c:forEach items="${ artists }" var="artist">
			<li>
				<c:out value="${ artist.getName() }" />
				
			</li>
		</c:forEach>
	</ol>
	
	

</body>
</html>