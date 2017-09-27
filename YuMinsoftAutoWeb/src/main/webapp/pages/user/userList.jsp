<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tu" uri="/WEB-INF/tlds/content-override.tld"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/constants.inc" %>

<%-- 头部 head --%>
<tu:content contentId="head">	
   	<%@ include file="/inc/layuipage.inc" %>
   	<title>用户管理</title>
   	<script type="text/javascript">
   		$(document).ready(function(){
   			//新增
    		$("#addbtn").click(function (){    			
    			var url = "${CONTEXT_PATH }/user/toAdd";
    			window.location.href = url;
    			return false;
    		});
    		//编辑
    		$("#editbtn").click(function (){    			
    			if( _selected_id == null){
    				tuAlert("请选择一条数据");
        			return false;
    			}
    			var id = _selected_id.find(".id").text();
    			//tuDialog("编辑用户","${CONTEXT_PATH }/user/toEdit?id="+id,"530px","90%");
    			var url = "${CONTEXT_PATH }/user/toEdit?id="+id;
    			window.location.href = url;
    			return false;
    		});
    		//删除
    		$("#deletebtn").click(function (){    			
    			if( _selected_id == null){
    				tuAlert("请选择一条数据");
        			return false;
    			}
    			tuConfirm("是否确认删除该条数据?",function onYes(){
    				var id = _selected_id.find(".id").text();
        			window.location.href = "${CONTEXT_PATH }/user/delete?id="+id;
    			});
    			return false;
    		});
    		//设置权限
    		$(".setuserqx").click(function (){
    			var userid = $(this).attr("userid");
    			tuDialog("设置用户权限","${CONTEXT_PATH }/user/toSetUserQx?id="+userid,"350px","350px");
    			return false;
    		});
    		
   		});
   		
	</script>
</tu:content>

<%-- 内容 content --%>
<tu:content contentId="content">
	<div class="layui-tab">
    	<ul class="layui-tab-title">
			<li class="layui-this">用户管理</li>
		</ul>		
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">
				<form id="tuform" action="${CONTEXT_PATH }/user/list" method="post" class="layui-form" >
				<c:if test="${USER_SESSION_ID.isadmin eq 1 }">
					<fieldset class="layui-elem-field">							  
					  <div class="layui-field-box">
					   	<button class="layui-btn" id="addbtn">
							<i class="layui-icon" style="font-size:20px;color:#fff;">&#xe654;</i>新增
						</button>
						<button class="layui-btn" id="editbtn">
							<i class="layui-icon" style="font-size:20px;color:#fff;">&#xe642;</i>编辑
						</button>
						<button class="layui-btn layui-btn-danger" id="deletebtn">
							<i class="layui-icon" style="font-size:20px;color:#fff;">&#xe640;</i>删除
						</button>
					  </div>
					</fieldset>
					<table class="layui-table data_tb">
					  <thead>
					    <tr>
					      <th width="15%">用户登录代码</th>
					      <th width="20%">用户名称</th>
					      <th width="110px">是否管理员</th>
					      <th width="110px">是否可用</th>
					      <th>所在部门</th>
					      <th>备注</th>
					      <th width="100px">操作</th>
					    </tr> 
					  </thead>						  
					  <tbody>
					  	<c:forEach items="${page.results }" var="item" varStatus="status">
						    <tr class="sel_data_tr">
						      <td class="layui-elip" title="${item.usercode }">
						      	<span class="id layui-hide">${item.id }</span>
						      	${item.usercode }
						      </td>
						      <td class="layui-elip" title="${item.username }">
						      	${item.username }
						      </td>
						      <td align="center">
						      	<c:if test="${item.isadmin eq 0 }">否</c:if>
						      	<c:if test="${item.isadmin eq 1 }">是</c:if>
						      </td>
						      <td align="center">
						      	<c:if test="${item.isuse eq 0 }">否</c:if>
						      	<c:if test="${item.isuse eq 1 }">是</c:if>
						      </td>
						      <td align="center">
						      	<c:if test="${empty item.deptname }">
						      		--
						      	</c:if>
						      	<c:if test="${not empty item.deptname }">
						      		${item.deptname }
						      	</c:if>
						      </td>
						      <td class="layui-elip" title="${item.remark }">${item.remark }</td>
						      <td>
						      	<button class="layui-btn layui-btn-mini setuserqx" userid="${item.id }">权限设置</button>
						      </td>
						    </tr>
					    </c:forEach>						    
					  </tbody>
					</table>
				</c:if>
				<c:if test="${USER_SESSION_ID.isadmin ne 1 }">
					<table class="layui-table data_tb">
					  <thead>
					    <tr>
					      <th width="15%">用户登录代码</th>
					      <th width="20%">用户名称</th>
					      <th width="110px">是否管理员</th>
					      <th width="110px">是否可用</th>
					      <th>所在部门</th>
					      <th>备注</th>
					    </tr> 
					  </thead>						  
					  <tbody>
					  	<c:forEach items="${page.results }" var="item" varStatus="status">
						    <tr class="sel_data_tr">
						      <td class="layui-elip" title="${item.usercode }">
						      	<span class="id layui-hide">${item.id }</span>
						      	${item.usercode }
						      </td>
						      <td class="layui-elip" title="${item.username }">
						      	${item.username }
						      </td>
						      <td align="center">
						      	<c:if test="${item.isadmin eq 0 }">否</c:if>
						      	<c:if test="${item.isadmin eq 1 }">是</c:if>
						      </td>
						      <td align="center">
						      	<c:if test="${item.isuse eq 0 }">否</c:if>
						      	<c:if test="${item.isuse eq 1 }">是</c:if>
						      </td>
						      <td align="center">
						      	<c:if test="${empty item.deptname }">
						      		--
						      	</c:if>
						      	<c:if test="${not empty item.deptname }">
						      		${item.deptname }
						      	</c:if>
						      </td>
						      <td class="layui-elip" title="${item.remark }">${item.remark }</td>
						    </tr>
					    </c:forEach>						    
					  </tbody>
					</table>
				</c:if>
				<div id="tuPage" form="tuform"></div>
				</form>
			</div>
		</div>					
	</div>
</tu:content>

<%@ include file="/common/tu-template.jsp" %>