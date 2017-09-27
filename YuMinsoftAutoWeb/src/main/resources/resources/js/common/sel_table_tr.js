/**
 * sel_tabletr.js
 */
var _selected_id = null;
$(document).ready(function(){
	//
	$(".sel_data_tr").click(function (event){
		event.stopPropagation();
		if(!$(this).hasClass("selected_tr")){
			$(this).addClass("selected_tr");
		}
		//
		var _this = this;
		$.each($(".sel_data_tr"), function(i, n){
			if(this != _this){
				$(this).removeClass("selected_tr");
			}
		});
		_selected_id = $(this);
		
	});	
});
