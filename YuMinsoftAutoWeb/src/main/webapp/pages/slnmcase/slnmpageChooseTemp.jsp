<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tu" uri="/WEB-INF/tlds/content-override.tld"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/constants.inc" %>

<%-- 头部 --%>
<tu:content contentId="head">	
   	<title>选择页面模板</title>
   	<script type="text/javascript">
	  	$(document).ready(function(){
	   		//保存
    		$("#savebtn").click(function (){
    			//是否选择
    			if(!isCheck()){
    				tuAlert("请选择数据");
        			return false;
       			}
    			//验证数据格式
    			if(!checkValue()){
    				return false;
    			}
    			
    			var jsonstring = getJsonString();
    			$("#jsonstring").val(jsonstring);
    			
	    		var url = "${CONTEXT_PATH }/slnmcase/doAddPageFromTemp";
	    		
	    		$("button").attr("disabled","true");
    			$("#tuform").attr("action",url);
	    		$("#tuform").submit();
    		});
	   		   			
   		});
	  	
	  	//全选
   		function doCheckAll(checked){   			
   			$.each($(".check_item"),function (i){
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
   		//验证数据格式
   		function checkValue(){
   			var flag = true;
   			$.each($(".check_item"),function (i){
   				if(!$(this).attr("checked")){
   					return true;
   				}
   				var index = i+1;
   				var orderno = $("#orderno_" + index).val();
   				if(isNull(orderno)){
   					tuAlert("第" + index + "行排序为空");
   					return flag = false;
   				}
   				var r = /^\+?[1-9][0-9]*$/;
   				if(!r.test(orderno)){
   					tuAlert("第" + index + "行排序必须为正整数");
   					return flag = false;
   				}
   				
   			});
   			
   			return flag;
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
   				var tempid = $("#tempid_" + index).val();
   				jsonobj.tempid = tempid;
   				var orderno = $("#orderno_" + index).val();
   				jsonobj.orderno = orderno;
   				
   				jsonArray[jsonArray.length] = jsonobj;   				
   			});
   			var retstr = JSON.stringify(jsonArray);
   			
   			return retstr;
   		}
   		
	</script>
	<style>
		.layui-elem-quote {background-color:#ffffff;border-left:1px solid #ffffff;text-align:center;font-weight:bold;font-size:20px;padding: 5px;}
	</style>
</tu:content>

<%-- 内容 --%>
<tu:content contentId="content">
	<div class="dialog-middle">
		<form id="tuform" class="layui-form" action="${CONTEXT_PATH }/slnmcase/toChoosePageTemp" method="post">
			<input type="hidden" name="chaptercode" id="chaptercode" value="${slnmChapter.chaptercode }" />
			<input type="hidden" name="jsonstring" id="jsonstring" />
		</form>
		<blockquote class="layui-elem-quote">页面模板列表</blockquote>
		
		<table width="90%" class="layui-table data_tb" align="center">
			<thead>
			<tr style="background-color:#408FBD;">
				<th width="13px">
		      	  <input type="checkbox" name="check_all" id="check_all" onclick="doCheckAll(this.checked)">
		        </th>
				<th width="40px">序号</th>
      			<th width="20%">页面名称</th>
      			<th width="">页面地址</th>
      			<th width="15%">数据文件类型</th>
      			<th width="10%">是否运行</th>
      			<th width="14%">排序</th>
			</tr>
			</thead>
			<c:forEach items="${slnmPageTempList }" var="item" varStatus="status">
			<tr>
				<td>
		      	  <input type="checkbox" name="check_item_${status.index+1 }" id="check_item_${status.index+1 }" class="check_item">
		      	  <input type="hidden" id="tempid_${status.index+1 }" value="${item.id }" />
		        </td>
				<td align="center">${status.index+1 }</td>
      			<td class="layui-elip" title="${item.pagename }">${item.pagename }</td>
				<td class="layui-elip" title="${item.pageurl }">${item.pageurl }</td>
      			<td align="center">
      				<c:if test="${item.datafiletype eq 1 }">Excel文件</c:if>
      				<c:if test="${item.datafiletype eq 2 }">Xml文件</c:if>
      			</td>
      			<td align="center">
      				<c:if test="${item.isrun eq 1 }">是</c:if>
      				<c:if test="${item.isrun ne 1 }">否</c:if>
      			</td>
      			<td align="center">
      				<input type="text" id="orderno_${status.index+1 }" value="${item.orderno }" 
      				  autocomplete="off" class="layui-input" style="width:70px;">
      			</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div class="dialog-bottom">
		<button class="layui-btn" style="background-color:#408FBD;" id="savebtn">添加至${slnmChapter.chaptername }</button>
		<button class="layui-btn layui-btn-primary" onclick="cloaseTuDialog();return false;">取消</button>
	</div>
</tu:content>

<%@ include file="/common/tu-dialog-template.jsp" %>