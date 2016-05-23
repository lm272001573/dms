//首页
dms.index = function(){
	var context = dms.cfg.sysPath;
	var menu = ui.menu,cacheParamData = {},
	tools = dms.common,userId ='';
	menu.init();
	
	/**
	 * [description]
	 * @param  {[type]} ) 
	 * @return {[type]}   [description]
	 */
	
	$("#menuTree").tree({
		url: context + 'um/getMenuTree.do',
		method:'post',
		animate:'true',
		onClick : function(target) {
			var node = $('#menuTree').tree('getSelected');
			if (node && node.action != null) {
				try{
					var node_id = node.id;					
					var url = node.action;
					url = url.indexOf("?")>-1 ? url+"&": url+"?" ;
					url +="menu_id="+node_id+"";
					var menu_name = node.text;
					menu.addTab(menu_name,url,"",node_id);
				}catch(e){}
			}else{
				$("#"+node.domId).removeClass("tree-node-selected"); 
			}
		}
	});
	
	//系统退出	
	$('#logoutBtn').click(function() {
		$.messager.confirm('系统退出','您确定退出系统吗?',function(r){
			if(r){
				$.post(context + "um/logout.do",{},
				function(data, textStatus, jqXHR) {
				    if (data.result == 0) {
						location.href = "login.html";						
					} else {
						$.messager.alert("提示","用户退出系统失败","info");
					}
				  },"json").fail(function(jqXHR, textStatus, errorThrown) {
						location.href = "login.html";	
				}
			  );  	
		   }
	   });	 
	});
	
	//获取用户名
	$.post( context + "um/getUserInfo.do",{},
		function(data, textStatus, jqXHR) {
	    if(data){
	       if(data.userId){
	    	   userId = data.userId;
	    	   $("#user_id").html("&nbsp;"+data.userId);
		       tools.dealSelectElement(paramterData,paramsMap,cacheParamData);  
	       }
	    }
	})/*.fail(function(jqXHR, textStatus, errorThrown) {location.href = "login.html";	})*/;  	

	//加载参数常量 查询常量的具体信息、并储存起来
	var paramterData = {};
	var paramsMap = {
		"SYS_TYPE" : "sys_type"
	};
	
	paramterData.req_list = [
        {prm_code:'SYS_TYPE',prm_lang:'ZH_CN'}
	];
}
dms.index();
