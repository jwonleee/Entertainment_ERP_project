//모달 창 내 발송 버튼 클릭 시, 쪽지 발송
$("#sendMsgBtn").on("click", function () {
  $("#sendMsgForm").submit();
});

//날짜 형식 0000-00-00 으로 변경
function changeDateFormat(regdate) {

  function pad(number) {
    if (number < 10) {
      return '0' + number;
    }
    return number;
  }

  var date = new Date(regdate);

  return date.getFullYear() + "-" + (pad(date.getMonth() + 1)) + "-" + pad(date.getDate());
}

// 모달 내부 내용 생성 및 열기
// 1. 받은 쪽지 조회
$(".received").click(function (e) {

  $(".accordion").html("");

  var msg_receiver_id = "manager127";

  $.ajax({
    url: "/getReceivedMsg",
    type: "post",
    data: JSON.stringify({msg_receiver_id: msg_receiver_id}),
    contentType: "application/json",
    success: function (arr) {
      console.log(arr);
      var str = '';

      for(var i=0; i<arr.length; i++) {

        str += '<div class="accordion-item">';
          str += '<h2 class="accordion-header" id="panelsStayOpen-heading' + i + '">';
            str += '<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapse' + i + '" aria-expanded="false" aria-controls="panelsStayOpen-collapse' + i + '">' + arr[i].msg_title + '</button>';
          str += '</h2>';
          str += '<div id="panelsStayOpen-collapse' + i + '" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-heading' + i + '">';
            str += '<div class="accordion-body">';
              str += '<div class="mb-3">';
                str += '<label for="formGroupExampleInput" class="form-label">작성자</label>';
                str += '<input type="text" class="form-control" id="formGroupExampleInput1-' + i + '" value="' + arr[i].msg_writer_name + '(' + arr[i].msg_writer_id + ')' + '" readonly>';
              str += '</div>';
              str += '<div class="mb-3">';
                str += '<label for="formGroupExampleInput2" class="form-label">발송 일자</label>';
                str += '<input type="text" class="form-control" id="formGroupExampleInput2-' + i + '" value="' + arr[i].msg_senddate + '" readonly>';
              str += '</div>';
              str += '<div class="mb-3">';
                str += '<label for="exampleFormControlTextarea'+ i + '" class="form-label">내용</label>';
                str += '<textarea name="msg_content" class="form-control" id="exampleFormControlTextarea'+ i + '" rows="3" readonly>' + arr[i].msg_content + '</textarea>';
              str += '</div>';
            str += '</div>';
          str += '</div>';
        str += '</div>';
      }
      $(".accordion").append(str);
    },
    error: function (err) {
      alert("받은 쪽지 조회에 실패했습니다.");
    }
  });

});

// 2. 보낸 쪽지 조회
$(".sent").click(function (e) {

  $(".accordion").html("");

  var msg_writer_id = "Administrator";

  $.ajax({
    url: "/getSentMsg",
    type: "post",
    data: JSON.stringify({msg_writer_id: msg_writer_id}),
    contentType: "application/json",
    success: function (arr) {
      console.log(arr);
      var str = '';

      for(var i=0; i<arr.length; i++) {

        str += '<div class="accordion-item">';
          str += '<h2 class="accordion-header" id="panelsStayOpen-heading' + i + '">';
            str += '<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapse' + i + '" aria-expanded="false" aria-controls="panelsStayOpen-collapse' + i + '">' + arr[i].msg_title + '</button>';
          str += '</h2>';
          str += '<div id="panelsStayOpen-collapse' + i + '" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-heading' + i + '">';
            str += '<div class="accordion-body">';
              str += '<div class="mb-3">';
                str += '<label for="formGroupExampleInput" class="form-label">수신자</label>';
                str += '<input type="text" class="form-control" id="formGroupExampleInput1-' + i + '" value="' + arr[i].msg_receiver_name + '(' + arr[i].msg_receiver_id + ')' + '" readonly>';
              str += '</div>';
              str += '<div class="mb-3">';
                str += '<label for="formGroupExampleInput2" class="form-label">발송 일자</label>';
                str += '<input type="text" class="form-control" id="formGroupExampleInput2-' + i + '" value="' + arr[i].msg_senddate + '" readonly>';
              str += '</div>';
              str += '<div class="mb-3">';
                str += '<label for="exampleFormControlTextarea'+ i + '" class="form-label">내용</label>';
                str += '<textarea name="msg_content" class="form-control" id="exampleFormControlTextarea'+ i + '" rows="3" readonly>' + arr[i].msg_content + '</textarea>';
              str += '</div>';
            str += '</div>';
          str += '</div>';
        str += '</div>';
      }
      $(".accordion").append(str);
    },
    error: function (err) {
      alert("받은 쪽지 조회에 실패했습니다.");
    }
  });

});




//모달 창 내 발송 버튼 클릭 시, 쪽지 발송
$("#sendMsgBtn").on("click", function () {
  $("#sendMsgForm").submit();
});