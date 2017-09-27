<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tu" uri="/WEB-INF/tlds/content-override.tld"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/constants.inc" %>

<%-- 头部 --%>
<tu:content contentId="head">	
   	<title>新增用户</title>
   	<script type="text/javascript">
   		$(document).ready(function(){
   			//保存
    		$("#savebtn").click(function (){
    			var deptcode = $("#deptcode").val();
	    		if(deptcode == ""){
	    			tuTips("请选择" + $("#deptcode").attr("tip"), "#deptcode-select-tip");
	    			return false;
	    		}
    			var usercode = $("#usercode").val();
	    		if(usercode == ""){
	    			tuTips("请输入" + $("#usercode").attr("tip"), "#usercode");
	    			return false;
	    		}
	    		var username = $("#username").val();
	    		if(username == ""){
	    			tuTips("请输入" + $("#username").attr("tip"), "#username");
	    			return false;
	    		}
	    		
	    		var id = $("#id").val();
	    		if(id == ""){
	    			var userpwd = $("#userpwd").val();
		    		if(userpwd == ""){
		    			tuTips("请输入" + $("#userpwd").attr("tip"), "#userpwd");
		    			return false;
		    		}
		    		var userpwd0 = $("#userpwd0").val();
		    		if(userpwd0 == ""){
		    			tuTips("请输入" + $("#userpwd0").attr("tip"), "#userpwd0");
		    			return false;
		    		}
		    		if(userpwd0 != userpwd){
		    			tuAlert($("#userpwd0").attr("tip") + "与" + $("#userpwd").attr("tip") + "不相同");
		    			return false;
		    		}
	    		}
	    		
	    		//var isuse = $("input[name='isuse']:checked").val();
	    		//alert(isuse);return false;
	    			    		
	    		var deptcode = $("#deptcode").val();
	    		if(deptcode == ""){
	    			tuTips("请输入" + $("#deptcode").attr("tip"), "#deptcode");
	    			return false;
	    		}
	    		
	    		var url = "${CONTEXT_PATH }/user/save";
	    		if(id != ""){
	    			url = "${CONTEXT_PATH }/user/update";
	    		}
	    		
	    		$("button").attr("disabled","true");
	    		$("#tuform").attr("action",url);
	    		$("#tuform").submit();
    		});
   			
    		//返回
    		$("#doBack").click(function (){    			
    			window.location.href = "${CONTEXT_PATH }/user/list";
    			return false;
    		});
   			
   		});
   		
	</script>
</tu:content>

<%-- 内容 --%>
<tu:content contentId="content">
	<div class="layui-tab">
    	<ul class="layui-tab-title">
			<li class="layui-this">新增用户</li>
		</ul>		
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">
				<div class="input-tab">
					<form id="tuform" class="layui-form" action="${CONTEXT_PATH }/user/toAdd" method="post">
						<input type="hidden" name="id" id="id" value="${bean.id }" />
						<table width="90%" class="input-table" align="center">
							<tr>
								<td>
									<div class="layui-form-item">
										<label class="layui-form-label">所在部门</label>
										<div class="layui-input-block" id="deptcode-select-tip">
											<select name="deptcode" id="deptcode" tip="所在部门" lay-filter="lay-select">
												<option value="">请选择</option>
												<c:forEach items="${sysDeptList }" var="item" varStatus="status">
													<option value="${item.deptcode }" <c:if test="${item.deptcode eq bean.deptcode }">selected</c:if>>${item.deptname }</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</td>
								<td><span class="red">*</span></td>
							</tr>
							<tr>
								<td width="55%">
									<div class="layui-form-item">
										<label class="layui-form-label">用户登录代码</label>
										<div class="layui-input-block">
											<input type="text" name="usercode" id="usercode" value="${bean.usercode }"
												tip="用户登录代码" placeholder="请输入用户登录代码" autocomplete="off" class="layui-input">					
										</div>
									</div>
								</td>
								<td><span class="red">*</span></td>
							</tr>
							<tr>
								<td>
									<div class="layui-form-item">
										<label class="layui-form-label">用户名称</label>
										<div class="layui-input-block">
											<input type="text" name="username" id="username" value="${bean.username }"
												tip="用户名称" placeholder="请输入用户名称" autocomplete="off" class="layui-input ">
										</div>
									</div>
								</td>
								<td><span class="red">*</span></td>
							</tr>
							<c:if test="${empty bean}">
								<tr>
									<td>
										<div class="layui-form-item">
											<label class="layui-form-label">密码</label>
											<div class="layui-input-block">
												<input type="password" name="userpwd" id="userpwd" value=""
													tip="密码" placeholder="请输入密码" autocomplete="off" class="layui-input ">
											</div>
										</div>
									</td>
									<td><span class="red">*</span></td>
								</tr>
								<tr>
									<td>
										<div class="layui-form-item">
											<label class="layui-form-label">确认密码</label>
											<div class="layui-input-block">
												<input type="password" name="userpwd0" id="userpwd0" value=""
													tip="确认密码" placeholder="请输入确认密码" autocomplete="off" class="layui-input ">
											</div>
										</div>
									</td>
									<td><span class="red">*</span></td>
								</tr>
							</c:if>
							<tr>
								<td>
									<div class="layui-form-item">
										<label class="layui-form-label">是否管理员</label>
										<div class="layui-input-block">
											<input type="radio" name="isadmin" value="0" title="否" <c:if test="${empty bean.isadmin or bean.isadmin eq 0 }">checked</c:if>>
										    <input type="radio" name="isadmin" value="1" title="是" <c:if test="${bean.isadmin eq 1 }">checked</c:if>>
										</div>
									</div>
								</td>
								<td>&nbsp;</td>
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
										<label class="layui-form-label">手机号码</label>
										<div class="layui-input-block">
											<input type="text" name="mobileno" id="mobileno" value="${bean.mobileno }"
												tip="手机号码" placeholder="请输入手机号码" autocomplete="off" class="layui-input ">
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