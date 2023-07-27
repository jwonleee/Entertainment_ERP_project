
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


//아티스트 선택하면 해당 아티스트의 기존 스케줄 가져와서 배열에 담기
$(".artistList_wrap").on("change", function() {
	var artistSelect = $(this).val();

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
				}
			},
			error: function(err) {
				alert("해당 아티스트의 일정 확인에 실패했습니다.");
			}
		}) //ajax 끝
	}
})


// 스케줄 type
$(document).ready(function() {
	$('#schedule_type').change(function() {
		var typeSelect = $(this).val()
	})
});


//제목, 장소, 세부내용 유효성 검사
function hideMessageIfValueExists(inputElement, warningElementId) {
  var value = inputElement.val().trim();
  var warningElement = $(warningElementId);
  
  if (value !== '') {
    warningElement.hide();
  } else {
    warningElement.show();
  }
}