
(function() {
	$.parser.onComplete = function() {
		$("#loading-mask").fadeOut("normal", function() {
			$(this).remove();
		});
	}
	// 设置ajax的默认值
	$.ajaxSetup({
		contentType : 'application/json;charset=UTF-8',
		dataType : 'json'
	});
})();
//----------------------------------启用新的参数配置[完美分隔符]----------------------------------

var dms = {};
dms.cfg = {cfg:{},util:{},ui:{},validator:{},biz:{}}; //模块对象的初始化
dms.cfg.timeout = 40000; //异步请求超时时间
dms.cfg.delay =100;   //异步请求回调处理函数延时时间
dms.cfg.sysPath = "/dms/"


