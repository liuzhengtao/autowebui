<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/inc/constants.inc" %>

<script type="text/javascript">    		    		
   	$(document).ready(function(){  
   		<c:if test="${not empty promptMessage.success}">
   			tuSucces("${promptMessage.success}");
			$.post("${CONTEXT_PATH}/promptmessage/removemessage");
	   	</c:if>
	   	<c:if test="${not empty promptMessage.error}">
	   		tuError("${promptMessage.error}");
	   		$.post("${CONTEXT_PATH}/promptmessage/removemessage");
   		</c:if>
	});
</script>
