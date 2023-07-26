
//아티스트 선택 ▶ 날짜 확인 가능

// 아티스트 type 클릭시
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
					str += '<option value="">선택</option>';
				result.forEach(function(item, index) {
					str += '<option value="' + item.ent_group_name + '" class="ent_group_name">' + item.ent_group_name +'</option>';
				})
				$(".artistList_wrap").html(str); //지워지고 다시 그려짐
			},
			error: function(err) {
				alert("아티스트 타입을 가져올 수 없습니다.");
			}
		})
	} 
	
	//actor	
	if(ent_type == 'Actor'){
		$.ajax({
			url: '../getEntType2',
			type: 'post',
			data: JSON.stringify({ent_type : ent_type}),
			contentType: "application/json",
			success: function(result) {
				var str = "";
					str += '<option value="">선택</option>';
				result.forEach(function(item, index) {
					str += '<option value="' + item.ent_name + '" class="ent_name">' + item.ent_name +'</option>';
				})
				$(".artistList_wrap").html(str);
			},
			error: function(err) {
				alert("아티스트 타입을 가져올 수 없습니다.");
			}
		})
	}
})


//DB에 담겨있어야 하는 날짜와 시간들, array형식으로 저장
let specificDates = {};

//아티스트 선택하면 해당 아티스트의 기존 스케줄 가져와서 배열에 담기
$(".artistList_wrap").on("change", function() {
	var artistSelect = $(this).val(); //blackpink

	//아티스트 선택시 ajax 태움
	if(artistSelect != '') {
		$.ajax({
			url: '../getSchedule/' + artistSelect,
			type: 'get',
			async:false,
			success: function(data) {
				//아티스트 변경될 경우 배열 초기화
				if($(".artistList_wrap").on("change")) {
					specificDates = [];
					addTimeList(data);
				} else {
					addTimeList(data);
				}
			},
			error: function(err) {
				alert("해당 아티스트의 일정 확인에 실패했습니다.");
			}
		}) //ajax 끝
	}

})

/////////////////////////////////////////////////시작-종료 사이 시간 넣어야 함///////////////////////////////////////////////
// date, time List에 담는 함수
function addTimeList (data) {
	
	//반복문
	$.each(data, function(index, item) {

		let selectedStartDate = item.schedule_start_time.substring(0, 10); //시작 날짜: 2023-03-04
		let selectedStartTime = item.schedule_start_time.substring(11, 16); //시작 시간: 13:00

		let selectedEndDate = item.schedule_end_time.substring(0, 10); //종료 날짜
		let selectedEndTime = item.schedule_end_time.substring(11, 16); //종료 시간

		let result = selectedStartTime;
		//시간 사이 넣기
		console.log(result);
		// console.log(selectedStartTime-selectedEndTime);
		// console.log(selectedEndTime);
		
		if(!Object.keys(specificDates).includes(selectedStartDate)) { //같은 날짜가 없으면
			specificDates[selectedStartDate] = [selectedStartTime];
		}
		else { //같은 날짜가 있으면 그 날짜의 뒤에 시간 담기
			specificDates[selectedStartDate].push(...[selectedStartTime]);
		}

		if(!Object.keys(specificDates).includes(selectedEndDate)) { //같은 날짜가 없으면
			specificDates[selectedEndDate] = [selectedEndTime];
		}
		else { //같은 날짜가 있으면 그 날짜의 뒤에 시간 담기
			specificDates[selectedEndDate].push(...[selectedEndTime]);
		}

	})
	console.log(specificDates);
};


// datetimepicker
$(function(){

		//시작 날짜
		$('#date_timepicker_start').datetimepicker({
			format:'Y-m-d H:i',
			onShow:function( ct ){
			
			//아티스트 선택 안 했을 경우
			if($('.artistList_wrap').val() === '') {
				$('.artistWarning').text("해당 아티스트를 선택해주세요.");
				$('.artistList_wrap').focus();
				return false;
			} else {
				$('.artistWarning').text("");
			}
			
			this.setOptions({
				maxDate: $('#date_timepicker_end').val()? $('#date_timepicker_end').val() : false,
				minDate: "d",
				})
			},
			timepicker:true,
			step: 30,
			inline:false,
			
			//스케줄 시간 막기
			onGenerate:function(ct){
				ct.setHours(ct.getHours() + 9) //9시간 추가
				timeZone = ct.toISOString().replace('T', ' ').substring(0, 16); //2023-03-19 11:21
				pickDate = timeZone.substring(0, 10); //날짜

				var date = Object.keys(specificDates); //일정 있는 날짜(키)의 배열				
				var ind = date.indexOf(pickDate); //선택한 인덱스 값
				
				// $('.xdsoft_time_variant .xdsoft_time').show();
				
				if(ind != -1) { //찾는 날짜가 있으면
					$('.xdsoft_time_variant .xdsoft_time').each(function(index){
						//if 조건문으로 해당 날짜의 시간 뽑아오기
						if(Object.values(specificDates)[ind].indexOf($(this).text()) !== -1) {
							$(this).addClass('xdsoft_disabled'); //시간 막기
						}
					});
				}

			  }
	
		});

	
	//종료 날짜
		$('#date_timepicker_end').datetimepicker({
			format:'Y-m-d H:i',
			onShow:function( ct ){
				
				//아티스트 선택 안 했을 경우
				if($('.artistList_wrap').val() === '') {
					$('.artistWarning').text("해당 아티스트를 선택해주세요.");
					$('.artistList_wrap').focus();
					return false;
				} else {
					$('.artistWarning').text("");
				}
				
				//시작 일정 선택 안 했을 경우
				if($('#date_timepicker_start').val() === '') {	
					$('.datetimepickerWarning').text("시간과 날짜를 선택해주세요");
					$('#date_timepicker_start').focus();
					return false;
				} else {
					$('.datetimepickerWarning').text("");
				}
				
				this.setOptions({
					minDate:$('#date_timepicker_start').val() ? $('#date_timepicker_start').val() : false,
				})
			},
			timepicker:true,
			step: 30,
			inline:false,

			//스케줄 시간 막기
			onGenerate:function(ct){
				ct.setHours(ct.getHours() + 9) //9시간 추가
				timeZone = ct.toISOString().replace('T', ' ').substring(0, 16); //2023-03-19 11:21
				pickDate = timeZone.substring(0, 10); //날짜

				var date = Object.keys(specificDates); //일정 있는 날짜(키)의 배열				
				var ind = date.indexOf(pickDate); //선택한 인덱스 값
				
				// $('.xdsoft_time_variant .xdsoft_time').show();
				
				if(ind != -1) { //찾는 날짜가 있으면
					$('.xdsoft_time_variant .xdsoft_time').each(function(index){
						//if 조건문으로 해당 날짜의 시간 뽑아오기
						if(Object.values(specificDates)[ind].indexOf($(this).text()) !== -1) {
							$(this).addClass('xdsoft_disabled'); //시간 막기
						}
					});
				}

			  }
		});
		
	}); // datetimepicker 끝



// 스케줄 type
$(document).ready(function() {
	$('#schedule_type').change(function() {
		var typeSelect = $(this).val()
		console.log(typeSelect);
	})
});