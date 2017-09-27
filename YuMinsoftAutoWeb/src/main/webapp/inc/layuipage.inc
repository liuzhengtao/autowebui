<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
	$(document).ready(function(){
		//
		layui.use(['laypage'], function(){
			var laypage = layui.laypage;
			
			var pages = parseInt("${page.pages}");
			var curr = parseInt("${page.pageNumber}");
			var groups = 5;
			//每页显示多少条
			var pageSize = parseInt("${page.pageSize}");
			//容器,"tuPage" $("#tuPage")
			var cont = document.getElementById("tuPage");
			laypage({
				cont: cont, //容器。值可以传入元素id或原生DOM或jquery对象
				pages: pages, //分页数。一般通过服务端返回得到
				curr: curr, //当前页
				groups: groups, //连续分页数
				skin: "default", //控制分页皮肤。一般传16进制色值即可，如:skin: '#c00'			
				skip: true, //是否显示跳转
				jump: function(obj,first){
					if(!first){
						var form = $("#tuPage").attr("form");
						var tuForm = $("#" + form);
						
						var curr = obj.curr;
						
						tuForm.append("<input type='hidden' name='pageNumber' value='" + curr +"'/>");
						tuForm.append("<input type='hidden' name='pageSize' value='" + pageSize +"'/>");
						
						tuForm.submit();
					}
					
				}
			});
			
		});
	});
	
</script>