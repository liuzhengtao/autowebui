<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/inc/constants.inc" %>

<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
    	<title>无权限页面</title>
    	<jsp:include page="/common/meta.jsp" flush="true"/>
    	<script type="text/javascript">    		    		
	    	$(document).ready(function(){
	    		//
		   		doSetContentHeight();
	    		//
	    		setInterval(showOverTime, 1000);
	   		});
	    	
	    	function doSetContentHeight(){
		   		//网页正文全文高
		   		var scrollHeight = document.documentElement.clientHeight;
		   		//
		   		var content_div = document.getElementsByClassName("noauth-content")[0];
		   		var head_div = document.getElementsByClassName("head")[0];
		   		var footer_div = document.getElementsByClassName("footer")[0];
		   		//
		   		var head_offsetHeight = head_div.offsetHeight;
		   		var footer_offsetHeight = footer_div.offsetHeight;
		   		//
		   		content_div.style.minHeight  = scrollHeight - head_offsetHeight - footer_offsetHeight + "px";
		   	}
	    	
	    	//
	    	function showOverTime(){
	    		//
	    		var leftseconds = parseInt($("#over_time").text());
	    		leftseconds = leftseconds - 1;
	    		if(leftseconds == 0){
	    			//
	    			window.location.href = "${CONTEXT_PATH }";
	    		}
	    		$("#over_time").text(leftseconds);
	    	}
	    	
		</script>
		<style>
    		
    	</style>
    </head>
    <body>    			
    	<!-- 头部 -->
    	<div class="head">
    		<jsp:include page="/common/head.jsp" flush="true"/>    		
    	</div>
    	<div class="noauth-content">
    		<form id="tuform" class="layui-form" action="${CONTEXT_PATH }/noAuth/index" method="post">	
				<div class="noauth-tips">
					<span class="tips_span">
						${noAuthMessage }， <span id="over_time" class="over_time">5</span> 秒后将跳转到<a href="${CONTEXT_PATH }">首页</a>
					</span>
				</div>				
			</form>
		</div>
		<!-- 底部 -->
    	<div class="footer layui-bg-gray">
    		<jsp:include page="/common/footer.jsp" flush="true"/>
    	</div>  	
    </body>
</html>