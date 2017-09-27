<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tu" uri="/WEB-INF/tlds/content-override.tld"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/constants.inc" %>

<%-- 头部 --%>
<tu:content contentId="head">	
   	<title>新增章节</title>
   	<script type="text/javascript">
	  	$(document).ready(function(){
	   		//保存
    		$("#savebtn").click(function (){
    			var chaptername = $("#chaptername").val();
	    		if(chaptername == ""){
	    			tuTips("请输入" + $("#chaptername").attr("tip"), "#chaptername");
	    			return false;
	    		}
	    		
	    		var orderno = $("#orderno").val();
	    		if(orderno == ""){
	    			tuTips("请输入" + $("#orderno").attr("tip"), "#orderno");
	    			return false;
	    		}
	    		
	    		var url = "${CONTEXT_PATH }/slnmcase/doCopyChapter";
	    		
	    		$("button").attr("disabled","true");
    			$("#tuform").attr("action",url);
	    		$("#tuform").submit();
    		});
	   		
   		});
   		
	</script>
	<style>
		
	</style>
</tu:content>

<%-- 内容 --%>
<tu:content contentId="content">
	<div class="dialog-middle">
		<form id="tuform" class="layui-form" action="${CONTEXT_PATH }/slnmcase/toCopyChapter" method="post">
			<input type="hidden" name="id" id="id" value="${bean.id }" />
			<table width="90%" class="input-table" align="center">
				<tr>
					<td width="65%">
						<div class="layui-form-item">
							<label class="layui-form-label">用例名称</label>							
							<div class="layui-input-block">
								<select name="casecode" id="casecode" lay-filter="lay-select">
									<c:forEach items="${slnmcaselist }" var="item" varStatus="status">
										<option value="${item.casecode }" <c:if test="${item.casecode eq bean.casecode }">selected</c:if>>${item.casename }</option>
									</c:forEach>
								</select>					
							</div>
						</div>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="65%">
						<div class="layui-form-item">
							<label class="layui-form-label">章节名称</label>
							<div class="layui-input-block">
								<input type="text" name="chaptername" id="chaptername" value="${bean.chaptername }"
									tip="章节名称" placeholder="请输入章节名称" autocomplete="off" class="layui-input input-d">					
							</div>
						</div>
					</td>
					<td><span class="red">*</span></td>
				</tr>
				<tr>
					<td>
						<div class="layui-form-item">
							<label class="layui-form-label">排序号</label>
							<div class="layui-input-block">
								<input type="text" name="orderno" id="orderno" value=""
									tip="排序号" placeholder="请输入排序号" autocomplete="off" class="layui-input input-d">					
							</div>
						</div>
					</td>
					<td><span class="red">*</span></td>
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
			</table>
		</form>
	</div>
	<div class="dialog-bottom">
		<button class="layui-btn" id="savebtn">保存</button>
		<button class="layui-btn layui-btn-primary" onclick="cloaseTuDialog();return false;">取消</button>
	</div>
</tu:content>

<%@ include file="/common/tu-dialog-template.jsp" %>