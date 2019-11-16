$(function () {
    $.idcode.setCode();   //页面加载完成时生成验证码方法
    /*登录表单的提交验证*/
    $("#login").on('click', function () {
        if (checkLoginName() && checkLoginPassword() && checkLoginCode()) {//登录表单标签验证都为真时提交表单
            $.post("login", {
                username: $("#username").val(),
                password: $("#password").val()
            }, function (data) {
                if (data.data === 'success') {
                    window.location.href = $("#link").val();
                } else {
                    setDivCenter('账号或密码错误');
                }
            });
        }
    });
    /*注册表单的提交验证*/
    $("#reg").on('click', function () {
        if (checkRegName() && checkRegMobile() && checkRegEmail() && checkRegPassword() && checkRegPassword_2() && checkRegCode()) {//登录表单标签验证都为真时提交表单
            $.post("register", {
                username: $("input[name='username']").val(),
                phone: $("input[name='phone']").val(),
                mail: $("input[name='mail']").val(),
                password: $("input[name='password']").val()
            }, function (data) {
                if (data.code === 3) {
                    setDivCenter(data.msg);
                } else if (data.data === 'success') {
                    setDivCenter('注册成功');
                    setTimeout("window.location.reload()", 2000);
                } else {
                    setDivCenter('注册失败');
                }
            });
        }
    });
    /*登录js验证*/
    /*账号验证*/
    function checkLoginName() {
        var result = true;
        var name = $('.name');
        if (!name.val()) {
            setDivCenter("请输入用户名");
            name.focus();
            result = false;
        }
        else if (!/^[0-9A-z]{6,12}$/.test(name.val())) {
            setDivCenter("用户名有误");
            name.focus();
            result = false;
        } else {
            result = true;
        }
        return result;
    }
    /*密码验证*/
    function checkLoginPassword() {
        var result = true;
        var password = $('.password');
        if (!password.val()) {
            setDivCenter("请输入密码");
            password.focus();
            result = false;
        }
        else if (!/^[0-9a-zA-Z]{6,16}$/.test(password.val())) {
            setDivCenter("密码有误");
            password.focus();
            result = false;
        }
        else {
            result = true;
        }
        return result;
    }
    /*登录验证码验证*/
    function checkLoginCode() {
        var result = true;
        var IsBy = $.idcode.validateCode();//调用返回值，返回值结果为true或者false
        if (IsBy) {
            result = true;
        } else {
            setDivCenter("验证码有误");
            $(".Txtidcode").val('').focus();
            result = false;
        }
        return result;
    }
    /*登录js验证结束*/
    /*注册js验证*/
    /*用户名验证*/
    function checkRegName() {
        var result = true;
        var name = $('.regName');
        if (!name.val()) {
            setDivCenter("请输入用户名");
            name.focus();
            result = false;
        }
        else if (!/^[0-9A-z]{6,12}$/.test(name.val())) {
            setDivCenter("用户名由6-12位数字或字母组成");
            name.focus();
            result = false;
        } else {
            result = true;
        }
        return result;
    }
    /*手机号码验证*/
    function checkRegMobile() {
        var result = true;
        var mobile = $('.mobile');
        if (!mobile.val()) {
            setDivCenter('请输入手机号码');
            mobile.focus();
            result = false;
        }
        else if (!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(mobile.val()))) {
            setDivCenter("请输入有效的手机号码");
            mobile.focus();
            result = false;
        }
        else {
            result = true;
        }
        return result;
    }
    /* Email验证*/
    function checkRegEmail() {
        var result = true;
        var email = $('.email');
        if (!email.val()) {
            setDivCenter('请输入邮箱');
            email.focus();
            result = false;
        } else if (!/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/.test(email.val())) {
            setDivCenter('请输入有效的邮箱地址');
            email.focus();
            result = false;
        } else {
            result = true;
        }
        return result;
    }
    /*密码验证*/
    function checkRegPassword() {
        var result = true;
        var password = $('.regPassword');
        if (!password.val()) {
            setDivCenter("请输入密码");
            password.focus();
            result = false;
        } else if (!/^[0-9a-zA-Z]{6,16}$/.test(password.val())) {
            setDivCenter("密码由6-16位数字或字母组成");
            password.focus();
            result = false;
        } else {
            result = true;
        }
        return result;
    }
    /*确认密码*/
    function checkRegPassword_2() {
        var result = true;
        var password = $('.regPassword');
        var password_2 = $('.regPassword_2');
        if (!password_2.val()) {
            setDivCenter('确认密码不能为空');
            password_2.focus();
            result = false;
        } else if (password_2.val() !== password.val()) {
            setDivCenter("两次密码输入不一致");
            password_2.focus();
            result = false;
        } else {
            result = true;
        }
        return result;
    }
    /*注册验证码验证*/
    function checkRegCode() {
        if (!sessionStorage.code) {
            alert("请先发送邮箱");
            return false;
        }
        var code = $('.regCode');
        if (!code.val()) {
            setDivCenter('请输入验证码');
            code.focus();
            return false;
        } else if (!/^[0-9a-zA-Z]{4,8}$/.test(code.val())) {
            setDivCenter("验证码有误");
            code.focus();
            return false;
        } else if (sessionStorage.code.toUpperCase() !== code.val().toUpperCase()) {
            setDivCenter("验证码有误");
            return false;
        }
        sessionStorage.removeItem("code");
        return true;
    }
    $('.regCodeBtn').on('click', function () {
        if (checkRegName() && checkRegMobile() && checkRegEmail() && checkRegPassword() && checkRegPassword_2()) {//登录表单标签验证都为真时提交表单
            var timer = 5;
            var codeBtn = $('.regCodeBtn');
            codeBtn.addClass('disabled');
            var set = function () {
                codeBtn.val(--timer + 's');
                if (timer === 0) {
                    clearInterval(setI);
                    codeBtn.val('重新获取');
                    codeBtn.removeClass('disabled');
                }
            };
            var setI = setInterval(set, 1000);
            $.post("sendCode", {mail: $("input[name='mail']").val()}, function (data) {
                if (data.msg === "success" && data.data) {
                    sessionStorage.code = data.data;
                    alert('发送成功');
                } else if (data.code === 16) {
                    alert(data.msg);
                } else {
                    alert('发送失败');
                }
            });
        }
    });
    /*注册js验证结束*/
    /*晃动js*/
    var shake = $('#shake');
    shake.addClass('shake');
    function setDivCenter(str) {
        shake.text(str);
        shake.show().delay(2500).fadeOut();
    }
});