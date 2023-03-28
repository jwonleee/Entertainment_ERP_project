//일정 삭제
$('#delete_btn').click(function(e) {
	e.preventDefault();

	if(confirm("일정을 삭제하시겠습니까?")) {
		document.actionForm.submit();
	}
})

//Modal - 수정 버튼 클릭시 나옴
$('#modify_btn').click(function(e){
    e.preventDefault();

        var schedule_no = $('#schedule_no').val();
		var str = '';

        //수정 화면에 나타낼 데이터
        $.ajax({
			url: '../getModifyForm',
            type: 'post',
            data: JSON.stringify({schedule_no:schedule_no}),
            contentType: "application/json",
            success: function (vo) {
				
				str += '<tbody>';
                str += '	<tr>';
				str += '		<th>일정 날짜</th>';
				str += '		<td class="schedule_calendar">';
				str += '			<input type="text" class="datetimepicker form-control timepicker_block" value="' + vo.schedule_start_time + '" name="schedule_start_time" id="date_timepicker_start" required readonly placeholder="시작날짜"/>';
				str += '				   <label for="date_timepicker_start"><img src="https://jafp.s3.ap-northeast-2.amazonaws.com/y4j/calendar.png"/></label> ~ ';
				str += '			<input type="text" class="datetimepicker form-control timepicker_block" value="' + vo.schedule_end_time + '" name="schedule_end_time" id="date_timepicker_end" required readonly placeholder="종료날짜"/>';
				str += '				   <label for="date_timepicker_end"><img src="https://jafp.s3.ap-northeast-2.amazonaws.com/y4j/calendar.png"/></label>';
				str += '		</td>';
				str += '	</tr>';
				str += '	<tr>';
				str += '		<th>관리자 ID</th>';
				str += '		<td>';
				str += '			<input type="text" name="schedule_writer" class="form-control" value="' + vo.schedule_writer + '" readonly/>';
				str += '		</td>';
				str += '	</tr>';
				str += '	<tr>';
				str += '		<th>타입</th>';
				str += '		<td>';
				str += '			<select class="form-select schedule_type" name="schedule_type">';
				str += '				<option value="TV" '+ (vo.schedule_type == "TV" ? "selected" : "") + '>TV</option>';
				str += '				<option value="Magazine" '+ (vo.schedule_type == "Magazine" ? "selected" : "") + '>Magazine</option>';
				str += '				<option value="Radio"'+ (vo.schedule_type == "Radio" ? "selected" : "") + '>Radio</option>';
				str += '				<option value="Concert"'+ (vo.schedule_type == "Concert" ? "selected" : "") + '>Concert</option>';
				str += '				<option value="Movie"'+ (vo.schedule_type == "Movie" ? "selected" : "") + '>Movie</option>';
				str += '				<option value="ETC"'+ (vo.schedule_type == "ETC" ? "selected" : "") + '>ETC</option>';
				str += '			</select>';
				str += '		</td>';
				str += '	</tr>';
				str += '	<tr>';
				str += '		<th>제목</th>';
				str += '		<td>';
				str += '			<input type="text" name="schedule_ent_name" class="form-control schedule_width70" value= "' + vo.schedule_ent_name + '" >';
				str += '		</td>';
				str += '	</tr>';
				str += '	<tr>';
				str += '		<th>아티스트</th>';
				str += '		<td>';
				str += '			<input type="text" name="ent_name" class="form-control" placeholder="' + vo.ent_name + '" readonly/>';
				str += '	</tr>';
				str += '	<tr>';
				str += '		<th>장소</th>';
				str += '		<td>';
				str += '			<input type="text" name="schedule_location" class="form-control schedule_width70" value=' + vo.schedule_location + '>';
				str += '		</td>';
				str += '	</tr>';
				str += '	<tr>';
				str += '		<th>세부내용</th>';
				str += '		<td>';
				str += '			<textarea name="schedule_content" class="content form-control">' + vo.schedule_content + '</textarea>';
				str += '		</td>';
				str += '	</tr>';
				str += '</tbody>';

                $("#schedule_detail").html(str); //지워지고 다시 그려짐
            },
            error: function (err) {
                console.log(err);
            }
        });
    
    $('#ModifyModal').modal("show");
});

// datetimepicker
$(document).on("click", ".schedule_calendar", function(){
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