/**
 * scroll.js
 */
$(document).ready(function(){
	//当滚动条发生变化时
	$(window).scroll(function(){ 
		var scrollTop = $(document).scrollTop();
		setCookie("scrollTop",scrollTop);
		//设置返回顶部
		setGotoTop(scrollTop);
	});	

	//返回顶部
	$("#gototop").click(function (){
		$(document).scrollTop(0);
	});
	
	//初始化设置滚动条
	setScrollTop();
});

//设置滚动条
function setScrollTop(){
	//
	var scrollTop = getCookie("scrollTop");
	if(scrollTop == null){
		return false;
	}
	$(document).scrollTop(scrollTop);
}

//设置返回顶部
function setGotoTop(scrollTop){
	if(isNotNull($("#gototop"))){
		if(scrollTop > 140){
			$("#gototop").show();
		}else{
			$("#gototop").hide();
		}
	}
}
