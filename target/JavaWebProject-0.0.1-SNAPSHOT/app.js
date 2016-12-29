$(document).ready(function() {
	
	$('.add-clear').click(function(){
		$('.clearable').each(function(i, obj){
			$(this).val("");	
			if($(this).is(':checkbox')){
				$(this).prop('checked', false); 
			}
		});
		
	});
	
	$('.workupitem').change(function(){
		var thisCheckBox = $(this).attr('id');
		var id = thisCheckBox.substring(5);
		
		if($(this).is(":checked")){
			$('#freq-'+id).prop("disabled", false);
			$('#freq-'+id).removeClass("disabled");
			$('#freq-'+id).val(1);
		}else{
			$('#freq-'+id).prop("disabled", true);
			$('#freq-'+id).addClass("disabled");
			$('#freq-'+id).val("0");
		}
	});
	
	$('.message .close').on('click', function() {
	    $(this)
	      .closest('.success-msg')
	      .fadeOut(600);
	});
	
	$('.message .close').on('click', function() {
	    $(this)
	      .closest('.failed-msg')
	      .fadeOut(600);
	});

});










