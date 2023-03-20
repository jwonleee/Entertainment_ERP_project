
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
// let specificDates = {"2023-03-04": ["13:00"]};
// console.log(specificDates);

// var tempDate = "2023-03-04";
// console.log(specificDates[tempDate]);
// console.log(Object.keys(specificDates).includes(tempDate));

//아티스트 선택하면 해당 아티스트의 기존 스케줄 가져와서 배열에 담기
$(".artistList_wrap").on("change", function() {
	var artistSelect = $(this).val();
	//console.log(artistSelect); //blackpink

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

// date, time List에 담는 함수
function addTimeList (data) {
	
	//반복문
	$.each(data, function(index, item) {
		console.log(data);

		let selectedStartDate = item.schedule_start_time.substring(0, 10); //시작 날짜: 2023-03-04
		let selectedStartTime = item.schedule_start_time.substring(11, 16); //시작 시간: 13:00
		// console.log(selectedStartDate);
		// console.log(selectedStartTime);

		if(!Object.keys(specificDates).includes(selectedStartDate)) { //같은 날짜가 없으면
			// console.log(selectedStartDate);
			return specificDates[selectedStartDate] = [selectedStartTime];
		}
		else { //같은 날짜가 있으면 그 날짜의 뒤에 시간 담기
			return specificDates[selectedStartDate].push(...[selectedStartTime]);
		}
	})
	console.log(specificDates);
};


	// const selectedEndDate = item.schedule_end_time.substring(0, 10); //종료 날짜
	// console.log(selectedEndDate);
	
	
	// console.log(item.schedule_start_time.indexOf(specificDates));
	// const selectedEndTime = item.schedule_end_time.substring(11, 16); //종료 시간
	// console.log(selectedEndTime);

// datetimepicker
$(function(){

		//시작 날짜
		$('#date_timepicker_start').datetimepicker({
			format:'Y-m-d H:i',
			onShow:function( ct ){
				
				//아티스트 선택 안 했을 경우
				if($('.artistList_wrap').val() == '') {
					alert("해당 아티스트를 선택해주세요.");
					document.location.reload(true);	
				}

			this.setOptions({
				maxDate:$('#date_timepicker_end').val()? $('#date_timepicker_end').val() : false,
				minDate: 0,
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
				
				// ind = specificDates.findIndex(e => e.date === pickDate); //고른 날짜가 있는지 확인
				var date = Object.keys(specificDates);
				console.log(date); //['2023-03-04', '2023-03-19', '2023-03-20']
				
				var ind = date.indexOf(pickDate); //선택한 인덱스 값
				console.log(ind); //2 (선택한 인덱스 값)
				
				// $('.xdsoft_time_variant .xdsoft_time').show();
				
				if(ind != -1) { //찾는 날짜가 있으면
					$('.xdsoft_time_variant .xdsoft_time').each(function(index){
						//if 조건문으로 해당 날짜의 시간 뽑아오기

						// console.log(Object.values(specificDates)[ind]);
						if(Object.values(specificDates)[ind].indexOf($(this).text()) !== -1) {
							$(this).addClass('xdsoft_disabled'); //선택한 날짜 시간 다 걸림
						}
					});
					
					
				}
	
			  }
	
		});

		
		



////////////////////////////////////////////////////////////////////////////////////////////		
	//종료 날짜
		$('#date_timepicker_end').datetimepicker({
			format:'Y-m-d H:i',
			onShow:function( ct ){
				
				//시작 일정 선택 안 했을 경우
				if($('#date_timepicker_start').val() == '') {
					alert("시작 일정의 날짜와 시간을 선택해주세요.");
					document.location.reload(true);	
				}
				
				this.setOptions({
					minDate:$('#date_timepicker_start').val() ? $('#date_timepicker_start').val() : false,
				})
			},
			timepicker:true,
			step: 30,
			inline:false,

			/////////////여기 시간 막기 해야함///////////
		});
		
	}); // datetimepicker 끝







// 스케줄 type
$(document).ready(function() {
	$('#schedule_type').change(function() {
		var typeSelect = $(this).val()
		console.log(typeSelect);
	})
});





$('#date_timepicker_start').on('click', function() {
	// console.log(timeZone);
	// console.log(pickDate);
	// console.log(pickTime);
	// console.log(ind);
})
	
