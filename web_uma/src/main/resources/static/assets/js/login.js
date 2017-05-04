
function login(){
	var username = $("#username").val();
	var password = $("#password").val();
	$.post("/auth/login", { "username":username , "password": password}, function (data) {
		alert(data.username + data.state);
	});
}

$(function () {
    $( "#btn_login" ).click(function() { login(); });
});