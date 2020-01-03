<html>

<head>
<title>First Web Application</title>
</head>

<body>
    <font color="red">${errorMessage}</font><Br/>
    <font color="red">Error: ${param.error}</font><Br/>
    <font color="red">Logout: ${param.logout}</font><Br/>
    <form method="post">
        Name : <input type="text" name="username" />
        Password : <input type="password" name="password" />
        <input type="submit" />
    </form>
</body>

</html>
