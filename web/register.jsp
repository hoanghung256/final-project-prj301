<%-- 
    Document   : register
    Created on : Jul 2, 2024, 8:35:55?AM
    Author     : TRINHHUY
--%>


<%@ include file="layout/main-header.jsp" %>
<link rel="stylesheet" type="text/css" href="style/register.css">

<body >

    <h1 class="title">Registration</h1>



    <form action="register" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" required><br><br>
        <label for="gender">Gender:</label>
        <select id="gender" name="gender" required>
            <option value="MALE">Male</option>
            <option value="FEMALE">Female</option>
            <option value="OTHER">Other</option>
        </select><br><br>
        <label for="dob">Date of Birth:</label>
        <input type="date" id="dob" name="dob" required><br><br>
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required><br><br>
        <input type="submit" value="Register">
        <br><br>
        <a class="end" href="login.jsp">Login</a>
        
    
    </form><p class="text-danger">
        ${error}
    </p>

    <p class="text-success">
        ${success}
        </p>
    
    <img src="image/imageleft.png" class="image-left" alt="Image Left">
    <img src="image/imagetopright.png" class="image-top-right" alt="Image Top Right">
    <img src="image/imagebottomright.png" class="image-bottom-right" alt="Image Bottom Right">

    


</body>
<%@ include file="layout/main-footer.jsp" %>
