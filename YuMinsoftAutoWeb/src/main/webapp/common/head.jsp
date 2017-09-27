<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/inc/constants.inc" %>

<script type="text/javascript">
   	$(document).ready(function(){
   		//
   		$("#logout").click(function(){
   			window.location.href = "${CONTEXT_PATH }/login/doLogout?url=${currPath }";
   		});
  	});
</script>

<div class="head-nav">
	<p class="head-title">${empty menusystem.sysname ? "YuMinSoftAutoWeb" : menusystem.sysname }</p>
	<p class="head-userinfo">
		<c:if test="${not empty USER_SESSION_ID }">
			当前用户：	${USER_SESSION_ID.username }
		 	<span class="separator">|</span>
		 	<span class="logout-icon" id="logout">退出</span>
		</c:if>
	</p>
	<p class="head-sysdate">
		<c:if test="${not empty USER_SESSION_ID }">
			当前时间:
			<span class="sysdate"></span>
		</c:if>
	</p>
     <script>
		 function showTime() {
			 var curTime = new Date();
			 $(".sysdate").html(curTime.toLocaleString());
			 setTimeout("showTime()", 1000);
		 }
		 // 页面加载完成后执行上面的自定义函数
		 $(function(){
			 showTime();
		 })
	 </script>
</div>
