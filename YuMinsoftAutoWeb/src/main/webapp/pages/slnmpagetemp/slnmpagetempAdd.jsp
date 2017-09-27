<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tu" uri="/WEB-INF/tlds/content-override.tld"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/constants.inc" %>

<%-- 头部 --%>
<tu:content contentId="head">	
   	<title>新增页面</title>
   	<script type="text/javascript">
	  	layui.use('upload', function(){
	  		layui.upload({
	  		  url: "${CONTEXT_PATH }/layui/upload",
	  		  title: "选择文件",
	  		  ext: "xls|xml",
	  		  type: "file",
	  		  unwrap: false,
	  		  before: function(input){
	  		  	//
	  			if($(input).attr("name")=="file0"){
	  				//正在上传
 					$("#datafilepath").parent().next().html("<i class='layui-icon' style='display:inline-block;margin-top:-15px;font-size:35px;'>&#xe63d;</i>");
 				}
	  		  },
	  		  success: function(res, input){
	  			  //上传成功
	  			  if(res.code == 1){
	  				  if($(input).attr("name")=="file0"){
	  					//文件路径
	  					$("#datafilepath").val(res.filepath);
	  					//文件名
	  					$("#datafilename").val(res.filename);
	  					//
	  					$("#datafilepath").parent().next().html("<i class='layui-icon' style='display:inline-block;margin-top:-15px;font-size:20px;color:#009688;'>&#xe618;</i>");
	  				  }	  			  	
	  			  }else{
	  				  alert($(input).attr("tip") + "失败");
	  			  }
	  			  
	  		  }
	  		});
	  	});
	  	
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
	    		
	    		var id = $("#id").val();
	    		var url = "${CONTEXT_PATH }/slnmpagetemp/save";
	    		if(id != ""){
	    			url = "${CONTEXT_PATH }/slnmpagetemp/update";
	    		}
	    		
	    		$("button").attr("disabled","true");
    			$("#tuform").attr("action",url);
	    		$("#tuform").submit();
    		});
	   		
    		//删除数据文件
    		$("#deletedatafile").click(function (){
    			//
    			$("#datafilepath").val("");
    			$("#datafilename").val("");
    			
    			return false;
    		});
   			
   		});
   		
	</script>
	<style>
		.layui-upload-button {min-width: 120px;}
	</style>
</tu:content>

<%-- 内容 --%>
<tu:content contentId="content">
	<div class="dialog-middle">
		<form id="tuform" class="layui-form" action="${CONTEXT_PATH }/slnmpagetemp/toAdd" method="post">
			<input type="hidden" name="id" id="id" value="${bean.id }" />
			<input type="hidden" name="casecode" id="casecode" value="0" />
			<table width="90%" class="input-table" align="center">
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
						<input type="hidden" name="datafilepath" id="datafilepath" value="${bean.datafilepath }"/>
						<div class="layui-form-item">
							<label class="layui-form-label">上传数据文件</label>
							<div class="layui-input-block">
								<input type="file" name="file0" tip="上传数据文件" class="layui-upload-file">
								<input type="text" name="datafilename" id="datafilename" value="${bean.datafilename }"
									 class="layui-input" style="display:inline-block;width:124px;vertical-align: middle;" readonly/>
							</div>
						</div>
					</td>
					<td>
						<c:if test="${empty bean.datafilepath }">
							<span style="display:inline-block;margin-top:-15px;font-size:13px;color:#9CBBB8;">未上传</span>
						</c:if>
						<c:if test="${not empty bean.datafilepath }">
							<i class='layui-icon' style='display:inline-block;margin-top:-15px;font-size:20px;color:#009688;'>&#xe618;</i>
							<i class='layui-icon' style='display:inline-block;margin-top:-15px;font-size:24px;color:#ff0000;cursor:pointer;' id="deletedatafile">&#xe640;</i>
							
						</c:if>
						
					</td>
				</tr>
				<tr>
					<td>
						<div class="layui-form-item">
							<label class="layui-form-label">排序号</label>
							<div class="layui-input-block">
								<input type="text" name="orderno" id="orderno" value="${bean.orderno }"
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