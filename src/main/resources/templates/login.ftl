<!DOCTYPE html>
<html lang="en">
<head>
    <title>Hi,你好呀</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link type="text/css" rel="styleSheet" href="css/login.css"/>
    <link rel="icon" type="image/x-icon" href="images/favicon.ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<div id="bg">
    <div id="hint">
        <p>登录失败</p>
    </div>
    <div id="login_wrap">
        <div id="login">
            <div id="status">
                <i style="top: 0">Log</i>
                <i style="top: 35px">Sign</i>
                <i style="right: 5px">in</i>
            </div>
            <span>
                    <form action="post">
                        <p class="form"><input type="text" id="user" placeholder="username" autocomplete="off"></p>
                        <p class="form"><input type="password" id="pwd" placeholder="password"></p>
                        <p class="form confirm"><input type="password" id="confirm-passwd"
                                                       placeholder="confirm password"></p>
                        <input type="button" value="Log in" class="btn" onclick="login()" style="margin-right: 20px;"
                               id="login_enter">
                        <input type="button" value="Sign in" class="btn" onclick='signin()' id="btn">
                    </form>
                    <a href="/blog">No Secret Landing？</a>
                </span>
        </div>
        <div id="login_img">
            <span class="circle">
                    <span></span>
                    <span></span>
                </span>
            <span class="star">
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                </span>
            <span class="fly_star">
                    <span></span>
                    <span></span>
                </span>
            <p id="title">Liwen</p>
        </div>
    </div>
</div>
<script src="js/login.js" type="text/javascript" charset="utf-8"></script>
</body>

</html>