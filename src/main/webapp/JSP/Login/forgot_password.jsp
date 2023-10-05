<!doctype html>
<html>
    <head>
        <meta charset='utf-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1'>
        <title>Forgot Password</title>
        <link
            href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css'
            rel='stylesheet'>
    </head>
    <body>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-lg-8 col-md-10">
                    <h2>Forgot your password?</h2>
                    <p>Change your password in three easy steps. This will help you
                        to secure your password!</p>
                    <ol class="list-unstyled">
                        <li><span class="text-primary text-medium">1. </span>Enter
                            your email address below.</li>
                        <li><span class="text-primary text-medium">2. </span>Our
                            system will send you an OTP to your email</li>
                        <li><span class="text-primary text-medium">3. </span>Enter the OTP on the 
                            next page</li>
                    </ol>
                    
                    <form class="mt-4" action="/ForgotPass" method="POST">
                        <div class="card-body">
                            <span style="color: red">${requestScope.mess} </span>
                            <div class="form-group">
                                <label for="email-for-pass">Enter your email address</label>
                                <input class="form-control" type="text" name="email" id="email-for-pass" required="">
                                <small class="form-text text-muted">Enter the registered email address . Then we'll
                                    email an OTP to this address.</small>
                            </div>
                        </div>
                        <div class="card-footer">
                            <button class="btn btn-success" type="submit">Get New Password</button>
                            <a href="/login" class="btn btn-danger">Back to Login</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
