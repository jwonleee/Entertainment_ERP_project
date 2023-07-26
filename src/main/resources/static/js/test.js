$(function(){
  function initializeDateTimePicker(element, relatedElementId) {
    $(element).datetimepicker({
      format: 'Y-m-d H:i',
      onShow: function (ct) {
        // 아티스트 선택 안 했을 경우
        if ($('.artistList_wrap').val() === '') {
          $('.artistWarning').text("해당 아티스트를 선택해주세요.");
          $('.artistList_wrap').focus();
          return false;
        } else {
          $('.artistWarning').text("");
        }

        // 시작 일정 선택 안 했을 경우
        if ($(relatedElementId).val() === '') {
          $('.datetimepickerWarning').text("시간과 날짜를 선택해주세요");
          $(relatedElementId).focus();
          return false;
        } else {
          $('.datetimepickerWarning').text("");
        }

        this.setOptions({
          maxDate: $('#date_timepicker_end').val()? $('#date_timepicker_end').val() : false,
          minDate: "d",
        });
      },
      timepicker: true,
      step: 30,
      inline: false,

      // 스케줄 시간 막기
      onGenerate: function (ct) {
        ct.setHours(ct.getHours() + 9); // 9시간 추가
        timeZone = ct.toISOString().replace('T', ' ').substring(0, 16); // 2023-03-19 11:21
        pickDate = timeZone.substring(0, 10); // 날짜

        var date = Object.keys(specificDates); // 일정 있는 날짜(키)의 배열
        var ind = date.indexOf(pickDate); // 선택한 인덱스 값

        // $('.xdsoft_time_variant .xdsoft_time').show();

        if (ind != -1) { // 찾는 날짜가 있으면
          $('.xdsoft_time_variant .xdsoft_time').each(function (index) {
            // if 조건문으로 해당 날짜의 시간 뽑아오기
            if (Object.values(specificDates)[ind].indexOf($(this).text()) !== -1) {
              $(this).addClass('xdsoft_disabled'); // 시간 막기
            }
          });
        }
      }
    });
  }

  // 시작 날짜 datetimepicker 초기화
  initializeDateTimePicker('#date_timepicker_start', '#date_timepicker_end');

  // 종료 날짜 datetimepicker 초기화
  initializeDateTimePicker('#date_timepicker_end', '#date_timepicker_start');
});