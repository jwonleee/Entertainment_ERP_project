// datetimepicker
	$(function(){
		$('#date_timepicker_start').datetimepicker({
			format:'Y-m-d H:i',
			onShow:function( ct ){
			this.setOptions({
				maxDate:$('#date_timepicker_end').val()?$('#date_timepicker_end').val():false,
				minDate: 0
				})
			},
			timepicker:true,
			step: 30
		});

		$('#date_timepicker_end').datetimepicker({
			format:'Y-m-d H:i',
			onShow:function( ct ){
				this.setOptions({
					minDate:$('#date_timepicker_start').val()?$('#date_timepicker_start').val():false,
				})
			},
			timepicker:true,
			step: 30
		});
	});


// 스케줄 types
    $(document).ready(function() {

		$('#schedule_type').change(function() {
			var typeSelect = $(this).val()
			console.log(typeSelect);
		})
    });


	$(".type_wrap").click(function(e) {
			
		var ent_type=$('[name=artist_type]:checked').val();
		
		//musician
		if(ent_type == 'Musician') {
			$.ajax({
				url: '../getEntType',
				type: 'post',
				data: JSON.stringify({ent_type: ent_type}),
				contentType: "application/json",
				success: function(result) {
					var str = "";
					result.forEach(function(item, index) {
						str += '<option value="' + item.ent_group_name + '" class="ent_group_name">' + item.ent_group_name +'</option>';
					})
					$(".artistList_wrap").html(str);
				},
				error: function(err) {
					console.log(err);
				}
			})
		//actor	
		} 
		
		if(ent_type == 'Actor'){
			$.ajax({
				url: '../getEntType2',
				type: 'post',
				data: JSON.stringify({ent_type: ent_type}),
				contentType: "application/json",
				success: function(result) {
					var str = "";
					result.forEach(function(item, index) {
						str += '<option value="' + item.ent_name + '" class="ent_name">' + item.ent_name +'</option>';
					})
					$(".artistList_wrap").html(str);
				},
				error: function(err) {
					console.log(err);
				}
			})
		}
		
	})
	
