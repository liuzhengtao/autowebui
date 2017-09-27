<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tu" uri="/WEB-INF/tlds/content-override.tld"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/constants.inc" %>

<%-- 头部 --%>
<tu:content contentId="head">	
   	<title>移动元素模板</title>
   	<script type="text/javascript">
	  	$(document).ready(function(){
	   		//保存
    		$("#savebtn").click(function (){
    			var locatorname = $("#locatorname").val();
	    		if(locatorname == ""){
	    			tuTips("请输入" + $("#locatorname").attr("tip"), "#locatorname");
	    			return false;
	    		}
	    		var byvalue = $("#byvalue").val();
	    		if(byvalue == ""){
	    			tuTips("请输入" + $("#byvalue").attr("tip"), "#byvalue");
	    			return false;
	    		}
	    		var timeout = $("#timeout").val();
	    		if(timeout == ""){
	    			tuTips("请输入" + $("#timeout").attr("tip"), "#timeout");
	    			return false;
	    		}
	    		var waittime = $("#waittime").val();
	    		if(waittime == ""){
	    			tuTips("请输入" + $("#waittime").attr("tip"), "#waittime");
	    			return false;
	    		}
	    		var orderno = $("#orderno").val();
	    		if(orderno == ""){
	    			tuTips("请输入" + $("#orderno").attr("tip"), "#orderno");
	    			return false;
	    		}
	    		
	    		var url = "${CONTEXT_PATH }/slnmpagetemp/doMoveLocator";
	    		
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
		<form id="tuform" class="layui-form" action="${CONTEXT_PATH }/slnmpagetemp/toMoveLocator" method="post">
			<input type="hidden" name="id" id="id" value="${bean.id }" />
			<table width="90%" class="input-table" align="center">
				<tr>
					<td width="65%">
						<div class="layui-form-item">
							<label class="layui-form-label">页面名称</label>							
							<div class="layui-input-block">
								<select name="pagecode" id="pagecode" lay-filter="lay-select">
									<c:forEach items="${slnmPageList }" var="item" varStatus="status">
										<option value="${item.pagecode }" <c:if test="${item.pagecode eq bean.pagecode }">selected</c:if>>${item.pagename }</option>
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
							<label class="layui-form-label">元素名称</label>
							<div class="layui-input-block">
								<input type="text" name="locatorname" id="locatorname" value="${bean.locatorname }"
									tip="元素名称" placeholder="请输入元素名称" autocomplete="off" class="layui-input input-d">					
							</div>
						</div>
					</td>
					<td><span class="red">*</span></td>
				</tr>
				<tr>
					<td>
						<div class="layui-form-item">
							<label class="layui-form-label">定位方式</label>
							<div class="layui-input-block" style="width:250px;" id="bytype-select-tip">
								<select name="bytype" id="bytype" tip="定位方式" lay-filter="lay-select">
									<option value="id" <c:if test="${empty bean.bytype or bean.bytype eq 'id' }">selected</c:if>>id</option>
									<option value="name" <c:if test="${bean.bytype eq 'name' }">selected</c:if>>name</option>
									<option value="linkText" <c:if test="${bean.bytype eq 'linkText' }">selected</c:if>>linkText</option>
									<option value="partialLinkText" <c:if test="${bean.bytype eq 'partialLinkText' }">selected</c:if>>partialLinkText</option>
									<option value="cssSelector" <c:if test="${bean.bytype eq 'cssSelector' }">selected</c:if>>cssSelector</option>
									<option value="xpath" <c:if test="${bean.bytype eq 'xpath' }">selected</c:if>>xpath</option>
									<option value="className" <c:if test="${bean.bytype eq 'className' }">selected</c:if>>className</option>
									<option value="tagName" <c:if test="${bean.bytype eq 'tagName' }">selected</c:if>>tagName</option>									
								</select>
							</div>
						</div>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="65%">
						<div class="layui-form-item">
							<label class="layui-form-label">定位信息</label>
							<div class="layui-input-block">
								<input type="text" name="byvalue" id="byvalue" value="${bean.byvalue }"
									tip="定位信息" placeholder="请输入定位信息" autocomplete="off" class="layui-input input-d">					
							</div>
						</div>
					</td>
					<td><span class="red">*</span></td>
				</tr>
				<tr>
					<td>
						<div class="layui-form-item">
							<label class="layui-form-label">操作类型</label>
							<div class="layui-input-block" style="width:250px;" id="opttype-select-tip">
								<select name="opttype" id="opttype" tip="操作类型" lay-filter="lay-select">
									<option value="click" <c:if test="${empty bean.opttype || bean.opttype eq 'click' }">selected</c:if>>点击(Click)</option>
									<option value="type" <c:if test="${bean.opttype eq 'type' }">selected</c:if>>文本框输入(Type)</option>
									<option value="keybord_type" <c:if test="${bean.opttype eq 'keybord_type' }">selected</c:if>>键盘输入(KeybordType)</option>
									<option value="prompt_type" <c:if test="${bean.opttype eq 'prompt_type' }">selected</c:if>>提示框输入(PromptType)</option>
									<option value="prompt_type_accept" <c:if test="${bean.opttype eq 'prompt_type_accept' }">selected</c:if>>提示框输入确认(PromptTypeAccept)</option>
									<option value="clear" <c:if test="${bean.opttype eq 'clear' }">selected</c:if>>文本框清空(Clear)</option>
									<option value="switchToFrame" <c:if test="${bean.opttype eq 'switchToFrame' }">selected</c:if>>进入(Frame)</option>
									<option value="exitFromFrame" <c:if test="${bean.opttype eq 'exitFromFrame' }">selected</c:if>>退出(Frame)</option>
									<option value="click_left" <c:if test="${bean.opttype eq 'click_left' }">selected</c:if>>鼠标左键单击</option>
									<option value="exist_click" <c:if test="${bean.opttype eq 'exist_click' }">selected</c:if>>元素存在则点击</option>
									<option value="exist_stop" <c:if test="${bean.opttype eq 'exist_stop' }">selected</c:if>>元素存在则停止</option>
									<option value="wait_type" <c:if test="${bean.opttype eq 'wait_type' }">selected</c:if>>等待文本框输入</option>
								</select>
							</div>
						</div>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="65%">
						<div class="layui-form-item">
							<label class="layui-form-label">数据值</label>
							<div class="layui-input-block">
								<input type="text" name="datavalue" id="datavalue" value="${bean.datavalue }"
									tip="数据值" placeholder="请输入数据值" autocomplete="off" class="layui-input input-d">					
							</div>
						</div>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="65%">
						<div class="layui-form-item">
							<label class="layui-form-label">超时时间(秒)</label>
							<div class="layui-input-block">
								<input type="text" name="timeout" id="timeout" value="${empty bean.timeout? 10:bean.timeout }"
									tip="超时时间" placeholder="请输入超时时间" autocomplete="off" class="layui-input input-b">					
							</div>
						</div>
					</td>
					<td><span class="red">*</span></td>
				</tr>
				<tr>
					<td width="65%">
						<div class="layui-form-item">
							<label class="layui-form-label">等待时间(秒)</label>
							<div class="layui-input-block">
								<input type="text" name="waittime" id="waittime" value="${empty bean.waittime? 0.2:bean.waittime }"
									tip="等待时间" placeholder="请输入等待时间" autocomplete="off" class="layui-input input-b">					
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
									tip="排序号" placeholder="请输入排序号" autocomplete="off" class="layui-input input-b">					
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