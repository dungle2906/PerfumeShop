<%@page import="Models.Account"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Update Information</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
            }

            .container {
                max-width: 800px;
                margin: 0 auto;
                padding: 20px;
            }

            .card {
                border-radius: 4px;
                background-color: #fff;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
                margin-bottom: 20px;
                overflow: hidden;
            }

            .card-title {
                background-color: #2196F3;
                color: #fff;
                padding: 15px;
                margin: 0;
                font-size: 24px;
            }

            .form-row {
                display: flex;
                align-items: center;
                justify-content: space-between;
                padding: 15px;
                border-bottom: 1px solid #ccc;
            }

            .form-label {
                font-weight: bold;
            }

            .form-input {
                flex: 1;
                margin-left: 10px;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            .form-actions {
                padding: 15px;
                text-align: right;
            }

            .btn {
                background-color: #4CAF50;
                color: white;
                border: none;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 10px 5px;
                cursor: pointer;
                border-radius: 4px;
            }

            .btn-primary {
                background-color: #2196F3;
            }

            .btn-primary:hover {
                background-color: #0b7dda;
            }
        </style>
    </head>
    <body>
        <%
            ResultSet rs = (ResultSet) request.getAttribute("rs");
            if (rs.next()) {
        %>
        <div class="container">
            <div class="card">
                <div class="card-title">Update Information</div>
                <form action="" method="post">
                    <div class="form-row">
                        <div class="form-label">Full Name:</div>
                        <input type="text" class="form-input" name="Fullname" value="<%= rs.getString("Fullname")%>" required>
                    </div>
                    <div class="form-row">
                        <div class="form-label">User Name:</div>
                        <input type="text" class="form-input" name="Username" value="<%= rs.getString("Username")%>" required>
                    </div>
                    <div class="form-row">
                        <div class="form-label">Mobile Number:</div>
                        <input type="text" class="form-input" name="Mobile_Number" value="<%= rs.getString("Mobile_Number")%>" required>
                    </div>
                    <div class="form-row">
                        <div class="form-label">Email:</div>
                        <input type="email" class="form-input" name="Email" value="<%= rs.getString("Email")%>" required>
                    </div>
                    <div class="form-row">
                        <div class="form-label">Address:</div>
                        <input type="text" class="form-input" name="Address" value="<%= rs.getString("Address")%>" required>
                    </div>
                    <div class="form-row">
                        <div class="form-label">Gender:</div>
                        <select name="slctGender" id="cars">
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                        </select>
                    </div>
                     <div class="form-row">
                        <div class="form-label">Date of Brith:</div>
                        <input type="date" class="form-input" name="DoB" value="<%= rs.getDate("Birthday")%>" required>
                    </div>
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary" name="btnUpdate" value="Update">Submit</button>
                    </div>
                </form>
            </div>
        </div>
        <%
            }
        %>
    </body>
</html>
