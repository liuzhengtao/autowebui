<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="yumin" uri="/WEB-INF/tlds/content-override.tld"%>
<%@ include file="/inc/constants.inc" %>

<%-- 头部 --%>
<yumin:content contentId="head">
	<title>首页</title>
   	<script type="text/javascript">
   		$(document).ready(function(){
   			//
   		});
   		
	</script>
</yumin:content>

<%-- 内容 --%>
<yumin:content contentId="content">
	<div class="layui-tab">
		<fieldset class="layui-elem-field">							  
			<div class="layui-field-box">
				这里是首页
			</div>
		</fieldset>
		
	</div>
</yumin:content>

<%@ include file="/common/tu-template.jsp" %>