<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tu" uri="/WEB-INF/tlds/content-override.tld"%>
<%@ include file="/inc/constants.inc" %>

<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
		<jsp:include page="/common/meta.jsp" flush="true"/>
    	<script type="text/javascript">
		   	$(document).ready(function(){
		   		//
		   		doSetContentHeight();
		   	});
		   	
		   	function doSetContentHeight(){
		   		//网页正文全文高
		   		var scrollHeight = document.documentElement.clientHeight;//document.body.scrollHeight;
		   		//
		   		var content_div = document.getElementsByClassName("content")[0];
		   		var head_div = document.getElementsByClassName("head")[0];
		   		var footer_div = document.getElementsByClassName("footer")[0];
		   		//
		   		var head_offsetHeight = head_div.offsetHeight;
		   		var footer_offsetHeight = footer_div.offsetHeight;
		   		//
		   		content_div.style.minHeight  = scrollHeight - head_offsetHeight - footer_offsetHeight + "px";
		   	}
	   		
		</script>
    	<tu:overridecontent id="head"/>
    </head>
    <body>
    	<!-- 头部 -->
    	<div class="head">
    		<jsp:include page="/common/head.jsp" flush="true"/>    		
    	</div>    	
    	<!-- 中部 -->
    	<div class="middle">
    		<!-- 左侧菜单 -->
    		<div class="menu">
    			<jsp:include page="/common/menu.jsp" flush="true"/>
    		</div>
    		<!-- 右侧内容 -->
    		<div class="content">    			
    			<!-- 面包屑 -->
				<div class="breadcrumb">
					<span class="layui-breadcrumb">
					  	<a href="${CONTEXT_PATH }${menusystem.url1 }">首页</a>
					  	<c:forEach items="${menusystem.sysmodules }" var="sysmodule">
					  		<c:forEach items="${sysmodule.sysmenus }" var="sysmenu">
					  			<c:if test="${currPath ne '/' and fn:contains(sysmenu.url0, currPath)}">
					  				<a href="javascript:;">${sysmenu.mdname }</a>
					  				<a href="${CONTEXT_PATH }${sysmenu.url }">${sysmenu.mnname }</a>
					  			</c:if>
					  		</c:forEach>
					  	</c:forEach>					  	
					</span>
				</div>
				<tu:overridecontent id="content"/>
				<div class="gototop" id="gototop">
					<li class="layui-icon" style="font-size:36px;">&#xe604;</li>
				</div>
				<!-- 滚动条设置 -->
				<script type="text/javascript" src="${CONTEXT_PATH }/js/common/scroll.js"></script>
    		</div>
    		<div class="clear"></div>
    	</div>
    	<!-- 底部 -->
    	<div class="footer layui-bg-gray">
    		<jsp:include page="/common/footer.jsp" flush="true"/>
    		<jsp:include page="/common/message.jsp" flush="true"/>    		
    	</div>
	</body>
</html>