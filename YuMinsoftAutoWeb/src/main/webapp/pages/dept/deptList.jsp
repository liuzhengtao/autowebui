<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tu" uri="/WEB-INF/tlds/content-override.tld"%>
<%@ include file="/inc/constants.inc" %>

<%-- 头部 head --%>
<tu:content contentId="head">	
   	<%@ include file="/inc/layuipage.inc" %>
   	<title>用户管理</title>
   	<script type="text/javascript">
   		$(document).ready(function(){
   			//新增
    		$("#addbtn").click(function (){    			
    			var url = "${CONTEXT_PATH }/dept/toAdd";
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
    			var url = "${CONTEXT_PATH }/dept/toEdit?id="+id;
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
        			window.location.href = "${CONTEXT_PATH }/dept/delete?id="+id;
    			});
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
				<form id="tuform" action="${CONTEXT_PATH }/dept/list" method="post" class="layui-form" >
				
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
				      <th width="15%">部门名称</th>
				      <th width="15%">上级部门名称</th>
				      <th>是否可用</th>
				      <th>电话号码</th>
				      <th>传真号码</th>
				      <th>邮箱</th>
				      <th width="20%">备注</th>
				    </tr> 
				  </thead>						  
				  <tbody>
				  	<c:forEach items="${page.results }" var="item" varStatus="status">
					    <tr class="sel_data_tr">
					      <td class="layui-elip" title="${item.deptname }">
					      	<span class="id layui-hide">${item.id }</span>
					      	${item.deptname }
					      </td>
					      <td align="center" class="layui-elip" title="${item.pdeptname }">
					      	<c:if test="${empty item.pdeptname }">
					      		--
					      	</c:if>
					      	<c:if test="${not empty item.pdeptname }">
					      		${item.pdeptname }
					      	</c:if>
					      </td>
					      <td align="center">
					      	<c:if test="${item.isuse eq 0 }">否</c:if>
					      	<c:if test="${item.isuse eq 1 }">是</c:if>
					      </td>
					      <td>${item.telno }</td>
					      <td>${item.faxno }</td>
					      <td>${item.email }</td>
					      <td class="layui-elip" title="${item.remark }">${item.remark }</td>
					    </tr>
				    </c:forEach>						    
				  </tbody>
				</table>
				
				<div id="tuPage" form="tuform"></div>
				</form>
			</div>
		</div>					
	</div>
</tu:content>

<%@ include file="/common/tu-template.jsp" %>