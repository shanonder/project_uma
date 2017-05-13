
function login(){
	var username = $("#username").val();
	var password = $("#password").val();
	$.post("/auth/login", { "username":username , "password": password,"_crsf.token":_crsf.token,"_crsf.parameterName":_crsf.parameterName}, function (data) {
		alert(data.username + data.state);
	});
}

$(function () {
    $( "#btn_login" ).click(function() { login(); });
});