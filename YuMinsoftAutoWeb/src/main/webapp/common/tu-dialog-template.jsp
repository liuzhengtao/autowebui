<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		   		var scrollHeight = document.body.scrollHeight;
		   		//
		   		var content_div = document.getElementsByClassName("dialog-middle")[0];
		   		var bottom_div = document.getElementsByClassName("dialog-bottom")[0];
		   		//
		   		var bottom_offsetHeight = bottom_div.offsetHeight;
		   		//
		   		content_div.style.minHeight  = scrollHeight - bottom_offsetHeight - 10 + "px";
		   	}
	   		
		</script>
    	<tu:overridecontent id="head"/>
    </head>
    <body>
    	<!-- 内容 -->
    	<div class="dialog-content">
    		<tu:overridecontent id="content"/>    		
    	</div>    	
	</body>
</html>