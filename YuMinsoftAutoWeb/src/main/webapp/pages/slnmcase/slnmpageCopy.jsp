<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tu" uri="/WEB-INF/tlds/content-override.tld"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/constants.inc" %>

<%-- 头部 --%>
<tu:content contentId="head">	
   	<title>复制页面</title>
   	<script type="text/javascript">
	  	$(document).ready(function(){
	   		//保存
    		$("#savebtn").click(function (){
    			var pagename = $("#pagename").val();
	    		if(pagename == ""){
	    			tuTips("请输入" + $("#pagename").attr("tip"), "#pagename");
	    			return false;
	    		}
	    		var datafiletype = $("#datafiletype").val();
	    		if(datafiletype == ""){
	    			tuTips("请选择" + $("#datafiletype").attr("tip"), "#datafiletype-select-tip");
	    			return false;
	    		}
	    		
	    		var orderno = $("#orderno").val();
	    		if(orderno == ""){
	    			tuTips("请输入" + $("#orderno").attr("tip"), "#orderno");
	    			return false;
	    		}
	    		
	    		var url = "${CONTEXT_PATH }/slnmcase/doCopyPage";
	    		
	    		$("button").attr("disabled","true");
    			$("#tuform").attr("action",url);
	    		$("#tuform").submit();
    		});
   			
   		});
   		
	</script>
	<style>
		.layui-upload-button {min-width: 250px;}
	</style>
</tu:content>

<%-- 内容 --%>
<tu:content contentId="content">
	<div class="dialog-middle">
		<form id="tuform" class="layui-form" action="${CONTEXT_PATH }/slnmcase/toCopyPage" method="post">
			<input type="hidden" name="id" id="id" value="${bean.id }" />
			<table width="90%" class="input-table" align="center">
				<tr>
					<td width="65%">
						<div class="layui-form-item">
							<label class="layui-form-label">章节名称</label>							
							<div class="layui-input-block">
								<select name="chaptercode" id="chaptercode" lay-filter="lay-select">
									<c:forEach items="${slnmchapterlist }" var="item" varStatus="status">
										<option value="${item.chaptercode }" <c:if test="${item.chaptercode eq bean.chaptercode }">selected</c:if>>${item.chaptername }</option>
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
							<label class="layui-form-label">页面名称</label>
							<div class="layui-input-block">
								<input type="text" name="pagename" id="pagename" value="${bean.pagename }"
									tip="页面名称" placeholder="请输入页面名称" autocomplete="off" class="layui-input input-d">					
							</div>
						</div>
					</td>
					<td><span class="red">*</span></td>
				</tr>
				<tr>
					<td>
						<div class="layui-form-item">
							<label class="layui-form-label">页面地址</label>
							<div class="layui-input-block">
								<input type="text" name="pageurl" id="pageurl" value="${bean.pageurl }"
									tip="页面地址" placeholder="请输入页面地址" autocomplete="off" class="layui-input input-d">					
							</div>
						</div>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
						<div class="layui-form-item">
							<label class="layui-form-label">数据文件类型</label>
							<div class="layui-input-block" style="width:250px;" id="datafiletype-select-tip">
								<select name="datafiletype" id="datafiletype" tip="驱动类型" lay-filter="lay-select">
									<option value="">请选择</option>
									<option value="1" <c:if test="${empty bean.datafiletype or bean.datafiletype eq 1 }">selected</c:if>>Excel文件</option>
									<option value="2" <c:if test="${bean.datafiletype eq 2 }">selected</c:if>>Xml文件</option>												
								</select>
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