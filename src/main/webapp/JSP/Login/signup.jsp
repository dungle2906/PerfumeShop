<%-- 
    Document   : register
    Created on : Jul 2, 2023, 1:33:51 PM
    Author     : Kiet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h2>Registration Form</h2>
            <form action="/Signup" method="post">
                <div class="form-group">
                    <label for="txtUsername">Username:</label>
                    <input type="text" class="form-control" id="txtUsername" name="txtUsername" placeholder="Enter username" required>
                </div>
                <div class="form-group">
                    <label for="txtPassword">Password:</label>
                    <input type="password" class="form-control" id="txtPassword" name="txtPassword" placeholder="Enter password" required>
                </div>
                <div class="form-group">
                    <label for="txtFullName">Full Name:</label>
                    <input type="text" class="form-control" id="txtFullName" name="txtFullName" placeholder="Enter full name" required>
                </div>
                <div class="form-group">
                    <label for="txtMobileNumber">Mobile Number:</label>
                    <input type="text" class="form-control" id="txtMobileNumber" name="txtMobileNumber" placeholder="Enter mobile number" required>
                </div>
                <div class="form-group">
                    <label for="txtEmail">Email:</label>
                    <input type="email" class="form-control" id="txtEmail" name="txtEmail" placeholder="Enter email" required>
                </div>
                <div class="form-group">
                    <label for="txtAddress">Address:</label>
                    <textarea class="form-control" id="txtAddress" name="txtAddress" placeholder="Enter address" required></textarea>
                </div>
                <div class="form-group">
                    <label for="selectGender">Gender:</label>
                    <select class="form-control" id="selectGender" name="selectGender" required>
                        <option value="">Select gender</option>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="dob">Date of Birth:</label>
                    <input type="date" class="form-control" id="dob" name="dob" required>
                </div>

                <button type="submit" class="btn btn-primary">Register</button>
            </form>
        </div>


        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    </body>
</html>
