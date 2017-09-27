<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tu" uri="/WEB-INF/tlds/content-override.tld"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/constants.inc" %>

<%-- 头部 --%>
<tu:content contentId="head">	
   	<title>新增部门</title>
   	<script type="text/javascript">
   		$(document).ready(function(){
   			//保存
    		$("#savebtn").click(function (){
    			var deptname = $("#deptname").val();
	    		if(deptname == ""){
	    			tuTips("请输入" + $("#deptname").attr("tip"), "#deptname");
	    			return false;
	    		}
	    		
	    		var id = $("#id").val();
	    		var url = "${CONTEXT_PATH }/dept/save";
	    		if(id != ""){
	    			url = "${CONTEXT_PATH }/dept/update";
	    		}
	    		
	    		$("button").attr("disabled","true");
	    		$("#tuform").attr("action",url);
	    		$("#tuform").submit();
    		});
   			
    		//返回
    		$("#doBack").click(function (){    			
    			window.location.href = "${CONTEXT_PATH }/dept/list";
    			return false;
    		});
   			
   		});
   		
	</script>
</tu:content>

<%-- 内容 --%>
<tu:content contentId="content">
	<div class="layui-tab">
    	<ul class="layui-tab-title">
			<li class="layui-this">新增部门</li>
		</ul>		
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">
				<div class="input-tab">
					<form id="tuform" class="layui-form" action="${CONTEXT_PATH }/dept/toAdd" method="post">
						<input type="hidden" name="id" id="id" value="${bean.id }" />
						<table width="90%" class="input-table" align="center">
							<tr>
								<td width="65%">
									<div class="layui-form-item">
										<label class="layui-form-label">上级部门</label>							
										<div class="layui-input-block" style="width:250px;">
											<c:if test="${empty bean.id }">
												<select name="pdeptcode" id="pdeptcode" tip="上级部门" lay-filter="lay-select">
													<option value="0">请选择</option>
													<c:forEach items="${sysDeptList }" var="item" varStatus="status">
														<option value="${item.deptcode }" <c:if test="${item.deptcode eq bean.pdeptcode }">selected</c:if>>${item.deptname }</option>
													</c:forEach>
												</select>
											</c:if>
											<c:if test="${not empty bean.id }">
												<input type="text" name="" value="${bean.pdeptname }" 
													class="layui-input input-d input-noborder" readonly>
											</c:if>
										</div>
									</div>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td width="55%">
									<div class="layui-form-item">
										<label class="layui-form-label">部门名称</label>
										<div class="layui-input-block">
											<input type="text" name="deptname" id="deptname" value="${bean.deptname }"
												tip="部门名称" placeholder="请输入部门名称" autocomplete="off" class="layui-input">					
										</div>
									</div>
								</td>
								<td><span class="red">*</span></td>
							</tr>
							<tr>
								<td>
									<div class="layui-form-item">
										<label class="layui-form-label">是否可用</label>
										<div class="layui-input-block">
											<input type="radio" name="isuse" value="0" title="否" <c:if test="${bean.isuse eq 0 }">checked</c:if>>
										    <input type="radio" name="isuse" value="1" title="是" <c:if test="${empty bean.isuse or bean.isuse eq 1 }">checked</c:if>>
										</div>
									</div>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
									<div class="layui-form-item">
										<label class="layui-form-label">电话号码</label>
										<div class="layui-input-block">
											<input type="text" name="telno" id="telno" value="${bean.telno }"
												tip="电话号码" placeholder="请输入电话号码" autocomplete="off" class="layui-input ">
										</div>
									</div>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
									<div class="layui-form-item">
										<label class="layui-form-label">传真号码</label>
										<div class="layui-input-block">
											<input type="text" name="faxno" id="faxno" value="${bean.faxno }"
												tip="传真号码" placeholder="请输入传真号码" autocomplete="off" class="layui-input ">
										</div>
									</div>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
									<div class="layui-form-item">
										<label class="layui-form-label">邮箱</label>
										<div class="layui-input-block">
											<input type="text" name="email" id="email" value="${bean.email }"
												tip="邮箱" placeholder="请输入邮箱" autocomplete="off" class="layui-input ">
										</div>
									</div>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
									<div class="layui-form-item">
										<label class="layui-form-label">备注</label>
										<div class="layui-input-block">
											<textarea name="remark" id="remark" placeholder="" class="layui-textarea">${bean.remark }</textarea>
										</div>
									</div>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2">
									<div class="layui-form-item">
										<div class="layui-input-block">
											<button class="layui-btn" id="savebtn">保存</button>
											<button class="layui-btn layui-btn-primary" id="doBack">返回</button>
										</div>
									</div>
								</td>					
							</tr>				
						</table>
					</form>
				</div>
			</div>
		</div>					
	</div>
	
	
	
</tu:content>

<%@ include file="/common/tu-template.jsp" %>