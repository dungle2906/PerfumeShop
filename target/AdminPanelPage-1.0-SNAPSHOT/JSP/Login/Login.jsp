<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #06283D;
            }

            .login-container {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .error-message {
                color: red;
            }
        </style>
    </head>
    <body>
        <div class="login-container">
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <ul class="nav nav-tabs mb-3">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/Login/Signup">Signup</a>
                        </li>
                    </ul>

                    <h5 class="card-title">Login</h5>
                    <form action="LoginController" method="post">
                        <div class="form-group">
                            <label for="txtUS">Username:</label>
                            <input type="text" class="form-control" id="txtUS" name="txtUS" placeholder="Enter username" required>
                        </div>
                        <div class="form-group">
                            <label for="txtPWD">Password:</label>
                            <input type="password" class="form-control" id="txtPWD" name="txtPWD" placeholder="Enter password" required>
                        </div>
                        <div class="form-group form-check">
                            <input type="checkbox" class="form-check-input" id="chkRem" name="chkRem" value="1">
                            <label class="form-check-label" for="chkRem">Remember me</label>
                        </div>
                        <button type="submit" class="btn btn-primary" name="btnLogin" value="Submit">Submit</button>
                    </form>

                    <div class="mt-3">
                        <a href="/Login/ForgetPass">Forgot Password?</a>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    </body>
</html>
