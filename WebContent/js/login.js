(function() {
 	var loginUI = $('.login');
	if (loginUI) {
		// 提交
		$('#loginBtn').click(function() {
			login();
		});

		// 清除
		$('#clearBtn').click(function() {
			$("#user_id").val("");
			$("#user_pwd").val("");
		});

		//注册enter时间按钮
		function enterKeyDown(selector){
			var inputs = $(selector).bind("keydown", function(e){
				var keyCode =  e.which || e.keyCode;
				if (keyCode === 13){
					var type = $(this).attr("type");
					
					if ((type && type === "button") || (this.tagName == 'A'||this.tagName == 'a')){
						$(this).click();
					}
					else{
						inputs[inputs.index(this) + 1].focus();
					}
					return false;
				}
			});
		}
		enterKeyDown(".login input");
		
		$(".login input:first").focus();
	}
	function login() {
		var userId = $("#user_id").val();
		var userPwd = $("#user_pwd").val();
		if (userId == "" || userPwd == "") {
			$.messager.alert("提示", "用户名、密码不允许为空,请您重新输入用户名及密码", 'error');
			return;
		}
		$.ajax({
			type : "POST",
			url :"um/login.do",
			dataType : 'json',
			data : {
				"userId" : userId,
				"password" : userPwd
			},
			success : function(_data) {
				if (_data.result == 0) {
					//生成随机数
					var rand = {};
					rand.seed = new Date().getTime();
					rand.seed = ( rand.seed * 9301 + 49297 )%233280;
			        location.href = "index.html?" + escape("r="+Math.ceil(rand.seed));
				} else {
					$.messager.alert('提示', "用户名或密码错误!", 'error');
				}
			},
			error : function() {
				$.messager.alert('提示', "用户登录系统异常!", 'error');
			}
		});
	}
	if (window != top) {
		top.location.href = "/dms/login.html";
	}
})();