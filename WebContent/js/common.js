dms.common = {
	revertData: function(rowData,cacheParamData){
		for(var rowkey in rowData) {
			for(var cachekey in cacheParamData) {
				if(rowkey  == cachekey) {
					var tempArray = cacheParamData[cachekey];
					for(i = 0; i < tempArray.length; i++) {
						//rowData[rowkey]为提交到后台的字段
						if(rowData[rowkey] == tempArray[i].prm_value) {
							//保存下提交到后台的字段
							rowData[rowkey+'_msg'] = rowData[rowkey];
							//account_currency字段特殊处理下
							if(rowkey == 'account_currency') {
								rowData[rowkey] = tempArray[i].prm_value+$.trim(tempArray[i].prm_showmsg);
							}else {
								rowData[rowkey] = $.trim(tempArray[i].prm_showmsg);
							}
						}
					}

				}
			}
		}
	},
	recover:function(rowData,existParamsArray) {
		for(var i = 0; i< existParamsArray.length; i++) {
			var key = existParamsArray[i];
			rowData[existParamsArray[i]] = rowData[key+'_msg'];
		}
	},
	/*
	 * 通过ajax获取数据
	 */
	ajaxLoadData:function(url,dataObj,successFun,errorFun) {
		var dataStr = JSON.stringify(dataObj);
		var tools = dms.common;
		var timer = setTimeout(function(){
			   clearTimeout(timer); 
			   if($('#loading-page-msg')){
				   tools.unmask(); 
				   objAjax.abort(); //终止超时异步请求
				   $.messager.alert('提示',"处理超时，请稍后查询业务");
			   }  
			},parseInt(dms.cfg.timeout )); //定时器
		tools.mask();
	    var objAjax = $.ajax({
	    	type: "POST",
	    	url: dms.cfg.sysPath + url,
	    	data: dataStr,
	     	success: ajaxSuccess,
	    	error: ajaxError
 		});
	    
	    function ajaxSuccess(data){
	    	 clearTimeout(timer);
	    	tools.unmask(); 
     		if(data.baseRespCode){
     			$.messager.alert('提示',data.baseRespMsg);	
     		}else {
				successFun(data);
			}
     	}
	    
	    function ajaxError(data){
	    	clearTimeout(timer);
	    	tools.unmask(); 
	    	errorFun(data);
	    }
	},
	formatDate:function(date,formatStr){ 
		//格式化时间
		function addZero(v,size){
			for(var i=0,len=size-(v+"").length;i<len;i++){
				v="0"+v;
			};
			return v+"";
		}
		var	arrWeek=['日','一','二','三','四','五','六'],
			str=formatStr
				.replace(/yyyy|YYYY/,date.getFullYear())
				.replace(/yy|YY/,addZero(date.getFullYear()%100,2)	)
				.replace(/mm|MM/,addZero(date.getMonth()+1,2))
				.replace(/m|M/g,date.getMonth()+1)
				.replace(/dd|DD/,addZero(date.getDate(),2)	)
				.replace(/d|D/g,date.getDate())
				.replace(/hh|HH/,addZero(date.getHours(),2))
				.replace(/h|H/g,date.getHours())
				.replace(/ii|II/,addZero(date.getMinutes(),2))
				.replace(/i|I/g,date.getMinutes())
				.replace(/ss|SS/,addZero(date.getSeconds(),2))
				.replace(/s|S/g,date.getSeconds())
				.replace(/w/g,date.getDay())
				.replace(/W/g,arrWeek[date.getDay()]); 
		return str; 
	},
	formatColDate:function(val,rec) {
		//格式化日期 yyyy-MM-dd HH:mm:ss
		if(!val)
		return "";
		var now = new Date(Number(val));
		var   year=now.getFullYear();     
        var   month=now.getMonth()+1;
        month = (""+month).length<2 ? "0"+month:month;
        var   date=now.getDate();    
        date =(""+date).length<2 ? "0"+date:date;
        var   hour=now.getHours();     
        hour =(""+hour).length<2 ? "0"+hour:hour;
        var   minute=now.getMinutes();   
        minute =(""+minute).length<2 ? "0"+minute:minute;
        var   second=now.getSeconds();   
        second =(""+second).length<2 ? "0"+second:second;
        return   year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second; 
	},
	formatStandardTime:function(val,rec) {
		var tools = dms.common;
		if($.type(val) === "date") {
			return tools.formatDate(val,"yyyy-mm-dd");
		}else {
			var now = new Date(val);
			return tools.formatDate(now,"yyyy-mm-dd");
		}
	},
	formatDateFrom_YYYYMMDD:function(val,rec) {
		if(val==""||val==null){
			return val;
		}else if(val.length!=8){
			return val;
		}else{
			return val.substring(0,4)+"-"+val.substring(4,6)+"-"+val.substring(6,8);
		}
	},
	getPreDate:function(date){
		if (!date){
			date = new Date();
		}
		return new Date(date.getTime() - 24*60*60*1000);
	},
	formatDateFrom_HHMMSS:function(val) {
		if(val==""||val==null){
			return val;
		}else if(val.length!=6){
			return val;
		}else{
			return val.substring(0,2)+":"+val.substring(2,4)+":"+val.substring(4,6);
		}
	},
	formatCurrency:function(val, rec)   
	{  
	   //表格金额格式化	
	   if(!val)
	   return "";
	  var  n = 2;
	  var negative ='';
	  val = ""+ val;
	  if( val.indexOf("-") >-1 ){
		  negative = val.substring(0,1);
		  val = val.substring(1);
	  }
	  var s = parseFloat((val + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
	  var l = s.split(".")[0].split("").reverse();   
	  var r = s.split(".")[1];   
	  var t = "";   
	   for(i = 0; i < l.length; i ++ )   
	   {   
	      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
	   } 
	   if(!r) r="00";
	   return negative+t.split("").reverse().join("") + "." + r;   
	},
	formatDouble_4:function(val, rec)   
	{  
		 if(val==0 || val=="0"){
				return "0.0000";
		   }
		 //表格金额格式化	
	     if(!val){
		     return "";
	     }
		  var  n = 4;   
		  var s = parseFloat((val + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
		  var l = s.split(".")[0].split("").reverse();   
		  var r = s.split(".")[1];   
		  var t = "";   
		   for(i = 0; i < l.length; i ++ )   
		   {   
		      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
		   } 
		   if(!r) r="00";
		   return t.split("").reverse().join("") + "." + r;   
	},
	/*
		paramterData：需要的具体数据获得参数常量，格式为：paramterData.req_list = [
	                 		{prm_code:'SAME_TRADE_BUSINESS_TYPE',prm_lang:'ZH_CN'},  //客户类型
	                 		{prm_code:'CLIENT_IDENTIFICATION',prm_lang:'ZH_CN'}]    //客户标识
	    paramsMap:  参数常量对应于前端提交的字段，如：paramsMap :{
							'SAME_TRADE_BUSINESS_TYPE':'corporate_client_type',
							'CLIENT_IDENTIFICATION':'financial_organization_number'} 
		cacheParamData：需要储存的数据：如同 {
			 * 								“register_country”：[{
			 * 									 "prm_code": "COUNTRY_CODE",
									                "prm_value": "ABW",
									                "prm_lang": "ZH_CN",
									                "prm_name": "国别代码",
									                "prm_showmsg": "阿鲁巴",
									                "prm_channel": "PUBLIC"
												 },
		loadSuccessCB 加载数据成功之后，要处理的函数									 	
 	*/

	dealSelectElement: function (paramterData,paramsMap,cacheParamData,loadSuccessCB) {

		/*储存常量参数，并填充到页面的下拉列表中
		  paraObjs:页面需要查询参数常量明细的详细情况
		  格式为：
	  		{
	        "rsp_list": [
	            {
	                "prm_code": "SAME_TRADE_BUSINESS_TYPE",
	                "prm_value": "A01",
	                "prm_lang": "ZH_CN",
	                "prm_name": "同业客户类型",
	                "prm_showmsg": "中国人民银行",
	                "prm_channel": "PUBLIC"
	            },
	            {
	                "prm_code": "SAME_TRADE_BUSINESS_TYPE",
	                "prm_value": "A02",
	                "prm_lang": "ZH_CN",
	                "prm_name": "同业客户类型",
	                "prm_showmsg": "国家外汇管理局",
	                "prm_channel": "PUBLIC"
	            }]
	        }
		*/	
		function cacheParams(paraObjs) {
			if(paraObjs.status == "FAIL") {
				$.messager.alert('提示',"加载参数常量失败，请稍候再试！");
				return;

			}
			var paraArray = paraObjs.result.rsp_list;
			/**
			 * 通过遍历paraArray数组使cacheParamData为下列一个结构
			 * customerObj.cacheParamData = {
			 * 								“register_country”：[{
			 * 									 "prm_code": "COUNTRY_CODE",
									                "prm_value": "ABW",
									                "prm_lang": "ZH_CN",
									                "prm_name": "国别代码",
									                "prm_showmsg": "阿鲁巴",
									                "prm_channel": "PUBLIC"
												 },	{
										                "prm_code": "COUNTRY_CODE",
										                "prm_value": "AFG",
										                "prm_lang": "ZH_CN",
										                "prm_name": "国别代码",
										                "prm_showmsg": "阿富汗",
										                "prm_channel": "PUBLIC"
										            }
			 * 								]	
			 * 							}
			 * @type {Number}
			 */
		   
			var tempMap ={};
			for(var key in paramsMap) {
				//paramsMap 格式：'FOREIGN_EXCHANGE_CODE':'foreign_exchange_code'
				if(tools.isArray(paramsMap[key])) {
					for(var i = 0; i< paramsMap[key].length; i++) {
						cacheParamData[paramsMap[key][i]] = [];
						tempMap[paramsMap[key][i]] = key;
					}
				}else {
					cacheParamData[paramsMap[key]] = [];
					tempMap[paramsMap[key]] = key;
				}
			}

			for(var i = 0; i < paraArray.length; i++) {
				var paraObj = paraArray[i];
				//遍历
				for(var key in cacheParamData) {
					var tempCacheArray = cacheParamData[key];
					if(paraObj.prm_code == tempMap[key]) {
						tempCacheArray.push(paraObj);
					}
				}
			}
			tempMap = null;
			if(loadSuccessCB != undefined && typeof(loadSuccessCB === 'function')) {
				loadSuccessCB(); 
			}else{
				//先清空Localstorage中的PARAMDATA中
				window.localStorage.removeItem("PARAMDATA");
				//把cacheParamData写入Localstorage
				window.localStorage.setItem("PARAMDATA", JSON.stringify(cacheParamData));
				cacheParamData = null;
			}
				
		}

		var parameterUrl = 'parameter/queryParameter.do',
			tools = this;

		tools.ajaxLoadData(parameterUrl,paramterData,cacheParams,function(){
						$.messager.alert("提示","加载参数常量失败，请稍候再试！");
		});
	},
	getTarget:function(e,parent,tag) {
	    var    e=window.event||e,
	        tar=e.srcElement||e.target;
	    if(parent && tag && tar.nodeName.toLowerCase()!=tag){
	        while(tar = tar.parentNode){
	            //对下拉框的点击会回溯到document，其它最多回溯到document.body即可
	            if(tar==parent || tar==document.body || tar==document){
	                return null;
	            }else if(tar.nodeName.toLowerCase()==tag){
	                break;
	            }
	        }
	    };
	    return tar;
	},
	isParent: function(obj, parentObj) {
        while(obj != undefined && obj != null && obj.tagName.toUpperCase() != 'BODY') {
        	console.log(obj);
            if(obj == parentObj) {
                return true;
			}
            obj = obj.parentNode;
		}
		obj = parentObj = null;
        return false;
	},
	
	/**
	 * 分页组件
	 * data为一个对象{
	 * paginationId         //分页组件的ID
	 * currows              //当前的分页行数
	 * curpage              //当前的页数
	 * total                //总的记录数
	 * }
	 */
	setPagination:function(dataParams, callback, itemObj) {
		var tools = this;
		var paginationId = itemObj.paginationId,
			currows = itemObj.currows,
			curpage = itemObj.curpage,
			total = itemObj.total;

		//设置页面对象
		var pagination = $('#'+paginationId).datagrid('getPager');

		var computerTotal = (curpage-1)*currows + currows;

		var fromRow = total == 0 ? 0: (curpage-1)*currows + 1,
		    toRow = (computerTotal > total)?total:computerTotal,
		    pageNumber = (total%currows == 0)?(total/currows):Math.floor(total/currows)+1;

		//设置分页
		$(pagination).pagination({
			showRefresh: false,
			pageSize:currows,
			rownumbers: true,
			beforePageText:"第",
			afterPageText:'页  &nbsp;&nbsp;&nbsp;共'+pageNumber + '页', 
			pageList: [10,20,30,50],
			displayMsg: '显示'+fromRow+'到'+toRow+'条记录，共'+total+'条记录',
	    	onSelectPage:function(pageNumber,pageSize){
	    		itemObj.curpage = pageNumber;
	    		itemObj.currows = pageSize;
	    		callback(dataParams,itemObj);    		
	        }
		});
	},
	
	isArray:function (source){
    	return '[object Array]' == Object.prototype.toString.call(source);
	},
	
	
	//获取请求参数
	 getReqParams: function(){
	   var win = window != top ? top:window;
      var reqUrl = window.location.href; 
	   var idx = reqUrl.indexOf("?");
	   if(idx >-1 ){
		  reqUrl = reqUrl.substring(idx+1);
	   }else{
		  reqUrl = "";
	   }
	   var reqParamMap={};
	   if(reqUrl && reqUrl.length >1){
		   var reqParams = reqUrl.split("&");
		   if(reqParams && reqParams.length>0){
			   for(var i=0;i<reqParams.length;i++){
				     var paramArray = reqParams[i].split("=");
					 var key =paramArray[0];
					 var value = paramArray[1];
					 if (key ){
					   reqParamMap[key]=value;
					 }
				}
			}
		}
		return reqParamMap;
	},
	
	//授权检查
	authPermission:function(objectParam,context){
	  function success(data){
		 if(data.status =="SUCCESS"){
		     if(data.result && data.result.function_res_array){
		    	var permission_codes =  data.result.function_res_array;
		    	//检查用户是按钮权限
		    	var btnPermissions = objectParam.button_auth_map;
		    	var domContext = !context ? $(document) : $('#'+context);
		    	for(var i =0;i<permission_codes.length;i++){
		    		if(permission_codes[i]){
		    			var return_code = permission_codes[i].res_code; //接口返回的授权码
			    		var button_id = btnPermissions[return_code];
			    		if(button_id){
			    			var buttonArray = button_id.split(';');
			    			for(var j=0;j<buttonArray.length;j++){
			    				$('#'+buttonArray[j],$(domContext)).show();
			    			}
			    		}	
		    		}
		    	}
		    }	
		 }
	  }
	  var tools = dms.common;
	  var reqParams = tools.getReqParams();
	  var authReqParams = {};
	  authReqParams.menu_id= reqParams.menu_id;
	  //authReqParams.user_id = tools.getCookie("user_id");
	  tools.ajaxLoadData("function/queryFunctions.do",authReqParams,success,function(data){
		   $.messager.alert('提示','获取用户权限失败','info');
	  });
	},//定义简单Map   
	 getMap: function(){//初始化map_,给map_对象增加方法，使map_像Map     
        var map_ = new Object();     
        map_.put = function(key, value) {     
            map_[key+'_'] = value;     
        };     
        map_.get = function(key) {     
            return map_[key+'_'];     
        };     
        map_.remove = function(key) {     
            delete map_[key+'_'];     
        };     
        map_.keyset = function() {     
            var ret = "";     
            for(var p in map_) {     
                if(typeof p == 'string' && p.substring(p.length-1) == "_") {     
                    ret += ",";     
                    ret += p.substring(0,p.length-1);     
                }     
            }     
            if(ret == "") {     
                return ret.split(",");     
            } else {     
                return ret.substring(1).split(",");     
            }     
        };     
        return map_;     
	},
	convertArrrayToMap:function(data,key_,value_) {
		var tools = dms.common;
		if(!tools.isArray(data)){
			return data;
		}
	
		var returnMap=tools.getMap();
		for(var i=0;i<data.length;i++){
			returnMap.put(data[i][key_],data[i][value_]);
		}
		return returnMap;
	 },
  //遮蔽层
   mask:function(){ 
	   $("<div class=\"loading\" id='load-page-mask'></div>").css({display:"block",width:"100%",height:$("body").height()}).appendTo("body");
       $("<div class=\"loading-msg\" id='loading-page-msg'></div>").html("正在处理,请稍候...").appendTo("body"); 	
    },
	unmask:function(){
	    if($("#load-page-mask")){
			$("#load-page-mask").remove();	
		}
		if($("#loading-page-msg")){
			$("#loading-page-msg").remove();
		}	
   },
   //加法
   add: function(arg1,arg2){
	   if(!arg1 && !arg2){
		   return 0.0;
	   }
	   var decimal_1, decimal_2, decimal_3;  
	    try {  
	    	decimal_1 = arg1.toString().split(".")[1].length;  
	    }  
	    catch (e) {  
	    	decimal_1 = 0;  
	    }  
	    try {  
	    	decimal_2 = arg2.toString().split(".")[1].length;  
	    }  
	    catch (e) {  
	    	decimal_2 = 0;  
	    }  
	    decimal_3 = Math.pow(10, Math.max(decimal_1, decimal_2));  
	    return (arg1 * decimal_3 + arg2 * decimal_3) / decimal_3;
   } ,
   //减法
   subtract:function(arg1,arg2){
	   var decimal_1, decimal_2, m, n;  
	    try {  
	    	decimal_1 = arg1.toString().split(".")[1].length;  
	    }  
	    catch (e) {  
	    	decimal_1 = 0;  
	    }  
	    try {  
	    	decimal_2 = arg2.toString().split(".")[1].length;  
	    }  
	    catch (e) {  
	    	decimal_2 = 0;  
	    }  
	    m = Math.pow(10, Math.max(decimal_1, decimal_2));  
	    n = (decimal_1 >= decimal_2) ? decimal_1 : decimal_2;  
	    return ((arg1 * m - arg2 * m) / m).toFixed(n);  
   }
	    
}