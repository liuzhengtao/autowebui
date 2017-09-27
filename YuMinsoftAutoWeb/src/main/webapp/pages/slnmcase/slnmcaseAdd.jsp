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
    			var casename = $("#casename").val();
	    		if(casename == ""){
	    			tuTips("请输入" + $("#casename").attr("tip"), "#casename");
	    			return false;
	    		}
	    		var drivertype = $("#drivertype").val();
	    		if(drivertype == ""){
	    			tuTips("请选择" + $("#drivertype").attr("tip"), "#drivertype-select-tip");
	    			return false;
	    		}
	    		
	    		var id = $("#id").val();
	    		var url = "${CONTEXT_PATH }/slnmcase/save";
	    		if(id != ""){
	    			url = "${CONTEXT_PATH }/slnmcase/update";
	    		}
	    		
	    		$("button").attr("disabled","true");
    			$("#tuform").attr("action",url);
	    		$("#tuform").submit();
    		});
   			
   		});
   		
	</script>
</tu:content>

<%-- 内容 --%>
<tu:content contentId="content">
	<div class="dialog-middle">
		<form id="tuform" class="layui-form" action="${CONTEXT_PATH }/slnmcase/toAdd" method="post">
			<input type="hidden" name="id" id="id" value="${bean.id }" />
			<table width="90%" class="input-table" align="center">
				<tr>
					<td width="65%">
						<div class="layui-form-item">
							<label class="layui-form-label">用例名称</label>
							<div class="layui-input-block">
								<input type="text" name="casename" id="casename" value="${bean.casename }"
									tip="用例名称" placeholder="请输入用例名称" autocomplete="off" class="layui-input input-d">					
							</div>
						</div>
					</td>
					<td><span class="red">*</span></td>
				</tr>
				<tr>
					<td>
						<div class="layui-form-item">
							<label class="layui-form-label">浏览器</label>
							<div class="layui-input-block" style="width:150px;" id="drivertype-select-tip">
								<select name="drivertype" id="drivertype" tip="浏览器" lay-filter="lay-select">
									<option value="1" <c:if test="${empty bean.drivertype or bean.drivertype eq 1 }">selected</c:if>>IE</option>
									<option value="2" <c:if test="${bean.drivertype eq 2 }">selected</c:if>>Firefox</option>
									<option value="3" <c:if test="${bean.drivertype eq 3 }">selected</c:if>>Chrome</option>													
								</select>
							</div>
						</div>
					</td>
					<td><span class="red">*</span></td>
				</tr>
				<tr>
					<td>
						<div class="layui-form-item">
							<label class="layui-form-label">浏览器路径</label>
							<div class="layui-input-block">
								<input type="text" name="browserpath" id="browserpath" value="${bean.browserpath }"
									tip="浏览器路径" placeholder="请输入浏览器路径" autocomplete="off" class="layui-input input-d">					
							</div>
						</div>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
						<div class="layui-form-item">
							<label class="layui-form-label">退出浏览器</label>
							<div class="layui-input-block">
								<input type="radio" name="isquitbrowser" value="0" title="否" <c:if test="${empty bean.isquitbrowser or bean.isquitbrowser eq 0 }">checked</c:if>>
							    <input type="radio" name="isquitbrowser" value="1" title="是" <c:if test="${bean.isquitbrowser eq 1 }">checked</c:if>>
							</div>
						</div>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
						<div class="layui-form-item">
							<label class="layui-form-label">描述</label>
							<div class="layui-input-block">
								<textarea name="description" id="description" class="layui-textarea">${bean.description }</textarea>
							</div>
						</div>
					</td>
					<td>&nbsp;</td>
				</tr>
				<!-- 
				<tr>
					<td colspan="2">
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn" id="savebtn">保存</button>
								<button class="layui-btn layui-btn-primary" onclick="cloaseTuDialog();return false;">取消</button>
							</div>
						</div>
					</td>					
				</tr> -->
			</table>
		</form>
	</div>
	<div class="dialog-bottom">
		<button class="layui-btn" id="savebtn">保存</button>
		<button class="layui-btn layui-btn-primary" onclick="cloaseTuDialog();return false;">取消</button>
	</div>
</tu:content>

<%@ include file="/common/tu-dialog-template.jsp" %>