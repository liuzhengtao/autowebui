<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tu" uri="/WEB-INF/tlds/content-override.tld"%>
<%@ include file="/inc/constants.inc" %>

<%-- 头部 --%>
<tu:content contentId="head">	
   	<title>新增用例</title>
   	<script type="text/javascript">
	   	$(document).ready(function(){
	   		//保存
    		$("#savebtn").click(function (){
    			var jsonstring = getJsonString();
    			$("#jsonstring").val(jsonstring);
    			//alert(jsonstring);return false;
	    		
	    		var url = "${CONTEXT_PATH }/user/doSetUserQx";
	    			    		
	    		$("button").attr("disabled","true");
    			$("#tuform").attr("action",url);
	    		$("#tuform").submit();
    		});
	   		
	   		//初始化模块选择
    		$.each($(".check_all"),function (i){
    			var check_items = $(this).parent().parent().next().find(".check_item");
    			var ischecked = true;
       			$.each($(check_items),function (i){
       				if(!$(this).attr("checked")){
       					ischecked = false;
       					return;
       				}
       			});
       			$(this).attr("checked", ischecked);
   			});
   			
   		});
	   	
	  	//全选
   		function doCheckAll(thisobj,checked){
	  		var check_items = $(thisobj).parent().parent().next().find(".check_item");
   			$.each($(check_items),function (i){
   				$(this).attr("checked", checked);   				
   			});
   		}
   		//是否勾选
   		function isCheck(){
   			var ischeck = false;
   			$.each($(".check_item"),function (i){
   				if($(this).attr("checked")){
   					ischeck = true;
   					return;
   				}
   			});
   			return ischeck;
   		}
   		//获取json数据
   		function getJsonString(){
   			var jsonArray = new Array();
   			$.each($(".check_item"),function (i){
   				if(!$(this).attr("checked")){
   					return true;
   				}
   				var index = i+1;
   				var jsonobj = {};
   				var mncode = $(this).attr("mncode");
   				jsonobj.mncode = mncode;
   				
   				jsonArray[jsonArray.length] = jsonobj;   				
   			});
   			var retstr = JSON.stringify(jsonArray);
   			if(jsonArray.length == 0){
   				retstr = "";
   			}
   			
   			return retstr;
   		}
   		
	</script>
	<style>
		.layui-table {margin: 0px;}
		.check_all,.check_item {cursor:pointer;}
		.layui-table tr td {background-color:#fff;}
	</style>
</tu:content>

<%-- 内容 --%>
<tu:content contentId="content">
	<div class="dialog-middle">
		<form id="tuform" class="layui-form" action="${CONTEXT_PATH }/user/toSetUserQx" method="post">
			<input type="hidden" name="id" id="id" value="${bean.id }" />
			<input type="hidden" name="jsonstring" id="jsonstring" />
		</form>
		<table class="layui-table" lay-skin="nob">
			<c:forEach items="${qxsystem.sysmodules }" var="sysmodule" varStatus="modulestatus">
				<tr>
					<td>
						<input type="checkbox" name="check_all" class="check_all" onclick="doCheckAll(this,this.checked)">
						${sysmodule.mdname }
					</td>
				</tr>
				<tr>
					<td>
						<table class="layui-table" lay-skin="nob">
							<c:forEach items="${sysmodule.sysmenus }" var="sysmenu" varStatus="menustatus">
								<tr>
									<td>
										<input type="checkbox" name="" mncode="${sysmenu.mncode }" class="check_item"
											<c:if test="${sysmenu.issetqx eq 1 }">checked</c:if>>
										${sysmenu.mnname }
									</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</c:forEach>
			
		</table>
	</div>
	<div class="dialog-bottom">
		<button class="layui-btn" id="savebtn">保存</button>
		<button class="layui-btn layui-btn-primary" onclick="cloaseTuDialog();return false;">取消</button>
	</div>
</tu:content>

<%@ include file="/common/tu-dialog-template.jsp" %>