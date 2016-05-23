var ui={}
ui.menu= {};
ui.menu.init = function(){
	this.tabCloseEven();
	this.tabClose();
}
ui.menu.tabClose = function()
{
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/* 为选项卡绑定右键 */
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#menuBtns').menu('show', {
			left: e.pageX,
			top: e.pageY
		});
		var subtitle =$(this).children(".tabs-closable").text();
		$('#menuBtns').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}

// 绑定右键菜单事件
ui.menu.tabCloseEven= function()
{ 
	// 关闭当前
	$('#menuBtns-tabclose').click(function(){
		var currtab_title = $('#menuBtns').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	// 全部关闭
	$('#menuBtns-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			$('#tabs').tabs('close',t);
		});	
	});
	// 关闭除当前之外的TAB
	$('#menuBtns-tabcloseother').click(function(){
		var othertabs = $('.tabs-selected').siblings();
		if(othertabs.length>0){
			othertabs.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				$('#tabs').tabs('close',t);
			});
			return false;
		}
	});
	// 关闭当前右侧的TAB
	$('#menuBtns-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	// 关闭当前左侧的TAB
	$('#menuBtns-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	// 退出
	$("#menuBtns-exit").click(function(){
		$('#menuBtns').menu('hide');
	})
}

ui.menu.addTab = function(tabTitle,url,icon,tabId){
	var objMenu = ui.menu;
	if(!$('#tabs').tabs('existsById',tabId)){
		$('#tabs').tabs('add',{
			title:tabTitle,
			id:tabId,
			content:objMenu.createIframe(url,tabId),
			closable:true,
			icon:""
		});
		
	}else{
		$('#tabs').tabs('selectById',tabId);
	}
	objMenu.tabClose();
}

ui.menu.createIframe = function (url,menuId)
{   
	var  t= new Date().getTime();
	var iframeHTML = '<iframe scrolling="auto" frameborder="0"  src="'+url+'&t='+t+'#'+menuId+'" style="width:100%;height:100%;"></iframe>';
	return iframeHTML;
}