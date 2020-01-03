<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
<title>First Web Application</title>
</head>
<body>
<security:authorize access="isAuthenticated()">
    authenticated as <security:authentication property="principal.username" />
     <a href="/dashboard">Dashboard</a>.
</security:authorize>
<security:authorize access="!isAuthenticated()">
    <a href="/login">Login</a>.
</security:authorize>
</body>

</html>
