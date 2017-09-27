<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/constants.inc" %>

<script type="text/javascript">    		    		
   	$(document).ready(function(){  
   		/*//菜单选中
		$("dl.layui-nav-child a").each(function() {
			if($(this).hasClass("layui-this")){
				$(this).parent().parent().parent().toggleClass("layui-nav-itemed");
			}
		});*/
	});
</script>

<div>	
	<ul class="layui-nav layui-nav-tree layui-nav-itemed" lay-filter="test">	
		<c:forEach items="${menusystem.sysmodules }" var="sysmodule" varStatus="modulestatus">
			<li class="layui-nav-item">
				<a href="javascript:;">${sysmodule.mdname }</a>
				<dl class="layui-nav-child">
					<c:forEach items="${sysmodule.sysmenus }" var="sysmenu" varStatus="menustatus">
						<dd>
							<a href="${CONTEXT_PATH }${sysmenu.url }" <c:if test="${currPath ne '/' and fn:contains(sysmenu.url0, currPath)}">class="layui-this"</c:if> >${sysmenu.mnname }</a>
						</dd>
					</c:forEach>
				</dl>	
			</li>	
		</c:forEach>
		<c:if test="${empty menusystem.sysmodules }">
			<li class="layui-nav-item"><a href="javascript:;">--</a></li>
		</c:if>
	</ul>
</div>
