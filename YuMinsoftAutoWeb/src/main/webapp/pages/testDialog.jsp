<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tu" uri="/WEB-INF/tlds/content-override.tld"%>
<%@ include file="/inc/constants.inc" %>

<%-- 头部 --%>
<tu:content contentId="head">	
   	<title>test</title>
   	<script type="text/javascript">
	   	$(document).ready(function(){
	   		//
    		$("#doPrompt").click(function (){    			
    			var name = prompt("请输入名字","");
    			return false;
    		});
   		});
   		
	</script>
</tu:content>

<%-- 内容 --%>
<tu:content contentId="content">
	<div class="input-tab">
		<fieldset class="layui-elem-field">							  
			<div class="layui-field-box">
				<button class="layui-btn" id="doPrompt">
					<i class="layui-icon" style="font-size:20px;color:#fff;">&#xe654;</i>弹出Prompt
				</button>
			</div>
		</fieldset>
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn layui-btn-primary" id="cloaseDialog" onclick="cloaseTuDialog();return false;">取消</button>
			</div>
		</div>
	</div>
</tu:content>

<%@ include file="/common/tu-dialog-template.jsp" %>