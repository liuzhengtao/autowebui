<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tu" uri="/WEB-INF/tlds/content-override.tld"%>
<%@ include file="/inc/constants.inc" %>

<%-- 头部 --%>
<tu:content contentId="head">
	<%@ include file="/inc/layuipage.inc" %>
	<title>用例管理</title>
   	<script type="text/javascript">
   		$(document).ready(function(){
   			layui.use(["form"], function(){
   				var form = layui.form();
   				//监听switch开关
   				form.on("switch(isquitbrowser-lay-switch)", function(data){
   					var caseid = $(data.elem).parent().parent().attr("caseid");
   					var isquitbrowser = 0;
	   				if(data.elem.checked){
	   					isquitbrowser = 1;
	   				}
	   				$.post("${CONTEXT_PATH }/ajax/slnmcase/setIsquitbrowser",{ id: caseid, isquitbrowser: isquitbrowser },function(data){
	    				//
					});
   				});
   				
   			});
   			
   			//新增用例
    		$("#addbtn").click(function (){    			
    			tuDialog("新增用例","${CONTEXT_PATH }/slnmcase/toAdd","500px","450px");    			
    			return false;
    		});
    		//编辑用例
    		$("#editbtn").click(function (){    			
    			//
    			if( _selected_id == null){
    				tuAlert("请选择一条数据");
        			return false;
    			}
    			var id = _selected_id.find(".id").text();
    			tuDialog("编辑用例","${CONTEXT_PATH }/slnmcase/toEdit?id="+id,"500px","450px");
    			return false;
    		});
    		//导出所有用例至xml文件
    		$("#exportAllCase2Xml").click(function (){
    			$("#tuform").attr("target","_blank");
				$("#tuform").attr("action","${CONTEXT_PATH }/slnmcase/exportCase2Xml");
				$("#tuform").submit();
				//
				$("#tuform").attr("target","_self");
				$("#tuform").attr("action","${CONTEXT_PATH }/slnmcase/list");
				
    			return false;
    		});
    		//导入用例
    		$("#importcase").click(function (){    			
    			tuDialog("导入用例","${CONTEXT_PATH }/slnmcase/toImportCase","500px","210px");
    			return false;
    		});
    		//删除用例
    		$("#deletebtn").click(function (){
    			if( _selected_id == null){
    				tuAlert("请选择一条数据");
        			return false;
    			}
    			tuConfirm("是否确认删除该条数据?",function onYes(){
    				var id = _selected_id.find(".id").text();
        			window.location.href = "${CONTEXT_PATH }/slnmcase/delete?id="+id;
    			});
    			return false;
    		});
    		
    		//运行用例
    		$(".runCase").click(function (){
    			var caseid = $(this).attr("caseid");
    			var index = layer.load(1);
				$.post("${CONTEXT_PATH }/ajax/slnmcase/doRunCase",{ id: caseid },function(data){
					layer.closeAll();
					var code = data.code;
    				var msg = data.msg;
    				tuWarn(code,msg);
				});
    			return false;
    		});
    		//导出用例至xml文件
    		$(".exportCase2Xml").click(function (){
    			var caseid = $(this).attr("caseid");
				$("#tuform").attr("target","_blank");
				$("#tuform").attr("action","${CONTEXT_PATH }/slnmcase/exportCase2Xml?id=" + caseid);
				$("#tuform").submit();
				//
				$("#tuform").attr("target","_self");
				$("#tuform").attr("action","${CONTEXT_PATH }/slnmcase/list");
				
    			return false;
    		});
    		
    		//章节列表
    		$(".tochapterlist").click(function (){
    			var casecode = $(this).attr("casecode");
    			$("#casecode").val(casecode);
				$("#tuform").attr("action","${CONTEXT_PATH }/slnmcase/chapterlist");
				$("#tuform").submit();
				return false;
    		});
    		
   		});
		
	</script>
	
	<style>
		.layui-form-switch {margin-top: 0px;}
	</style>
</tu:content>

<%-- 内容 --%>
<tu:content contentId="content">
	<div class="layui-tab">					 	
		<ul class="layui-tab-title">
			<li class="layui-this">用例管理</li>
		</ul>					
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">
				<form id="tuform" action="${CONTEXT_PATH }/slnmcase/list" method="post" target="_self" class="layui-form">
				<input type="hidden" name="casecode" id="casecode" value="" />
				<fieldset class="layui-elem-field">							  
				  <div class="layui-field-box">
				   	<button class="layui-btn" id="addbtn">
						<i class="layui-icon" style="font-size:20px;color:#fff;">&#xe654;</i>新增用例
					</button>
					<button class="layui-btn" id="editbtn">
						<i class="layui-icon" style="font-size:20px;color:#fff;">&#xe642;</i>编辑用例
					</button>
					<button class="layui-btn layui-btn-danger" id="deletebtn">
						<i class="layui-icon" style="font-size:20px;color:#fff;">&#xe640;</i>删除用例
					</button>
					<button class="layui-btn layui-btn-normal" id="exportAllCase2Xml" style="background-color:#71AB76;">
						<i class="layui-icon" style="font-size:19px;color:#fff;">&#xe601;</i>
						批量导出Xml
					</button>
					<button class="layui-btn layui-btn-normal" id="importcase" style="background-color:#71AB76;">
						<i class="layui-icon" style="font-size:17px;color:#fff;">&#xe62f;</i> 批量导入用例
					</button>
				  </div>
				</fieldset>				
				<table class="layui-table data_tb">							  
				  <thead>
				    <tr>
				      <th width="40px">序号</th>
				      <th width="">用例名称</th>
				      <th width="10%">浏览器</th>
				      <th width="">浏览器路径</th>
				      <th width="11%">退出浏览器</th>
				      <th width="">描述</th>
				      <th width="180px">操作</th>
				    </tr> 
				  </thead>
				  <tbody>
				  	<c:forEach items="${page.results }" var="item" varStatus="status">				    			
					    <tr class="sel_data_tr" caseid="${item.id }">
					      <td align="center">
					      	${status.index+1 }
					      </td>
					      <td class="layui-elip" title="${item.casename }">
					      	<span class="id layui-hide">${item.id }</span>
					     	${item.casename }
					      </td>
					      <td align="center">
					      	<c:if test="${item.drivertype eq 1 }">IE</c:if>
					      	<c:if test="${item.drivertype eq 2 }">Firefox</c:if>
					      	<c:if test="${item.drivertype eq 3 }">Chrome</c:if>
					      </td>
					      <td class="layui-elip" title="${item.browserpath }">${item.browserpath }</td>
					      <td align="center">
					      	<input type="checkbox" name="isquitbrowser" lay-skin="switch" lay-text="是|否" lay-filter="isquitbrowser-lay-switch" <c:if test="${item.isquitbrowser eq 1 }">checked</c:if>>
					      </td>
					      <td class="layui-elip" title="${item.description }">${item.description }</td>	
					      <td align="">
					      	<!-- 
					      	<button class="layui-btn layui-btn-small runCase" caseid="${item.id }">运行用例</button>
					      	 -->
					      	<button class="layui-btn layui-btn-mini exportCase2Xml" caseid="${item.id }">导出用例至Xml</button>
					      	<button class="layui-btn layui-btn-mini tochapterlist" style="background-color:#4588BB;" casecode="${item.casecode }">章节管理</button>
					      </td>
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