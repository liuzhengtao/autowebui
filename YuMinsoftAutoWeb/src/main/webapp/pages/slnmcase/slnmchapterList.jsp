<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tu" uri="/WEB-INF/tlds/content-override.tld"%>
<%@ include file="/inc/constants.inc" %>

<%-- 头部 --%>
<tu:content contentId="head">
	<%@ include file="/inc/layuipage.inc" %>
	<title>${slnmCase.casename }</title>
   	<script type="text/javascript">
   		$(document).ready(function(){
   			layui.use(["form"], function(){
   				var form = layui.form();
   				//监听switch开关
   				form.on("switch(isrunpage-lay-switch)", function(data){
   					var pageid = $(data.elem).parent().parent().attr("pageid");
   					var isrun = 0;
	   				if(data.elem.checked){
	   					isrun = 1;
	   				}
	   				$.post("${CONTEXT_PATH }/ajax/slnmcase/setIsRunPage",{ id: pageid, isrun: isrun },function(data){
	    				//
					});
   				});
   				//监听switch开关
   				form.on("switch(isrunchapter-lay-switch)", function(data){
   					var chapterid = $(data.elem).parent().parent().attr("chapterid");
   					var isrun = 0;
	   				if(data.elem.checked){
	   					isrun = 1;
	   				}
	   				$.post("${CONTEXT_PATH }/ajax/slnmcase/setIsRunChapter",{ id: chapterid, isrun: isrun },function(data){
	    				//
					});
   				});
   				
   			});
   			
   			//
   			var casecode = "${query.casecode }";
   			
   			//新增章节
    		$("#addbtn").click(function (){    			
    			tuDialog("新增章节","${CONTEXT_PATH }/slnmcase/toAddChapter?casecode="+casecode,"500px","400px");    			
    			return false;
    		});
    		//编辑章节
    		$("#editbtn").click(function (){
    			//
    			if( _selected_id == null){
    				tuAlert("请选择一条数据");
        			return false;
    			}
    			var id = _selected_id.find(".id").text();
    			tuDialog("编辑章节","${CONTEXT_PATH }/slnmcase/toEditChapter?id="+id,"500px","400px");
    			return false;
    		});
    		//删除章节
    		$("#deletebtn").click(function (){
    			if( _selected_id == null){
    				tuAlert("请选择一条数据");
        			return false;
    			}
    			tuConfirm("是否确认删除该条数据?",function onYes(){
    				var id = _selected_id.find(".id").text();
        			window.location.href = "${CONTEXT_PATH }/slnmcase/doDeleteChapter?id="+id + "&casecode=" + casecode;
    			});
    			return false;
    		});
    		//全部收起
    		$("#retractall").click(function (){
    			$.each($(".show_sub_data"), function(i){
    				var sub_tr = $(this).parent().parent().next(".data_tb_sub_tr");    			
	    			if(sub_tr.length > 0){
	    				$(this).html("");
	    				$(sub_tr).hide();
	    			}
				});
    			setExpandedInfo();
    			
    			return false;
    		});
    		//刷新
    		$("#refresh").click(function (){
    			$("#tuform").attr("action","${CONTEXT_PATH }/slnmcase/chapterlist");
				$("#tuform").submit();
    			return false;
    		});
    		
    		//复制章节
    		$(".copychapter").click(function (){
    			var chapterid = $(this).attr("chapterid");
    			tuDialog("复制章节","${CONTEXT_PATH }/slnmcase/toCopyChapter?id="+chapterid,"500px","400px");
    			return false;
    		});
    		//运行用例
    		$("#runcase").click(function (){
    			var chapterid = $(this).attr("chapterid");
    			var index = layer.load(1);
				$.post("${CONTEXT_PATH }/ajax/slnmcase/doRunCase",{ casecode: casecode },function(data){
					layer.closeAll();
					var code = data.code;
    				var msg = data.msg;
    				tuWarn(code,msg);
				});
    			return false;
    		});
    		//运行章节
    		$(".runchapter").click(function (){
    			var chapterid = $(this).attr("chapterid");
    			var index = layer.load(1);
				$.post("${CONTEXT_PATH }/ajax/slnmcase/doRunChapter",{ id: chapterid },function(data){
					layer.closeAll();
					var code = data.code;
    				var msg = data.msg;
    				tuWarn(code,msg);
				});
    			return false;
    		});
    		
    		//章节上移
    		$(".upChapter").click(function (){
    			//
    			var chapterid = $(this).parent().parent().attr("chapterid");
    			var changedid = "";
    			if($(this).parent().parent().prev().attr("chapterid")){
    				changedid = $(this).parent().parent().prev().attr("chapterid");
    			}else{
    				changedid = $(this).parent().parent().prev().prev().attr("chapterid");
    			}
    			window.location.href = "${CONTEXT_PATH }/slnmcase/changeChapterOrder?id=" + chapterid +
    				"&changedid=" + changedid + "&casecode=" + casecode;
    		});    		
    		//章节下移
    		$(".downChapter").click(function (){
    			//
    			var chapterid = $(this).parent().parent().attr("chapterid");
    			var changedid = "";
    			if($(this).parent().parent().next().attr("chapterid")){
    				changedid = $(this).parent().parent().next().attr("chapterid");
    			}else{
    				changedid = $(this).parent().parent().next().next().attr("chapterid");
    			}
    			window.location.href = "${CONTEXT_PATH }/slnmcase/changeChapterOrder?id=" + chapterid +
					"&changedid=" + changedid + "&casecode=" + casecode;
    		});
    		
    		//新增页面
    		$(".addpage").click(function (){
    			var chaptercode = $(this).attr("chaptercode");
    			tuDialog("新增页面","${CONTEXT_PATH }/slnmcase/toAddPage?chaptercode="+chaptercode,"500px","560px");    			
    			return false;
    		});
    		//选择页面模板
    		$(".choosepagetemp").click(function (){
    			var chaptercode = $(this).attr("chaptercode");
    			tuDialog("选择页面模板","${CONTEXT_PATH }/slnmcase/toChoosePageTemp?chaptercode="+chaptercode,"1000px","560px");    			
    			return false;
    		});
    		//编辑页面
    		$(".editPage").click(function (){
    			var pageid = $(this).attr("pageid");
    			tuDialog("编辑页面","${CONTEXT_PATH }/slnmcase/toEditPage?id="+pageid,"550px","560px");    			
    			return false;
    		});
    		//生成数据模板
    		$(".createdatafile").click(function (){
    			var pageid = $(this).attr("pageid");
    			$("#tuform").attr("target","_blank");
				$("#tuform").attr("action","${CONTEXT_PATH }/slnmcase/createDataFile?id=" + pageid);
				$("#tuform").submit();
				//
				$("#tuform").attr("target","_self");
				$("#tuform").attr("action","${CONTEXT_PATH }/slnmcase/chapterlist");
				
    			return false;
    		});
    		//运行页面
    		$(".runpage").click(function (){
    			var pageid = $(this).attr("pageid");
    			var index = layer.load(1);
				$.post("${CONTEXT_PATH }/ajax/slnmcase/doRunPage",{ id: pageid },function(data){
					layer.closeAll();
					var code = data.code;
    				var msg = data.msg;
    				tuWarn(code,msg);
				});
    			return false;
    		});
    		//复制页面
    		$(".copypage").click(function (){
    			var pageid = $(this).attr("pageid");
    			tuDialog("复制页面","${CONTEXT_PATH }/slnmcase/toCopyPage?id="+pageid,"500px","510px");
    			return false;
    		});
    		
    		//删除页面
    		$(".deletepage").click(function (){
    			var pageid = $(this).attr("pageid");
    			tuConfirm("是否确认删除页面?",function onYes(){
    				window.location.href = "${CONTEXT_PATH }/slnmcase/doDeletePage?id="+pageid + "&casecode=" + casecode;
    			});
    			return false;
    		});
    		//页面上移
    		$(".upPage").click(function (){
    			//
    			var pageid = $(this).parent().parent().attr("pageid");
    			var changedid = "";
    			if($(this).parent().parent().prev().attr("pageid")){
    				changedid = $(this).parent().parent().prev().attr("pageid");
    			}else{
    				changedid = $(this).parent().parent().prev().prev().attr("pageid");
    			}
    			window.location.href = "${CONTEXT_PATH }/slnmcase/changePageOrder?id="+pageid 
    				+ "&changedid=" + changedid + "&casecode=" + casecode;
    		});    		
    		//页面下移
    		$(".downPage").click(function (){
    			//
    			var pageid = $(this).parent().parent().attr("pageid");
    			var changedid = "";
    			if($(this).parent().parent().next().attr("pageid")){
    				changedid = $(this).parent().parent().next().attr("pageid");
    			}else{
    				changedid = $(this).parent().parent().next().next().attr("pageid");
    			}
    			window.location.href = "${CONTEXT_PATH }/slnmcase/changePageOrder?id="+pageid 
    				+ "&changedid=" + changedid + "&casecode=" + casecode;
    		});
    		
    		//新增元素
    		$(".addlocator").click(function (){
    			var pagecode = $(this).attr("pagecode");
    			tuDialog("新增元素","${CONTEXT_PATH }/slnmcase/toAddLocator?pagecode="+pagecode,"500px","97%");    			
    			return false;
    		});
    		//编辑元素
    		$(".editlocator").click(function (){
    			var locatorid = $(this).attr("locatorid");
    			tuDialog("编辑元素","${CONTEXT_PATH }/slnmcase/toEditLocator?id="+locatorid,"500px","97%");    			
    			return false;
    		});
    		//复制元素
    		$(".copylocator").click(function (){
    			var locatorid = $(this).attr("locatorid");
    			tuDialog("复制元素","${CONTEXT_PATH }/slnmcase/toCopyLocator?id="+locatorid,"500px","97%");    			
    			return false;
    		});
    		//移动元素
    		$(".movelocator").click(function (){
    			var locatorid = $(this).attr("locatorid");
    			tuDialog("移动元素","${CONTEXT_PATH }/slnmcase/toMoveLocator?id="+locatorid,"500px","97%");    			
    			return false;
    		});
    		//删除元素
    		$(".deletelocator").click(function (){
    			var locatorid = $(this).attr("locatorid");
    			tuConfirm("是否确认删除元素?",function onYes(){
    				window.location.href = "${CONTEXT_PATH }/slnmcase/doDeleteLocator?id="+locatorid + "&casecode=" + casecode;
    			});
    			return false;
    		});
    		//元素上移
    		$(".upLocator").click(function (){
    			//
    			var locatorid = $(this).parent().parent().attr("locatorid");
    			var changedid = $(this).parent().parent().prev().attr("locatorid");
    			window.location.href = "${CONTEXT_PATH }/slnmcase/changeLocatorOrder?id="+locatorid 
    				+ "&changedid=" + changedid + "&casecode=" + casecode;
    		});    		
    		//元素下移
    		$(".downLocator").click(function (){
    			//
    			var locatorid = $(this).parent().parent().attr("locatorid");
    			var changedid = $(this).parent().parent().next().attr("locatorid");
    			window.location.href = "${CONTEXT_PATH }/slnmcase/changeLocatorOrder?id="+locatorid 
    				+ "&changedid=" + changedid + "&casecode=" + casecode;
    		});
    		
    		//返回
    		$("#backbtn").click(function (){
    			window.location.href = "${CONTEXT_PATH }/slnmcase/list";
    		});
    		
    		//展开页面项
    		$(".show_sub_data").click(function (){
    			//
    			var sub_tr = $(this).parent().parent().next(".data_tb_sub_tr");    			
    			if(sub_tr.length > 0){
    				$(sub_tr).toggle();
    				//
    				if($(this).html()==""){
    					$(this).html("");
    				}else if($(this).html()==""){
    					$(this).html("");
    				}
    			}
    			setExpandedInfo();
    		});
    		
    		//初始化展开信息
    		initExpandedInfo();
   		});

   		//设置被展开的信息
   		function setExpandedInfo(){
   			var jsonArray = new Array();
   			$.each($(".show_sub_data"), function(i){
   				var index = i;
   				var jsonObj = {};
   				if($(this).html()==""){
					//
					jsonObj.expandname = $(this).attr("expandname");
					jsonArray[jsonArray.length] = jsonObj;
				}
   			});
   			//
   			if(jsonArray.length >0){
   				setCookie("expandjson",JSON.stringify(jsonArray));
   			}else{
   				delCookie("expandjson");
   			}
   		}
   		
   		//初始化展开信息
   		function initExpandedInfo(){
   			var expandjson_str = getCookie("expandjson");
    		if(isNotNull(expandjson_str)){
    			var expandjson_obj = JSON.parse(expandjson_str); 
    			//
    			for(var i in expandjson_obj){  
    				var expandname = expandjson_obj[i].expandname;
    				$.each($(".show_sub_data"), function(i){
						if(expandname == $(this).attr("expandname")){
							//$(this).click();
							var sub_tr = $(this).parent().parent().next(".data_tb_sub_tr");    			
			    			if(sub_tr.length > 0){
			    				$(sub_tr).toggle();
			    				//
			    				if($(this).html()==""){
			    					$(this).html("");
			    				}else if($(this).html()==""){
			    					$(this).html("");
			    				}
			    			}
						}
					});
    			} 
    		}
   		}
   		
	</script>
	
	<style>
		.layui-form-switch {margin-top: 0px;}
	</style>
</tu:content>

<%-- 内容 --%>
<tu:content contentId="content">
	<div class="layui-tab">					 	
		<ul class="layui-tab-title">
			<li class="layui-this">章节管理</li>
		</ul>					
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">
				<form id="tuform" action="${CONTEXT_PATH }/slnmcase/chapterlist" method="post" target="_self" class="layui-form">
				<input type="hidden" name="casecode" id="casecode" value="${query.casecode }" />
				<fieldset class="layui-elem-field">							  
				  <div class="layui-field-box">
				   	<button class="layui-btn" style="background-color:#4588BB;" id="addbtn">
						<i class="layui-icon" style="font-size:20px;color:#fff;">&#xe654;</i>新增章节
					</button>
					<button class="layui-btn" style="background-color:#4588BB;" id="editbtn">
						<i class="layui-icon" style="font-size:20px;color:#fff;">&#xe642;</i>编辑章节
					</button>
					<button class="layui-btn layui-btn-danger" id="deletebtn">
						<i class="layui-icon" style="font-size:20px;color:#fff;">&#xe640;</i>删除章节
					</button>
					<button class="layui-btn" style="background-color:#4588BB;" id="runcase">
						<i class="layui-icon" style="font-size:17px;color:#fff;">&#xe652;</i> 运行
					</button>
					<button class="layui-btn layui-btn-normal" id="retractall" style="background-color:#3C77A2;">
						<i class="layui-icon" style="font-size:18px;color:#fff;">&#xe623;</i>全部收起
					</button>
					<button class="layui-btn layui-btn-primary" id="refresh">
						<i class="layui-icon" style="font-size:18px;">&#x1002;</i> 刷新
					</button>
				  </div>
				</fieldset>				
				<table class="layui-table data_tb">							  
				  <thead>
				    <tr style="background-color:#4588BB;">
				      <th width="40px">序号</th>
				      <th width="">章节名称</th>
				      <th width="10%">是否运行</th>
						<th width="10%">预期结果</th>
				      <th width="10%">排序</th>
				      <th width="">描述</th>
				      <th width="310px">操作</th>
				    </tr> 
				  </thead>
				  <tbody>
				  	<c:forEach items="${page.results}" var="item" varStatus="status">
					    <tr class="sel_data_tr" chapterid="${item.id }">
					      <td align="center">
					      	<c:if test="${not empty item.pagelist }"><i class="layui-icon show_sub_data" expandname="${item.id }" style="cursor:pointer;font-size:14px;color:#039083;">&#xe623;</i></c:if>${status.index+1 }
					      </td>
					      <td class="layui-elip" title="${item.chaptername }">
					      	<span class="id layui-hide">${item.id }</span>
					     	${item.chaptername }
					      </td>
					      <td align="center">
					      	<input type="checkbox" name="isrunchapter" lay-skin="switch" lay-text="是|否" lay-filter="isrunchapter-lay-switch" <c:if test="${item.isrun eq 1 }">checked</c:if>>
					      </td>
							<td class="layui-elip" title="${item.expectedresults }">${item.expectedresults }</td>
					      <td>
					      	${item.orderno }
		      				<c:if test="${not status.first }">
		      					&nbsp;
		      					<i class="layui-icon upChapter" style="cursor:pointer;font-size:16px;" title="上移">&#xe619;</i>
		      				</c:if>
		      				<c:if test="${not status.last }">
		      					&nbsp;
		      					<i class="layui-icon downChapter" style="cursor:pointer;font-size:16px;" title="下移">&#xe61a;</i>
		      				</c:if>
					      </td>
					      <td class="layui-elip" title="${item.description }">${item.description }</td>	
					      <td align="">
					      	<button class="layui-btn layui-btn-small copychapter" style="background-color:#4588BB;" chapterid="${item.id }">复制</button>
					      	<button class="layui-btn layui-btn-small runchapter" style="background-color:#4588BB;" chapterid="${item.id }">运行</button>
					      	<button class="layui-btn layui-btn-normal layui-btn-small addpage" chaptercode="${item.chaptercode }">添加页面</button>
					      	<button class="layui-btn layui-btn-normal layui-btn-small choosepagetemp" chaptercode="${item.chaptercode }">从模板添加页面</button>
					      </td>
					    </tr>
					    <c:if test="${not empty item.pagelist }">
						    <tr class="data_tb_sub_tr" style="display:none;">
						      <td colspan="7" align="center">
						      	<table width="100%" align="center" class="sub_tr_datatb">
						      		<tr>
						      			<th width="40px">序号</th>
						      			<th width="">页面名称</th>
						      			<th width="">页面地址</th>
						      			<th width="12%">数据文件类型</th>
						      			<th width="9%">是否运行</th>
						      			<th width="80px">排序</th>
						      			<th width="365px">操作</th>
						      		</tr>
						      		<c:forEach items="${item.pagelist }" var="pageitem" varStatus="pagestatus">
							      		<tr pageid="${pageitem.id }">
							      			<td align="center">
							      				<c:if test="${not empty pageitem.locatorlist }"><i class="layui-icon show_sub_data" expandname="${item.id }${pageitem.id }" style="cursor:pointer;font-size:14px;color:#34A8FF;">&#xe623;</i></c:if>${pagestatus.index+1 }
							      			</td>
							      			<td class="layui-elip" title="${pageitem.pagename }">${pageitem.pagename }</td>
							      			<td class="layui-elip" title="${pageitem.pageurl }">${pageitem.pageurl }</td>
							      			<td align="center">
							      				<c:if test="${pageitem.datafiletype eq 1 }">Excel文件</c:if>
							      				<c:if test="${pageitem.datafiletype eq 2 }">Xml文件</c:if>
							      			</td>
							      			<td align="center">
							      				<input type="checkbox" name="isrunpage" lay-skin="switch" lay-text="是|否" lay-filter="isrunpage-lay-switch" <c:if test="${pageitem.isrun eq 1 }">checked</c:if>>
							      			</td>
							      			<td>
							      				${pageitem.orderno }
							      				<c:if test="${not pagestatus.first }">
							      					&nbsp;
							      					<i class="layui-icon upPage" style="cursor:pointer;font-size:16px;" title="上移">&#xe619;</i>
							      				</c:if>
							      				<c:if test="${not pagestatus.last }">
							      					&nbsp;
							      					<i class="layui-icon downPage" style="cursor:pointer;font-size:16px;" title="下移">&#xe61a;</i>
							      				</c:if>
							      			</td>
							      			<td>
							      				<button class="layui-btn layui-btn-normal layui-btn-mini editPage" pageid="${pageitem.id }">编辑</button>
							      				<button class="layui-btn layui-btn-normal layui-btn-mini copypage" pageid="${pageitem.id }">复制</button>
							      				<c:if test="${not empty pageitem.locatorlist }">
							      					<button class="layui-btn layui-btn-mini createdatafile" pageid="${pageitem.id }" style="background-color:#71AB76;">
							      						<i class="layui-icon" style="font-size:15px;color:#fff;">&#xe60a;</i>生成数据模板
							      					</button>
							      				</c:if>
							      				<c:if test="${not empty pageitem.pageurl }">
							      					<button class="layui-btn layui-btn-normal layui-btn-mini runpage" pageid="${pageitem.id}">运行</button>
							      				</c:if>
							      				<button class="layui-btn layui-btn-danger layui-btn-mini deletepage" pageid="${pageitem.id }">删除</button>
							      				<button class="layui-btn layui-btn-warm layui-btn-mini addlocator" pagecode="${pageitem.pagecode }">添加元素</button>
							      			</td>
							      		</tr>
							      		<c:if test="${not empty pageitem.locatorlist }">
										    <tr class="data_tb_sub_tr" style="display:none;">
										      <td colspan="7" align="center">
										      	<table width="100%" align="center" class="sub_tr_datatb">
										      		<tr>
										      			<th width="40px">序号</th>
										      			<th width="14%">元素名称</th>
										      			<th width="12%">定位方式</th>
										      			<th width="12%">定位信息</th>
										      			<th width="13%">操作</th>
										      			<th width="95px">超时时间(秒)</th>
										      			<th width="95px">等待时间(秒)</th>
										      			<th width="80px">排序</th>
										      			<th width="183px">操作</th>
										      		</tr>
										      		<c:forEach items="${pageitem.locatorlist }" var="locatoritem" varStatus="locatorstatus">
											      		<tr locatorid="${locatoritem.id }">
											      			<td align="center">${locatorstatus.index+1 }</td>
											      			<td class="layui-elip" title="${locatoritem.locatorname }">
											      				${locatoritem.locatorname }
											      			</td>
											      			<td class="layui-elip" title="${locatoritem.bytype }" align="center">
											      				${locatoritem.bytype }
											      			</td>
											      			<td class="layui-elip" title="${locatoritem.byvalue }">${locatoritem.byvalue }</td>
											      			<td align="center" class="layui-elip"
											      				title='<c:if test="${locatoritem.opttype eq 'click' }">点击(Click)</c:if>
											      				    <c:if test="${locatoritem.opttype eq 'submit' }">表单提交(Submit)</c:if>
												      				<c:if test="${locatoritem.opttype eq 'type' }">文本框输入(Type)</c:if>
												      				<c:if test="${locatoritem.opttype eq 'keybord_type' }">键盘输入(KeybordType)</c:if>
											      					<c:if test="${locatoritem.opttype eq 'prompt_type' }">提示框输入(PromptType)</c:if>
											      					<c:if test="${locatoritem.opttype eq 'prompt_type_accept' }">提示框输入确认(PromptTypeAccept)</c:if>
												      				<c:if test="${locatoritem.opttype eq 'clear' }">文本框清空(Clear)</c:if>
												      				<c:if test="${locatoritem.opttype eq 'switchToFrame' }">选择(Frame)</c:if>
												      				<c:if test="${locatoritem.opttype eq 'exitFromFrame' }">退出(Frame)</c:if>
												      				<c:if test="${locatoritem.opttype eq 'click_left' }">鼠标左键单击</c:if>
												      				<c:if test="${locatoritem.opttype eq 'exist_click' }">元素存在则点击</c:if>
											      					<c:if test="${locatoritem.opttype eq 'exist_stop' }">元素存在则停止</c:if>
											      					<c:if test="${locatoritem.opttype eq 'wait_type' }">等待文本框输入</c:if>'>
											      				<c:if test="${locatoritem.opttype eq 'click' }">点击(Click)</c:if>
											      				<c:if test="${locatoritem.opttype eq 'type' }">文本框输入(Type)</c:if>
											      				<c:if test="${locatoritem.opttype eq 'keybord_type' }">键盘输入(KeybordType)</c:if>
											      				<c:if test="${locatoritem.opttype eq 'prompt_type' }">提示框输入(PromptType)</c:if>
											      				<c:if test="${locatoritem.opttype eq 'prompt_type_accept' }">提示框输入确认(PromptTypeAccept)</c:if>
											      				<c:if test="${locatoritem.opttype eq 'clear' }">文本框清空(Clear)</c:if>
											      				<c:if test="${locatoritem.opttype eq 'switchToFrame' }">选择(Frame)</c:if>
											      				<c:if test="${locatoritem.opttype eq 'exitFromFrame' }">退出(Frame)</c:if>
											      				<c:if test="${locatoritem.opttype eq 'click_left' }">鼠标左键单击</c:if>
											      				<c:if test="${locatoritem.opttype eq 'exist_click' }">元素存在则点击</c:if>
											      				<c:if test="${locatoritem.opttype eq 'exist_stop' }">元素存在则停止</c:if>
											      				<c:if test="${locatoritem.opttype eq 'wait_type' }">等待文本框输入</c:if>
											      			</td>
											      			<td align="center">${locatoritem.timeout }</td>
											      			<td align="center">${locatoritem.waittime }</td>
											      			<td>
											      				${locatoritem.orderno }
											      				<c:if test="${not locatorstatus.first }">
											      					&nbsp;
											      					<i class="layui-icon upLocator" style="cursor:pointer;font-size:16px;" title="上移">&#xe619;</i>
											      				</c:if>
											      				<c:if test="${not locatorstatus.last }">
											      					&nbsp;
											      					<i class="layui-icon downLocator" style="cursor:pointer;font-size:16px;" title="下移">&#xe61a;</i>
											      				</c:if>
											      			</td>
											      			<td align="">
											      				<button class="layui-btn layui-btn-warm layui-btn-mini editlocator" locatorid="${locatoritem.id }">编辑</button>
											      				<button class="layui-btn layui-btn-warm layui-btn-mini copylocator" locatorid="${locatoritem.id }">复制</button>
											      				<c:if test="${fn:length(item.pagelist) > 1 }">
											      					<button class="layui-btn layui-btn-warm layui-btn-mini movelocator" locatorid="${locatoritem.id }">移动</button>
											      				</c:if>
											      				<button class="layui-btn layui-btn-danger layui-btn-mini deletelocator" locatorid="${locatoritem.id }">删除</button>
											      			</td>
											      		</tr>
										      		</c:forEach>
										      	</table>
										      </td>
										    </tr>
									    </c:if>
						      		</c:forEach>
						      	</table>
						      </td>
						    </tr>
					    </c:if>
				    </c:forEach>					    
				  </tbody>
				</table>
				<div id="tuPage" form="tuform"></div>
			</form>						
			</div>
			<div class="bottom-button-row" style="margin-top:35px;text-align:center;">
				<fieldset class="layui-elem-field">
					<button class="layui-btn " id="backbtn" style="padding:0 38px;">返回</button>
				</fieldset>
			</div>
		</div>
	</div> 	
</tu:content>

<%@ include file="/common/tu-template.jsp" %>