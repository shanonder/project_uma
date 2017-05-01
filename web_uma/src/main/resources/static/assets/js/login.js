
function login(){
	var username = $("#username").val();
	var password = $("#password").val();
	$.post("/auth/login/", { "username":username , "password": password}, function (data) {
		if (data == "ok") {
			alert("登录成功！");
		}
	});
}

$(function () {
    $( "#btn_login" ).click(function() { login(); });
});