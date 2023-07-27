//DB에 담겨있어야 하는 날짜와 시간들 ▶ 객체에 key: 날짜, value: 시간 (array형식으로 저장)
let specificDates = {};

//특정 날짜의 시작 시간과 끝나는 시간을 List에 담기
function addTimeList (data) {
	//반복문
	$.each(data, function(index, item) {
		
		/*//등록(item), 수정(data)할때 들어오는 data가 달라서 구분
		let scheduleData = item;
	   	if (typeof data === 'object' && data !== null && !Array.isArray(data)) {
	    	scheduleData = data;
		}*/
		
		let selectedStartDate = item.schedule_start_time.substring(0, 10); //시작 날짜: 2023-03-04
		let selectedStartTime = item.schedule_start_time.substring(11, 16); //시작 시간: 13:00
		let selectedEndDate = item.schedule_end_time.substring(0, 10); //종료 날짜
		let selectedEndTime = item.schedule_end_time.substring(11, 16); //종료 시간

		// 시작 시간과 종료 시간을 30분 단위로 쪼개서 배열에 추가
	    let startHour = parseInt(selectedStartTime.substring(0, 2));
	    let startMinute = parseInt(selectedStartTime.substring(3, 5));
	    let endHour = parseInt(selectedEndTime.substring(0, 2));
	    let endMinute = parseInt(selectedEndTime.substring(3, 5));
	
	    let scheduleTimeArray = [];
	
	    while (startHour < endHour || (startHour === endHour && startMinute <= endMinute)) {
	      scheduleTimeArray.push(("0" + startHour).slice(-2) + ":" + ("0" + startMinute).slice(-2)); //00:00 형식
	      startMinute += 30;
	      if (startMinute >= 60) {
	        startHour += 1;
	        startMinute -= 60;
	      }
	    }
	
	    specificDates[selectedStartDate] = specificDates[selectedStartDate] || []; //specificDates[selectedStartDate] 유무 확인
	    specificDates[selectedStartDate].push(...scheduleTimeArray);

	})
	console.log(specificDates);
};


// datetimepicker
$(document).ready(function() {
	
	function initDateTimePicker() {
		//시작 날짜
		$('#date_timepicker_start').datetimepicker({
			format:'Y-m-d H:i',
			timepicker:true,
			step: 30,
			inline:false,
			
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
					minDate: "d",
					maxDate: $('#date_timepicker_end').val()?
							 $('#date_timepicker_end').val() : false,
				})
			},
			onGenerate:timeToBlock
		});

	
	//종료 날짜
		$('#date_timepicker_end').datetimepicker({
			format:'Y-m-d H:i',
			timepicker:true,
			step: 30,
			inline:false,

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
					minDate:$('#date_timepicker_start').val() ?
							$('#date_timepicker_start').val() : false,
				})
			},
			onGenerate:timeToBlock
		});
		
		}

    	initDateTimePicker();
	}); //end datetimepicker


//dateTimepicker 스케줄 시간 막기
	function timeToBlock(ct) {
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