<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tu" uri="/WEB-INF/tlds/content-override.tld"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/inc/constants.inc" %>

<%-- 头部 --%>
<tu:content contentId="head">	
   	<title>导入用例</title>
   	<script type="text/javascript">
	  	layui.use('upload', function(){
	  		layui.upload({
	  		  url: "${CONTEXT_PATH }/layui/upload",
	  		  title: "选择文件",
	  		  ext: "xml",
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
    			var datafilepath = $("#datafilepath").val();
	    		if(datafilepath == ""){
	    			tuTips("请选择文件", "#datafilename");
	    			return false;
	    		}
	    		
	    		var url = "${CONTEXT_PATH }/slnmcase/doImportCase";
	    		
	    		$("button").attr("disabled","true");
    			$("#tuform").attr("action",url);
	    		$("#tuform").submit();
	    		
	    		//
	    		var index = layer.load(1);
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
		<form id="tuform" class="layui-form" action="${CONTEXT_PATH }/slnmcase/toImportCase" method="post">
			<table width="90%" class="input-table" align="center">
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="365px">
						<input type="hidden" name="datafilepath" id="datafilepath" value=""/>
						<div class="layui-form-item">
							<label class="layui-form-label">上传Xml文件</label>
							<div class="layui-input-block">
								<input type="file" name="file0" tip="上传Xml文件" class="layui-upload-file">
								<input type="text" name="datafilename" id="datafilename" value="${bean.datafilename }"
									 class="layui-input" style="display:inline-block;width:124px;vertical-align: middle;" readonly/>
							</div>
						</div>
					</td>
					<td>
						<span style="display:inline-block;margin-top:-15px;font-size:13px;color:#9CBBB8;">未上传</span>
					</td>
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