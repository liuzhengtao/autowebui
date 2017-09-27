<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/inc/constants.inc" %>

<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
    	<title>登录</title>
    	
    	<jsp:include page="/common/meta.jsp" flush="true"/>
    	<link rel="stylesheet" type="text/css" href="${CONTEXT_PATH }/css/login.css"/>
    	
    	<style>
    		
    	</style>
    	<script type="text/javascript">    		    		
	    	$(document).ready(function(){
	    		var kookie_name = "operator_code";
	    		var operatorcode0 = getCookie(kookie_name);
	    		if(operatorcode0 == null || operatorcode0 == ""){
	    			//alert("operatorcode0 == null");
	    		}else{
	    			$("#operatorcode").val(operatorcode0);
	    			$("#rememberCode").attr("checked",true);
	    			$("#rememberCode").val(1);
	    		}
	    		
	    		//登录
	    		$("#loginbtn").click(function(){
	    			//用户名
		    		var operatorcode = $("#operatorcode").val();	    			
		    		if(operatorcode == ""){
		    			tuTips("请输入" + $("#operatorcode").attr("tip"), "#operatorcode");
		    			return false;
		    		}
		    		//密码
		    		var operatorpass = $("#operatorpass").val();
		    		if(operatorpass == ""){
		    			tuTips("请输入" + $("#operatorpass").attr("tip"), "#operatorpass");
		    			return false;
		    		}
		    		
		    		if($("#rememberCode").attr("checked")){
		    			setCookie(kookie_name,operatorcode);
		    		}else{
		    			delCookie(kookie_name);
		    		}
		    		
		    		$("#tuform").attr("action","${CONTEXT_PATH }/login/doLogin");
		    		$("#tuform").submit();
	    		});
	    		
	    		//
		   		doSetContentHeight();
	   			
	   		});
	    	
	    	function doSetContentHeight(){
		   		//网页正文全文高
		   		var scrollHeight = document.documentElement.clientHeight;
		   		//
		   		var content_div = document.getElementsByClassName("login-content")[0];
		   		var head_div = document.getElementsByClassName("head")[0];
		   		var footer_div = document.getElementsByClassName("footer")[0];
		   		//
		   		var head_offsetHeight = head_div.offsetHeight;
		   		var footer_offsetHeight = footer_div.offsetHeight;
		   		//
		   		content_div.style.minHeight  = scrollHeight - head_offsetHeight - footer_offsetHeight + "px";
		   	}
		</script>    	
    </head>
    <body>    			
    	<!-- 头部 -->
    	<div class="head">
    		<jsp:include page="/common/head.jsp" flush="true"/>    		
    	</div>
    	<div class="login-content">
    		<div class="login-formdiv">
				<form id="tuform" class="layui-form" action="${CONTEXT_PATH }/login/index" method="post">	
					<input type="hidden" name="url" id="url" value="${url }" />
					<div class="layui-form-item">
						<label class="layui-form-label">用户名</label>
						<div class="layui-input-block">
							<input type="text" name="operatorcode" id="operatorcode" tip="用户名"
								placeholder="请输入用户名" autocomplete="off" class="layui-input">							
						</div>						
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block checkbox-input-block">
							<input type="checkbox" name="rememberCode" id="rememberCode" value="0" lay-filter="lay-checkbox"
								lay-filter="rememberCode" lay-skin="primary" title="记住用户名">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">密码</label>
						<div class="layui-input-block">
							<input type="password" name="operatorpass" id="operatorpass" tip="密码"
								placeholder="请输入密码" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item">						
						<div class="layui-input-block">
							<button class="layui-btn layui-btn-big" id="loginbtn" style="width:100%">登&nbsp;&nbsp;录</button>
						</div>
					</div>					
				</form>			
			</div>
		</div>  
		<!-- 底部 -->
    	<div class="footer layui-bg-gray">
    		<jsp:include page="/common/footer.jsp" flush="true"/>
    		<jsp:include page="/common/message.jsp" flush="true"/>    		
    	</div>  	
    </body>
</html>